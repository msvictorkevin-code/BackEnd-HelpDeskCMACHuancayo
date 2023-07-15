package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@Entity
@Table(name = "detalle_ticket")
public class DetalleTicket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_detalle")
	@SequenceGenerator(name = "seq_detalle", sequenceName = "seq_detalle", allocationSize = 1)
	@Column(name = "id_detalle")
	private Integer id;

	@Column(name = "id_ticket")
	private Integer idTicket;

	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "estado")
	private String estado;

}
