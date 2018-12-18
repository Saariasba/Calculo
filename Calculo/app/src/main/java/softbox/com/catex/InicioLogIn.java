package softbox.com.catex;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class InicioLogIn extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    public String promedioVisto="¿Agregar Promedio?";
    public static String historia=null;//La pregunta numero 1
    public static boolean años = false;//La pregunta numero 2
    public static String añoN = null;// La pregunta numero 3
    public static String periodo = null;//La pregunta numero 4
    public static int cantidad = 0;//La pregunta numero 5
    public static Double min = null;//La pregunta numero 8
    public static Double max = null;//La pregunta numero 8
    public static Double apb = null;//La pregunta numero 8

    private AdView mAdView;

    private GoogleApiClient googleApiClient;

    private SignInButton signInButton;

    public static final int SIGN_IN_CODE = 777;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_log_in);

        MobileAds.initialize(this, "ca-app-pub-7328406967236626~7788304617");//Inicializar publicidad
        mAdView = findViewById(R.id.adInicio);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        getSupportActionBar().hide();// oculta la barra
        preguntasInicio();//abrir preguntas
        abrirActividades();//Función lanzar actividades
        promedioTexto();
        login();
    }

    private void login() {

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton = (SignInButton) findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent,SIGN_IN_CODE);
            }
        });
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    goMainScreen();
                }
            }
        };

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()){
            firebaseAuthWithGoogle(result.getSignInAccount());
        }else{
            Toast.makeText(this,"No fue posible iniciar sesión", Toast.LENGTH_SHORT).show();
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount signInAccount) {
        AuthCredential credential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(),null);
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"No se pudo autenticar con firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void goMainScreen() {
        Intent intent = new Intent(this, Inicio.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public static String[] removeTrailingQuotes(String[] fields, String QUOTE) {

        String result[] = new String[fields.length];

        for (int i=0;i<result.length;i++){
            result[i] = fields[i].replaceAll("^"+QUOTE, "").replaceAll(QUOTE+"$", "");
        }
        return result;
    }

    public void lector(){
            File archivo = null;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                // Apertura del fichero y creacion de BufferedReader para poder
                // hacer una lectura comoda (disponer del metodo readLine()).
                archivo = new File ("C:\\archivo.txt");
                fr = new FileReader (archivo);
                br = new BufferedReader(fr);

                // Lectura del fichero
                String linea;
                while((linea=br.readLine())!=null){
                    System.out.println(linea);
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }finally{
                // En el finally cerramos el fichero, para asegurarnos
                // que se cierra tanto si todo va bien como si salta
                // una excepcion.
                try{
                    if( null != fr ){
                        fr.close();
                    }
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
    }

    public void EscribeFichero(){
            FileWriter fichero = null;
            PrintWriter pw = null;
            try
            {
                fichero = new FileWriter("c:/prueba.txt");
                pw = new PrintWriter(fichero);

                for (int i = 0; i < 10; i++)
                    pw.println("Linea " + i);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    // Nuevamente aprovechamos el finally para
                    // asegurarnos que se cierra el fichero.
                    if (null != fichero)
                        fichero.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
    }

    public void promedioTexto(){
        TextView textView = (TextView) findViewById(R.id.textView3);
        SpannableString miTexto = new SpannableString(promedioVisto);
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        textView.setText(miTexto);

    }
    public void preguntasInicio() {
        /*if (historia==null){
            Intent Bienvenida = new Intent(getApplicationContext(), Bienvenida.class);//Lanzar actividad
            startActivity(Bienvenida);
        }*/
    }

    public void abrirActividades() {

        ImageButton BotonCa = (ImageButton) findViewById(R.id.imageButton);//Id del boton y crear en java
        BotonCa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Aún no has inicado sesión", Toast.LENGTH_SHORT).show();                                          //Iniciar Actividad
            }
        });

        ImageButton BotonM = (ImageButton) findViewById(R.id.imageButton4);//Id del boton y crear en java
        BotonM.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Aún no has inicado sesión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
