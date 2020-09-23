package cl.inacap.carroprod.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.carroprod.dto.Producto;

public class ProductosDAOLista implements ProductosDAO {

    private  List<Producto> productos = new ArrayList<>();
    //Patron singleton:
    //1. Una instancia como atributo estatico de si misma
    //2 el constructor debe ser private
    //3 Debe ecistir un metodo que verigfique la existencia de la unica instancia
    //(get Instance)
    //no necesita ser static ya que solo se crea 1 vez por app.

    private static ProductosDAOLista instancia;

    public static ProductosDAOLista getInstance(){
        if(instancia == null){
            instancia = new ProductosDAOLista();
        }
        return instancia;
    }
    private ProductosDAOLista(){
        Producto p = new Producto();
        p.setNombre("Coca Cola Zero");
        p.setDescripcion("asdasdasdas");
        p.setPrecio(2000);
        p.setFoto("https://jumbo.vteximg.com.br/arquivos/ids/336744/Principal-3936.jpg?v=637237316740900000");

        productos.add(p);
        p = new Producto();
        p.setNombre("Miti miti");
        p.setDescripcion("asdasdasdas");
        p.setPrecio(50);
        p.setFoto("https://i.pinimg.com/474x/8b/3f/c0/8b3fc091e1ac70ee71594f6a54897bf7.jpg");
        productos.add(p);
        p = new Producto();
        p.setNombre("Austral Bock");
        p.setDescripcion("asdasdasdas");
        p.setPrecio(2000);
        p.setFoto("https://www.ccu.cl/wp-content/uploads/2018/03/austral-torres-del-paine.png");
        productos.add(p);
        p = new Producto();
        p.setNombre("Galletas museo");
        p.setDescripcion("asdasdasdas");
        p.setPrecio(150);
        p.setFoto("https://i.pinimg.com/originals/d4/0e/6e/d40e6e9a74a97cfde41024ac9db37115.jpg");

    }

    @Override
    public Producto save(Producto p) {
        productos.add(p);
        return p;
    }

    @Override
    public List<Producto> getAll() {
        return productos;
    }
}
