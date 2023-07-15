package pe.cmac.huancayo.sistema.helpdesk.entity;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@ToString
@Data
@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_colaborador")
    @SequenceGenerator(name = "seq_colaborador", sequenceName = "seq_colaborador", allocationSize = 1)
    @Column(name = "id_Colaborador")
    private Integer id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "codEmpleado")
    private String codEmpleado;

    @Column(name = "area")
    private String area;

    @Column(name = "is_activo")
    private Boolean isActivo;
}
