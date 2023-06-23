package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ticket")
	@SequenceGenerator(name = "seq_ticket", sequenceName = "seq_ticket", allocationSize = 1)
	@Column(name = "id_ticket")
	private Integer id;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
	private String estado;

	@Column(name = "fecha_generada")
	private Date fechaGenerada;

	@Column(name = "fecha_cierre")
	private Date fechaCierre;

	@Column(name = "prioridad")
	private Integer prioridad;

	@Column(name = "id_tipo")
	private Integer idTipo;

	@Column(name = "id_categoria")
	private Integer idCategoria;

	@Column(name = "id_usuario")
	private Integer idUsuario;
}
