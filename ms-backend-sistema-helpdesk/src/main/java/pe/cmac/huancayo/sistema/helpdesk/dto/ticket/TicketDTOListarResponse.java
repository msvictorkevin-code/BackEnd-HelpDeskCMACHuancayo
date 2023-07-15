package pe.cmac.huancayo.sistema.helpdesk.dto.ticket;

import lombok.Data;

import java.util.Date;
@Data
public class TicketDTOListarResponse {
    private Integer idTicket;
    private String descripcion;
    private String estado;

    private Date fechaGenerada;

    private Date fechaCierre;

    private Integer prioridad;

    private String tipo;

    private String categoria;

    private String usuario;
}
