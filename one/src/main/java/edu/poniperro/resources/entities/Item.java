package edu.poniperro.resources.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_items")
public class Item extends PanacheEntityBase {
    @Id
    @Column(name = "item_nom")
    @NotNull
    private String nombre;

    @Column(name = "item_prop")
    private Integer quality;

    @Column(name = "item_tipo")
    private String tipo;
}
