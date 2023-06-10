package pe.cmac.huancayo.sistema.helpdesk.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pe.cmac.huancayo.sistema.helpdesk.dto.RestResponse;
import pe.cmac.huancayo.sistema.helpdesk.dto.UsuarioDTORequest;
import pe.cmac.huancayo.sistema.helpdesk.dto.UsuarioDTOResponse;
import pe.cmac.huancayo.sistema.helpdesk.repository.UsuarioRepository;

@Slf4j
//@Repository
public class UsuarioRepositoryImpl  {





 public RestResponse<UsuarioDTOResponse> autenticar(UsuarioDTORequest request) {
      /*     try {
            Optional<Usuario> optUsuario = usuarioDAO.findUserByUsernameAndPassword(request.getUsername(), request.getPassword());
            RestResponse<UsuarioDTOResponse> response = new RestResponse<>();
            UsuarioDTOResponse usuarioDTOResponse = new UsuarioDTOResponse();
            if (optUsuario.isPresent()) {
                Usuario usuario = optUsuario.get();
                if (usuario.getNroIntentos() >= Constantes.MAX_INTENTOS) {
                    response.setData(null);
                    response.setStatus(true);
                    response.setMessage(Constantes.MSG_INTENTOS_SUPERADOS);
                    return response;
                }

                // acceder
                if (request.getUsername().equalsIgnoreCase(usuario.getUsername()) &&
                        request.getPassword().equalsIgnoreCase(usuario.getPassword())) {
                    usuarioDTOResponse.setAcceso(true);
                    response.setData(null);
                    response.setStatus(true);
                    response.setMessage(String.format(Constantes.MSG_CON_ACCESO, usuario.getUsername()));
                    return response;

                    // rechazar y bloquear
                } else {

                    usuario.setNroIntentos(usuario.getNroIntentos() + 1);
                    usuarioDAO.save(usuario);
                    usuarioDTOResponse.setAcceso(false);
                    response.setStatus(true);
                    response.setMessage(String.format(Constantes.MSG_SIN_ACCESO, usuario.getUsername()));
                    return response;
                }
            }
        } catch (Exception ex) {
            log.error("Error" + ex.getMessage());
        }*/
        return null;
    }
}
