package pe.cmac.huancayo.sistema.helpdesk.dto.ticket;

import lombok.Data;

import java.util.Date;
@Data
public class TicketDTORegistrarRequest {
    private String descripcion;
    private String estado;

    private Date fechaGenerada;

    private Date fechaCierre;

    private Integer prioridad;

    private Integer idTipo;

    private Integer idCategoria;

    private Integer idUsuario;
}

