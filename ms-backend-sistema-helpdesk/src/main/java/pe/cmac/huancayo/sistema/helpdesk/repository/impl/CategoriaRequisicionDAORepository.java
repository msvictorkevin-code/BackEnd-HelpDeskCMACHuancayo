package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import java.util.List;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;

public interface CategoriaRequisicionDAORepository {

	RestResponse<List<CategoriaRequisicionDTOResponse>> listar(Integer idTipo);
}
