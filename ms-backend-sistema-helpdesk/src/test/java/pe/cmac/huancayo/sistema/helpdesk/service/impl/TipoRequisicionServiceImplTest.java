package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.TipoRequisicionDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.TipoRequisicionService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class TipoRequisicionServiceImplTest {

    @Mock
    private TipoRequisicionDAORepository tipoRequisicionDAORepository;

    @MockBean
    private TipoRequisicionService tipoRequisicionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        tipoRequisicionService = new TipoRequisicionServiceImpl(tipoRequisicionDAORepository);
    }

    @Test
    void listar() {
        RestResponse<List<TipoRequisicionDTOResponse>> listRestResponse = buildListRestResponse();
        Mockito.when(tipoRequisicionDAORepository.listar()).thenReturn(listRestResponse);
        RestResponse<List<TipoRequisicionDTOResponse>> response = tipoRequisicionService.listar();
        Assertions.assertNotNull(response);
    }

    private RestResponse<List<TipoRequisicionDTOResponse>> buildListRestResponse() {
        RestResponse<List<TipoRequisicionDTOResponse>> response = new RestResponse<>();
        List<TipoRequisicionDTOResponse> list = new ArrayList<>();
        TipoRequisicionDTOResponse tipoRequisicionDTOResponse = new TipoRequisicionDTOResponse();
        tipoRequisicionDTOResponse.setId(1);
        tipoRequisicionDTOResponse.setNombre("test");
        tipoRequisicionDTOResponse.setDescripcion("test");
        list.add(tipoRequisicionDTOResponse);
        response.setData(list);
        return response;


    }
}