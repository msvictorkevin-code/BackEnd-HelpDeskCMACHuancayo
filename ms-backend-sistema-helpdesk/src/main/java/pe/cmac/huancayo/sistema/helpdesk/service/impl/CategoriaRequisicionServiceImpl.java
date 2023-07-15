package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import org.springframework.stereotype.Service;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.CategoriaRequisicionDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.CategoriaRequisicionService;

import java.util.List;


@Service
public class CategoriaRequisicionServiceImpl implements CategoriaRequisicionService {

    private final CategoriaRequisicionDAORepository categoriaRequisicionDAORepository;

    public CategoriaRequisicionServiceImpl(CategoriaRequisicionDAORepository categoriaRequisicionDAORepository) {

        this.categoriaRequisicionDAORepository = categoriaRequisicionDAORepository;
    }

    @Override
    public RestResponse<List<CategoriaRequisicionDTOResponse>> listar(Integer idTipo) {
        // TODO Auto-generated method stub
        return categoriaRequisicionDAORepository.listar(idTipo);
    }

}
