package edu.poniperro.resources.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;

import edu.poniperro.resources.entities.Usuaria;

@ApplicationScoped
public class SerivceOlli {

    public Usuaria cargaUsuaria(String nombre) {
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre);
        return usuaria.isPresent() ? usuaria.get() : new Usuaria();
    }
}
