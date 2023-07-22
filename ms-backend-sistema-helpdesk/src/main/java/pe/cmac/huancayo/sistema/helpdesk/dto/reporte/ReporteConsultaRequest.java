package pe.cmac.huancayo.sistema.helpdesk.dto.reporte;

import lombok.Data;

@Data
public class ReporteConsultaRequest {

    private String idTicket;

    private String estado;

    private String fechaInicio;

    private String fechaFin;

    private String idtipo;

    private String idCategoria;

    private String idUsuario;

}
