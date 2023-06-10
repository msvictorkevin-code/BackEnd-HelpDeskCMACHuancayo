package pe.cmac.huancayo.sistema.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;

import java.util.Optional;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query(
            value = "SELECT * FROM usuario u WHERE u.username = :username and u.password = :password",
            nativeQuery = true)
    Optional<Usuario> findUserByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

}
