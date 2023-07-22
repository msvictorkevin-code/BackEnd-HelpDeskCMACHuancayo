package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import jakarta.servlet.http.HttpServletResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.*;

import java.io.IOException;
import java.util.List;

public interface ReporteDAORepository {
    RestResponse<List<EstadoTicketDTOResponse>> listarEstados();

    RestResponse<List<UsuarioDTOResponse>> listarUsuarios();

    void exportarPdf(ReporteConsultaRequest request, HttpServletResponse response) throws IOException;



    void exportarXlsx(ReporteConsultaRequest request,HttpServletResponse response) throws IOException;

    RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request);
}
