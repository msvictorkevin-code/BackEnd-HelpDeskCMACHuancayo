package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.usuario.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.Colaborador;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.repository.ColaboradorRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioDAORepositoryImplTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;
    @MockBean
    private UsuarioDAORepository usuarioDAORepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        usuarioDAORepository = new UsuarioDAORepositoryImpl(usuarioRepository, colaboradorRepository);
    }

    @Test
    void autenticar_correctamente() {
        UsuarioDTORequest request = buildUsuarioDTORequest();
        Usuario usuario = buildUsuarioCorrecto();
        Colaborador colaborador = buildColaborador();
        Mockito.when(usuarioRepository.findUserByUsername(Mockito.anyString())).thenReturn(Optional.of(usuario));
        Mockito.when(colaboradorRepository.findColaboradorById(Mockito.anyInt())).thenReturn(colaborador);
        RestResponse<UsuarioDTOResponse> autenticar = usuarioDAORepository.autenticar(request);
        Assertions.assertEquals(String.format(Messages.MSG_LOGIN_CON_ACCESO, usuario.getUsername()), autenticar.getMessage());
    }

    @Test
    void autenticar_incorrecto() {
        UsuarioDTORequest request = buildUsuarioIncorrectoDTORequest();
        Usuario usuario = buildUsuarioIncorrecto();
        Colaborador colaborador = buildColaborador();
        Mockito.when(usuarioRepository.findUserByUsername(Mockito.anyString())).thenReturn(Optional.of(usuario));
        Mockito.when(colaboradorRepository.findColaboradorById(Mockito.anyInt())).thenReturn(colaborador);
        RestResponse<UsuarioDTOResponse> autenticar = usuarioDAORepository.autenticar(request);
        Assertions.assertEquals(String.format(Messages.MSG_LOGIN_SIN_ACCESO, usuario.getUsername()), autenticar.getMessage());
    }

    private Usuario buildUsuarioIncorrecto() {
        Usuario usuario = new Usuario();
        usuario.setUsername("usuarioxd");
        usuario.setPassword("123456");
        usuario.setIsAdmin(false);
        usuario.setNroIntentos(1);
        return usuario;
    }

    private UsuarioDTORequest buildUsuarioIncorrectoDTORequest() {
        UsuarioDTORequest request = new UsuarioDTORequest();
        request.setUsername("usuarioxd");
        request.setPassword("654321");
        return request;
    }


    @Test
    void autenticar_no_existe_usuario() {
        UsuarioDTORequest request = buildUsuarioDTORequest();
        Mockito.when(usuarioRepository.findUserByUsername(Mockito.anyString())).thenReturn(Optional.empty());
        RestResponse<UsuarioDTOResponse> autenticar = usuarioDAORepository.autenticar(request);
        Assertions.assertEquals(Messages.MSG_LOGIN_NO_EXISTE, autenticar.getMessage());
    }


    @Test
    void autenticar_intentos_superados() {
        UsuarioDTORequest request = buildUsuarioDTORequest();
        Usuario usuario = buildUsuarioConIntentos();
        Mockito.when(usuarioRepository.findUserByUsername(Mockito.anyString())).thenReturn(Optional.of(usuario));
        RestResponse<UsuarioDTOResponse> autenticar = usuarioDAORepository.autenticar(request);
        Assertions.assertEquals(Messages.MSG_LOGIN_INTENTOS, autenticar.getMessage());
    }


    private Colaborador buildColaborador() {
        var colaborador = new Colaborador();
        colaborador.setNombres("juan");
        colaborador.setApellidos("perez");
        colaborador.setCodEmpleado("124554");
        colaborador.setArea("Finanzas");
        return colaborador;
    }


    private Usuario buildUsuarioCorrecto() {
        Usuario usuario = new Usuario();
        usuario.setUsername("usuarioxd");
        usuario.setPassword("usuarioxd");
        usuario.setIdColaborador(12555);
        usuario.setIsAdmin(false);
        usuario.setNroIntentos(1);
        return usuario;
    }

    private Usuario buildUsuarioConIntentos() {
        Usuario usuario = new Usuario();
        usuario.setUsername("usuarioxd");
        usuario.setNroIntentos(3);
        return usuario;

    }

    private UsuarioDTORequest buildUsuarioDTORequest() {
        UsuarioDTORequest request = new UsuarioDTORequest();
        request.setUsername("usuarioxd");
        request.setPassword("usuarioxd");
        return request;
    }

}