package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.TicketDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.TicketServicie;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketServicie {

    private TicketDAORepository ticketDAORepository;

    public TicketServiceImpl(TicketDAORepository ticketDAORepository) {
        this.ticketDAORepository = ticketDAORepository;
    }

    @Override
    public RestResponse<TicketDTORegistrarResponse> registrar(TicketDTORegistrarRequest request) {
        return ticketDAORepository.registrar(request);
    }

    @Override
    public RestResponse<TicketDTOActualizarResponse> actualizar(TicketDTOActualizarRequest request) {
        return ticketDAORepository.actualizar(request);
    }

    @Override
    public RestResponse<List<TicketDTOListarResponse>> listar() {
        return ticketDAORepository.listar();
    }

    @Override
    public RestResponse<TicketDTOItemResponse> obtener(Integer idTicket) {
        return ticketDAORepository.obtener(idTicket);
    }
}
