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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class P6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p6);
        getSupportActionBar().hide();// oculta la barra
        pregunta();
        abrirActividades();
    }

    public void pregunta(){
        TextView textView = (TextView) findViewById(R.id.textView);
        SpannableString miTexto = new SpannableString("Danos tu rango de notas");
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(miTexto);

    }

    public void abrirActividades() {
        Button atras = (Button) findViewById(R.id.button);//Id del boton y crear en java ----ATRAS----
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Inicio.cantidad == 0){
                    Inicio.periodo = null;
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else{
                    Inicio.cantidad= Long.valueOf(0);
                    Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                    startActivity(P5);//Iniciar Actividad
                }
            }
        });
        Button siguiente = (Button) findViewById(R.id.button2);//Id del boton y crear en java
        siguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText texto2 = findViewById(R.id.editText2);
                EditText texto3 = findViewById(R.id.editText3);
                EditText texto4 = findViewById(R.id.editText4);

                if(texto2.getText().toString().length() == 0 || texto3.getText().toString().length() == 0 || texto4.getText().toString().length() == 0){
                   Toast toast1 = Toast.makeText(getApplicationContext(), "Llene todos los espacios", Toast.LENGTH_SHORT);
                   toast1.show();
                }else{
                    Inicio.min = Double.parseDouble(texto2.getText().toString());
                    Inicio.max = Double.parseDouble(texto3.getText().toString());
                    Inicio.apb = Double.parseDouble(texto4.getText().toString());
                    Intent P10 = new Intent(getApplicationContext(), Despedida.class);//Lanzar actividad
                    startActivity(P10);//Iniciar Actividad
                }
            }
        });
    }
}
