package com.example.prm392_flood_secure.data.remote;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    
    // AUTH
    @POST("api/auth/login")
    Call<BaseResponse<Object>> login(@Body Object loginRequest);
    
    @POST("api/auth/register")
    Call<BaseResponse<Object>> register(@Body Object registerRequest);
    
    @GET("api/auth/me")
    Call<BaseResponse<Object>> getCurrentUser();
    
    // REQUESTS (Citizen/Coordinator)
    @POST("api/requests")
    Call<BaseResponse<Object>> createRequest(@Body Object request);
    
    @GET("api/requests")
    Call<BaseResponse<List<Object>>> getAllRequests(@Query("page") int page, @Query("limit") int limit);
    
    @GET("api/requests/my")
    Call<BaseResponse<List<Object>>> getMyRequests(@Query("page") int page, @Query("limit") int limit);
    
    @PATCH("api/requests/{id}/verify")
    Call<BaseResponse<Object>> verifyRequest(@Path("id") String id, @Body Object body);
    
    // MISSIONS
    @GET("api/missions")
    Call<BaseResponse<List<Object>>> getMissions();
    
    // NOTIFICATIONS
    @GET("api/notifications")
    Call<BaseResponse<List<Object>>> getNotifications();
    
    @GET("api/notifications/unread-count")
    Call<BaseResponse<Object>> getUnreadNotificationCount();
}
