package pe.cmac.huancayo.sistema.helpdesk.service;

import jakarta.servlet.http.HttpServletResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;

import java.io.IOException;
import java.util.List;

public interface ReporteService {
    RestResponse<List<EstadoTicketDTOResponse>> listarEstados();

    RestResponse<List<UsuarioDTOResponse>> listarUsuarios();

    void exportarPdf(ReporteConsultaRequest request,HttpServletResponse response) throws IOException;

    void exportarXlsx(ReporteConsultaRequest request, HttpServletResponse response) throws IOException;

    RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request);
}
