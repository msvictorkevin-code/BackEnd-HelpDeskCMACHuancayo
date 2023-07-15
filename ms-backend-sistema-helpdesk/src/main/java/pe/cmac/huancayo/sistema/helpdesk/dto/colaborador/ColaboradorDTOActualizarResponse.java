package pe.cmac.huancayo.sistema.helpdesk.dto.colaborador;

import lombok.Data;

@Data
public class ColaboradorDTOActualizarResponse {
    private Integer idColaborador;
    private String codigo;
    private String area;
    private String nombre;
    private String apellido;
    private Integer idUsuario;
    private String usuario;
    private Boolean administador;
    private Boolean activo;
}
