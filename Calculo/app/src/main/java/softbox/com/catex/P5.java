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

public class P5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5);
        getSupportActionBar().hide();// oculta la barra
        pregunta();
        abrirActividades();
    }

    public void pregunta(){
        TextView textView = (TextView) findViewById(R.id.textView);
        SpannableString miTexto = new SpannableString("¿Cuántos '"+Inicio.periodo+"' hay en tu año?");
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
                Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                startActivity(P4);//Iniciar Actividad
            }
        });
        Button siguiente = (Button) findViewById(R.id.button2);//Id del boton y crear en java
        siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RadioButton rb1 = findViewById(R.id.RadioButton1);
                RadioButton rb2 = findViewById(R.id.RadioButton2);
                RadioButton rb3 = findViewById(R.id.RadioButton3);
                RadioButton rb4 = findViewById(R.id.RadioButton4);
                RadioButton rb5 = findViewById(R.id.RadioButton5);
                RadioButton rb6 = findViewById(R.id.RadioButton6);

                if(rb1.isChecked()){
                    Inicio.cantidad = Long.valueOf(1);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else if(rb2.isChecked()){
                    Inicio.cantidad = Long.valueOf(2);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else if(rb3.isChecked()){
                    Inicio.cantidad = Long.valueOf(3);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else if(rb4.isChecked()){
                    Inicio.cantidad = Long.valueOf(4);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else if(rb5.isChecked()){
                    Inicio.cantidad = Long.valueOf(5);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else if(rb6.isChecked()){
                    Inicio.cantidad = Long.valueOf(6);
                    Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                    startActivity(P6); //Iniciar Actividad
                }else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);
                    toast1.show();
                }

            }
        });
    }
}
