package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Producto;

public class CrearProductoActivity extends AppCompatActivity {
    private ProductosDAO prodDAO = new ProductosDAOSqLite(this);
    private EditText nombreProdEv;
    private EditText precioProdEv;
    private EditText descripcionProdEv;
    private EditText urlFoto;
    private Toolbar toolbar;
    private Button agregar;

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
        this.precioProdEv = findViewById(R.id.precio_editTxt);
        this.descripcionProdEv = findViewById(R.id.descripcion_ev);
        this.urlFoto = findViewById(R.id.url_foto);
        this.agregar = findViewById(R.id.registrar_btn);
        //Referencia al toolbar
        this.toolbar = findViewById(R.id.toolbat);
        this.setSupportActionBar(this.toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);

        this.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Crear el producto
                Producto p = new Producto();
                p.setNombre(nombreProdEv.getText().toString());
                p.setFoto(urlFoto.getText().toString());
                p.setDescripcion(descripcionProdEv.getText().toString());
                p.setPrecio(Integer.parseInt(precioProdEv.getText().toString()));

                //Llamar al DAO
                prodDAO.save(p);
                //Enviar al activity principal
                startActivity(new Intent(CrearProductoActivity.this,MainActivity.class));

            }
        });
    }
}