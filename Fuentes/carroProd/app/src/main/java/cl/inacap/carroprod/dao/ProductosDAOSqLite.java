package cl.inacap.carroprod.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.carroprod.dto.Producto;
import cl.inacap.carroprod.helpers.ProductosDBOpenHelper;

public class ProductosDAOSqLite implements ProductosDAO {

    private ProductosDBOpenHelper db;

    public ProductosDAOSqLite(Context contexto){
        this.db = new ProductosDBOpenHelper(contexto,
                "DBProductos",
                null,
                3);

    }



    @Override
    public Producto save(Producto p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO productos(" +
                "precio,nombre,foto,descripcion,nombreLista,fecha,hora) VALUES ('%d','%s','%s','%s','%s','%s','%s')"
                ,p.getPrecio(),p.getNombre(),p.getFoto(),p.getDescripcion(),p.getNombreLista(),p.getFechaCreacion(),p.getHoraCreacion());
                writer.execSQL(sql);
        writer.close();
        return null;
    }

    @Override
    public List<Producto> getAll() {

        SQLiteDatabase reader = this.db.getReadableDatabase();
        List<Producto> productos = new ArrayList<>();
        try{

            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,precio,nombre,foto" +
                        ",descripcion,fecha,hora FROM productos",null);
            if(c.moveToFirst()){
                do{
                    Producto p = new Producto();
                    p.setIdProducto(c.getInt(0));
                    p.setPrecio(c.getInt(1));
                    p.setNombre(c.getString(2));
                    p.setFoto(c.getString(3));
                    p.setDescripcion(c.getString(4));
                    p.setFechaCreacion(c.getString(5));
                    p.setHoraCreacion(c.getString(6));
                    productos.add(p);
                }while(c.moveToNext());
            }
            reader.close();
            }

        }catch(Exception ex){
            productos = null;
        }
        return productos;


    }

    @Override
    public List<Producto> getAllByNombreLista(String nombreLista) {

        SQLiteDatabase reader = this.db.getReadableDatabase();
        List<Producto> productos = new ArrayList<>();
        try{

            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id,precio,nombre,foto" +
                        ",descripcion,fecha,hora FROM productos WHERE nombreLista='"+nombreLista+"'",null);
                if(c.moveToFirst()){
                    do{
                        Producto p = new Producto();
                        p.setIdProducto(c.getInt(0));
                        p.setPrecio(c.getInt(1));
                        p.setNombre(c.getString(2));
                        p.setFoto(c.getString(3));
                        p.setDescripcion(c.getString(4));
                        p.setFechaCreacion(c.getString(5));
                        p.setHoraCreacion(c.getString(6));
                        productos.add(p);
                    }while(c.moveToNext());
                }
                reader.close();
            }

        }catch(Exception ex){
            productos = null;
        }
        return productos;


    }

    @Override
    public Producto erase(Producto p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("DELETE FROM productos " +
                        "WHERE id=%d", p.getIdProducto());
        writer.execSQL(sql);
        writer.close();


        return null;
    }

    @Override
    public void update(Producto p) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("UPDATE productos SET " +
                        "nombre='%s', fecha='%s', hora='%s' " +
                        "WHERE id=%d",
                p.getNombre(), p.getFechaCreacion(), p.getHoraCreacion(), p.getIdProducto());
        writer.execSQL(sql);
        writer.close();
    }
}
