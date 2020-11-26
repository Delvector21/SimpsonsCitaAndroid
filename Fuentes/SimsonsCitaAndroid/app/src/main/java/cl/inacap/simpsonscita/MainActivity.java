package cl.inacap.simpsonscita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cl.inacap.simpsonscita.adapters.Personaje2Adapter;
import cl.inacap.simpsonscita.adapters.PersonajesAdapater;
import cl.inacap.simpsonscita.dto.Personaje;
import cl.inacap.simpsonscita.dto.Personaje2;

public class MainActivity extends AppCompatActivity {

    private ListView personajesLv;
    private List<Personaje> personajes = new ArrayList<>();
    //private List<Personaje2> personajes = new ArrayList<>();
    private PersonajesAdapater adapter;
    private Personaje2Adapter adapter2;
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
        this.personajesLv = findViewById(R.id.list);



        Integer[] frases = new Integer[10];
        for (int i=0;i<10;++i){
            frases[i]=i+1;
        }
        ArrayAdapter<Integer> adapterS = new ArrayAdapter<Integer>(this,
                android.R.layout.simple_spinner_item, frases);
        adapterS.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapterS);
        this.adapter = new PersonajesAdapater(this,R.layout.personaje_list,this.personajes);
        this.personajesLv.setAdapter(this.adapter);
        //this.adapter2 = new Personaje2Adapter(this,R.layout.personaje_list,this.personajes);
        //this.personajesLv.setAdapter(this.adapter2);
        this.buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int frases = (int) spin.getSelectedItem();
                String url = "https://thesimpsonsquoteapi.glitch.me/quotes?count=6";
                //String url = "https://rickandmortyapi.com/api/character";
                queue = Volley.newRequestQueue(MainActivity.this);

                JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET,
                        url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    personajes.clear();
                                    //Personaje2[] arreglo = new Gson().fromJson(response.getString("results") ,Personaje2[].class);
                                    Personaje[] arreglo = new Gson()
                                            .fromJson(response.getString("")
                                                    ,Personaje[].class);
                                    personajes.addAll(Arrays.asList(arreglo));
                                }catch (Exception ex){
                                    personajes.clear();
                                    Log.e("Personajes","Error de peticion");
                                }finally {
                                    adapter.notifyDataSetChanged();
                                    //adapter2.notifyDataSetChanged();
                                }

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        personajes.clear();
                        Log.e("Personajes","Error de peticion 2");
                        //adapter2.notifyDataSetChanged();
                        adapter.notifyDataSetChanged();
                    }
                });
                queue.add(jsonReq);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}