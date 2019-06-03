package com.example.uv_index;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("data/2.5/uvi?")
    Call<ApiResponse> getCurrentUVData(@Query("lat") String lat, @Query("lon") String lon, @Query("AppID") String app_id)
}
