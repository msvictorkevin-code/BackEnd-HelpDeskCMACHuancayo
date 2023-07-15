package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;
import pe.cmac.huancayo.sistema.helpdesk.entity.Ticket;
import pe.cmac.huancayo.sistema.helpdesk.repository.TicketRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TicketDAORepositoryImplTest {
    @Mock
    private TicketRepository ticketRepository;
    @MockBean
    private TicketDAORepository ticketDAORepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        ticketDAORepository = new TicketDAORepositoryImpl(ticketRepository);
    }

    @Test
    void registrar() {
        Ticket ticket = buildTicket();
        TicketDTORegistrarRequest request = buildTicketDTORegistrarRequest();
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);
        RestResponse<TicketDTORegistrarResponse> response = ticketDAORepository.registrar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void actualizar() {
        Ticket ticket = buildTicket();
        TicketDTOActualizarRequest request = buildTicketDTOActualizarRequest();
        Mockito.when(ticketRepository.save(Mockito.any())).thenReturn(ticket);
        RestResponse<TicketDTOActualizarResponse> response = ticketDAORepository.actualizar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void listar() {
        List<Ticket> ticket = buildListTicket();
        Mockito.when(ticketRepository.findAll()).thenReturn(ticket);
        RestResponse<List<TicketDTOListarResponse>> response = ticketDAORepository.listar();
        Assertions.assertNotNull(response);
    }


    @Test
    void obtener() {
        Ticket ticket = buildTicket();
        Optional<Ticket> ticketOptional = Optional.of(ticket);
        Mockito.when(ticketRepository.findById(Mockito.anyInt())).thenReturn(ticketOptional);
        RestResponse<TicketDTOItemResponse> response = ticketDAORepository.obtener(1);
        Assertions.assertNotNull(response);
    }

    private Ticket buildTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setDescripcion("test");
        ticket.setEstado("Iniciado");
        ticket.setFechaGenerada(new Date());
        ticket.setFechaCierre(new Date());
        ticket.setPrioridad(1);
        ticket.setIdTipo(1);
        ticket.setIdCategoria(1);
        ticket.setIdUsuario(1);
        return ticket;
    }

    private List<Ticket> buildListTicket() {
        List<Ticket> ticketList = new ArrayList<>();
        ticketList.add(buildTicket());
        ticketList.add(buildTicket());
        ticketList.add(buildTicket());
        return ticketList;
    }

    private TicketDTORegistrarRequest buildTicketDTORegistrarRequest() {
        TicketDTORegistrarRequest request = new TicketDTORegistrarRequest();
        request.setDescripcion("test");
        request.setEstado("Iniciado");
        request.setFechaCierre(new Date());
        request.setFechaGenerada(new Date());
        request.setPrioridad(1);
        request.setIdTipo(1);
        request.setIdCategoria(1);
        request.setIdUsuario(1);
        return request;
    }

    private TicketDTOActualizarRequest buildTicketDTOActualizarRequest() {
        TicketDTOActualizarRequest request = new TicketDTOActualizarRequest();
        request.setIdTicket(1);
        request.setDescripcion("test");
        request.setEstado("Iniciado");
        request.setFechaCierre(new Date());
        request.setFechaGenerada(new Date());
        request.setPrioridad(1);
        request.setIdTipo(1);
        request.setIdCategoria(1);
        request.setIdUsuario(1);
        return request;
    }


}