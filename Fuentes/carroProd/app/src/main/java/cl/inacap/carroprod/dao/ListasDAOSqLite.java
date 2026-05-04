package cl.inacap.carroprod.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;
import cl.inacap.carroprod.helpers.ListasDBOpenHelper;
import cl.inacap.carroprod.helpers.ProductosDBOpenHelper;

public class ListasDAOSqLite implements ListasDAO {


    private ProductosDBOpenHelper db;

    public ListasDAOSqLite(Context contexto){
        this.db = new ProductosDBOpenHelper(contexto,
                "DBProductos",
                null,
                3);

    }
    @Override
    public Lista save(Lista lista) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO listas(" +
                        "nombre, fecha, hora) VALUES ('%s', '%s', '%s')"
                ,lista.getNombreLista(), lista.getFechaCreacion(), lista.getHoraCreacion());
        writer.execSQL(sql);
        writer.close();

        return null;
    }

    @Override
    public List<Lista> getAll() {

        SQLiteDatabase reader = this.db.getReadableDatabase();
        List<Lista> listas = new ArrayList<>();
        try{

            if(reader != null){
                Cursor c = reader.rawQuery("SELECT id, nombre, fecha, hora FROM listas",null);
                if(c.moveToFirst()){
                    do{
                        Lista p = new Lista();
                        p.setId(c.getInt(0));
                        p.setNombreLista(c.getString(1));
                        p.setFechaCreacion(c.getString(2));
                        p.setHoraCreacion(c.getString(3));

                        listas.add(p);
                    }while(c.moveToNext());
                }
                reader.close();
            }

        }catch(Exception ex){
            listas = null;
        }
        return listas;
    }

    @Override
    public Lista erase(Lista lista) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("DELETE FROM listas " +
                "WHERE id=%d", lista.getId());
        String sql2 = String.format("DELETE FROM productos " +
                "WHERE nombreLista='%s'", lista.getNombreLista());
        writer.execSQL(sql);
        writer.execSQL(sql2);
        writer.close();


        return null;
    }

    @Override
    public void update(Lista l) {
        SQLiteDatabase writer = this.db.getWritableDatabase();

        // Si el nombre de la lista cambia, debemos actualizar también los productos asociados
        // Primero obtenemos el nombre antiguo
        SQLiteDatabase reader = this.db.getReadableDatabase();
        String nombreAntiguo = "";
        Cursor c = reader.rawQuery("SELECT nombre FROM listas WHERE id=" + l.getId(), null);
        if (c.moveToFirst()) {
            nombreAntiguo = c.getString(0);
        }
        c.close();

        String sql = String.format("UPDATE listas SET " +
                        "nombre='%s', fecha='%s', hora='%s' " +
                        "WHERE id=%d",
                l.getNombreLista(), l.getFechaCreacion(), l.getHoraCreacion(), l.getId());
        writer.execSQL(sql);

        if (!nombreAntiguo.isEmpty() && !nombreAntiguo.equals(l.getNombreLista())) {
            String sqlProductos = String.format("UPDATE productos SET nombreLista='%s' WHERE nombreLista='%s'",
                    l.getNombreLista(), nombreAntiguo);
            writer.execSQL(sqlProductos);
        }

        writer.close();
    }
}
