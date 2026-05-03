package cl.inacap.carroprod;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Producto;

public class ProductoViewActivity extends AppCompatActivity {
    private EditText nombreProdEt, fechaProdEt, horaProdEt;
    private Producto producto;
    private Toolbar toolbar;
    private TextView tituloToolbar;
    private ImageView imagenView;
    private Button btnBorrar, btnActualizar;
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
        
        this.nombreProdEt = findViewById(R.id.nombre_prod_edit_txt);
        this.fechaProdEt = findViewById(R.id.fecha_prod_edit_txt);
        this.horaProdEt = findViewById(R.id.hora_prod_edit_txt);
        this.imagenView = findViewById(R.id.imagen_view_prod);
        this.btnBorrar = findViewById(R.id.btn_borrar);
        this.btnActualizar = findViewById(R.id.btn_actualizar);

        this.btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nuevoNombre = nombreProdEt.getText().toString().trim();
                String nuevaFecha = fechaProdEt.getText().toString().trim();
                String nuevaHora = horaProdEt.getText().toString().trim();

                if (!nuevoNombre.isEmpty() && !nuevaFecha.isEmpty() && !nuevaHora.isEmpty()) {
                    producto.setNombre(nuevoNombre);
                    producto.setFechaCreacion(nuevaFecha);
                    producto.setHoraCreacion(nuevaHora);
                    
                    prodDAO.update(producto);
                    Toast.makeText(ProductoViewActivity.this, "Producto actualizado correctamente", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ProductoViewActivity.this, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
                }
            }
        });

        this.btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialogo = new AlertDialog
                        .Builder(ProductoViewActivity.this)
                        .setPositiveButton("Sí, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                prodDAO.erase(producto);
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

        if(getIntent().getExtras() != null){
            //El pasaje de avion venia con yapa,,,,, me enviaron algo en el intent
            this.producto = (Producto) getIntent().getSerializableExtra("producto");
            this.nombreProdEt.setText(producto.getNombre());
            this.fechaProdEt.setText(producto.getFechaCreacion());
            this.horaProdEt.setText(producto.getHoraCreacion());
            this.tituloToolbar.setText(producto.getNombre());

            Picasso.get().load(this.producto.getFoto())
                    .resize(300,300)
                    .centerCrop()
                    .into(this.imagenView);
        }


    }
}