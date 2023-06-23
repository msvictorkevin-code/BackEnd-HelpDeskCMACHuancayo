package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.categoriarequisicion.CategoriaRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.CategoriaRequisicionRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class CategoriaRequisicionDAORepositoryImpl implements CategoriaRequisicionDAORepository {


    private final CategoriaRequisicionRepository categoriaRequisicionRepository;


    @Override
    public RestResponse<List<CategoriaRequisicionDTOResponse>> listar(Integer idTipo) {
        // TODO Auto-generated method stub
        RestResponse<List<CategoriaRequisicionDTOResponse>> response = new RestResponse<>();
        try {
            List<CategoriaRequisicionDTOResponse> list = categoriaRequisicionRepository.findCategoriaRequisicionByIdTipo(idTipo).stream().map(x -> {
                CategoriaRequisicionDTOResponse categoriaRequisicionDTOResponse = new CategoriaRequisicionDTOResponse();
                categoriaRequisicionDTOResponse.setId(x.getId());
                categoriaRequisicionDTOResponse.setIdTipo(x.getIdTipo());
                categoriaRequisicionDTOResponse.setNombre(x.getNombre());
                categoriaRequisicionDTOResponse.setDescripcion(x.getDescripcion());
                log.info("categoriaRequisicionDTOResponse=" + categoriaRequisicionDTOResponse);
                return categoriaRequisicionDTOResponse;

            }).toList();
            response.setData(list);
            response.setStatus(true);
            response.setMessage(Messages.MSG_LISTADO_OK);
        } catch (Exception ex) {
            log.info("Mensaje=" + ex.getMessage());
            response.setData(null);
            response.setStatus(false);
            response.setMessage(Messages.MSG_LISTADO_VACIO);
        }
        return response;

    }
}
