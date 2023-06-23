package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;
import pe.cmac.huancayo.sistema.helpdesk.entity.Colaborador;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.repository.ColaboradorRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Repository
public class ColaboradorDAORepositoryImpl implements ColaboradorDAORepository {


    private final UsuarioRepository usuarioRepository;

    private final ColaboradorRepository colaboradorRepository;



    @Override
    public RestResponse<ColaboradorDTORegistrarResponse> registrar(ColaboradorDTORegistrarRequest request) {
        RestResponse<ColaboradorDTORegistrarResponse> response = new RestResponse<>();
        ColaboradorDTORegistrarResponse dtoResponse = new ColaboradorDTORegistrarResponse();
        Colaborador colaboradorResp = null;
        Usuario usuarioResp = null;
        try {


            Colaborador colaborador = new Colaborador();
            colaborador.setNombres(request.getNombre());
            colaborador.setApellidos(request.getApellido());
            colaborador.setArea(request.getArea());
            colaborador.setCodEmpleado(request.getCodigo());
            colaborador.setIsActivo(true);
            colaboradorResp = colaboradorRepository.save(colaborador);

            if (Objects.nonNull(colaboradorResp)) {
                Usuario usuario = new Usuario();
                usuario.setUsername(request.getUsuario());
                usuario.setPassword(request.getClave());
                usuario.setIdColaborador(colaborador.getId());
                usuario.setIsAdmin(request.getAdministador());
                usuario.setNroIntentos(0);
                usuarioResp = usuarioRepository.save(usuario);
            }

            dtoResponse.setAdministador(usuarioResp.getIsAdmin());
            dtoResponse.setUsuario(usuarioResp.getUsername());
            dtoResponse.setNombre(colaborador.getNombres());
            dtoResponse.setApellido(colaborador.getApellidos());
            dtoResponse.setArea(colaborador.getArea());
            dtoResponse.setCodigo(colaborador.getCodEmpleado());
            response.setData(dtoResponse);
            response.setStatus(true);
            response.setMessage(Messages.MSG_REGISTRO_OK);
            return response;
        } catch (Exception ex) {
            log.error("Excepcion generada:", ex.getMessage());
            response.setData(null);
            response.setStatus(false);
            response.setMessage(Messages.MSG_REGISTRO_ERROR);
        }
        return response;
    }

    @Override
    public RestResponse<List<ColaboradorDTOListarResponse>> listar() {
        RestResponse<List<ColaboradorDTOListarResponse>> response = new RestResponse<>();
        List<ColaboradorDTOListarResponse> list = mapTuples(colaboradorRepository.listar());
        response.setData(list);
        response.setMessage(Messages.MSG_LISTADO_OK);
        if (response.getData().isEmpty()) {
            response.setMessage(Messages.MSG_LISTADO_VACIO);
        }
        response.setStatus(true);
        return response;
    }

    @Override
    public RestResponse<ColaboradorDTOItemResponse> obtener(Integer idColaborador, Integer idUsuario) {
        RestResponse<ColaboradorDTOItemResponse> response = new RestResponse<>();
        Colaborador colaborador = colaboradorRepository.findColaboradorById(idColaborador);
        Usuario usuario = usuarioRepository.findUsuarioById(idUsuario);
        ColaboradorDTOItemResponse itemResponse = mapTuples(colaborador, usuario);

        response.setData(itemResponse);
        response.setMessage(Messages.MSG_ITEM_OK);
        if (response.getData() == null) {
            response.setMessage(Messages.MSG_ITEM_VACIO);
        }
        response.setStatus(true);
        return response;

    }

    @Override
    public RestResponse<ColaboradorDTOActualizarResponse> actualizar(ColaboradorDTOActualizarRequest request) {
        RestResponse<ColaboradorDTOActualizarResponse> response = new RestResponse<>();

        ColaboradorDTOActualizarResponse dtoActualizarResponse = new ColaboradorDTOActualizarResponse();
        Colaborador colaborador = new Colaborador();
        colaborador.setId(request.getIdColaborador());
        colaborador.setNombres(request.getNombre());
        colaborador.setApellidos(request.getApellido());
        colaborador.setCodEmpleado(request.getCodigo());
        colaborador.setArea(request.getArea());
        colaborador.setIsActivo(request.getActivo());

        Usuario usuario = new Usuario();
        usuario.setId(request.getIdUsuario());
        usuario.setIdColaborador(request.getIdColaborador());
        usuario.setUsername(request.getUsuario());
        usuario.setPassword(request.getClave());
        usuario.setNroIntentos(request.getNroIntentos());
        usuario.setIsAdmin(request.getAdministador());


        Colaborador colaboradorUpd = colaboradorRepository.save(colaborador);
        Usuario usuarioUpd = usuarioRepository.save(usuario);
        dtoActualizarResponse.setIdColaborador(colaboradorUpd.getId());
        dtoActualizarResponse.setCodigo(colaboradorUpd.getCodEmpleado());
        dtoActualizarResponse.setArea(colaboradorUpd.getArea());
        dtoActualizarResponse.setNombre(colaboradorUpd.getNombres());
        dtoActualizarResponse.setApellido(colaboradorUpd.getApellidos());
        dtoActualizarResponse.setIdUsuario(usuarioUpd.getId());
        dtoActualizarResponse.setUsuario(usuarioUpd.getUsername());
        dtoActualizarResponse.setAdministador(usuarioUpd.getIsAdmin());
        dtoActualizarResponse.setActivo(colaboradorUpd.getIsActivo());
        response.setData(dtoActualizarResponse);
        response.setMessage(Messages.MSG_ACTUALIZO_OK);
        response.setStatus(true);

        return response;
    }

    @Override
    public RestResponse<ColaboradorDTODeshabilitarResponse> deshabilitar(Integer idColaborador) {
        RestResponse<ColaboradorDTODeshabilitarResponse> response = new RestResponse<>();
        ColaboradorDTODeshabilitarResponse dtoDeshabilitarResponse = new ColaboradorDTODeshabilitarResponse();
        Colaborador colaborador = colaboradorRepository.findColaboradorById(idColaborador);
        colaborador.setIsActivo(false);
        Colaborador colaboradorDes = colaboradorRepository.save(colaborador);
        dtoDeshabilitarResponse.setIdColaborador(colaboradorDes.getId());
        dtoDeshabilitarResponse.setActivo(colaboradorDes.getIsActivo());
        response.setData(dtoDeshabilitarResponse);
        response.setStatus(true);
        response.setMessage(Messages.MSG_DESHABILITADO_OK);
        return response;
    }



    private ColaboradorDTOItemResponse mapTuples(Colaborador colaborador, Usuario usuario) {
        return new ColaboradorDTOItemResponse(
                colaborador.getCodEmpleado(),
                colaborador.getId(),
                colaborador.getArea(),
                colaborador.getNombres(),
                colaborador.getApellidos(),
                usuario.getId(),
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getNroIntentos(),
                usuario.getIsAdmin(),
                colaborador.getIsActivo()
        );
    }


    private List<ColaboradorDTOListarResponse> mapTuples(List<Tuple> ColaboradorDTOListarResponse) {
        return ColaboradorDTOListarResponse.stream()
                .map(tuple -> new ColaboradorDTOListarResponse(
                        tuple.get("cod_empleado", String.class),
                        tuple.get("id_Colaborador", Integer.class),
                        tuple.get("area", String.class),
                        tuple.get("nombres", String.class),
                        tuple.get("apellidos", String.class),
                        tuple.get("id_usuario", Integer.class),
                        tuple.get("username", String.class),
                        tuple.get("is_admin", Boolean.class),
                        tuple.get("is_activo", Boolean.class)
                )).toList();
    }

/*
    private ColaboradorDTOItemResponse mapTuples(Tuple tuple) {
        return new ColaboradorDTOItemResponse(
                tuple.get("cod_empleado", String.class),
                tuple.get("id_Colaborador", Integer.class),
                tuple.get("area", String.class),
                tuple.get("nombres", String.class),
                tuple.get("apellidos", String.class),
                tuple.get("id_usuario", Integer.class),
                tuple.get("username", String.class),
                tuple.get("password", String.class),
                tuple.get("is_admin", Integer.class),
                tuple.get("nro_intentos", Boolean.class),
                tuple.get("is_activo", Boolean.class)
        );
    }*/
}


