package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;
import pe.cmac.huancayo.sistema.helpdesk.entity.Ticket;
import pe.cmac.huancayo.sistema.helpdesk.repository.TicketRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class TicketDAORepositoryImpl implements TicketDAORepository {

    private final TicketRepository ticketRepository;


    @Override
    public RestResponse<TicketDTORegistrarResponse> registrar(TicketDTORegistrarRequest request) {
        RestResponse<TicketDTORegistrarResponse> response = new RestResponse<>();
        TicketDTORegistrarResponse ticketDTORegistrarResponse = new TicketDTORegistrarResponse();
        Ticket ticket = new Ticket();
        ticket.setDescripcion(request.getDescripcion());
        ticket.setEstado(request.getEstado());
        ticket.setFechaCierre(request.getFechaCierre());
        ticket.setFechaGenerada(request.getFechaGenerada());
        ticket.setEstado(request.getEstado());
        ticket.setPrioridad(request.getPrioridad());
        ticket.setIdTipo(request.getIdTipo());
        ticket.setIdCategoria(request.getIdCategoria());
        ticket.setIdUsuario(request.getIdUsuario());
        Ticket ticketSave = ticketRepository.save(ticket);
        ticketDTORegistrarResponse.setIdTicket(ticketSave.getId());
        response.setData(ticketDTORegistrarResponse);
        response.setStatus(true);
        response.setMessage(Messages.MSG_REGISTRO_OK);

        return response;
    }

    @Override
    public RestResponse<TicketDTOActualizarResponse> actualizar(TicketDTOActualizarRequest request) {
        RestResponse<TicketDTOActualizarResponse> response = new RestResponse<>();
        TicketDTOActualizarResponse ticketDTOActualizarResponse = new TicketDTOActualizarResponse();
        Ticket ticket = new Ticket();
        ticket.setId(request.getIdTicket());
        ticket.setDescripcion(request.getDescripcion());
        ticket.setEstado(request.getEstado());
        ticket.setFechaCierre(request.getFechaCierre());
        ticket.setFechaGenerada(request.getFechaGenerada());
        ticket.setEstado(request.getEstado());
        ticket.setPrioridad(request.getPrioridad());
        ticket.setIdTipo(request.getIdTipo());
        ticket.setIdCategoria(request.getIdCategoria());
        ticket.setIdUsuario(request.getIdUsuario());
        Ticket ticketSave = ticketRepository.save(ticket);
        ticketDTOActualizarResponse.setIdTicket(ticketSave.getId());
        response.setData(ticketDTOActualizarResponse);
        response.setStatus(true);
        response.setMessage(Messages.MSG_ACTUALIZO_OK);
        return response;
    }

    @Override
    public RestResponse<List<TicketDTOListarResponse>> listar() {
        RestResponse<List<TicketDTOListarResponse>> response = new RestResponse<>();
        try {
            List<Tuple> ticketList = ticketRepository.listar();
            List<TicketDTOListarResponse> ticketDTOList = ticketList.stream().toList().stream().map(t -> {
                TicketDTOListarResponse ticketDTOListarResponse = new TicketDTOListarResponse();
                ticketDTOListarResponse.setIdTicket(t.get("id_ticket", Integer.class));
                ticketDTOListarResponse.setDescripcion(t.get("descripcion", String.class));
                ticketDTOListarResponse.setEstado(t.get("estado", String.class));
                ticketDTOListarResponse.setFechaCierre(t.get("fecha_cierre", Date.class));
                ticketDTOListarResponse.setFechaGenerada(t.get("fecha_generada", Date.class));
                ticketDTOListarResponse.setPrioridad(t.get("prioridad", Integer.class));
                ticketDTOListarResponse.setTipo(t.get("tipo", String.class));
                ticketDTOListarResponse.setCategoria(t.get("categoria", String.class));
                ticketDTOListarResponse.setUsuario(t.get("username", String.class));
                return ticketDTOListarResponse;
            }).toList();
            response.setData(ticketDTOList);
            response.setStatus(true);
            response.setMessage(Messages.MSG_LISTADO_OK);
        } catch (Exception ex) {
            log.info("Mensaje=" + ex.getMessage());
            response.setData(null);
            response.setStatus(false);
            response.setMessage(Messages.MSG_LISTADO_VACIO);
        }

        return response;
    }

    @Override
    public RestResponse<TicketDTOItemResponse> obtener(Integer idTicket) {
        RestResponse<TicketDTOItemResponse> response = new RestResponse<>();
        try {
            Ticket ticket = ticketRepository.findById(idTicket).get();
            TicketDTOItemResponse ticketDTOItemResponse = new TicketDTOItemResponse();
            ticketDTOItemResponse.setIdTicket(ticket.getId());
            ticketDTOItemResponse.setDescripcion(ticket.getDescripcion());
            ticketDTOItemResponse.setEstado(ticket.getEstado());
            ticketDTOItemResponse.setFechaCierre(ticket.getFechaCierre());
            ticketDTOItemResponse.setFechaGenerada(ticket.getFechaGenerada());
            ticketDTOItemResponse.setEstado(ticket.getEstado());
            ticketDTOItemResponse.setPrioridad(ticket.getPrioridad());
            ticketDTOItemResponse.setIdTipo(ticket.getIdTipo());
            ticketDTOItemResponse.setIdCategoria(ticket.getIdCategoria());
            ticketDTOItemResponse.setIdUsuario(ticket.getIdUsuario());
            response.setData(ticketDTOItemResponse);
            response.setStatus(true);
            response.setMessage(Messages.MSG_ITEM_OK);
        } catch (Exception ex) {
            log.info("Mensaje=" + ex.getMessage());
            response.setData(null);
            response.setStatus(false);
            response.setMessage(Messages.MSG_ITEM_VACIO);
        }

        return response;
    }
}
