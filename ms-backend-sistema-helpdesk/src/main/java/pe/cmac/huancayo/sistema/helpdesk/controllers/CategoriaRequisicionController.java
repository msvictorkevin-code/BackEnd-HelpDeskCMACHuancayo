package pe.cmac.huancayo.sistema.helpdesk.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.service.CategoriaRequisicionService;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PathNames.URL_API_BASE)
public class CategoriaRequisicionController {

    private final CategoriaRequisicionService categoriaRequisicionService;

    public CategoriaRequisicionController(CategoriaRequisicionService categoriaRequisicionService) {
        this.categoriaRequisicionService = categoriaRequisicionService;
    }
    
    @GetMapping(PathNames.URL_API_CATEGORIA_REQUISICION_LISTAR)
    public ResponseEntity<RestResponse<List<CategoriaRequisicionDTOResponse>>> listar(@PathVariable Integer idTipo) {
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = new RestResponse<>();
        try {
            response = categoriaRequisicionService.listar(idTipo);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
  
}
