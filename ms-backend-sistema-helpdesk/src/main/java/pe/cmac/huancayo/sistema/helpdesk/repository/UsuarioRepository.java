package pe.cmac.huancayo.sistema.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pe.cmac.huancayo.sistema.helpdesk.entity.Usuario;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query(value = "SELECT * FROM usuario u WHERE u.username = :username", nativeQuery = true)
	Optional<Usuario> findUserByUsername(@Param("username") String username);


	@Query(value = "SELECT * FROM usuario u WHERE u.id_usuario = :id_usuario", nativeQuery = true)
	Usuario findUsuarioById(@Param("id_usuario") Integer idUsuario);

}
