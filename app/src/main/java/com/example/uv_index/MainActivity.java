package com.example.uv_index;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.uv_index.model.ResponseResult;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText latEditText;
    private EditText longEditText;
    private TextView uvDataTextView;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latEditText = findViewById(R.id.latitudeEditText);
        longEditText = findViewById(R.id.longitudeEditText);
        uvDataTextView = findViewById(R.id.UVDataTextView);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                Log.i("LOCATION", location.toString());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

            }
        }
    }

    public void getUVData(View view) {

        ApiService apiService = RetrofitInstance.getService();
        Call<ResponseResult> call = apiService.getCurrentUVData(latEditText.getText().toString(), longEditText.getText().toString());


        call.enqueue(new Callback<ResponseResult>() {
            @Override
            public void onResponse(Call call, Response response) {

                if (response.body() != null) {
                    ResponseResult responseResult = (ResponseResult) response.body();

                    DecimalFormat formatedUvValue = new DecimalFormat("0.0");
                    String uvValue = formatedUvValue.format(responseResult.getResult().getUv());

                    uvDataTextView.setText(uvValue);
                    setBackgroundColor(responseResult.getResult().getUv());

                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });


    }

    private void setBackgroundColor(Double uvValue) {

        if (uvValue >= 0 && uvValue < 3) {

            uvDataTextView.setBackgroundResource(R.color.colorUvLevelLow);
        }
        if (uvValue >= 3 && uvValue < 6) {


            uvDataTextView.setBackgroundResource(R.color.colorUvLevelModerate);
        }
        if (uvValue >= 6 && uvValue < 8) {


            uvDataTextView.setBackgroundResource(R.color.colorUvLevelHigh);
        }
        if (uvValue >= 8 && uvValue < 11) {


            uvDataTextView.setBackgroundResource(R.color.colorUvLevelVeryHigh);
        }
        if (uvValue >= 11) {


            uvDataTextView.setBackgroundResource(R.color.colorUvLevelExtreme);
        }


    }
}
