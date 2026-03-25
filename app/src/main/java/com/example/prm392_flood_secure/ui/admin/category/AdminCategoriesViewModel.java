package com.example.prm392_flood_secure.ui.admin.category;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_flood_secure.domain.model.Category;
import com.example.prm392_flood_secure.data.remote.BaseResponse;
import com.example.prm392_flood_secure.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminCategoriesViewModel extends ViewModel {

    private final MutableLiveData<List<Category>> _categories = new MutableLiveData<>();
    public LiveData<List<Category>> categories = _categories;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    public void fetchCategories() {
        _isLoading.setValue(true);
        RetrofitClient.getApiService().getCategories().enqueue(new Callback<BaseResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Category>>> call, Response<BaseResponse<List<Category>>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    _categories.setValue(response.body().getData());
                } else {
                    String errorMsg = "Failed to fetch categories";
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
            public void onFailure(Call<BaseResponse<List<Category>>> call, Throwable t) {
                _isLoading.setValue(false);
                _errorMessage.setValue("Network error");
            }
        });
    }
}
