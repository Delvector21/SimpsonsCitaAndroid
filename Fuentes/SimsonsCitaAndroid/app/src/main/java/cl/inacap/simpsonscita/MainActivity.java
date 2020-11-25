package cl.inacap.simpsonscita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import java.util.List;

import cl.inacap.simpsonscita.adapters.PersonajesAdapater;
import cl.inacap.simpsonscita.dto.Personaje;

public class MainActivity extends AppCompatActivity {

    private ListView personajesLv;
    private List<Personaje> personajes;
    private PersonajesAdapater adapter;
    private Spinner spin;
    private Button buton;
    private RequestQueue queue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setSupportActionBar(findViewById(R.id.toolbar));

        this.spin = findViewById(R.id.spinner);
        this.buton = findViewById(R.id.cita_btn);


        Integer[] frases = new Integer[10];
        for (int i=0;i<10;++i){
            frases[i]=i+1;
        }
        ArrayAdapter<Integer> adapterS = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, frases);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterS);




    }

    @Override
    protected void onResume() {
        super.onResume();
        queue = Volley.newRequestQueue(this);

        this.personajesLv = findViewById(R.id.list);
        this.adapter = new PersonajesAdapater(this,R.layout.personaje_list,this.personajes);
        this.personajesLv.setAdapter(this.adapter);
        JsonObjectRequest jsonReq = new JsonObjectRequest(
                Request.Method.GET,
        )

    }
}