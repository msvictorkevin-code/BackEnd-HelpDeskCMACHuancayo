package pe.cmac.huancayo.sistema.helpdesk.service;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;

import java.util.List;

public interface ReporteService {
    RestResponse<List<EstadoTicketDTOResponse>> listarEstados();

    RestResponse<List<UsuarioDTOResponse>> listarUsuarios();

    void exportarPdf(ReporteConsultaRequest request);

    void exportarXlsx(ReporteConsultaRequest request);

    RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request);
}
