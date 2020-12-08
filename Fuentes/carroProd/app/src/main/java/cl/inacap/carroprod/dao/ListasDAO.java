package cl.inacap.carroprod.dao;

import java.util.List;

import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;

public interface ListasDAO {
    Lista save(Lista lista);
    List<Lista> getAll();
    Lista erase(Lista lista);
}
