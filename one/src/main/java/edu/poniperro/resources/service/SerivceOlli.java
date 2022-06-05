package edu.poniperro.resources.service;

import java.util.ArrayList;
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

    public Orden comanda(String nombreUser, String nombreItem) {
        Optional<Usuaria> usuaria = getOptionalUsuaria(nombreUser);
        Optional<Item> item = getOptionaItem(nombreItem);
        if (usuaria.isPresent() && item.isPresent() && comprobarCalidad(usuaria, item)) {
            Orden orden = new Orden(usuaria.get(), item.get());
            orden.persist();
            return orden;
        } else {
            return null;
        }
    }

    private boolean comprobarCalidad(Optional<Usuaria> user, Optional<Item> item) {
        return user.get().getDestreza() >= item.get().getQuality();
    }

    private Optional<Usuaria> getOptionalUsuaria(String nombre) {
        return Usuaria.findByIdOptional(nombre);
    }

    private Optional<Item> getOptionaItem(String nombre) {
        return Item.findByIdOptional(nombre);
    }

    public List<Orden> comandaMultiple(String nombre, List<String> items) {
        Optional<Usuaria> usuaria = getOptionalUsuaria(nombre);
        List<Orden> listaOrdenes = new ArrayList<Orden>();
        if (usuaria.isEmpty()) {
            return listaOrdenes;
        }
        for (String item : items) {
            Orden orden = comanda(nombre, item);
            if (orden != null) {
                listaOrdenes.add(orden);
            }
        }
        return listaOrdenes;

    }
}
