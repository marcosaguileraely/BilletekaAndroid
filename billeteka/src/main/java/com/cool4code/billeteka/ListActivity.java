package com.cool4code.billeteka;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class ListActivity extends ActionBarActivity implements OnClickListener{
    Button queryButton;
    ListView listBills;
    List<ParseObject> ob;
/*    // Declare Variables
    ListView listview;

    ProgressDialog mProgressDialog;
    ListViewAdapter adapter;
    private List<WorldPopulation> worldpopulationlist = null;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        queryButton= (Button) findViewById(R.id.list_query_button);
        listBills= (ListView) findViewById(R.id.list_lisresults_query);
        queryButton.setOnClickListener(this);
//      new RemoteDataTask().execute();

        /*// Set up the listview
        ArrayList<String> playerlist = new ArrayList<String>();
        // Create and populate an ArrayList of objects from parse
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1);
        final ListView playerlv = (ListView)findViewById(android.R.id.list);
        playerlv.setAdapter(listAdapter);
        final ParseQuery query = ParseUser.getQuery();
        query.findInBackground(new FindCallback() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), objects.toString(), Toast.LENGTH_LONG).show();
                    for (int i = 0; i < objects.size(); i++) {
                        ParseUser u = (ParseUser)objects.get(i);
                        String name = u.getString("username").toString();
                        String email = u.getString("email").toString();
                        listAdapter.add(name);
                        listAdapter.add(email);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }

        });*/

        Bundle dataFromHomeActivity= getIntent().getExtras();
        if(dataFromHomeActivity != null){
            String data1= dataFromHomeActivity.getString("CALORIE");
            String data2= dataFromHomeActivity.getString("PROTEIN");
            Log.d("MyApp", data1);
            Log.d("MyApp", data2);
        }
    }
    @Override
    public void onClick(View v) {
        try {
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Country");
            query.whereEqualTo("country", "Colombia");
            ob = query.find();
            String descriptions = null;
            for (ParseObject country : ob) {
                descriptions = country.getString("country");
                Log.d("Accion", descriptions);
            }
        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list, menu);
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
