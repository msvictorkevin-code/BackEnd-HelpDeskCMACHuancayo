package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;

import java.util.List;

public interface TicketDAORepository {
    RestResponse<TicketDTORegistrarResponse> registrar(TicketDTORegistrarRequest request);

    RestResponse<TicketDTOActualizarResponse> actualizar(TicketDTOActualizarRequest request);

    RestResponse<List<TicketDTOListarResponse>> listar();

    RestResponse<TicketDTOItemResponse> obtener(Integer idTicket);
}
