package pe.cmac.huancayo.sistema.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.cmac.huancayo.sistema.helpdesk.entity.DetalleTicket;

public interface DetalleTicketRepository extends JpaRepository<DetalleTicket, Integer> {
}
