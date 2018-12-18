package softbox.com.catex;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Materias extends AppCompatActivity {

    ListView lista;

    String[][] datos = {
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materias);
        getSupportActionBar().hide();// oculta la barra
        abrirActividades();
    }

    public void abrirActividades() {
        /*FloatingActionButton mas = (FloatingActionButton) findViewById(R.id.mas);
        mas.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nuevo = new Intent(getApplicationContext(), CalculoRapidoNuevo.class);//Lanzar actividad
                startActivity(nuevo);
            }
        });
        Button mas = (Button) findViewById(R.id.mas);//Id del boton y crear en java
        mas.setOnClickListener(new View.OnClickListener() {

           @Override
            public void onClick(View v) {
                Intent nuevo = new Intent(getApplicationContext(), CalculoRapidoNuevo.class);//Lanzar actividad
                startActivity(nuevo);                                            //Iniciar Actividad
            }
        });*/
    }
}
