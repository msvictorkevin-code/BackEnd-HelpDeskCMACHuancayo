package pe.cmac.huancayo.sistema.helpdesk.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;
import pe.cmac.huancayo.sistema.helpdesk.service.ColaboradorService;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PathNames.URL_API_BASE)
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping(PathNames.URL_API_COLABORADOR_REGISTRAR)
    public ResponseEntity<RestResponse<ColaboradorDTORegistrarResponse>> registrarColaborador(@Valid @RequestBody ColaboradorDTORegistrarRequest request) {
        RestResponse<ColaboradorDTORegistrarResponse> response = new RestResponse<>();
        try {
            response = colaboradorService.registrar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @PostMapping(PathNames.URL_API_COLABORADOR_ACTUALIZAR)
    public ResponseEntity<RestResponse<ColaboradorDTOActualizarResponse>> actualizarColaborador(@Valid @RequestBody ColaboradorDTOActualizarRequest request) {
        RestResponse<ColaboradorDTOActualizarResponse> response = new RestResponse<>();
        try {
            response = colaboradorService.actualizar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @GetMapping(PathNames.URL_API_COLABORADOR_LISTAR)
    public ResponseEntity<RestResponse<List<ColaboradorDTOListarResponse>>> listarColaboradores() {
        RestResponse<List<ColaboradorDTOListarResponse>> response = new RestResponse<>();
        try {
            response = colaboradorService.listar();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping(PathNames.URL_API_COLABORADOR_OBTENER)
    public ResponseEntity<RestResponse<ColaboradorDTOItemResponse>> obtenerColaborador(@PathVariable Integer idColaborador, @PathVariable Integer idUsuario) {
        RestResponse<ColaboradorDTOItemResponse> response = new RestResponse<>();
        try {
            response = colaboradorService.obtener(idColaborador, idUsuario);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }


    @PostMapping(PathNames.URL_API_COLABORADOR_DESHABILITAR)
    public ResponseEntity<RestResponse<ColaboradorDTODeshabilitarResponse>> deshabilitarColaborador(@PathVariable Integer idColaborador) {
        RestResponse<ColaboradorDTODeshabilitarResponse> response = new RestResponse<>();
        try {
            response = colaboradorService.deshabilitar(idColaborador);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
