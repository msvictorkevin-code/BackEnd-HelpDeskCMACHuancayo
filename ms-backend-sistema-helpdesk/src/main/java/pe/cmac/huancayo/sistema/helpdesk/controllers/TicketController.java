package pe.cmac.huancayo.sistema.helpdesk.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.ticket.*;
import pe.cmac.huancayo.sistema.helpdesk.service.TicketServicie;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PathNames.URL_API_BASE)
public class TicketController {
    private final TicketServicie ticketServicie;

    public TicketController(TicketServicie ticketServicie) {
        this.ticketServicie = ticketServicie;
    }

    @PostMapping(PathNames.URL_API_TICKET_REGISTRAR)
    public ResponseEntity<RestResponse<TicketDTORegistrarResponse>> registrarTicket(@Valid @RequestBody TicketDTORegistrarRequest request) {
        RestResponse<TicketDTORegistrarResponse> response = new RestResponse<>();
        try {
            response = ticketServicie.registrar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping(PathNames.URL_API_TICKET_ACTUALIZAR)
    public ResponseEntity<RestResponse<TicketDTOActualizarResponse>> actualizarTicket(@Valid @RequestBody TicketDTOActualizarRequest request) {
        RestResponse<TicketDTOActualizarResponse> response = new RestResponse<>();
        try {
            response = ticketServicie.actualizar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(PathNames.URL_API_TICKET_LISTAR)
    public ResponseEntity<RestResponse<List<TicketDTOListarResponse>>> listarTickets() {
        RestResponse<List<TicketDTOListarResponse>> response = new RestResponse<>();
        try {
            response = ticketServicie.listar();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(PathNames.URL_API_TICKET_OBTENER)
    public ResponseEntity<RestResponse<TicketDTOItemResponse>> obtenerTicket(@PathVariable Integer idTicket) {
        RestResponse<TicketDTOItemResponse> response = new RestResponse<>();
        try {
            response = ticketServicie.obtener(idTicket);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
