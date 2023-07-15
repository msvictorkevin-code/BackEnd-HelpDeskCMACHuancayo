package pe.cmac.huancayo.sistema.helpdesk.dto.reporte;

import lombok.Data;

@Data
public class ReporteConsultaRequest {

    private Integer idTicket;

    private String estado;

    private String fechaInicio;

    private String fechaFin;

    private Integer idtipo;

    private Integer idCategoria;

    private Integer idUsuario;

}
