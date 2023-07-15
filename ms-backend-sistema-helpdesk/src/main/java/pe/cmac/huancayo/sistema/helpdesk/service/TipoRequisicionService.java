package pe.cmac.huancayo.sistema.helpdesk.service;

import java.util.List;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;

public interface TipoRequisicionService {

	RestResponse<List<TipoRequisicionDTOResponse>> listar();

 
}
