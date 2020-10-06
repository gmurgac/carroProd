package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import cl.inacap.carroprod.dto.Producto;

public class ProductoViewActivity extends AppCompatActivity {
TextView nombreProdTv;
Producto producto;
Toolbar toolbar;
TextView tituloToolbar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto_view);
        this.tituloToolbar = findViewById(R.id.titulo_toolbar_txt);
        //Referencia al toolbar
        this.toolbar = findViewById(R.id.toolbat);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        this.nombreProdTv = findViewById(R.id.nombre_prod_view_txt);

        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.producto = (Producto) getIntent().getSerializableExtra("producto");
            this.nombreProdTv.setText(producto.getDescripcion());
            this.tituloToolbar.setText(producto.getNombre());

        }


    }
}