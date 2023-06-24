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
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.ColaboradorDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.ColaboradorService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
class ColaboradorServiceImplTest {
    @Mock
    private ColaboradorDAORepository colaboradorDAORepository;

    @MockBean
    private ColaboradorService colaboradorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // this is needed for inititalizytion of mocks, if you use @Mock
        colaboradorService = new ColaboradorServiceImpl(colaboradorDAORepository);
    }

    @Test
    void registrar() {
        ColaboradorDTORegistrarRequest request = buildColaboradorDTORegistrarRequest();
        RestResponse<ColaboradorDTORegistrarResponse> responseRestResponse = buildColaboradorDTORegistrarResponse();
        Mockito.when(colaboradorDAORepository.registrar(Mockito.any())).thenReturn(responseRestResponse);
        RestResponse<ColaboradorDTORegistrarResponse> response = colaboradorService.registrar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void actualizar() {
        ColaboradorDTOActualizarRequest request = buildColaboradorDTOActualizarRequest();
        RestResponse<ColaboradorDTOActualizarResponse> responseRestResponse = buildolaboradorDTOActualizarResponse();
        Mockito.when(colaboradorDAORepository.actualizar(Mockito.any())).thenReturn(responseRestResponse);
        RestResponse<ColaboradorDTOActualizarResponse> response = colaboradorService.actualizar(request);
        Assertions.assertNotNull(response);
    }

    @Test
    void listar() {
        RestResponse<List<ColaboradorDTOListarResponse>> responseRestResponse = builColaboradorDTOListarResponse();
        Mockito.when(colaboradorDAORepository.listar()).thenReturn(responseRestResponse);
        RestResponse<List<ColaboradorDTOListarResponse>> response = colaboradorService.listar();
        Assertions.assertNotNull(response);
    }


    @Test
    void obtener() {
        RestResponse<ColaboradorDTOItemResponse> responseRestResponse = builColaboradorDTOItemResponse();
        Mockito.when(colaboradorDAORepository.obtener(Mockito.anyInt(),Mockito.anyInt())).thenReturn(responseRestResponse);
        RestResponse<ColaboradorDTOItemResponse> response = colaboradorService.obtener(1,1);
        Assertions.assertNotNull(response);
    }

    @Test
    void deshabilitar() {
        RestResponse<ColaboradorDTODeshabilitarResponse> responseRestResponse = builColaboradorDTODeshabilitarResponse();
        Mockito.when(colaboradorDAORepository.deshabilitar(Mockito.anyInt())).thenReturn(responseRestResponse);
        RestResponse<ColaboradorDTODeshabilitarResponse> response = colaboradorService.deshabilitar(1);
        Assertions.assertNotNull(response);

    }

    private RestResponse<ColaboradorDTODeshabilitarResponse> builColaboradorDTODeshabilitarResponse() {
        RestResponse<ColaboradorDTODeshabilitarResponse> responseRestResponse = new RestResponse<>();
        ColaboradorDTODeshabilitarResponse dtoDeshabilitarResponse = new ColaboradorDTODeshabilitarResponse();
        dtoDeshabilitarResponse.setIdColaborador(1);
        dtoDeshabilitarResponse.setActivo(false);
        responseRestResponse.setData(dtoDeshabilitarResponse);
        return responseRestResponse;
    }


    private ColaboradorDTOActualizarRequest buildColaboradorDTOActualizarRequest() {
        ColaboradorDTOActualizarRequest colaboradorDTOActualizarRequest = new ColaboradorDTOActualizarRequest();
        colaboradorDTOActualizarRequest.setNombre("test");
        colaboradorDTOActualizarRequest.setApellido("test");
        return colaboradorDTOActualizarRequest;
    }

    private RestResponse<ColaboradorDTOActualizarResponse> buildolaboradorDTOActualizarResponse() {
        RestResponse<ColaboradorDTOActualizarResponse> response = new RestResponse<>();
        ColaboradorDTOActualizarResponse colaboradorDTOActualizarResponse = new ColaboradorDTOActualizarResponse();
        colaboradorDTOActualizarResponse.setUsuario("test");
        colaboradorDTOActualizarResponse.setNombre("test");
        colaboradorDTOActualizarResponse.setApellido("test");
        response.setData(colaboradorDTOActualizarResponse);
        return response;
    }

    private RestResponse<ColaboradorDTORegistrarResponse> buildColaboradorDTORegistrarResponse() {
        RestResponse<ColaboradorDTORegistrarResponse> response = new RestResponse<>();
        ColaboradorDTORegistrarResponse colaboradorDTORegistrarResponse = new ColaboradorDTORegistrarResponse();
        colaboradorDTORegistrarResponse.setUsuario("test");
        colaboradorDTORegistrarResponse.setNombre("test");
        colaboradorDTORegistrarResponse.setApellido("test");
        response.setData(colaboradorDTORegistrarResponse);
        return response;
    }

    private ColaboradorDTORegistrarRequest buildColaboradorDTORegistrarRequest() {
        ColaboradorDTORegistrarRequest colaboradorDTORegistrarRequest = new ColaboradorDTORegistrarRequest();
        colaboradorDTORegistrarRequest.setNombre("test");
        colaboradorDTORegistrarRequest.setApellido("test");
        return colaboradorDTORegistrarRequest;
    }

    private RestResponse<List<ColaboradorDTOListarResponse>> builColaboradorDTOListarResponse() {
        RestResponse<List<ColaboradorDTOListarResponse>> response = new RestResponse<>();
        List<ColaboradorDTOListarResponse> list = new ArrayList<>();
        ColaboradorDTOListarResponse colaboradorDTOListarResponse = new ColaboradorDTOListarResponse("1", 1, "Finanzas", "KEvin", "matos", 1, "usaur", false, false);
        list.add(colaboradorDTOListarResponse);
        return response;
    }

    private RestResponse<ColaboradorDTOItemResponse> builColaboradorDTOItemResponse() {
        RestResponse<ColaboradorDTOItemResponse> response = new RestResponse<>();
        ColaboradorDTOItemResponse colaboradorDTOItemResponse = new ColaboradorDTOItemResponse("1", 1, "Finanzas", "KEvin", "matos", 1, "usaur", "asd155##", 5,true,false);
        response.setData(colaboradorDTOItemResponse);
        return response;
    }

}