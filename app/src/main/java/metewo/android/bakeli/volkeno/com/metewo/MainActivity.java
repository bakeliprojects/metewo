package metewo.android.bakeli.volkeno.com.metewo;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import model.Wether;
import parser.WeatherXmlParser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ProgressBar pbar;
    List<MyTask> tasks;
    List<Wether> wetherList;
    TextView location;
    TextView lastUpdated;
    TextView tempC;
    TextView icon;
    TextView conditionText;
    TextView wind;
    TextView mintemp;
    TextView maxtemp;
    String dakar = "http://api.apixu.com/v1/forecast.xml?key=e10f2a2773ae47d4ac1131057171606&q=Dakar";
    String thies = "http://api.apixu.com/v1/forecast.xml?key=e10f2a2773ae47d4ac1131057171606&q=Thies";
    String place;
    String Louga = "http://api.apixu.com/v1/forecast.xml?key=e10f2a2773ae47d4ac1131057171606&q=Louga";

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if(isOnline())
            {
                place = thies;
                requestData(place);
            }else{
                Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_gallery) {
            if(isOnline())
            {
                place = dakar;
                requestData(place);
            }else{
                Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_slideshow) {
            if(isOnline())
            {
                place = Louga;
                requestData(place);
            }else{
                Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        pbar = (ProgressBar) findViewById(R.id.progressBar);
        location = (TextView) findViewById(R.id.location);
        lastUpdated = (TextView) findViewById(R.id.lastUpdated);
        tempC = (TextView) findViewById(R.id.tempC);
        conditionText = (TextView) findViewById(R.id.conditionText);
        wind = (TextView) findViewById(R.id.windMph);
        mintemp = (TextView) findViewById(R.id.minTemp);
        maxtemp = (TextView) findViewById(R.id.maxTemp);
        tasks = new ArrayList<>();

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /*if(isOnline())
        {
            requestData(place);
        }else{
            Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }


    protected void updateDisplay()
    {
        if(wetherList != null) {
            for (Wether wether : wetherList) {
                location.setText(wether.getLocationRegion());
                tempC.setText(wether.getCurrentTempC());
                conditionText.setText(wether.getConditionText());
                wind.setText(wether.getWindMph());
                mintemp.setText(wether.getMinTemp());
                lastUpdated.setText(wether.getCurrentLastUpdated());
                maxtemp.setText(wether.getMaxTemp());

            }
        }
    }

    protected boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }else{
            return false;
        }
    }

    private class MyTask extends AsyncTask<String,String,String>
    {
        @Override
        protected void onPreExecute() {
            // updateDisplay("Starting task!");
            if(tasks.size() == 0)
            {
                pbar.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected String doInBackground(String... params) {

            String content = HttpManager.getData(params[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            wetherList = WeatherXmlParser.parseFeed(s);
            updateDisplay();
            tasks.remove(this);
            if(tasks.size() == 0)
            {
                pbar.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            //updateDisplay(values[0]);
        }
    }
}
