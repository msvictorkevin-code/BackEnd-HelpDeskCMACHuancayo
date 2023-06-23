package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.TipoRequisicion;
import pe.cmac.huancayo.sistema.helpdesk.repository.TipoRequisicionRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TipoRequisicionDAORepositoryImplTest {

    @Mock
    private TipoRequisicionRepository tipoRequisicionRepository;
    @MockBean
    private TipoRequisicionDAORepository tipoRequisicionDAORepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        tipoRequisicionDAORepository = new TipoRequisicionDAORepositoryImpl(tipoRequisicionRepository);
    }

    @DisplayName("Listado de requisiciones ok")
    @Test
    void listar() {
        List<TipoRequisicion> list = listado();
        Mockito.when(tipoRequisicionRepository.findAll()).thenReturn(list);
        RestResponse<List<TipoRequisicionDTOResponse>> response = tipoRequisicionDAORepository.listar();
        Assertions.assertNotNull(response);
    }

    @DisplayName("Listado de requisiciones con errores")
    @Test
    void listar_with_errors() {
        Mockito.when(tipoRequisicionRepository.findAll()).thenThrow(new NullPointerException("Error de conectividad BD."));
        RestResponse<List<TipoRequisicionDTOResponse>> response = tipoRequisicionDAORepository.listar();
        Assertions.assertNull(response.getData());
        Assertions.assertEquals("Consulta no devuelve resultados.", response.getMessage());
    }


    private List<TipoRequisicion> listado() {
        TipoRequisicion tipoRequisicion1 = buildTipoRequisicion();
        TipoRequisicion tipoRequisicion2 = buildTipoRequisicion();
        TipoRequisicion tipoRequisicion3 = buildTipoRequisicion();
        List<TipoRequisicion> tipoRequisicionList = new ArrayList<>();
        tipoRequisicionList.add(tipoRequisicion1);
        tipoRequisicionList.add(tipoRequisicion2);
        tipoRequisicionList.add(tipoRequisicion3);
        return tipoRequisicionList;
    }

    private TipoRequisicion buildTipoRequisicion() {
        TipoRequisicion tipoRequisicion = new TipoRequisicion();
        tipoRequisicion.setId(1);
        tipoRequisicion.setNombre("test");
        tipoRequisicion.setDescripcion("test");
        return tipoRequisicion;
    }
}