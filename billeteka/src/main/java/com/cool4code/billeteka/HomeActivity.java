package com.cool4code.billeteka;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class HomeActivity extends Activity implements OnClickListener{
    private Spinner ourcolorspinner;
    private String selection;
    private Button buscar;
    final Context context = this;
    String calorie = "gordito";
    String protein = "flaquito";
    List<ParseObject> ob;

    private SQLiteDatabase mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ourcolorspinner = (Spinner) findViewById(R.id.home_spinner);
        buscar = (Button) findViewById(R.id.home_button_buscar);
        buscar.setOnClickListener(this);
        listenerMethod();

        Parse.initialize(this, "XdWuoWJKZ4rL1hv2lGfgB9IP4fy5po65xayBwwNW", "Ky1GlA3n4jiEnTTLreBjDzo94J5tLshdnZ9sDR1e");
        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);
    }
    @Override
    public void onClick(View v) {
        mydb = this.openOrCreateDatabase("billsdb", MODE_PRIVATE, null);
        Log.d("->", "DB Creada");
        mydb.execSQL("CREATE TABLE IF NOT EXISTS "+ "banknote" + "(country VARCHAR);");
        Log.d("->", "Tabla bank note creada");
        /* Insert data to a Table*/
        Log.d("MyApp", "Buscar accionado--->");
        try {
            Log.d("MyApp", "Iniciando busqueda! ->");
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Country");
         //   query.whereEqualTo("country", "Colombia");
            ob = query.find();
            String descriptions = null;
            for (ParseObject country : ob) {
                descriptions = country.getString("country");
                Log.d("Accion", descriptions);
                mydb.execSQL("INSERT INTO "+ "banknote" + "(country)"+ " VALUES ('"+descriptions+"');");
                Log.d("->", "insert into");
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        /*retrieve data from database */
        Cursor c = mydb.rawQuery("SELECT * FROM " + "banknote" , null);

        int Column1 = c.getColumnIndex("country");
        //  int Column2 = c.getColumnIndex("Field2");

        // Check if our result was valid.
        c.moveToFirst();
        Log.d("results", c.getString(Column1));


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
