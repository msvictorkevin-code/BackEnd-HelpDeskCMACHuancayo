package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@Entity
@Table(name = "categoria_requisicion")
public class CategoriaRequisicion {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_categoria_requisicion")
	@SequenceGenerator(name = "seq_categoria_requisicion", sequenceName = "seq_categoria_requisicion", allocationSize = 1)
	@Column(name = "id_categoria")
	private Integer id;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "id_tipo")
	private Integer idTipo;

	@Column(name = "descripcion")
	private String descripcion;
}
