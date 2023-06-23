package pe.cmac.huancayo.sistema.helpdesk.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.service.UsuarioService;
import pe.cmac.huancayo.sistema.helpdesk.utils.PathNames;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PathNames.URL_API_BASE)
public class AutenticarController {

    private final UsuarioService usuarioService;

    public AutenticarController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = PathNames.URL_API_AUTENTICAR_ACCESO)
    public ResponseEntity<RestResponse<UsuarioDTOResponse>> acceder(@Valid @RequestBody UsuarioDTORequest request) {
        RestResponse<UsuarioDTOResponse> response = new RestResponse<>();
        try {
            response = usuarioService.autenticar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
