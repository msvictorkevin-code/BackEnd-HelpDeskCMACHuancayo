package pe.cmac.huancayo.sistema.helpdesk.service;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;

public interface UsuarioService {

	RestResponse<UsuarioDTOResponse> autenticar(UsuarioDTORequest request);
}
