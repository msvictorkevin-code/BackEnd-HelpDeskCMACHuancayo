package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.TicketDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.TicketService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TicketServiceImplTest {

    @Mock
    private TicketDAORepository ticketDAORepository;

    @MockBean
    private TicketService ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        ticketService = new TicketServiceImpl(ticketDAORepository);
    }

    @Test
    void registrar() {
        TicketDTORegistrarRequest request = buildTicketDTORegistrarRequest();
        RestResponse<TicketDTORegistrarResponse> responseRestResponse = buildTicketDTORegistrarResponse();
        Mockito.when(ticketDAORepository.registrar(Mockito.any())).thenReturn(responseRestResponse);
        RestResponse<TicketDTORegistrarResponse> response = ticketService.registrar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void actualizar() {
        TicketDTOActualizarRequest request = buildTicketDTOActualizarRequest();
        RestResponse<TicketDTOActualizarResponse> responseRestResponse = buildTicketDTOActualizarResponse();
        Mockito.when(ticketDAORepository.actualizar(Mockito.any())).thenReturn(responseRestResponse);
        RestResponse<TicketDTOActualizarResponse> response = ticketService.actualizar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void listar() {
        RestResponse<List<TicketDTOListarResponse>> responseRestResponse = buildTicketDTOListarResponse();
        Mockito.when(ticketDAORepository.listar()).thenReturn(responseRestResponse);
        RestResponse<List<TicketDTOListarResponse>> response = ticketService.listar();
        Assertions.assertNotNull(response);
    }


    @Test
    void obtener() {
        RestResponse<TicketDTOItemResponse> responseRestResponse = buildTicketDTOItemResponse();
        Mockito.when(ticketDAORepository.obtener(Mockito.anyInt())).thenReturn(responseRestResponse);
        RestResponse<TicketDTOItemResponse> response = ticketService.obtener(1);
        Assertions.assertNotNull(response);
    }




    private RestResponse<TicketDTORegistrarResponse> buildTicketDTORegistrarResponse() {
        RestResponse<TicketDTORegistrarResponse> responseRestResponse = new RestResponse<>();
        TicketDTORegistrarResponse ticketDTORegistrarResponse = new TicketDTORegistrarResponse();
        responseRestResponse.setData(ticketDTORegistrarResponse);
        return responseRestResponse;
    }

    private TicketDTORegistrarRequest buildTicketDTORegistrarRequest() {
        TicketDTORegistrarRequest request = new TicketDTORegistrarRequest();
        return request;
    }

    private RestResponse<TicketDTOActualizarResponse> buildTicketDTOActualizarResponse() {
        RestResponse<TicketDTOActualizarResponse> responseRestResponse = new RestResponse<>();
        TicketDTOActualizarResponse ticketDTORegistrarResponse = new TicketDTOActualizarResponse();
        responseRestResponse.setData(ticketDTORegistrarResponse);
        return responseRestResponse;
    }

    private TicketDTOActualizarRequest buildTicketDTOActualizarRequest() {
        TicketDTOActualizarRequest request = new TicketDTOActualizarRequest();
        return request;
    }

    private RestResponse<List<TicketDTOListarResponse>> buildTicketDTOListarResponse() {
        RestResponse<List<TicketDTOListarResponse>> response = new RestResponse<>();
        TicketDTOListarResponse ticketDTOListarResponse = new TicketDTOListarResponse();
        List<TicketDTOListarResponse> ticketDTOListarResponses = new ArrayList<>();
        ticketDTOListarResponses.add(ticketDTOListarResponse);
        response.setData(ticketDTOListarResponses);
        return response;
    }

    private RestResponse<TicketDTOItemResponse> buildTicketDTOItemResponse() {
        RestResponse<TicketDTOItemResponse> responseRestResponse = new RestResponse<>();
        TicketDTOItemResponse ticketDTOItemResponse = new TicketDTOItemResponse();
        responseRestResponse.setData(ticketDTOItemResponse);
        return responseRestResponse;
    }

}