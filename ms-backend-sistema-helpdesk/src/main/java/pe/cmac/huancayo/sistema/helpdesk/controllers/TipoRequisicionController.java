package pe.cmac.huancayo.sistema.helpdesk.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
 
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.service.TipoRequisicionService;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PathNames.URL_API_BASE)
public class TipoRequisicionController {

    private final TipoRequisicionService tipoRequisicionService;

    public TipoRequisicionController(TipoRequisicionService tipoRequisicionService) {
        this.tipoRequisicionService = tipoRequisicionService;
    }
    
    @GetMapping(PathNames.URL_API_TIPO_REQUISICION_LISTAR)
    public ResponseEntity<RestResponse<List<TipoRequisicionDTOResponse>>> listar() {
        RestResponse<List<TipoRequisicionDTOResponse>> response = new RestResponse<>();
        try {
             response = tipoRequisicionService.listar();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
