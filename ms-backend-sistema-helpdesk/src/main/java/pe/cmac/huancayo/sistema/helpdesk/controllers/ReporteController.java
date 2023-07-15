package pe.cmac.huancayo.sistema.helpdesk.controllers;

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
    public void exportarReportePdf(@RequestBody ReporteConsultaRequest request) {
        try {
            reporteService.exportarPdf(request);
        } catch (Exception ex) {
            log.error("ex=".concat(ex.getMessage()));
        }
    }

    @GetMapping(PathNames.URL_API_REPORTE_EXPORTAR_XLSX)
    public void exportarReporteXlsx(@RequestBody ReporteConsultaRequest request) {
        try {
            reporteService.exportarXlsx(request);
        } catch (Exception ex) {
            log.error("ex=".concat(ex.getMessage()));
        }
    }

}
