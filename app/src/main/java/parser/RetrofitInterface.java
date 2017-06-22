package parser;

import model.Wether;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by Cheikh DG.
 */

public interface RetrofitInterface {
    @GET("v1/forecast.json?key=e10f2a2773ae47d4ac1131057171606&q=Dakar&days=5")
    Call<Wether> getWeather();

    @GET("v1/forecast.json?key=e10f2a2773ae47d4ac1131057171606&days=5")
    Call<Wether> getWeather(@Query("q") String  wetherRegion);

}
