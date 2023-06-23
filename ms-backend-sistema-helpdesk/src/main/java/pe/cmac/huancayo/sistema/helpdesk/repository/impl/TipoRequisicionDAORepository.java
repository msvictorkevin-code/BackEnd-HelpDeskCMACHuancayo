package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import java.util.List;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;

public interface TipoRequisicionDAORepository {

	RestResponse<List<TipoRequisicionDTOResponse>> listar();

}
