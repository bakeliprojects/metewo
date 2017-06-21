package parser;

import model.Wether;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by Mobile App Develop on 4/17/2017.
 */

public interface RetrofitInterface {
    @GET("v1/forecast.json?key=e10f2a2773ae47d4ac1131057171606&q=Dakar&days=7")
    Call<Wether> getWeather();

    @GET("v1/forecast.json?key=e10f2a2773ae47d4ac1131057171606&days=7")
    Call<Wether> getWeather(@Query("q") String  wetherRegion);

}
