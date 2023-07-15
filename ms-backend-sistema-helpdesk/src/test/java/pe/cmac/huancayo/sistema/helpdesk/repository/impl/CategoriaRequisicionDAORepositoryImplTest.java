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
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.CategoriaRequisicion;
import pe.cmac.huancayo.sistema.helpdesk.repository.CategoriaRequisicionRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CategoriaRequisicionDAORepositoryImplTest {

    @Mock
    private CategoriaRequisicionRepository requisicionRepository;

    @MockBean
    private CategoriaRequisicionDAORepository categoriaRequisicionDAORepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        categoriaRequisicionDAORepository = new CategoriaRequisicionDAORepositoryImpl(requisicionRepository);

    }

    @Test
    void listar() {
        List<CategoriaRequisicion> list = listado();
        Mockito.when(requisicionRepository.findCategoriaRequisicionByIdTipo(1)).thenReturn(list);
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = categoriaRequisicionDAORepository.listar(1);
        Assertions.assertNotNull(response);
    }

    @DisplayName("Listado de categoria ok")
    @Test
    void list_categories() {
        List<CategoriaRequisicion> list = listado();
        Mockito.when(requisicionRepository.findCategoriaRequisicionByIdTipo(Mockito.anyInt())).thenReturn(list);
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = categoriaRequisicionDAORepository.listar(1);
        Assertions.assertNotNull(response);
    }

    @DisplayName("Listado de categoria con errores")
    @Test
    void list_with_errors() {
        Mockito.when(requisicionRepository.findCategoriaRequisicionByIdTipo(Mockito.anyInt())).thenThrow(new NullPointerException("Error de conectividad BD."));
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = categoriaRequisicionDAORepository.listar(Mockito.anyInt());
        Assertions.assertNull(response.getData());
        Assertions.assertEquals("Consulta no devuelve resultados.", response.getMessage());

    }


    private List<CategoriaRequisicion> listado() {
        CategoriaRequisicion categoriaRequisicion1 = buildCategoriaRequisicion();
        CategoriaRequisicion categoriaRequisicion2 = buildCategoriaRequisicion();
        CategoriaRequisicion categoriaRequisicion3 = buildCategoriaRequisicion();
        List<CategoriaRequisicion> lista = new ArrayList<>();
        lista.add(categoriaRequisicion1);
        lista.add(categoriaRequisicion2);
        lista.add(categoriaRequisicion3);
        return lista;
    }

    private CategoriaRequisicion buildCategoriaRequisicion() {
        var categoriaRequisicion = new CategoriaRequisicion();
        categoriaRequisicion.setId(1);
        categoriaRequisicion.setNombre("test");
        categoriaRequisicion.setIdTipo(1);
        categoriaRequisicion.setDescripcion("test");
        return categoriaRequisicion;
    }
}
