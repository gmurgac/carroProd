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
                1);

    }
    @Override
    public Lista save(Lista lista) {
        SQLiteDatabase writer = this.db.getWritableDatabase();
        String sql = String.format("INSERT INTO listas(" +
                        "nombre) VALUES ('%s')"
                ,lista.getNombreLista());
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
                Cursor c = reader.rawQuery("SELECT nombre FROM listas",null);
                if(c.moveToFirst()){
                    do{
                        Lista p = new Lista();
                        p.setNombreLista(c.getString(0));

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
                "WHERE nombre='"+lista.getNombreLista()+"'");
        String sql2 = String.format("DELETE FROM productos " +
                "WHERE nombreLista='"+lista.getNombreLista()+"'");
        writer.execSQL(sql);
        writer.execSQL(sql2);
        writer.close();


        return null;
    }
}
