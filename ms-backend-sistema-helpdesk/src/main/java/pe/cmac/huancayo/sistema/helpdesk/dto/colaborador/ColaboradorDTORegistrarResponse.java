package pe.cmac.huancayo.sistema.helpdesk.dto.colaborador;

import lombok.Data;

@Data
public class ColaboradorDTORegistrarResponse {
    private String codigo;
    private String area;
    private String nombre;
    private String apellido;
    private String usuario;
    private Boolean administador;
}
