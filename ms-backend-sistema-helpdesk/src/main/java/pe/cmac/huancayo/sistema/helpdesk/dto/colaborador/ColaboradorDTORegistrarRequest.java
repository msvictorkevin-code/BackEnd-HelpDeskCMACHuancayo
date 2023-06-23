package pe.cmac.huancayo.sistema.helpdesk.dto.colaborador;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ColaboradorDTORegistrarRequest {

    @NotBlank(message = "Propiedad codigo es requerido.")
    @Size( max = 40, message = "Propiedad codigo con tamaño invalido.")
    private String codigo;
    @NotBlank(message = "Propiedad area es requerido.")
    @Size( max = 50, message = "Propiedad area con tamaño invalido.")
    private String area;
    @NotBlank(message = "Propiedad nombre es requerido.")
    @Size(max = 80, message = "Propiedad nombre con tamaño invalido.")
    private String nombre;
    @NotBlank(message = "Propiedad apellido es requerido.")
    @Size( max = 80, message = "Propiedad apellido con tamaño invalido.")
    private String apellido;
    @NotBlank(message = "Propiedad usuario es requerido.")
    @Size( max = 80, message = "Propiedad usuario con tamaño invalido.")
    private String usuario;
    @NotBlank(message = "Propiedad clave es requerido.")
    @Size(max = 80, message = "Propiedad clave con tamaño invalido.")
    private String clave;
    @NotNull(message = "Propiedad clave es requerido.")
    private Boolean administador;
}
