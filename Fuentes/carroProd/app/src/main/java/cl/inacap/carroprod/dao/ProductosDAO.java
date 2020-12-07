package cl.inacap.carroprod.dao;

import java.util.List;

import cl.inacap.carroprod.dto.Producto;

public interface ProductosDAO {
    Producto save(Producto p);
    List<Producto> getAll();
    Producto erase(Producto p);
}
