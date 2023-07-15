package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.ReporteDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.ReporteService;

import java.io.IOException;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private final ReporteDAORepository repository;

    public ReporteServiceImpl(ReporteDAORepository repository) {
        this.repository = repository;
    }

    @Override
    public RestResponse<List<EstadoTicketDTOResponse>> listarEstados() {
        return repository.listarEstados();
    }

    @Override
    public RestResponse<List<UsuarioDTOResponse>> listarUsuarios() {
        return repository.listarUsuarios();
    }

    @Override
    public void exportarPdf(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
        repository.exportarPdf(request,response);
    }

    @Override
    public void exportarXlsx(ReporteConsultaRequest request, HttpServletResponse response) throws IOException {
         repository.exportarXlsx(request,response);
    }

    @Override
    public RestResponse<List<ReporteConsultaResponse>> listado(ReporteConsultaRequest request) {
        return repository.listado(request);
    }
}
