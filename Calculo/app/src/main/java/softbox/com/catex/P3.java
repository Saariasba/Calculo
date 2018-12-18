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
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class P3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p3);
        getSupportActionBar().hide();// oculta la barra
        pregunta();
        abrirActividades();
    }
    public void pregunta(){
        TextView textView = (TextView) findViewById(R.id.textView);
        SpannableString miTexto = new SpannableString("¿Cúal es el año actual?");
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(miTexto);

    }

    public void abrirActividades() {
        Button atras = (Button) findViewById(R.id.button);//Id del boton y crear en java  ----ATRAS----
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Inicio.años=Long.valueOf(0);
                Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                startActivity(P2);//Iniciar Actividad
            }
        });
        Button siguiente = (Button) findViewById(R.id.button2);//Id del boton y crear en java
        siguiente.setOnClickListener(new View.OnClickListener() {
            RadioButton rb1 = findViewById(R.id.RadioButton1);
            RadioButton rb2 = findViewById(R.id.RadioButton2);
            RadioButton rb3 = findViewById(R.id.RadioButton3);
            RadioButton rb4 = findViewById(R.id.RadioButton4);
            RadioButton rb5 = findViewById(R.id.RadioButton5);
            RadioButton rb6 = findViewById(R.id.RadioButton6);
            RadioButton rb7 = findViewById(R.id.RadioButton7);
            RadioButton rb8 = findViewById(R.id.RadioButton8);
            RadioButton rb9 = findViewById(R.id.RadioButton9);
            RadioButton rb10 = findViewById(R.id.RadioButton10);
            RadioButton rb11 = findViewById(R.id.RadioButton11);
            RadioButton rb12 = findViewById(R.id.RadioButton12);
            EditText texto = findViewById(R.id.editText);
            @Override
            public void onClick(View v) {
                if(rb1.isChecked()){
                    Inicio.añoN = rb1.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb2.isChecked()){
                    Inicio.añoN = rb2.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb3.isChecked()){
                    Inicio.añoN = rb3.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb4.isChecked()){
                    Inicio.añoN = rb4.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb5.isChecked()){
                    Inicio.añoN = rb5.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb6.isChecked()){
                    Inicio.añoN = rb6.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb7.isChecked()){
                    Inicio.añoN = rb7.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb8.isChecked()){
                    Inicio.añoN = rb8.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb9.isChecked()){
                    Inicio.añoN = rb9.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb10.isChecked()){
                    Inicio.añoN = rb10.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb11.isChecked()){
                    Inicio.añoN = rb11.getText().toString();
                    Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                    startActivity(P4);//Iniciar Actividad
                }else if(rb12.isChecked()){
                    if(texto.getText().toString().length() == 0 ){
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Escriba el nombre alternativo", Toast.LENGTH_SHORT);
                        toast1.show();
                    }else{
                        Inicio.añoN = texto.getText().toString();
                        Intent P4 = new Intent(getApplicationContext(), P4.class);//Lanzar actividad
                        startActivity(P4);//Iniciar Actividad
                    }
                }else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
    }
}
