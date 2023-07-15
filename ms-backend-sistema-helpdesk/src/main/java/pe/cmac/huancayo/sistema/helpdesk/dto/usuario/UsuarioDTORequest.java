package pe.cmac.huancayo.sistema.helpdesk.dto.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioDTORequest {
    @NotBlank(message = "Propiedad username es requerido.")
    private String username;
    @NotBlank(message = "Propiedad password es requerido.")
    private String password;
}
