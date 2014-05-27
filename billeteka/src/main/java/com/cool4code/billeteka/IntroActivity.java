package com.cool4code.billeteka;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;


public class IntroActivity extends ActionBarActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Button goToHome= (Button) findViewById(R.id.intro_button_goto_home);

        File db =new File("/data/data/com.cool4code.billeteka/databases/billsdb");
        if(db.exists()){
           SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);
           Cursor detailsCursor= mydb.rawQuery("SELECT * FROM firstime WHERE entry=1", null);
           int count_unique_query= detailsCursor.getCount();
            Log.d("count", "--->"+count_unique_query);
           if(count_unique_query>0){
               Log.d("intent", "ir a home automatico");
               Intent goToHomeView= new Intent(IntroActivity.this, HomeActivity.class);
               startActivity(goToHomeView);
           }
        }else{
            Log.d("No existe", "No existe");
        }
        goToHome.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("intent", "ir a home click");
        Intent goToHomeView= new Intent(IntroActivity.this, HomeActivity.class);
        startActivity(goToHomeView);
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.intro, menu);
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
