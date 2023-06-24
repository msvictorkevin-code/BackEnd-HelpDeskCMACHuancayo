package pe.cmac.huancayo.sistema.helpdesk.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.tiporequisicion.TipoRequisicionDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.impl.TipoRequisicionDAORepository;
import pe.cmac.huancayo.sistema.helpdesk.service.TipoRequisicionService;

@Service
public class TipoRequisicionServiceImpl implements TipoRequisicionService {

	private TipoRequisicionDAORepository tipoRequisicionDAORepository;

	public TipoRequisicionServiceImpl(TipoRequisicionDAORepository tipoRequisicionDAORepository) {

		this.tipoRequisicionDAORepository = tipoRequisicionDAORepository;
	}

	@Override
	public RestResponse<List<TipoRequisicionDTOResponse>> listar() {
		// TODO Auto-generated method stub
		return tipoRequisicionDAORepository.listar();
	}

}
