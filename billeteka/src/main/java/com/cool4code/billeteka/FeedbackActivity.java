package com.cool4code.billeteka;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class FeedbackActivity extends ActionBarActivity implements OnClickListener {
    EditText nombre_persona;
    EditText correo_electronico;
    Spinner tipo_retroalimentacion;
    EditText comentario;
    Button enviar_retroalimentacion;

    String selecciontipo;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        nombre_persona= (EditText) findViewById(R.id.helpUs_input_fullname);
        correo_electronico= (EditText) findViewById(R.id.helpUs_input_email);
        tipo_retroalimentacion= (Spinner) findViewById(R.id.helpUs_input_tipo); //something wrong here!, maybe check for a while later....
        comentario= (EditText) findViewById(R.id.helpUs_input_comentario);
        enviar_retroalimentacion= (Button) findViewById(R.id.helpUs_button_enviar);
        enviar_retroalimentacion.setOnClickListener(this);

        listenerMethod();
    }

    private void listenerMethod() {
        tipo_retroalimentacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                selecciontipo = (String) parentView.getItemAtPosition(position);
                Log.d("id", selecciontipo);
                //Toast.makeText(context, "You seleceted " + selection + ".", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){

        }});
    }

    @Override
    public void onClick(View v) {
        String nombre_value= nombre_persona.getText().toString();
        String correo_value= correo_electronico.getText().toString();
        String tipo_value= selecciontipo;
        String comentario_value= comentario.getText().toString();
        String to = "marcosaguileraely@gmail.com";
        String subject = "Retroalimentación Billeteka - Usuarios";
        String message = "Envío de retroalimentación App Billeteka."+"\n\nNombre completo: "+nombre_value+"\n\nE-mail: "+correo_value+"\n\nTipo retroalimentación: "+tipo_value+"\n\nComentario: "+comentario_value;

        if(!"".equals(nombre_value) && !"".equals(correo_value) && !"Elije un tipo...".equals(tipo_value) && !"".equals(comentario_value)){
            Log.d("campos correctos", "Todos los campos completos");
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
            //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
            //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
            email.putExtra(Intent.EXTRA_SUBJECT, subject);
            email.putExtra(Intent.EXTRA_TEXT, message);

            //need this to prompts email client only
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Choose an Email client :"));
        }else{
            Toast.makeText(context, "Todos los campos deben ser diligenciados.", Toast.LENGTH_LONG).show();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feedback, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
