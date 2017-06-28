package metewo.android.bakeli.volkeno.com.metewo;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import model.Current;
import model.Forecast;
import model.ForecastDay;
import model.Location;
import model.Wether;
import parser.RetrofitInterface;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {
   //private String url = "http://api.apixu.com/v1/forecast.json?key=e10f2a2773ae47d4ac1131057171606&q=Dakar&days=7";
    private static final String BASE_URL = "http://api.apixu.com/";
    private static String place = "Dakar";
    private ImageView imgView;
    private RetrofitInterface retrofitInterface;
    private ProgressBar pbar;
    private TextView location,lastUpdated,tempC,icon,conditionText,wind,mintemp,maxtemp;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ForecastDay> fdays = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        location = (TextView) findViewById(R.id.location);
        lastUpdated = (TextView) findViewById(R.id.lastUpdated);
        tempC = (TextView) findViewById(R.id.tempC);
        conditionText = (TextView) findViewById(R.id.conditionText);
        wind = (TextView) findViewById(R.id.windMph);
        mintemp = (TextView) findViewById(R.id.minTemp);
        maxtemp = (TextView) findViewById(R.id.maxTemp);
        pbar = (ProgressBar) findViewById(R.id.progressBar);
        imgView = (ImageView) findViewById(R.id.icon);

        getRetrofit(place);


        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                drawer.openDrawer(GravityCompat.START);

            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            if(isOnline())
            {
                //getRetrofit("Thies");
                place = "Thies";
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
            }
        } else if (id == R.id.nav_gallery) {
            if(isOnline())
            {
                //getRetrofit("Kaolack");
                place = "Kaolack";
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }else{
                Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
            }

        } else if (id == R.id.nav_slideshow) {
            if(isOnline())
            {
                //getRetrofit("Louga");
                place = "Louga";
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
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

    //Weather au demarrage
    public void getWeather(Wether wether)
    {
        if(wether != null)
        {
            Current current = wether.getCurrent();
            Location locat = wether.getLocation();
            Forecast forecast = wether.getForecast();

            location.setText(locat.getRegion());
            lastUpdated.setText(current.getLastUpdated());
            tempC.setText(""+current.getTempC().intValue());
            conditionText.setText(current.getCondition().getText());
            wind.setText(""+current.getWindMph().intValue());
            maxtemp.setText(""+forecast.getForcastDays().get(0).getDay().getMaxtempC().intValue());
            mintemp.setText(""+forecast.getForcastDays().get(0).getDay().getMintempC().intValue());
            Picasso
                    .with(this).load("http:"+forecast.getForcastDays().get(0).getDay().getCondition().getIcon())
                    .resize(180,180)
                    .into(imgView);
            ForecastDay d;

            for(int i = 1; i<forecast.getForcastDays().size(); i++)
            {
               d = forecast.getForcastDays().get(i);
                fdays.add(d);
            }
            mAdapter = new WeatherAdapter(this,fdays);
            mRecyclerView.setAdapter(mAdapter);


        }
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

    public void getRetrofit(String place)
    {
        //Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        retrofitInterface = retrofit.create(RetrofitInterface.class);

        final Call<Wether> weatherData = retrofitInterface.getWeather(place);

        pbar.setVisibility(View.VISIBLE);

        weatherData.enqueue(new Callback<Wether>() {
            @Override
            public void onResponse(Response<Wether> response) {

                Wether wether = response.body();
                if(isOnline())
                {
                    getWeather(wether);
                   pbar.setVisibility(View.INVISIBLE);
                }else{
                    Toast.makeText(MainActivity.this, "Network is not available!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

                Toast.makeText(MainActivity.this, "Objet non recu!", Toast.LENGTH_LONG).show();
                pbar.setVisibility(View.INVISIBLE);

            }
        });
    }

}
