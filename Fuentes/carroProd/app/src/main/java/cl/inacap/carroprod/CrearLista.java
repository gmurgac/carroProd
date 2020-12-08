package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import cl.inacap.carroprod.dao.ListasDAO;
import cl.inacap.carroprod.dao.ListasDAOSqLite;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;

public class CrearLista extends AppCompatActivity {

    private ListasDAO listasDAO = new ListasDAOSqLite(this);
    private EditText nombreListaEv;
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
        setContentView(R.layout.activity_crear_lista2);
        this.nombreListaEv = findViewById(R.id.nombre_lista_edit_txt);
        this.agregar = findViewById(R.id.registrar_btn_list);
        this.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombreListaEv.getText().toString().trim().isEmpty()){
                    Lista l = new Lista();
                    l.setNombreLista(nombreListaEv.getText().toString().trim());
                    listasDAO.save(l);
                    startActivity(new Intent(CrearLista.this,MainActivity.class));
                }else{
                    Toast.makeText(CrearLista.this,"DEBE INGRESAR NOMBRE",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}