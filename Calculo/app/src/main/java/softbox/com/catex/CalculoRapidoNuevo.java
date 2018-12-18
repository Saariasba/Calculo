package softbox.com.catex;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalculoRapidoNuevo extends AppCompatActivity {
    private AdView mAdView;

    public ListView items;
    private AdaptadorCalculoRapidoNuevo adaptador;
    public static ArrayList<CalculoRapidoNuevoItem> listItems;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_rapido_nuevo);
        getSupportActionBar().hide();// oculta la barra
        lista();
        abrirActividades();

        mAdView = findViewById(R.id.adCalculoRapidoNuevo);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        db = FirebaseFirestore.getInstance();
    }

    private void lista() {
        listItems = new ArrayList<CalculoRapidoNuevoItem>();
        items = (ListView) findViewById(R.id.listaCalculoRapidoNuevo);
        adaptador = new AdaptadorCalculoRapidoNuevo(this,listItems);
        items.setAdapter(adaptador);
    }


    public void abrirActividades() {
        FloatingActionButton mas = (FloatingActionButton) findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int cantidadItem = listItems.size();
                CalculoRapidoNuevoItem itemNuevo = new CalculoRapidoNuevoItem();
                listItems.add(itemNuevo);
                //agregarValor(itemNuevo,cantidadItem);
                adaptador.notifyDataSetChanged();
                //Intent nuevo = new Intent(getApplicationContext(), CalculoRapidoNuevo.class);//Lanzar actividad
                //startActivity(nuevo);
            }
        });
        Button volver = (Button) findViewById(R.id.button);//Id del boton y crear en java
        volver.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent CalculoRapido = new Intent(getApplicationContext(), CalculoRapido.class);//Lanzar actividad
                startActivity(CalculoRapido);
            }
        });

        items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        items.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int posicion=i;

                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(CalculoRapidoNuevo.this);
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿ Elimina este teléfono ?");
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        listItems.remove(posicion);
                        adaptador.notifyDataSetChanged();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show();

                return false;
            }
        });
    }

   /* private void agregarValor(CalculoRapidoNuevoItem calculoRapidoNuevoItem, int cantidadItem) {
        // Create a new user with a first and last name
        Map<String, Object> item = new HashMap<>();
        cantidadItem++;
        String id = Integer.toString(cantidadItem);
        item.put("uno", "otro");

// Add a new document with a generated ID
        db.collection("items")
                .add(item)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getApplicationContext(),"Agregado con Exito",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Error agregando nota a Firestore",Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
}
