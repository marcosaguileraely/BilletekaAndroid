package com.cool4code.billeteka;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
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
    ListView listView;
    final Context context = this;
    String idregistro;

    ArrayList<BillsBean> lista= new ArrayList<BillsBean>();
    BillsBean bills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bills);
        listView = (ListView) findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("Pls work!", "->"+listView.getItemAtPosition(position));
            }
        });
        listView.setClickable(true);
        listenerMethod();
        //ListView listView = (ListView) findViewById(R.id.listview);

        /*tabhost*/
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();

        TabHost.TabSpec spec= tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("BILLETES");
        tabs.addTab(spec);

        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("DETALLES");
        tabs.addTab(spec);

        tabs.setCurrentTab(0);

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

        tabs.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsAction>>", "Pulsada pestaña: " + tabId);
            }
        });
    }

    private ArrayList<BillsBean> generateData(){
        ArrayList<BillsBean> items = new ArrayList<BillsBean>();
        SQLiteDatabase mydb = getBaseContext().openOrCreateDatabase("billsdb", SQLiteDatabase.OPEN_READWRITE, null);
        //3 elementos: denominacion + año + mes
        if(!"Elija una...".equals(var_denominacion) && !"".equals(var_ano) && !"Elija un mes...".equals(var_mes)){
            Log.d("modo:", "===> 3 inputs");
            Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE denominacion = '"+ var_denominacion+"'AND ano='"+ var_ano+"'AND mes='"+var_mes+"'", null);
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
        }
        //solo denominacion
        if(!"Elija una...".equals(var_denominacion) && var_ano.equals("") && var_mes.equals("Elija un mes...")){
            Log.d("modo:", "===> Solo denominacion");
            Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE denominacion = '"+var_denominacion+"'", null);
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
        }
        //solo año
        if(var_denominacion.equals("Elija una...") && !"".equals(var_ano) && var_mes.equals("Elija un mes...")){
            Log.d("modo:", "===> Solo año");
            Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE ano = '"+var_ano+"'", null);
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
        }
        // solo mes
        if(var_denominacion.equals("Elija una...") && var_ano.equals("") && !"Elija un mes...".equals(var_mes)){
            Log.d("modo:", "===> Solo mes");
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
        }
        // 2 elementos: año + mes
        if(var_denominacion.equals("Elija una...") && !"".equals(var_ano) && !"Elija un mes...".equals(var_mes)){
            Log.d("modo:", "===> Solo mes");
            Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE ano='"+ var_ano+"'AND mes='"+var_mes+"'", null);
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
        }
        // 2 elementos: denominacion + mes
        if(!"Elija una...".equals(var_denominacion) && var_ano.equals("") && !"Elija un mes...".equals(var_mes)){
            Log.d("modo:", "===> Solo mes");
            Cursor c= mydb.rawQuery("SELECT * FROM banknote WHERE denominacion = '"+var_denominacion+"'AND mes='"+var_mes+"'", null);
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
        }
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


    private void listenerMethod() {
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                idregistro = (String) parentView.getItemAtPosition(position);
                Log.d("id", idregistro);
                //Toast.makeText(context, "You seleceted " + selection + ".", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });
    }
}
