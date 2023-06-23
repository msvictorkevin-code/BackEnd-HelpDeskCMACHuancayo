package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.Colaborador;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.repository.ColaboradorRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Constantes;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Repository
public class UsuarioDAORepositoryImpl implements UsuarioDAORepository {

    private final UsuarioRepository usuarioRepository;

    private final ColaboradorRepository colaboradorRepository;



    public RestResponse<UsuarioDTOResponse> autenticar(UsuarioDTORequest request) {
        try {
            Optional<Usuario> optUsuario = usuarioRepository.findUserByUsername(request.getUsername());
            RestResponse<UsuarioDTOResponse> response = new RestResponse<>();
            UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
            if (!optUsuario.isEmpty()) {
                Usuario usuario = optUsuario.get();
                if (usuario.getNroIntentos() >= Constantes.MAX_INTENTOS) {
                    usuarioDTOResponse.setAcceso(false);
                    response.setData(usuarioDTOResponse);
                    response.setStatus(true);
                    response.setMessage(Messages.MSG_LOGIN_INTENTOS);
                    return response;
                }


                String pass = request.getPassword();
                // String encryptPassordMD5 = EncryptUtil.digest(request.getPassword(), EncryptUtil.ALGORITHM.MD5);
                //String encryptPassordMD5 = AES256.encrypt(request.getPassword());

                log.info("pass=" + pass);
               // log.info("encryptPassordMD5=" + encryptPassordMD5);
                // acceder
                Boolean boolUsername = request.getUsername().equalsIgnoreCase(usuario.getUsername());
                Boolean boolPassword = usuario.getPassword().equalsIgnoreCase(request.getPassword());

                if (boolUsername && boolPassword) {

                    usuarioDTOResponse.setIdColaborador(usuario.getIdColaborador());
                    usuarioDTOResponse.setUsername(usuario.getUsername());
                    usuarioDTOResponse.setIsAdmin(usuario.getIsAdmin());

                    Colaborador colaborador = colaboradorRepository.findColaboradorById(usuario.getIdColaborador());
                    usuarioDTOResponse.setNombres(colaborador.getNombres());
                    usuarioDTOResponse.setApellidos(colaborador.getApellidos());
                    usuarioDTOResponse.setCodEmpleado(colaborador.getCodEmpleado());
                    usuarioDTOResponse.setArea(colaborador.getArea());

                    usuarioDTOResponse.setAcceso(true);
                    response.setData(usuarioDTOResponse);
                    response.setStatus(true);
                    response.setMessage(String.format(Messages.MSG_LOGIN_CON_ACCESO, usuario.getUsername()));
                    return response;

                    // rechazar y bloquear
                } else {

                    usuario.setNroIntentos(usuario.getNroIntentos() + 1);
                    usuarioRepository.save(usuario);

                    usuarioDTOResponse.setAcceso(false);
                    response.setData(usuarioDTOResponse);
                    response.setStatus(true);
                    response.setMessage(String.format(Messages.MSG_LOGIN_SIN_ACCESO, usuario.getUsername()));
                    return response;
                }
            } else {
                response.setStatus(true);
                usuarioDTOResponse.setAcceso(false);
                response.setData(usuarioDTOResponse);
                response.setMessage(String.format(Messages.MSG_LOGIN_NO_EXISTE, request.getUsername()));
                return response;
            }
        } catch (Exception ex) {
            log.error("Error" + ex.getMessage());
        }
        return null;
    }
}
