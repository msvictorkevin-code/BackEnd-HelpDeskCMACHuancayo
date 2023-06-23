package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.entity.TipoRequisicion;
import pe.cmac.huancayo.sistema.helpdesk.repository.TipoRequisicionRepository;
import pe.cmac.huancayo.sistema.helpdesk.utils.Messages;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Repository
public class TipoRequisicionDAORepositoryImpl implements TipoRequisicionDAORepository {

    private final TipoRequisicionRepository tipoRequisicionRepository;


    @Override
    public RestResponse<List<TipoRequisicionDTOResponse>> listar() {
        // TODO Auto-generated method stub
        RestResponse<List<TipoRequisicionDTOResponse>> response = new RestResponse<>();
        try {
            List<TipoRequisicion> tipoRequisicionList = tipoRequisicionRepository.findAll();
            List<TipoRequisicionDTOResponse> tipoRequisicionDTOResponseList = tipoRequisicionList.stream().map(x -> {
                TipoRequisicionDTOResponse tipoRequisicionDTOResponse = new TipoRequisicionDTOResponse();
                tipoRequisicionDTOResponse.setId(x.getId());
                tipoRequisicionDTOResponse.setDescripcion(x.getDescripcion());
                tipoRequisicionDTOResponse.setNombre(x.getNombre());
                log.info("tipoRequisicionDTOResponse=" + tipoRequisicionDTOResponse);
                return tipoRequisicionDTOResponse;
            }).toList();
            response.setData(tipoRequisicionDTOResponseList);
            response.setStatus(true);
            response.setMessage(Messages.MSG_LISTADO_OK);
        } catch (Exception ex) {
            log.info("Mensaje="+ex.getMessage());
            response.setData(null);
            response.setStatus(false);
            response.setMessage(Messages.MSG_LISTADO_VACIO);
        }


        return response;
    }
}
