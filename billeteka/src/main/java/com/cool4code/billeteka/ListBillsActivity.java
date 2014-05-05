package com.cool4code.billeteka;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListBillsActivity extends ActionBarActivity {
    String var_denominacion;
    String var_ano;
    String var_mes;

    String _idparse;
    String _dia;
    String _mes;
    String _ano;
    String _denominacion;
    String _descripcion;
    String _complete;
    final Context context = this;

    ArrayList<BillsBean> lista= new ArrayList<BillsBean>();
    BillsBean bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bills);
        ListView listView = (ListView) findViewById(R.id.listview);
        /*Intent getting data*/
        Bundle dataFromHomeActivity= getIntent().getExtras();
        if(dataFromHomeActivity != null){
            var_denominacion= dataFromHomeActivity.getString("DENOMINACION");
            var_ano= dataFromHomeActivity.getString("ANO");
            var_mes= dataFromHomeActivity.getString("MES");
            Log.d("MyApp", "denominacion-> " + var_denominacion);
            Log.d("MyApp", "año-> "+ var_ano);
            Log.d("MyApp", "mes-> "+ var_mes);
        }
        MyAdapter adapter = new MyAdapter(this, generateData());
        listView.setAdapter(adapter);
    }

    private ArrayList<BillsBean> generateData(){
        ArrayList<BillsBean> items = new ArrayList<BillsBean>();
        /*Sqlite query*/
        SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);
//        Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE denominacion = '"+ var_denominacion+"'AND ano='"+ var_ano+"'AND mes='"+var_mes+"'", null);
        Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE mes = '"+var_mes+"'", null);
        int _count= c.getCount();
        Log.d("count", "registros encontrados-->"+_count);
        Toast.makeText(context, "Hemos encontrado "+_count+" resultados.", Toast.LENGTH_LONG).show();
        if(c.moveToNext()){
            do {
                _idparse= c.getString(0);
                _dia= c.getString(1);
                _mes= c.getString(2);
                _ano= c.getString(3);
                _denominacion= c.getString(4);
                _descripcion= c.getString(6);
                _complete= _denominacion+" Peso(s)"+"\n"+_dia+" de "+_mes+" de "+_ano;
                Log.d("db", "query -->"+_idparse+" - "+_dia+" - "+_mes+" - "+_ano+" - "+_denominacion+" - "+_descripcion);
                items.add(new BillsBean(_descripcion, _complete));
            }while(c.moveToNext());
        }
        c.close();
        return items;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_bills, menu);
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
    }
}
