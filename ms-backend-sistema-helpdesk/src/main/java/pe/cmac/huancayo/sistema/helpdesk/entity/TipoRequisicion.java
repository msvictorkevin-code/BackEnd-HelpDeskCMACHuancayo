package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "tipo_requisicion")
public class TipoRequisicion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_requisicion")
	@SequenceGenerator(name = "seq_tipo_requisicion", sequenceName = "seq_tipo_requisicion", allocationSize = 1)
	@Column(name = "id_tipo")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "descripcion")
	private String descripcion;
}
