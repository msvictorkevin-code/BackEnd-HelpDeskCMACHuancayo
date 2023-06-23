package pe.cmac.huancayo.sistema.helpdesk.dto.colaborador;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ColaboradorDTOListarResponse {
    private String codEmpleado;
    private Integer idColaborador;
    private String area;
    private String nombre;
    private String apellido;
    private Integer idUsuario;
    private String usuario;
    private Boolean administador;
    private Boolean estado;

}
