package cl.inacap.carroprod.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class ProductosDBOpenHelper extends SQLiteOpenHelper {
    private final String sqlCreate = "CREATE TABLE productos(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "precio INTEGER," +
            "nombre TEXT," +
            "foto TEXT," +
            "descripcion TEXT)";
    public ProductosDBOpenHelper(@Nullable Context context,
                                 @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.sqlCreate);
        sqLiteDatabase.execSQL(
                "INSERT into productos(precio,nombre,foto,descripcion)" +
                        " VALUES(1000"+
                        ", 'CocaColaZero'"+
                        ", 'https://jumbo.vteximg.com.br/arquivos/ids/336744/Principal-3936.jpg?v=637237316740900000'"+
                        ",'Descripcion pulenta')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS productos");
        sqLiteDatabase.execSQL(sqlCreate);
    }
}
