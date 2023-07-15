package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.UsuarioDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioDAORepository usuarioRepository;

	public UsuarioServiceImpl(UsuarioDAORepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public RestResponse<UsuarioDTOResponse> autenticar(UsuarioDTORequest request) {
		return usuarioRepository.autenticar(request);
	}
}
