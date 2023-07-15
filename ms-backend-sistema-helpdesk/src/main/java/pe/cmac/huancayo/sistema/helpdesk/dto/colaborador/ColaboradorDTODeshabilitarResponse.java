package pe.cmac.huancayo.sistema.helpdesk.dto.colaborador;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ColaboradorDTODeshabilitarResponse {
    private Integer idColaborador;
    private Boolean activo;
}
