package softbox.com.catex;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Bienvenida extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);
        getSupportActionBar().hide();// oculta la barra
        abrirActividades();
    }

    public void abrirActividades() {
        Button Boton = (Button) findViewById(R.id.button);//Id del boton y crear en java

        Boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent P1 = new Intent(getApplicationContext(), P1.class);//Lanzar actividad
                startActivity(P1);//Iniciar Actividad
            }
        });
    }

    @Override
    public void onBackPressed (){
       Toast toast1 = Toast.makeText(getApplicationContext(), "No es posible volver", Toast.LENGTH_SHORT);//El Toast que se mostrará cuando se intente volver
       toast1.show();
    }
}
