package pe.cmac.huancayo.sistema.helpdesk.dto.usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDTOResponse {

	// INFO USUARIO
	private Integer idColaborador;
	private String username;
	private Boolean isAdmin;

	// INFO EMPLEADO
	private String nombres;
	private String apellidos;
	private String codEmpleado;
	private String area;

	private Boolean acceso;
}
