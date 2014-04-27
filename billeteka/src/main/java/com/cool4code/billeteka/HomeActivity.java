package com.cool4code.billeteka;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.view.View.OnClickListener;

public class HomeActivity extends Activity implements OnClickListener{
    private Spinner ourcolorspinner;
    private String selection;
    private Button buscar;
    final Context context = this;
    String calorie = "gordito";
    String protein = "flaquito";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ourcolorspinner = (Spinner) findViewById(R.id.home_spinner);
        buscar = (Button) findViewById(R.id.home_button_buscar);
        buscar.setOnClickListener(this);
        listenerMethod();
    }
    @Override
    public void onClick(View v) {
        Log.d("MyApp", "Buscar accionado--->");
        Intent goToListActivity= new Intent(HomeActivity.this, ListActivity.class);
        goToListActivity.putExtra("CALORIE", calorie);
        goToListActivity.putExtra("PROTEIN", protein);
        startActivity(goToListActivity);
        Log.d("MyApp", "Yendo al listado--->");
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
