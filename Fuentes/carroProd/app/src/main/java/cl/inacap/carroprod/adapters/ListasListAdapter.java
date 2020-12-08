package cl.inacap.carroprod.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.List;

import cl.inacap.carroprod.R;
import cl.inacap.carroprod.dto.Lista;


public class ListasListAdapter extends ArrayAdapter<Lista> {
        private List<Lista> listas;
        private Activity activity;



        public ListasListAdapter(@NonNull Activity context, int resource, @NonNull List<Lista> objects) {
            super(context, resource, objects);
            this.listas = objects;
            this.activity = context;


        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = this.activity.getLayoutInflater();
            View fila = inflater.inflate(R.layout.listas_list,null,true);
            //Aqui cargar el contenido del layout:
            TextView nombreTv = fila.findViewById(R.id.nombre_lista);

            Lista actual = listas.get(position);
            nombreTv.setText(actual.getNombreLista());

            return fila;
        }
    }


