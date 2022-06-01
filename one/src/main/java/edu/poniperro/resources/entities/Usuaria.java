package edu.poniperro.resources.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_users")
public class Usuaria {

    @Id
    @Column(name = "user_nom")
    private String nombre;

    @Column(name = "user_prop")
    private Integer destreza;
}
