package cl.inacap.carroprod;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cl.inacap.carroprod.dao.ListasDAO;
import cl.inacap.carroprod.dao.ListasDAOSqLite;
import cl.inacap.carroprod.dao.ProductosDAO;
import cl.inacap.carroprod.dao.ProductosDAOSqLite;
import cl.inacap.carroprod.dto.Lista;

public class CrearLista extends AppCompatActivity {

    private ListasDAO listasDAO = new ListasDAOSqLite(this);
    private EditText nombreListaEv;
    private EditText fechaListaEv;
    private EditText horaListaEv;
    private Toolbar toolbar;
    private Button agregar;
    private Lista listaAEditar;

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
        this.fechaListaEv = findViewById(R.id.fecha_lista_edit_txt);
        this.horaListaEv = findViewById(R.id.hora_lista_edit_txt);
        this.agregar = findViewById(R.id.registrar_btn_list);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey("lista")) {
            this.listaAEditar = (Lista) getIntent().getSerializableExtra("lista");
            this.nombreListaEv.setText(this.listaAEditar.getNombreLista());
            this.fechaListaEv.setText(this.listaAEditar.getFechaCreacion());
            this.horaListaEv.setText(this.listaAEditar.getHoraCreacion());
            this.agregar.setText("Guardar Cambios");
        } else {
            // Si es nueva, podemos autocompletar fecha y hora
            String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
            String hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
            this.fechaListaEv.setText(fecha);
            this.horaListaEv.setText(hora);
        }

        this.agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!nombreListaEv.getText().toString().trim().isEmpty()){
                    if (listaAEditar == null) {
                        Lista l = new Lista();
                        l.setNombreLista(nombreListaEv.getText().toString().trim());
                        l.setFechaCreacion(fechaListaEv.getText().toString().trim());
                        l.setHoraCreacion(horaListaEv.getText().toString().trim());
                        listasDAO.save(l);
                    } else {
                        listaAEditar.setNombreLista(nombreListaEv.getText().toString().trim());
                        listaAEditar.setFechaCreacion(fechaListaEv.getText().toString().trim());
                        listaAEditar.setHoraCreacion(horaListaEv.getText().toString().trim());
                        listasDAO.update(listaAEditar);
                    }
                    startActivity(new Intent(CrearLista.this,MainActivity.class));
                }else{
                    Toast.makeText(CrearLista.this,"DEBE INGRESAR NOMBRE",Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}