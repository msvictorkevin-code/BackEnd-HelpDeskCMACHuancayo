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
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;
import pe.cmac.huancayo.sistema.helpdesk.entity.Colaborador;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;
import pe.cmac.huancayo.sistema.helpdesk.repository.ColaboradorRepository;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ColaboradorDAORepositoryImplTest {

    @Mock
    private ColaboradorRepository colaboradorRepository;

    @Mock
    private UsuarioRepository usuarioRepository;
    @MockBean
    private ColaboradorDAORepository colaboradorDAORepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        colaboradorDAORepository = new ColaboradorDAORepositoryImpl(usuarioRepository, colaboradorRepository);
    }

    @Test
    void registrar() {
        Colaborador colaborador = buildColaborador();
        Usuario usuario = buildUsuario();
        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
        ColaboradorDTORegistrarRequest request = buildColaboradorDTORegistrarRequest();
        RestResponse<ColaboradorDTORegistrarResponse> response = colaboradorDAORepository.registrar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void actualizar() {
        Colaborador colaborador = buildColaborador();
        Usuario usuario = buildUsuario();
        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);
        Mockito.when(usuarioRepository.save(Mockito.any())).thenReturn(usuario);
        ColaboradorDTOActualizarRequest request = buildColaboradorDTOActualizarRequest();
        RestResponse<ColaboradorDTOActualizarResponse> response = colaboradorDAORepository.actualizar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void listar() {
        List<Colaborador> list = buildListColaborador();
        Mockito.when(colaboradorRepository.findAll()).thenReturn(list);
        RestResponse<List<ColaboradorDTOListarResponse>> response = colaboradorDAORepository.listar();
        Assertions.assertNotNull(response);
    }


    @Test
    void obtener() {
        Colaborador colaborador = buildColaborador();
        Usuario usuario = buildUsuario();
        Mockito.when(usuarioRepository.findUsuarioById(Mockito.anyInt())).thenReturn(usuario);
        Mockito.when(colaboradorRepository.findColaboradorById(Mockito.anyInt())).thenReturn(colaborador);
        RestResponse<ColaboradorDTOItemResponse> response = colaboradorDAORepository.obtener(1, 1);
        Assertions.assertNotNull(response);
    }


    @Test
    void deshabilitar() {
        Colaborador colaborador = buildColaborador();
        Mockito.when(colaboradorRepository.save(Mockito.any())).thenReturn(colaborador);
        Mockito.when(colaboradorRepository.findColaboradorById(Mockito.anyInt())).thenReturn(colaborador);

        RestResponse<ColaboradorDTODeshabilitarResponse> deshabilitar = colaboradorDAORepository.deshabilitar(1);
        Assertions.assertNotNull(deshabilitar);

    }

    private List<Colaborador> buildListColaborador() {
        List<Colaborador> list = Arrays.asList(buildColaborador(), buildColaborador(), buildColaborador());
        return list;
    }

    private ColaboradorDTORegistrarRequest buildColaboradorDTORegistrarRequest() {
        ColaboradorDTORegistrarRequest colaboradorDTORegistrarRequest = new ColaboradorDTORegistrarRequest();
        colaboradorDTORegistrarRequest.setCodigo("1");
        colaboradorDTORegistrarRequest.setArea("Sistemas");
        colaboradorDTORegistrarRequest.setNombre("Victor");
        colaboradorDTORegistrarRequest.setApellido("Matos");
        colaboradorDTORegistrarRequest.setUsuario("usuario155");
        colaboradorDTORegistrarRequest.setClave("Pwda155");
        colaboradorDTORegistrarRequest.setAdministador(true);
        return colaboradorDTORegistrarRequest;

    }

    private ColaboradorDTOActualizarRequest buildColaboradorDTOActualizarRequest() {
        ColaboradorDTOActualizarRequest colaboradorDTOActualizarRequest = new ColaboradorDTOActualizarRequest();
        colaboradorDTOActualizarRequest.setIdColaborador(1);
        colaboradorDTOActualizarRequest.setCodigo("1");
        colaboradorDTOActualizarRequest.setArea("Sistemas");
        colaboradorDTOActualizarRequest.setNombre("Victor");
        colaboradorDTOActualizarRequest.setApellido("Matos");
        colaboradorDTOActualizarRequest.setUsuario("usuario155");
        colaboradorDTOActualizarRequest.setClave("Pwda155");
        colaboradorDTOActualizarRequest.setAdministador(true);
        return colaboradorDTOActualizarRequest;
    }

    private Colaborador buildColaborador() {
        Colaborador colaborador = new Colaborador();
        colaborador.setId(1);
        colaborador.setNombres("nombre");
        colaborador.setApellidos("apellido");
        colaborador.setCodEmpleado("1001399");
        colaborador.setArea("Sistemas");
        colaborador.setIsActivo(true);
        return colaborador;
    }

    private Usuario buildUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setIdColaborador(1);
        usuario.setUsername("msvickei");
        usuario.setPassword("PEsi895");
        usuario.setIsAdmin(false);
        return usuario;
    }
}