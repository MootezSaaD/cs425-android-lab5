package tn.medtech.lab5.webservices;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import tn.medtech.lab5.weather.MyWeather;

public interface RestInterface {

    @GET("weather?APPID=17db59488cadcad345211c36304a9266")
    Call<MyWeather> getWeatherReport(
            @Query("q") String countryName

    );

    public static final String url = "https://api.openweathermap.org/data/2.5/";
    public static Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create()).build();

}
