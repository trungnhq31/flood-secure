package com.example.prm392_flood_secure.ui.auth;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_flood_secure.MyApplication;
import com.example.prm392_flood_secure.data.local.SessionManager;
import com.example.prm392_flood_secure.data.remote.BaseResponse;
import com.example.prm392_flood_secure.data.remote.RetrofitClient;
import com.example.prm392_flood_secure.domain.model.LoginRequest;
import com.example.prm392_flood_secure.domain.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final MutableLiveData<LoginResponse> _loginResult = new MutableLiveData<>();
    public LiveData<LoginResponse> loginResult = _loginResult;

    public void login(String email, String password) {
        _isLoading.setValue(true);
        LoginRequest request = new LoginRequest(email, password);
        
        RetrofitClient.getApiService().login(request).enqueue(new Callback<BaseResponse<LoginResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<LoginResponse>> call, Response<BaseResponse<LoginResponse>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    LoginResponse data = response.body().getData();
                    SessionManager.getInstance(MyApplication.getAppContext()).saveToken(data.getAccessToken());
                    _loginResult.setValue(data);
                } else {
                    String error = "Login failed";
                    try {
                        if (response.errorBody() != null) {
                            error = "Error: " + response.errorBody().string();
                        } else if (response.body() != null) {
                            error = response.body().getMessage();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    _errorMessage.setValue(error);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<LoginResponse>> call, Throwable t) {
                _isLoading.setValue(false);
                _errorMessage.setValue("Connection error: " + t.getMessage());
            }
        });
    }
}
