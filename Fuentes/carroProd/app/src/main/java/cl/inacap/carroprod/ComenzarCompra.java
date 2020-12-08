package cl.inacap.carroprod;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import cl.inacap.carroprod.adapters.ProductosListAdapter;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;

public class ComenzarCompra extends AppCompatActivity {
    private Lista lista;
    private TextView tituloToolbar;
    private ListView lvCompra;
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);
    private ProductosListAdapter adapter;
    private List<Producto> productos;


    @Override
    public void onBackPressed(){

        AlertDialog dialogo = new AlertDialog
                .Builder(ComenzarCompra.this)
                .setPositiveButton("Sí, volver", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ComenzarCompra.this, MainActivity.class);
                        startActivity(intent);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("Confirmar")
                .setMessage("Seguro de terminar el proceso de compra? Se perderá todo su progreso")
                .create();
        dialogo.show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comenzar_compra);

        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.lista = (Lista) getIntent().getSerializableExtra("lista");
            this.productos = prodDAO.getAllByNombreLista(this.lista.getNombreLista());

            this.lvCompra = findViewById(R.id.productos_lv_compra);
            this.tituloToolbar = findViewById(R.id.titulo_toolbar_txt);
            this.tituloToolbar.setText("Comprando...");
            this.lvCompra = findViewById(R.id.productos_lv_compra);
            this.adapter = new ProductosListAdapter(this,R.layout.productos_list,this.productos);
            this.lvCompra.setAdapter(adapter);
        }
        this.lvCompra.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Producto producto = productos.get(i);
                                productos.remove(producto);
                                adapter.notifyDataSetChanged();


            }
        });
    }
}