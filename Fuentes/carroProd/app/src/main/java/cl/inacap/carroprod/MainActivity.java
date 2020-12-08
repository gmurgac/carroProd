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

import cl.inacap.carroprod.adapters.ListasListAdapter;
import cl.inacap.carroprod.adapters.ProductosListAdapter;
import cl.inacap.carroprod.dao.ListasDAO;
import cl.inacap.carroprod.dao.ListasDAOSqLite;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOLista;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;

public class MainActivity extends AppCompatActivity {

    private ListView listasLv;
    private ListasListAdapter adapter;
    private List<Lista> listas;
    private ListasDAO listasDAO = new ListasDAOSqLite(this);
    private FloatingActionButton agregarBtn;


    @Override
    protected void onResume() {
        super.onResume();
        this.listas = this.listasDAO.getAll();
        this.listasLv = findViewById(R.id.listas_lv);
        this.adapter = new ListasListAdapter(this,R.layout.listas_list,this.listas);
        this.listasLv.setAdapter(this.adapter);
        //Agregar un listener a los elementos de la lista
        this.listasLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Lista lista = listas.get(i);

                //1. Como lo mando al otro activity
                //2 como hago que vaya al otro activity
                //Inten recive desde donde viene(contexto), hacia donde va (contexto)
                Intent intent = new Intent(MainActivity.this, VerListadoProductos.class);
                intent.putExtra("lista",lista);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setSupportActionBar((Toolbar) findViewById(R.id.toolbat));
        this.agregarBtn = findViewById(R.id.boton_crear_lista);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, CrearLista.class);
                startActivity(i);
            }
        });

    }
}


//TODO: generar Lista de listados de mercaderia
//TODO: seleccionar listado, comenzar compra y borrar productos de la memoria volatil, (DAO)
//TODO: el listado persiste en la db