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

public class P4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p4);
        getSupportActionBar().hide();// oculta la barra
        pregunta();//mostrar pregunta
        abrirActividades();//activar botones
    }

    public void pregunta(){
        if(Inicio.añoN==null){
            TextView textView = (TextView) findViewById(R.id.textView);
            SpannableString miTexto = new SpannableString("¿Cómo se llaman las divisiones de '"+ Inicio.historia+"' ?");
            StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
            miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            textView.setText(miTexto);
        }else{
            TextView textView = (TextView) findViewById(R.id.textView);
            SpannableString miTexto = new SpannableString("¿Cómo se llaman las divisiones de '"+ Inicio.añoN+"' ?");
            StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
            miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            textView.setText(miTexto);
        }
    }

    public void abrirActividades() {
        Button atras = (Button) findViewById(R.id.button);//Id del boton y crear en java   ----ATRAS----
        atras.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(Inicio.añoN==null){
                    Inicio.años=Long.valueOf(0);
                    Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                    startActivity(P2);//Iniciar Actividad
                }else{
                    Inicio.añoN=null;
                    Intent P3 = new Intent(getApplicationContext(), P3.class);//Lanzar actividad
                    startActivity(P3);//Iniciar Actividad
                }
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
                EditText texto = findViewById(R.id.editText);

                if(Inicio.añoN==null){
                    if(rb1.isChecked()){
                        Inicio.periodo = rb1.getText().toString();
                        Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                        startActivity(P6); //Iniciar Actividad
                    }else if(rb2.isChecked()){
                        Inicio.periodo = rb2.getText().toString();
                        Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                        startActivity(P6); //Iniciar Actividad
                    }else if(rb3.isChecked()){
                        Inicio.periodo = rb3.getText().toString();
                        Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                        startActivity(P6); //Iniciar Actividad
                    }else if(rb4.isChecked()){
                        Inicio.periodo = rb4.getText().toString();
                        Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                        startActivity(P6); //Iniciar Actividad
                    }else if(rb5.isChecked()){
                        if(texto.getText().toString().length() == 0 ){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Escriba el nombre alternativo", Toast.LENGTH_SHORT);
                            toast1.show();
                        }else{
                            Inicio.periodo = texto.getText().toString();
                            Intent P6 = new Intent(getApplicationContext(), P6.class);//Lanzar actividad
                            startActivity(P6); //Iniciar Actividad
                        }
                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }else{
                    if(rb1.isChecked()){
                        Inicio.periodo = rb1.getText().toString();
                        Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                        startActivity(P5); //Iniciar Actividad
                    }else if(rb2.isChecked()){
                        Inicio.periodo = rb2.getText().toString();
                        Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                        startActivity(P5); //Iniciar Actividad
                    }else if(rb3.isChecked()){
                        Inicio.periodo = rb3.getText().toString();
                        Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                        startActivity(P5); //Iniciar Actividad
                    }else if(rb4.isChecked()){
                        Inicio.periodo = rb4.getText().toString();
                        Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                        startActivity(P5); //Iniciar Actividad
                    }else if(rb5.isChecked()){
                        if(texto.getText().toString().length() == 0 ){
                            Toast toast1 = Toast.makeText(getApplicationContext(), "Escriba el nombre alternativo", Toast.LENGTH_SHORT);
                            toast1.show();
                        }else{
                            Inicio.periodo = texto.getText().toString();
                            Intent P5 = new Intent(getApplicationContext(), P5.class);//Lanzar actividad
                            startActivity(P5);//Iniciar Actividad
                        }
                    }else{
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
            }
        });
    }
}
