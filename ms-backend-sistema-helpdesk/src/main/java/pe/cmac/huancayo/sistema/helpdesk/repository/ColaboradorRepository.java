package pe.cmac.huancayo.sistema.helpdesk.repository;

import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



import pe.cmac.huancayo.sistema.helpdesk.entity.Colaborador;

import java.util.List;


public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    @Query(value = "SELECT * FROM colaborador c WHERE c.id_Colaborador = :id_Colaborador", nativeQuery = true)
    Colaborador findColaboradorById(@Param("id_Colaborador") int idColaborador);

    @Query(value = "SELECT c.cod_empleado,c.id_Colaborador,c.area,c.nombres,c.apellidos,u.id_usuario,u.username,u.is_admin,c.is_activo " +
            "FROM colaborador c INNER JOIN usuario u ON c.id_Colaborador = u.id_Colaborador " +
            "WHERE c.is_activo = true "
            , nativeQuery = true)
    List<Tuple> listar();


}
