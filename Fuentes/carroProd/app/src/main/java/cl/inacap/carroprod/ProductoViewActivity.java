package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Producto;

public class ProductoViewActivity extends AppCompatActivity {
    private TextView nombreProdTv;
    private Producto producto;
    private Toolbar toolbar;
    private TextView tituloToolbar;
    private ImageView imagenView;
    private Button btnBorrar;
    private List<Producto> productos;
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume(){
        super.onResume();
        this.productos = this.prodDAO.getAll();
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

        this.imagenView = findViewById(R.id.imagen_view_prod);
        this.btnBorrar = findViewById(R.id.btn_borrar);
        this.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodDAO.erase(producto);
                startActivity(new Intent(ProductoViewActivity.this,MainActivity.class));
            }
        });

        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.producto = (Producto) getIntent().getSerializableExtra("producto");
            this.nombreProdTv.setText(producto.getNombre());
            this.tituloToolbar.setText(producto.getNombre());

            Picasso.get().load(this.producto.getFoto())
                    .resize(300,300)
                    .centerCrop()
                    .into(this.imagenView);
        }


    }
}