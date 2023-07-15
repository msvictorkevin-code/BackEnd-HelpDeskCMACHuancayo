package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import pe.cmac.huancayo.sistema.helpdesk.dto.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;

import java.util.List;

public interface ColaboradorDAORepository {
    RestResponse<ColaboradorDTORegistrarResponse> registrar(ColaboradorDTORegistrarRequest request);

    RestResponse<List<ColaboradorDTOListarResponse>> listar();

    RestResponse<ColaboradorDTOItemResponse> obtener(Integer idColaborador, Integer idUsuario);

    RestResponse<ColaboradorDTOActualizarResponse> actualizar(ColaboradorDTOActualizarRequest request);

    RestResponse<ColaboradorDTODeshabilitarResponse> deshabilitar(Integer idColaborador);
}
