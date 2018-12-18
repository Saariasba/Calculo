package softbox.com.catex;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Inicio extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    /////Variables que usan las preguntas de inicio///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String promedioVisto="¿Agregar Promedio?";//La frase que aparece en Inicio
    public static String historia=null;//La pregunta numero 1
    public static Long años = Long.valueOf(0);//La pregunta numero 2
    public static String añoN = null;// La pregunta numero 3
    public static String periodo = null;//La pregunta numero 4
    public static Long cantidad = Long.valueOf(0);//La pregunta numero 5
    public static Double min = null;//La pregunta numero 8
    public static Double max = null;//La pregunta numero 8
    public static Double apb = null;//La pregunta numero 8
    public static Long id = Long.valueOf(1);
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Variables Para XML//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private AdView mAdView;
    private ImageView foto;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Variables Para autenticación////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private GoogleApiClient googleApiClient;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Variable De Base de Datos///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    FirebaseFirestore db;
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función OnCreate////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        foto = (ImageView) findViewById(R.id.foto); //Enlace de ImageView por ID
        db = FirebaseFirestore.getInstance(); //Creación de Instancia de Base de Datos
        login(); //login de  usuarios
        getSupportActionBar().hide();// oculta la barra
        abrirActividades(); //Función lanzar actividades
        promedioTexto(); //Pone el texto en el cuadro Azul
        cargarAds(); //Carga la publicidad
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Carga de Publicidad////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void cargarAds() {
        MobileAds.initialize(this, "ca-app-pub-7328406967236626~7788304617"); //Inicializar publicidad
        mAdView = findViewById(R.id.adInicio); //Enlace de AdView por ID
        AdRequest adRequest = new AdRequest.Builder().build(); //hacer el request de Ads
        mAdView.loadAd(adRequest); //Cargar la respuesta de la solicitud en el AdView
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Cargar Datos////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void cargar(final FirebaseUser user) {
        DocumentReference referencia = db.collection("usuarios").document(user.getUid()).collection("historias").document(String.valueOf(id)); // Crear la referencia del Documento
        referencia.get() //Obtener Documento de referencia
                .addOnCompleteListener(new OnCompleteListener < DocumentSnapshot > () {
                    @Override
                    public void onComplete(@NonNull Task < DocumentSnapshot > task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot doc = task.getResult(); //Obtener el documento
                            if (doc.exists()) { //Saber si existe el documento
                                ////Cargar información////
                                historia = (String) doc.get("Nombre");
                                años = (Long) doc.get("Divide Años");
                                añoN = (String) doc.get("Nombre Año");
                                periodo = (String) doc.get("Divisiones");
                                cantidad = (Long) doc.get("Cantidad");
                                min = (Double) doc.get("Minimo");
                                max = (Double) doc.get("Maximo");
                                apb = (Double) doc.get("Aprobatorio");
                                id = (Long) doc.get("ID");
                                Toast.makeText(getApplicationContext(),"Sisarrion   " + historia , Toast.LENGTH_SHORT).show();
                            } else {
                                if(esNulo(historia)){ //Comprobamos que historia sea nula
                                    //Lanzar actividad de preguntas//
                                    Intent Bienvenida = new Intent(getApplicationContext(), Bienvenida.class);
                                    startActivity(Bienvenida);
                                }else{
                                    baseDeDatos(user); //Guardar información de variables en Base de Datos
                                }
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() { //Respuesta en caso de no funcipnar el Listener
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Error","No such document!");
                    }
                });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Guardar Datos en Base de Datos//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void baseDeDatos(FirebaseUser user) {
        //Datos que se van a guardar y Map para organizarlos
        Map<String, Object> historias = new HashMap<>();
        historias.put("Nombre", historia);
        historias.put("Divide Años", años);
        historias.put("Nombre Año", añoN);
        historias.put("Divisiones", periodo);
        historias.put("Cantidad", cantidad);
        historias.put("Minimo", min);
        historias.put("Maximo", max);
        historias.put("Aprobatorio", apb);
        historias.put("ID",id);

        db.collection("usuarios").document(user.getUid()).collection("historias").document(String.valueOf(id)) //documento donde se guardan los datos
                .set(historias) //sobreescribe los datos o los escribe por primera vez
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        //Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error writing document", e);
                    }
                });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para hacer Log In del usuario///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void login() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN) //Opciones que se requieren de cuenta Google
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this) //Conexión a Google
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        FirebaseApp.initializeApp(this); //Inicializar FireBase en la App
        firebaseAuth = FirebaseAuth.getInstance(); //Instanciar la autenticación de FireBase
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) { //El escucha del autenticador
                 final FirebaseUser user = firebaseAuth.getCurrentUser();//Obtener usuario del Log In
                if(user != null){ //Se verifica si el usuario es nulo
                    setUserData(user);//Envia usuario para obtener información
                }else{
                    goLogInScreen(); //Envia al Log In
                }
            }
        };
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para manejar informacion del usuario////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void setUserData(FirebaseUser user) {
        cargar(user); //Envia para cargar información de Firestore
        Glide.with(this).load(user.getPhotoUrl()).into(foto); // Carga la imagen del usuario en la aplicación
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para cuando la conexión falle///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para Iniciar el listener de la autenticación///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para Detener el Listener de la autenticación///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    protected void onStop() {
        super.onStop();
        if(firebaseAuthListener != null){
            firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para Lanzar Inicio sin Log In///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void goLogInScreen() {
        Intent intent = new Intent(this, InicioLogIn.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para hacer Log Out del usuario///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void logOut(){
        historia = null; //Borra la historia actual
        firebaseAuth.signOut();//Cierra en FireBase
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) { //Cierra en Google
                if(status.isSuccess()){
                    goLogInScreen();
                }else{
                    Toast.makeText(getApplicationContext(),"No fue posible cerrar sesión", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para Mostrar texto en cuadro///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void promedioTexto(){
        TextView textView = (TextView) findViewById(R.id.textView3);  //TextView por ID
        SpannableString miTexto = new SpannableString(promedioVisto); //Convierte el String
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD); //Tipo de letra
        miTexto.setSpan(boldSpan1, 0, miTexto.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); //Guarda el texto modificado en variable
        textView.setText(miTexto); //Imprime el texto en TextView
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para Preguntar si un String es NULO/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private static boolean esNulo(String s){
        return (s==null || s.trim().equals(""));
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /////Función Para lanzar actividades/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void abrirActividades() {

        ImageButton BotonCa = (ImageButton) findViewById(R.id.imageButton);//Id del boton y crear en java
        BotonCa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent CalculoRapido = new Intent(getApplicationContext(), CalculoRapido.class);//Lanzar actividad
                startActivity(CalculoRapido);                                            //Iniciar Actividad
            }
        });

        ImageButton BotonM = (ImageButton) findViewById(R.id.imageButton4);//Id del boton y crear en java
        BotonM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button cerrar = (Button) findViewById(R.id.logOut);//Id del boton y crear en java
        cerrar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Inicio.this); //Construye cuadro de dialogo
                dialogo1.setTitle("Importante"); // Titulo
                dialogo1.setMessage("¿Deseas cerrar sesión?"); // mensaje del cuadro
                dialogo1.setCancelable(false);
                dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) { //Cierra sesión al presionar botón
                        logOut();
                    }
                });
                dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() { //Cancela la solicitud de cerrar sesión
                    public void onClick(DialogInterface dialogo1, int id) {
                    }
                });
                dialogo1.show(); //Muestra el cuadro de dialogo
            }
        });
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}
