package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;

public interface UsuarioDAORepository {
	RestResponse<UsuarioDTOResponse> autenticar(UsuarioDTORequest request);
}
