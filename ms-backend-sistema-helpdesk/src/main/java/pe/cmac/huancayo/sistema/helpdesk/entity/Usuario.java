package pe.cmac.huancayo.sistema.helpdesk.entity;


import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;

@ToString
@Data
@Entity
@Table(name = "usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_usuario")
	@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1)
	@Column(name = "id_usuario")
	private Integer id;

	@Column(name = "id_Colaborador")
	private Integer idColaborador;


	@Column(name = "username")
	private String username;


	@Column(name = "password")
	private String password;


	@Column(name = "is_admin",columnDefinition = "boolean default true")
	private Boolean isAdmin;

	@Column(name = "nro_intentos")
	private Integer nroIntentos;

}
