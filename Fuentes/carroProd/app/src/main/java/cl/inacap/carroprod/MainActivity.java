package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cl.inacap.carroprod.adapters.ProductosListAdapter;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOLista;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Producto;

public class MainActivity extends AppCompatActivity {
    private ListView productosLv;
    private ProductosListAdapter adapter;
    private List<Producto> productos;
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);
    private FloatingActionButton agregarBtn;

    @Override
    protected void onResume(){
        super.onResume();
        this.productos = this.prodDAO.getAll();
        this.productosLv = findViewById(R.id.productos_lv);
        this.adapter = new ProductosListAdapter(this,R.layout.productos_list,this.productos);
        this.productosLv.setAdapter(this.adapter);
        //Agregar un listener a los elementos de la lista
        this.productosLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Producto producto = productos.get(i);

                //1. Como lo mando al otro activity
                //2 como hago que vaya al otro activity
                //Inten recive desde donde viene(contexto), hacia donde va (contexto)
                Intent intent = new Intent(MainActivity.this, ProductoViewActivity.class);
                intent.putExtra("producto",producto);
                startActivity(intent);
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbat));
        this.agregarBtn = findViewById(R.id.boton_crear_fb);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CrearProductoActivity.class);
                startActivity(i);
            }
        });

    }
}


//TODO: generar Lista de listados de mercaderia
//TODO: seleccionar listado, comenzar compra y borrar productos de la memoria volatil, (DAO)
//TODO: el listado persiste en la db