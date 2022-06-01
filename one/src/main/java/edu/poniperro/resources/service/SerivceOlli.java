package edu.poniperro.resources.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import edu.poniperro.resources.entities.Item;
import edu.poniperro.resources.entities.Orden;
import edu.poniperro.resources.entities.Usuaria;

@ApplicationScoped
public class SerivceOlli {

    public Usuaria cargaUsuaria(String nombre) {
        Optional<Usuaria> usuaria = Usuaria.findByIdOptional(nombre);
        return usuaria.isPresent() ? usuaria.get() : new Usuaria();
    }

    public Item cargaItem(String nombre) {
        Optional<Item> item = Item.findByIdOptional(nombre);
        return item.isPresent() ? item.get() : new Item();

    }

    public List<Orden> cargaOrden(String nombre) {
        List<Orden> filtrada = todasOrdenes().stream().filter(o -> o.getUser().getNombre().equals(nombre))
                .collect(Collectors.toList());
        return filtrada;
    }

    private List<Orden> todasOrdenes() {
        List<Orden> listOrden = Orden.findAll().list();
        return listOrden;

    }
}
