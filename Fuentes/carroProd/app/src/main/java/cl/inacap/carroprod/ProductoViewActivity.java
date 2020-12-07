package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cl.inacap.carroprod.dto.Producto;

public class ProductoViewActivity extends AppCompatActivity {
    private TextView nombreProdTv;
    private Producto producto;
    private Toolbar toolbar;
    private TextView tituloToolbar;
    private TextView precioTv;
    private TextView descripcionTv;
    private ImageView imagenView;


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
        this.precioTv = findViewById(R.id.precio_tv);
        this.descripcionTv = findViewById(R.id.descripcion_tv);
        this.imagenView = findViewById(R.id.imagen_view_prod);

        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.producto = (Producto) getIntent().getSerializableExtra("producto");
            this.nombreProdTv.setText(producto.getNombre());
            this.tituloToolbar.setText(producto.getNombre());
            this.precioTv.setText(""+producto.getPrecio());
            this.descripcionTv.setText(producto.getDescripcion());
            Picasso.get().load(this.producto.getFoto())
                    .resize(300,300)
                    .centerCrop()
                    .into(this.imagenView);
        }
//TODO: cargar datos del producto al carro

    }
}