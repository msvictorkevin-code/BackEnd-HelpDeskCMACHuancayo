package pe.cmac.huancayo.sistema.helpdesk.mapper;

import lombok.Data;

import java.util.Date;

@Data
public class ItemTicket {

    private Integer idTicket;
    private String descripcion;
    private String estado;
    private Date fechaGenerada;
    private String tipo;
    private String categoria;
    private String username;


}
