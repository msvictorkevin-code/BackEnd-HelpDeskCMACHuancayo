package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.UsuarioDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.UsuarioService;

@ExtendWith(SpringExtension.class)
class UsuarioServiceImplTest {

    @Mock
    private UsuarioDAORepository usuarioDAORepository;
    @MockBean
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        usuarioService = new UsuarioServiceImpl(usuarioDAORepository);
    }

    @Test
    void autenticar() {
        RestResponse<UsuarioDTOResponse> dtoResponse = buildUsuarioDTOResponse();
        UsuarioDTORequest dtoRequest = buildUsuarioDTORequest();
        Mockito.when(usuarioDAORepository.autenticar(Mockito.any())).thenReturn(dtoResponse);
        RestResponse<UsuarioDTOResponse> response = usuarioService.autenticar(dtoRequest);
    }

    private UsuarioDTORequest buildUsuarioDTORequest() {
        UsuarioDTORequest usuarioDTORequest = new UsuarioDTORequest();
        usuarioDTORequest.setUsername("usuarioxs");
        usuarioDTORequest.setPassword("usuarioxs");

        return usuarioDTORequest;
    }

    private RestResponse<UsuarioDTOResponse> buildUsuarioDTOResponse() {
        RestResponse<UsuarioDTOResponse> response = new RestResponse<>();
        UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
        usuarioDTOResponse.setUsername("usuarioxs");
        usuarioDTOResponse.setArea("Sistemas");
        response.setData(usuarioDTOResponse);
        return response;
    }


}