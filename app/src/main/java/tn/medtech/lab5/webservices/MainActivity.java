package tn.medtech.lab5.webservices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import tn.medtech.lab5.R;
import tn.medtech.lab5.weather.MyWeather;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView city, status, humidity, press;
    Spinner countries;
    String[] countryNames = new String[]{"Tunisia", "France", "Italy"};
    RestInterface restInterface;
    Call<MyWeather> call;
    MyWeather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Lab5-MootezSaad");
        countries = (Spinner) findViewById(R.id.countries);
        countries.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, countryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        countries.setAdapter(adapter);


        city = (TextView) findViewById(R.id.txcity);
        status = (TextView) findViewById(R.id.txstatus);
        humidity = (TextView) findViewById(R.id.txhumidity);
        press = (TextView) findViewById(R.id.txpressure);


    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        restInterface = RestInterface.retrofit.create(RestInterface.class);
        call = restInterface.getWeatherReport(countryNames[position]);

        call.enqueue(new Callback<MyWeather>() {
            @Override
            public void onResponse(Call<MyWeather> call, Response<MyWeather> response) {
                weather = response.body();
                Log.d("Info",response.body().getName());
                city.setText("City: " + weather.getName());
                status.setText("Status: " + weather.getWeather().get(0).getDescription());
                humidity.setText("Humidity: " + weather.getMain().getHumidity().toString());
                press.setText("Pressure: " + weather.getMain().getPressure().toString());


            }

            @Override
            public void onFailure(Call<MyWeather> call, Throwable t) {
                try {
                    throw t;
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
