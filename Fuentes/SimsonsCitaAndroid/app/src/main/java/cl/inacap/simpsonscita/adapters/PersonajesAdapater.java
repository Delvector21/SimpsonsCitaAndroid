package cl.inacap.simpsonscita.adapters;

import android.app.Activity;
import android.content.Context;
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

import cl.inacap.simpsonscita.R;
import cl.inacap.simpsonscita.dto.Personaje;

public class PersonajesAdapater extends ArrayAdapter<Personaje> {

    private Activity activity;
    private List<Personaje> personajes;

    public PersonajesAdapater(@NonNull Activity context, int resource, @NonNull List<Personaje> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.personajes = objects;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.personaje_list,null,true);

        TextView nombreTxt = rowView.findViewById(R.id.nombreVw);
        TextView citaTxt = rowView.findViewById(R.id.citaVw);
        ImageView image = rowView.findViewById(R.id.imageVw);

        nombreTxt.setText(personajes.get(position).getNombre());
        citaTxt.setText(personajes.get(position).getCita());
        Picasso.get().load(this.personajes.get(position).getImage()).resize(300,300)
                .centerCrop().into(image);

        return rowView;




    }
}
