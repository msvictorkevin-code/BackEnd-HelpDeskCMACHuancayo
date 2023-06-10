package pe.cmac.huancayo.sistema.helpdesk.entity;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;


@ToString
@Data
@Entity
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_colaborador")
    @SequenceGenerator(name = "id_Colaborador", sequenceName = "seq_colaborador", allocationSize = 1)
    @Column(name = "id_Colaborador")
    private Integer id;

    @NotNull(message = "Propiedad nombres requerido.")
    @Size(min = 0, max = 80, message = "Propiedad nombres con tamaño invalido.")
    @Column(name = "nombres")
    private String nombres;

    @NotNull(message = "Propiedad apellidos requerido.")
    @Size(min = 0, max = 80, message = "Propiedad apellidos con tamaño invalido.")
    @Column(name = "apellidos")
    private String apellidos;

    @NotNull(message = "Propiedad codEmpleado requerido.")
    @Size(min = 0, max = 40, message = "Propiedad codEmpleado con tamaño invalido.")
    @Column(name = "codEmpleado")
    private String codEmpleado;

    @NotNull(message = "Propiedad area requerido.")
    @Size(min = 0, max = 50, message = "Propiedad area con tamaño invalido.")
    @Column(name = "area")
    private String area;
}
