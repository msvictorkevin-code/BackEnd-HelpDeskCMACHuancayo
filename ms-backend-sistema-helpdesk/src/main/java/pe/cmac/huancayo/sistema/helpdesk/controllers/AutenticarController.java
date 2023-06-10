package pe.cmac.huancayo.sistema.helpdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.service.UsuarioService;

@RestController
@RequestMapping("autenticar")
public class AutenticarController {


    private final UsuarioService usuarioService;

    public AutenticarController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/acceder")
    public ResponseEntity<RestResponse<UsuarioDTOResponse>> acceder(@RequestBody UsuarioDTORequest request) {
        RestResponse<UsuarioDTOResponse> response = new RestResponse<>();
        try {
            response = usuarioService.autenticar(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
