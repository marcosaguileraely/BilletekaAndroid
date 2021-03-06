package com.cool4code.billeteka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.io.File;
import java.util.List;

public class HomeActivity extends Activity implements OnClickListener{
    private Spinner ourcolorspinner;
    private Spinner denominacionspinner;
    private EditText anoedittext;
    private String selecciondenominacion;
    private String selectionmes;
    private Button buscar;
    String denominacion;
    String ano;
    String mes;
    List<ParseObject> ob;
    ProgressDialog mProgressDialog;
    ProgressDialog barProgressDialog;
    private SQLiteDatabase mydb;
    int queryLimit= 1000;
    final Context context = this;
    int entry_value=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ourcolorspinner = (Spinner) findViewById(R.id.home_spinner);
        denominacionspinner = (Spinner) findViewById(R.id.home_spinner_denominacion);
        anoedittext= (EditText) findViewById(R.id.home_box_ano);
        buscar = (Button) findViewById(R.id.home_button_buscar);
        buscar.setOnClickListener(this);
        listenerMethod();

        AdView adview = (AdView) findViewById(R.id.ad);
        AdRequest re = new AdRequest();
        adview.loadAd(re);

        ParseObject.registerSubclass(feedBackModel.class);
        Parse.initialize(this, "mjtQePti6kho4ep0Kq6llUXBX6kd8ZtehD4uev7n", "gUegGvKeUqU1kzQnUeeW4vFUkOZlvulxxDdiB16p");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        // To track statistics around application
        //ParseAnalytics.trackAppOpened(getIntent());

        // inform the Parse Cloud that it is ready for notifications
        /*PushService.setDefaultPushCallback(this, HomeActivity.class);
        ParseInstallation.getCurrentInstallation().getInstallationId();
        ParseInstallation.getCurrentInstallation().saveInBackground();*/

        /*String  android_id = Secure.getString(getApplicationContext().getContentResolver(),Secure.ANDROID_ID);
        Log.e("LOG","android id >>" + android_id);
        PushService.setDefaultPushCallback(this, HomeActivity.class);
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("UniqueId",android_id);
        installation.saveInBackground();*/

        new RemoteDataTask().execute();

        anoedittext.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(s.length() > 4){
                  Toast.makeText(context, "Solo se permiten 4 dígitos para el Año.", Toast.LENGTH_LONG).show();
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });




    }
    // RemoteDataTask AsyncTask
    private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(HomeActivity.this);
            mProgressDialog.setTitle("Billeteka");
            mProgressDialog.setMessage("Estamos descargando datos. Esto tomará unos segundos...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            File db =new File("/data/data/com.cool4code.billeteka/databases/billsdb");
            String objectId, dia, mes, ano, denominacion, serie, descripcion, tiempo;
            String f1_4, f5_7, f8_10, p1_4, p5_7, p8_10, bernardom13, createdAt, updatedAt;
            int size;
            if(db.exists()){
            Log.d("->", "Existe db");
            Log.d("->", "¡NOTHING TO-DO HERE!");
            }else{
                Log.d("->", "No Existe db");
                SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);
                //this.openOrCreateDatabase("billsdb", MODE_PRIVATE, null);
                Log.d("->", "billsdb Creada");
                mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "banknote" + "(objectId VARCHAR,dia VARCHAR,mes VARCHAR,ano VARCHAR,denominacion VARCHAR,serie VARCHAR,descripcion VARCHAR,tiempo VARCHAR,f1_4 VARCHAR,f5_7 VARCHAR,f8_10 VARCHAR,p1_4 VARCHAR,p5_7 VARCHAR,p8_10 VARCHAR,bernardom13 VARCHAR,createdAt VARCHAR,updatedAt VARCHAR);");
                Log.d("->", "tabla banknote creada");
                mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "firstime" + "(entry VARCHAR);");
                Log.d("->", "tabla firstime creada");
                //mydb.execSQL("insert into firstime "+("entry")+" values('Yes');");
                mydb.execSQL("INSERT INTO firstime"+"(entry)"+
                             "VALUES ('"+entry_value+"');");
                try {
                    Log.d("MyApp", "Iniciando busqueda! ->");
                    ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("billeteca2014");
                    query.setLimit(queryLimit); // limit to at most 10 results
                    ob = query.find();
                    size= ob.size();
                    Log.d("Accion"," total: "+size);
                    for (ParseObject bills : ob) {
                        objectId= bills.getObjectId();
                        dia = bills.getString("dia");
                        mes = bills.getString("mes");
                        ano = bills.getString("ano");
                        denominacion = bills.getString("denominacion");
                        serie = bills.getString("serie");
                        descripcion = bills.getString("descripcion");
                        tiempo = bills.getString("tiempo");
                        f1_4 = bills.getString("f1_4");
                        f5_7 = bills.getString("f5_7");
                        f8_10 = bills.getString("f8_10");
                        p1_4 = bills.getString("p1_4");
                        p5_7 = bills.getString("p5_7");
                        p8_10 = bills.getString("p8_10");
                        bernardom13 = bills.getString("bernardom13");
                        createdAt = bills.getString("createdAt");
                        updatedAt = bills.getString("updatedAt");
                        Log.d("Accion","-> "+size+" - "+objectId+" - "+size--);
                        mydb.execSQL("INSERT INTO banknote"+"(objectId, dia, mes, ano, denominacion, serie, descripcion, tiempo, f1_4, f5_7, f8_10, p1_4, p5_7, p8_10, bernardom13, createdAt, updatedAt)"+
                                "VALUES ('"+objectId+"','"+dia+"','"+mes+"','"+ano+"','"+denominacion+"','"+serie+"','"+descripcion+"','"+tiempo+"','"+f1_4+"','"+f5_7+"','"+f8_10+"','"+p1_4+"','"+p5_7+"','"+p8_10+"','"+bernardom13+"','"+createdAt+"','"+updatedAt+"');");
                    }
                } catch (ParseException e) {
                    Log.e("Error", e.getMessage());
                    e.printStackTrace();
                }
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) {
            SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);
            mProgressDialog.dismiss();
            mydb.execSQL("update main.banknote set serie='' where serie='null'");
            mydb.execSQL("update main.banknote set descripcion='' where descripcion='null'");
        }
    }
    @Override
    public void onClick(View v) {
        Intent goToListActivity= new Intent(HomeActivity.this, ListBillsActivity.class);
        denominacion= this.selecciondenominacion;
        ano= anoedittext.getText().toString();
        mes= this.selectionmes;

        String denom_ = denominacionspinner.getSelectedItem().toString();
        ano= anoedittext.getText().toString();
        String mes_ = ourcolorspinner.getSelectedItem().toString();
        Log.d("denominacion->", "-> "+denom_);
        Log.d("ano->", "-> "+ano);
        Log.d("mes->", "-> "+mes_);
        if(!"Elija una...".equals(denom_) && !"".equals(ano) && !"Elija un mes...".equals(mes_)){
          Log.d("condicion1->", "3 inputs ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
        }
        if(!"Elija una...".equals(denom_) && ano.equals("") && mes_.equals("Elija un mes...")){
          Log.d("condicion2->", "solo denominacion ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(denom_.equals("Elija una...") && !"".equals(ano) && mes_.equals("Elija un mes...")){
          Log.d("condicion3->", "solo año ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(denom_.equals("Elija una...") && ano.equals("") && !"Elija un mes...".equals(mes_)){
          Log.d("condicion4->", "solo mes ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(denom_.equals("Elija una...") && !"".equals(ano) && !"Elija un mes...".equals(mes_)){
          Log.d("condicion5->", "solo mes y año ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(!"Elija una...".equals(denom_) && ano.equals("") && !"Elija un mes...".equals(mes_)){
          Log.d("condicion6->", "solo mes y denominacion ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(!"Elija una...".equals(denom_) && !"".equals(ano)  && mes_.equals("Elija un mes...")){
          Log.d("condicion6->", "solo año y denominacion ok!");
          goToListActivity.putExtra("DENOMINACION", denominacion);
          goToListActivity.putExtra("ANO", ano);
          goToListActivity.putExtra("MES", mes);
          startActivity(goToListActivity);
          Log.d("MyApp", "Yendo al listado--->");
        }
        if(denom_.equals("Elija una...") && ano.equals("") && mes_.equals("Elija un mes...")){
            Log.d("condicion2->", "mensaje toast ok!");
            Toast.makeText(context, "Selecciona o ingresa un valor de busqueda.", Toast.LENGTH_LONG).show();
        }
    }

    private void listenerMethod() {
        denominacionspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                selecciondenominacion = (String) parentView.getItemAtPosition(position);
                Log.d("id", selecciondenominacion);
                //Toast.makeText(context, "You seleceted " + selection + ".", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){

        }});
        ourcolorspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                selectionmes = (String) parentView.getItemAtPosition(position);
                Log.d("id", selectionmes);
                //Toast.makeText(context, "You seleceted " + selection + ".", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){

        }});
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_action_book:
                Log.d("acerca-de", "Ha presionado librito");
                Intent goToAds= new Intent(HomeActivity.this, AdsActivity.class);
                startActivity(goToAds);
                return true;

            case R.id.home_action_reset:
                Log.d("acerca-de", "Ha presionado restablecer");
                ourcolorspinner.setSelection(0);
                denominacionspinner.setSelection(0);
                anoedittext.setText("");
                Toast.makeText(context, "Campos restablecidos.", Toast.LENGTH_LONG).show();
                return true;

            case R.id.home_action_helpus:
                Log.d("report", "Ha presionado reportar");
                Intent goToHelpUs= new Intent(HomeActivity.this, FeedbackActivity.class);
                startActivity(goToHelpUs);
                return true;

            case R.id.home_action_about:
                Log.d("acerca-de", "Ha presionado acerca de...");
                Intent goToAbout= new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(goToAbout);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        return;
    }
}
