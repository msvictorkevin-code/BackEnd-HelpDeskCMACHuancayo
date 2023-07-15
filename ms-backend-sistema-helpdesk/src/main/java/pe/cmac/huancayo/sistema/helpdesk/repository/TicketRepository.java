package pe.cmac.huancayo.sistema.helpdesk.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.cmac.huancayo.sistema.helpdesk.entity.Ticket;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

    @Query(value = "select id_ticket , t.descripcion ,estado, fecha_generada ,fecha_cierre, " +
            "prioridad , tr.nombre as tipo , cr.nombre  as categoria,u.username " +
            "from Ticket t " +
            "inner join tipo_requisicion tr on t.id_tipo = tr.id_tipo " +
            "inner join categoria_requisicion cr  on t.id_categoria = cr.id_categoria " +
            "inner join usuario u on t.id_usuario = u.id_usuario"
            , nativeQuery = true)
    List<Tuple> listar();


}
