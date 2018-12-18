package softbox.com.catex;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class P2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p2);
        getSupportActionBar().hide();// oculta la barra
        pregunta();
        abrirActividades();
    }

    public void pregunta(){
        TextView textView = (TextView) findViewById(R.id.textView);
        SpannableString miTexto = new SpannableString("¿Tu "+Inicio.historia+" se divide en años?");
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(miTexto);

    }

    public void abrirActividades() {
        Button atras = (Button) findViewById(R.id.button);//Id del boton y crear en java ----ATRAS----
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Inicio.historia=null;
                Intent P1 = new Intent(getApplicationContext(), P1.class);//Lanzar actividad
                startActivity(P1);//Iniciar Actividad
            }
        });
        Button siguiente = (Button) findViewById(R.id.button2);//Id del boton y crear en java
        siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioButton rb1 = findViewById(R.id.RadioButton1);
                RadioButton rb2 = findViewById(R.id.RadioButton2);

                if(rb1.isChecked()){
                    Inicio.años = Long.valueOf(1);
                    Intent P3 = new Intent(getApplicationContext(), P3.class);//Lanzar actividad
                    startActivity(P3); //Iniciar Actividad
                }else if(rb2.isChecked()){
                    Inicio.años = Long.valueOf(0);
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4); //Iniciar Actividad
                }else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);
                    toast1.show();
                }

            }
        });
    }
}
