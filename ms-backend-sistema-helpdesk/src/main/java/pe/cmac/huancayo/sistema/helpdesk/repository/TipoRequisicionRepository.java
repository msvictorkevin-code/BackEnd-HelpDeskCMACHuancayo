package pe.cmac.huancayo.sistema.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cmac.huancayo.sistema.helpdesk.entity.Ticket;
import pe.cmac.huancayo.sistema.helpdesk.entity.TipoRequisicion;

import java.util.List;

public interface TipoRequisicionRepository extends JpaRepository<TipoRequisicion, Integer> {

}
