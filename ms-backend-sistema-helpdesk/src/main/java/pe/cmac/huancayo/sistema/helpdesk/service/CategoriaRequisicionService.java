package pe.cmac.huancayo.sistema.helpdesk.service;

import java.util.List;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;

public interface CategoriaRequisicionService {

	RestResponse<List<CategoriaRequisicionDTOResponse>> listar(Integer idTipo);
}
