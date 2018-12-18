package softbox.com.catex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Despedida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despedida);
        getSupportActionBar().hide();// oculta la barra
        abrirActividades();
    }

    public void abrirActividades() {
        Button Boton = (Button) findViewById(R.id.button);//Id del boton y crear en java

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Inicio = new Intent(getApplicationContext(), Inicio.class);//Lanzar actividad
                startActivity(Inicio);//Iniciar Actividad
            }
        });
    }
}
