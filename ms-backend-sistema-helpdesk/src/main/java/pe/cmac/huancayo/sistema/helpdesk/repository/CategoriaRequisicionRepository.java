package pe.cmac.huancayo.sistema.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.cmac.huancayo.sistema.helpdesk.entity.CategoriaRequisicion;

import java.util.List;


public interface CategoriaRequisicionRepository extends JpaRepository<CategoriaRequisicion, Integer> {

    @Query(value = "SELECT * FROM  categoria_requisicion c WHERE c.id_tipo = :id_tipo", nativeQuery = true)
    List<CategoriaRequisicion> findCategoriaRequisicionByIdTipo(@Param("id_tipo") int idTipo);
}
