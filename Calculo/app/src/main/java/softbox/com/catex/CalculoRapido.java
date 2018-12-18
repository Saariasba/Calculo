package softbox.com.catex;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class CalculoRapido extends AppCompatActivity {
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculo_rapido);

        mAdView = findViewById(R.id.adCalculoRapido);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportActionBar().hide();// oculta la barra
        abrirActividades();

    }

    public void abrirActividades() {
        FloatingActionButton mas = (FloatingActionButton) findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nuevo = new Intent(getApplicationContext(), CalculoRapidoNuevo.class);//Lanzar actividad
                startActivity(nuevo);
            }
        });
        Button volver = (Button) findViewById(R.id.button);//Id del boton y crear en java
        volver.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
               Intent Inicio = new Intent(getApplicationContext(), Inicio.class);//Lanzar actividad
               startActivity(Inicio);
            }
        });
    }
}
