package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@ToString
@Data
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
    @SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
    @Column(name = "id_usario")
    private Integer id;

    @NotNull(message = "Propiedad idColaborador requerido.")
    @Column(name = "id_Colaborador")
    private Integer idColaborador;

    @NotNull(message = "Propiedad username requerido.")
    @Size(min = 0, max = 80, message = "Propiedad username con tamaño invalido.")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Propiedad password requerido.")
    @Size(min = 0, max = 80, message = "Propiedad password con tamaño invalido.")
    @Column(name = "password")
    private String password;

    @NotNull(message = "Propiedad isAdmin requerido.")
    @Size(min = 0, max = 80, message = "Propiedad isAdmin con tamaño invalido.")
    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "nro_intentos")
    private Integer nroIntentos;

}
