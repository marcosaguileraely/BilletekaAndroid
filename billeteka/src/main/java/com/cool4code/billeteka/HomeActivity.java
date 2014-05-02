package com.cool4code.billeteka;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

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
    private String selection;
    private Button buscar;
    final Context context = this;
    String calorie = "gordito";
    String protein = "flaquito";
    List<ParseObject> ob;
    TextView paises;
    ProgressDialog mProgressDialog;
    ProgressDialog barProgressDialog;

    private SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ourcolorspinner = (Spinner) findViewById(R.id.home_spinner);
        buscar = (Button) findViewById(R.id.home_button_buscar);
        paises= (TextView) findViewById(R.id.home_text_paises);
        buscar.setOnClickListener(this);
        listenerMethod();

        Parse.initialize(this, "mjtQePti6kho4ep0Kq6llUXBX6kd8ZtehD4uev7n", "gUegGvKeUqU1kzQnUeeW4vFUkOZlvulxxDdiB16p");
        //Parse.initialize(this, "XdWuoWJKZ4rL1hv2lGfgB9IP4fy5po65xayBwwNW", "Ky1GlA3n4jiEnTTLreBjDzo94J5tLshdnZ9sDR1e");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
    @Override
    public void onClick(View v) {
        File db =new File("/data/data/com.cool4code.billeteka/databases/billsdb");
        String objectId, dia, mes, ano, denominacion, serie, descripcion, tiempo;
        String f1_4, f5_7, f8_10, p1_4, p5_7, p8_10, bernardom13, createdAt, updatedAt;
        int size;
        if(db.exists()){
            Log.d("->", "Existe db");
            mydb = this.openOrCreateDatabase("billsdb", MODE_PRIVATE, null);
            mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "banknote" + "(objectId VARCHAR,dia VARCHAR,mes VARCHAR,ano VARCHAR,denominacion VARCHAR,serie VARCHAR,descripcion VARCHAR,tiempo VARCHAR,f1_4 VARCHAR,f5_7 VARCHAR,f8_10 VARCHAR,p1_4 VARCHAR,p5_7 VARCHAR,p8_10 VARCHAR,bernardom13 VARCHAR,createdAt VARCHAR,updatedAt VARCHAR);");
            mydb.execSQL("DELETE FROM banknote");
            try {
                final ProgressDialog ringProgressDialog = ProgressDialog.show(HomeActivity.this, "Billeteka message", "Downloading data from cloud...", true);
                ringProgressDialog.setCancelable(true);
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("billeteca2014");
                query.setLimit(1000); // limit to at most 10 results
                ob = query.find();
                size= ob.size();
                Log.d("Accion"," total: "+size);
                String descriptions = null;
                String all= null;
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
                    Log.d("Accion","-> "+size+" - "+objectId);
                    mydb.execSQL("INSERT INTO banknote"+"(objectId, dia, mes, ano, denominacion, serie, descripcion, tiempo, f1_4, f5_7, f8_10, p1_4, p5_7, p8_10, bernardom13, createdAt, updatedAt)"+
                                 "VALUES ('"+objectId+"','"+dia+"','"+mes+"','"+ano+"','"+denominacion+"','"+serie+"','"+descripcion+"','"+tiempo+"','"+f1_4+"','"+f5_7+"','"+f8_10+"','"+p1_4+"','"+p5_7+"','"+p8_10+"','"+bernardom13+"','"+createdAt+"','"+updatedAt+"');");
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
        }else{
            Log.d("->", "No Existe db");
            mydb = this.openOrCreateDatabase("billsdb", MODE_PRIVATE, null);
            Log.d("->", "billsdb Creada");
            mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "banknote" + "(objectId VARCHAR,dia VARCHAR,mes VARCHAR,ano VARCHAR,denominacion VARCHAR,serie VARCHAR,descripcion VARCHAR,tiempo VARCHAR,f1_4 VARCHAR,f5_7 VARCHAR,f8_10 VARCHAR,p1_4 VARCHAR,p5_7 VARCHAR,p8_10 VARCHAR,bernardom13 VARCHAR,createdAt VARCHAR,updatedAt VARCHAR);");
            Log.d("->", "tabla banknote creada");
            try {
                Log.d("MyApp", "Iniciando busqueda! ->");
                ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("billeteca2014");
                query.setLimit(1000); // limit to at most 10 results
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
                    Log.d("Accion","-> "+size+" - "+objectId);
                    mydb.execSQL("INSERT INTO banknote"+"(objectId, dia, mes, ano, denominacion, serie, descripcion, tiempo, f1_4, f5_7, f8_10, p1_4, p5_7, p8_10, bernardom13, createdAt, updatedAt)"+
                            "VALUES ('"+objectId+"','"+dia+"','"+mes+"','"+ano+"','"+denominacion+"','"+serie+"','"+descripcion+"','"+tiempo+"','"+f1_4+"','"+f5_7+"','"+f8_10+"','"+p1_4+"','"+p5_7+"','"+p8_10+"','"+bernardom13+"','"+createdAt+"','"+updatedAt+"');");
                }
            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
        }

        /*Intent goToListActivity= new Intent(HomeActivity.this, ListActivity.class);
        goToListActivity.putExtra("CALORIE", calorie);
        goToListActivity.putExtra("PROTEIN", protein);
        startActivity(goToListActivity);
        Log.d("MyApp", "Yendo al listado--->");*/
    }

    private void listenerMethod() {
        // TODO Auto-generated method stub
        ourcolorspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id){
                selection = (String) parentView.getItemAtPosition(position);
                Log.d("id", selection);
                //Toast.makeText(context, "You seleceted " + selection + ".", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView){

            }});
    }
}
