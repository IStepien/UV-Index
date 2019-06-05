package com.example.uv_index;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latEditText = findViewById(R.id.latitudeEditText);
        longEditText = findViewById(R.id.longitudeEditText);
        uvDataTextView = findViewById(R.id.UVDataTextView);
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
