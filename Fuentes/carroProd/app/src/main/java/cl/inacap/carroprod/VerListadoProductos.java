package cl.inacap.carroprod;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.carroprod.adapters.ProductosListAdapter;
import cl.inacap.carroprod.dao.ListasDAO;
import cl.inacap.carroprod.dao.ListasDAOSqLite;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;

public class VerListadoProductos extends AppCompatActivity {

    private ListView productosLv;
    private ProductosListAdapter adapter;
    private List<Producto> productos;
    private List<Lista> listas;
    private ListasDAO listDAO = new ListasDAOSqLite(this);
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);
    private FloatingActionButton agregarBtn;
    private Lista lista;
    private Toolbar toolbar;
    private TextView tituloToolbar;
    private FloatingActionButton comenzarCompraBtn;
    private FloatingActionButton eliminarBtn;

    @Override
    protected void onResume(){
        super.onResume();
        this.listas = this.listDAO.getAll();
        this.productos = this.prodDAO.getAllByNombreLista(lista.getNombreLista());
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
                Intent intent = new Intent(VerListadoProductos.this, ProductoViewActivity.class);
                intent.putExtra("producto",producto);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_listado_productos);
        this.eliminarBtn = findViewById(R.id.boton_borrar);
        this.eliminarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(VerListadoProductos.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                listDAO.erase(lista);
                                onBackPressed();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setTitle("Confirmar")
                        .setMessage("Seguro que deseas eliminar ?")
                        .create();
                dialogo.show();
            }
        });
        this.tituloToolbar = findViewById(R.id.titulo_toolbar_txt);
        //Referencia al toolbar
        this.toolbar = findViewById(R.id.toolbat);
        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.lista = (Lista) getIntent().getSerializableExtra("lista");

            this.tituloToolbar.setText(lista.getNombreLista());
        }
        this.comenzarCompraBtn = findViewById(R.id.boton_comenzar);
        this.comenzarCompraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(VerListadoProductos.this, ComenzarCompra.class);
                i.putExtra("lista", lista);
                startActivity(i);
            }
        });
        this.agregarBtn = findViewById(R.id.boton_crear_fb);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerListadoProductos.this, CrearProductoActivity.class);
                i.putExtra("lista",lista);
                startActivity(i);
            }
        });

    }
}