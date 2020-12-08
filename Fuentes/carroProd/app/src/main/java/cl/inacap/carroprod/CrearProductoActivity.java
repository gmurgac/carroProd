package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;
import cl.inacap.carroprod.dto.Producto;

public class CrearProductoActivity extends AppCompatActivity {
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);
    private EditText nombreProdEv;
    private Toolbar toolbar;
    private Button agregar;
    private Lista lista;
    private TextView tituloToolbar;

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_producto);
        this.nombreProdEv = findViewById(R.id.nombre_prod_edit_txt);
        this.tituloToolbar = findViewById((R.id.titulo_toolbar_txt));
        this.agregar = findViewById(R.id.registrar_btn);
        //Referencia al toolbar
        this.toolbar = findViewById(R.id.toolbat);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.lista = (Lista) getIntent().getSerializableExtra("lista");

            this.tituloToolbar.setText(lista.getNombreLista());


        }
        this.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crear el producto
                if(!nombreProdEv.getText().toString().trim().isEmpty()) {
                    Producto p = new Producto();
                    p.setNombre(nombreProdEv.getText().toString());
                    p.setFoto("https://www.nostalgica.cl/wp-content/uploads/2020/05/CAJAS.jpg");
                    p.setDescripcion("");
                    p.setPrecio(1);
                    p.setNombreLista(lista.getNombreLista());
                    //Llamar al DAO
                    prodDAO.save(p);
                    //Enviar al activity principal
                    Toast.makeText(CrearProductoActivity.this,"Añadido a la lista",Toast.LENGTH_SHORT).show();
                    nombreProdEv.setText("");
                }else{
                    Toast.makeText(CrearProductoActivity.this,"DEBE INGRESAR NOMBRE",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}