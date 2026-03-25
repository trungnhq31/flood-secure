package com.example.prm392_flood_secure.ui.admin.config;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_flood_secure.domain.model.SystemConfig;
import com.example.prm392_flood_secure.data.remote.BaseResponse;
import com.example.prm392_flood_secure.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminConfigViewModel extends ViewModel {

    private final MutableLiveData<List<SystemConfig>> _configs = new MutableLiveData<>();
    public LiveData<List<SystemConfig>> configs = _configs;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    public void fetchConfig() {
        _isLoading.setValue(true);
        RetrofitClient.getApiService().getSystemConfig().enqueue(new Callback<BaseResponse<List<SystemConfig>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<SystemConfig>>> call, Response<BaseResponse<List<SystemConfig>>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    _configs.setValue(response.body().getData());
                } else {
                    String errorMsg = "Failed to fetch configuration";
                    try {
                        if (response.errorBody() != null) {
                            errorMsg = "Error " + response.code() + ": " + response.errorBody().string();
                        } else {
                            errorMsg = "Error " + response.code();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    _errorMessage.setValue(errorMsg);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<SystemConfig>>> call, Throwable t) {
                _isLoading.setValue(false);
                _errorMessage.setValue("Network error");
            }
        });
    }
}
