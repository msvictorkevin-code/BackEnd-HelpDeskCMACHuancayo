package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.mapper.ItemTicket;
import pe.cmac.huancayo.sistema.helpdesk.mapper.TicketMapper;
import pe.cmac.huancayo.sistema.helpdesk.repository.TicketRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@AllArgsConstructor
@Repository
public class ReporteDAORepositoryImpl implements ReporteDAORepository {

    private final UsuarioRepository usuarioRepository;

    private final TicketRepository ticketRepository;

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    @Override
    public RestResponse<List<EstadoTicketDTOResponse>> listarEstados() {
        RestResponse<List<EstadoTicketDTOResponse>> response = new RestResponse<>();
        List<EstadoTicketDTOResponse> list = new ArrayList<>();
        list.add(new EstadoTicketDTOResponse("Inicializado"));
        list.add(new EstadoTicketDTOResponse("En proceso"));
        list.add(new EstadoTicketDTOResponse("Asignado"));
        list.add(new EstadoTicketDTOResponse("Manual"));
        list.add(new EstadoTicketDTOResponse("Finalizado"));
        response.setData(list);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    @Override
    public RestResponse<List<UsuarioDTOResponse>> listarUsuarios() {
        RestResponse<List<UsuarioDTOResponse>> response = new RestResponse<>();
        List<Usuario> list = usuarioRepository.findAll();
        List<UsuarioDTOResponse> usuarioDTOResponseList = list.stream().map(u -> {
                    UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
                    usuarioDTOResponse.setIdUsuario(u.getId());
                    usuarioDTOResponse.setUsername(u.getUsername());
                    return usuarioDTOResponse;
                }
        ).toList();
        response.setData(usuarioDTOResponseList);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    @Override
    public RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request) {
        RestResponse<List<ReporteConsultaResponse>> response = new RestResponse<>();
        List<ItemTicket> list = consultar(request);
        List<ReporteConsultaResponse> consultaResponse = list.stream().map(x -> {
            ReporteConsultaResponse response1 = new ReporteConsultaResponse();
            response1.setIdTicket(x.getIdTicket());
            response1.setDescripcion(x.getDescripcion());
            response1.setEstado(x.getEstado());
            response1.setFechaGenerada(x.getFechaGenerada());
            response1.setTipo(x.getTipo());
            response1.setCategoria(x.getCategoria());
            response1.setUsername(x.getUsername());
            return response1;
        }).toList();

        response.setData(consultaResponse);
        response.setMessage(Messages.MSG_LISTADO_OK);
        response.setStatus(true);
        return response;
    }

    private List<ItemTicket> consultar(ReporteConsultaRequest request) {
        StringBuilder builder = new StringBuilder();
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        builder.append("select id_ticket , t.descripcion ,estado, fecha_generada,prioridad, ");
        builder.append("tr.nombre as tipo , cr.nombre  as categoria,u.username ");
        builder.append("from Ticket t ");
        builder.append("inner join tipo_requisicion tr on t.id_tipo = tr.id_tipo ");
        builder.append("inner join categoria_requisicion cr  on t.id_categoria = cr.id_categoria ");
        builder.append("inner join usuario u on t.id_usuario = u.id_usuario ");
        builder.append("where ");
        String andOp = "";


        if (Objects.nonNull(request.getIdTicket())) {

            builder.append(andOp);
            builder.append(" ");
            builder.append("id_ticket");
            builder.append("=:id_ticket");
            andOp = " AND ";
            parameters.addValue("id_ticket", request.getIdTicket());

        }

        if (Objects.nonNull(request.getIdCategoria())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_categoria");
            builder.append("=:id_categoria");
            andOp = " AND ";
            parameters.addValue("id_categoria", request.getIdCategoria());

        }
        if (Objects.nonNull(request.getIdtipo())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_tipo");
            builder.append("=:id_tipo");
            andOp = " AND ";
            parameters.addValue("id_tipo", request.getIdtipo());

        }

        if (Objects.nonNull(request.getIdUsuario())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("t.id_usuario");
            builder.append("=:idUsuario");
            andOp = " AND ";
            parameters.addValue("idUsuario", request.getIdUsuario());

        }

        if (Objects.nonNull(request.getEstado())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("estado");
            builder.append("=:estado");
            andOp = " AND ";
            parameters.addValue("estado", request.getEstado());

        }

        if (Objects.nonNull(request.getFechaInicio()) && Objects.nonNull(request.getFechaFin())) {
            builder.append(andOp);
            builder.append(" ");
            builder.append("fecha_generada ");
            builder.append("between :fechaIni and :fechaFin");
            parameters.addValue("fechaIni", request.getFechaInicio());
            parameters.addValue("fechaFin", request.getFechaFin());

        }

        List<ItemTicket> list = null;
        try {
            list = namedParameterJdbcTemplate.query(builder.toString(), parameters, new TicketMapper());
        } catch (Exception ex) {
            log.error("ex".concat(ex.getMessage()));
        }

        return list;

    }

    @Override
    public void exportarPdf(ReporteConsultaRequest request) {

    }

    @Override
    public void exportarXlsx(ReporteConsultaRequest request) {

    }


}
