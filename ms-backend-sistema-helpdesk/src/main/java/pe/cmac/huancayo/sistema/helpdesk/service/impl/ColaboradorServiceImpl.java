package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pe.cmac.huancayo.sistema.helpdesk.dto.*;
import pe.cmac.huancayo.sistema.helpdesk.dto.colaborador.*;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.ColaboradorDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.ColaboradorService;

import java.util.List;

@Service
public class ColaboradorServiceImpl implements ColaboradorService {


    private ColaboradorDAORepository colaboradorDAORepository;

    public ColaboradorServiceImpl(ColaboradorDAORepository colaboradorDAORepository) {
        this.colaboradorDAORepository = colaboradorDAORepository;
    }

    @Override
    public RestResponse<ColaboradorDTORegistrarResponse> registrar(ColaboradorDTORegistrarRequest request) {
        return colaboradorDAORepository.registrar(request);
    }

    @Override
    public RestResponse<List<ColaboradorDTOListarResponse>> listar() {
        return colaboradorDAORepository.listar();
    }

    @Override
    public RestResponse<ColaboradorDTOItemResponse> obtener(Integer idColaborador, Integer idUsuario) {
        return colaboradorDAORepository.obtener(idColaborador, idUsuario);
    }

    @Override
    public RestResponse<ColaboradorDTOActualizarResponse> actualizar(ColaboradorDTOActualizarRequest request) {
        return colaboradorDAORepository.actualizar(request);
    }

    @Override
    public RestResponse<ColaboradorDTODeshabilitarResponse> deshabilitar(Integer idColaborador) {
        return colaboradorDAORepository.deshabilitar(idColaborador);
    }
}
