package softbox.com.catex;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class P1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p1);
        getSupportActionBar().hide();// oculta la barra
        pregunta();//pregunta o texto de arriba
        abrirActividades();//son los botones
    }

    public void pregunta(){
        TextView textView = (TextView) findViewById(R.id.textView);//se crea el textview unido a su ID
        SpannableString miTexto = new SpannableString("¿Cómo deseas que se llame tu historia académica?");//La variable que tiene el texto
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);//Estilo de letra
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);//se crea el texto en el tipo de letra
        textView.setText(miTexto);//Se envia el texto a el TextView
    }

    public void abrirActividades() {
        Button Boton = (Button) findViewById(R.id.button);//Id del boton y crear en java

        Boton.setOnClickListener(new View.OnClickListener() {
            //Botones y IDs
            RadioButton rb1 = findViewById(R.id.RadioButton1);
            RadioButton rb2 = findViewById(R.id.RadioButton2);
            RadioButton rb3 = findViewById(R.id.RadioButton3);
            RadioButton rb4 = findViewById(R.id.RadioButton4);
            RadioButton rb5 = findViewById(R.id.RadioButton5);
            EditText texto = findViewById(R.id.editText);

            @Override
            public void onClick(View v) {
                if(rb1.isChecked()){
                    Inicio.historia = rb1.getText().toString();
                    Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                    startActivity(P2);//Iniciar Actividad
                }else if(rb2.isChecked()){
                    Inicio.historia = rb2.getText().toString();
                    Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                    startActivity(P2);//Iniciar Actividad
                }else if(rb3.isChecked()){
                    Inicio.historia = rb3.getText().toString();
                    Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                    startActivity(P2);//Iniciar Actividad
                }else if(rb4.isChecked()){
                    Inicio.historia = rb4.getText().toString();
                    Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                    startActivity(P2);//Iniciar Actividad
                }else if(rb5.isChecked()){
                    if(texto.getText().toString().length() == 0 ){
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Escriba el nombre alternativo", Toast.LENGTH_SHORT);
                        toast1.show();
                    }else{
                        Inicio.historia = texto.getText().toString();
                        Intent P2 = new Intent(getApplicationContext(), P2.class);//Lanzar actividad
                        startActivity(P2);//Iniciar Actividad
                    }
                }else{
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Seleccione una opción", Toast.LENGTH_SHORT);//Toast en caso de que no se ingresen ls datos pedidos
                    toast1.show();
                }
            }
        });
    }
}
