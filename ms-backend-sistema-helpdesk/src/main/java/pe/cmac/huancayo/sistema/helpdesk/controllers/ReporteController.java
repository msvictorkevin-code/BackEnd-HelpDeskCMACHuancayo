package pe.cmac.huancayo.sistema.helpdesk.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.EstadoTicketDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaRequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.ReporteConsultaResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.reporte.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.service.ReporteService;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("api")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }


    @GetMapping(PathNames.URL_API_REPORTE_LISTAR_ESTADOS)
    public ResponseEntity<RestResponse<List<EstadoTicketDTOResponse>>> listarEstados() {
        RestResponse<List<EstadoTicketDTOResponse>> response = new RestResponse<>();
        try {
            response = reporteService.listarEstados();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(PathNames.URL_API_REPORTE_LISTAR_USUARIOS)
    public ResponseEntity<RestResponse<List<UsuarioDTOResponse>>> listarUsuarios() {
        RestResponse<List<UsuarioDTOResponse>> response = new RestResponse<>();
        try {
            response = reporteService.listarUsuarios();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping(PathNames.URL_API_REPORTE_LISTADO)
    public ResponseEntity<RestResponse<List<ReporteConsultaResponse>>> listado(@RequestBody ReporteConsultaRequest request) {
        RestResponse<List<ReporteConsultaResponse>> response = new RestResponse<>();
        try {
            response = reporteService.listado(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @GetMapping(PathNames.URL_API_REPORTE_EXPORTAR_PDF)
    public void exportarReportePdf(@RequestBody ReporteConsultaRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/pdf");
            DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
            String currentDateTime = dateFormat.format(new Date());
            String headerkey = "Content-Disposition";
            String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
            response.setHeader(headerkey, headervalue);
            reporteService.exportarPdf(request, response);
        } catch (Exception ex) {
            log.error("ex=".concat(ex.getMessage()));
        }
    }

    @GetMapping(PathNames.URL_API_REPORTE_EXPORTAR_XLSX)
    public void exportarReporteXlsx(@RequestBody ReporteConsultaRequest request, HttpServletResponse response) {
        try {
            response.setContentType("application/octet-stream");
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=student" + currentDateTime + ".xlsx";
            response.setHeader(headerKey, headerValue);
            reporteService.exportarXlsx(request,response);
        } catch (Exception ex) {
            log.error("ex=".concat(ex.getMessage()));
        }
    }

}
