package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.CategoriaRequisicionDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.CategoriaRequisicionService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class CategoriaRequisicionServiceImplTest {

    @Mock
    private CategoriaRequisicionDAORepository categoriaRequisicionDAORepository;

    @MockBean
    private CategoriaRequisicionService categoriaRequisicionService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        categoriaRequisicionService = new CategoriaRequisicionServiceImpl(categoriaRequisicionDAORepository);
    }

    @Test
    void listar() {
        RestResponse<List<CategoriaRequisicionDTOResponse>> listRestResponse = buildListRestResponse();
        Mockito.when(categoriaRequisicionDAORepository.listar(Mockito.anyInt())).thenReturn(listRestResponse);
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = categoriaRequisicionService.listar(1);
        Assertions.assertNotNull(response.getData());
    }

    private RestResponse<List<CategoriaRequisicionDTOResponse>> buildListRestResponse() {
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = new RestResponse<>();
        List<CategoriaRequisicionDTOResponse> list = new ArrayList<>();
        CategoriaRequisicionDTOResponse dtoResponse = new CategoriaRequisicionDTOResponse();
        dtoResponse.setId(1);
        dtoResponse.setIdTipo(1);
        dtoResponse.setNombre("test");
        list.add(dtoResponse);
        response.setData(list);
        return response;
    }
}