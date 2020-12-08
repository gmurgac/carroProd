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
            "nombreLista TEXT," +
            "foto TEXT," +
            "descripcion TEXT)";
    private final String sqlCreate2 = "CREATE TABLE listas(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "nombre TEXT)";


    public ProductosDBOpenHelper(@Nullable Context context,
                                 @Nullable String name,
                                 @Nullable SQLiteDatabase.CursorFactory factory,
                                 int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(this.sqlCreate);
        sqLiteDatabase.execSQL(this.sqlCreate2);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS productos");
        sqLiteDatabase.execSQL(sqlCreate);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS listas");
        sqLiteDatabase.execSQL(sqlCreate2);
    }
}
