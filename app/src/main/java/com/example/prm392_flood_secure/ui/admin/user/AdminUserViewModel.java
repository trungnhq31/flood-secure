package com.example.prm392_flood_secure.ui.admin.user;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.prm392_flood_secure.domain.model.UpdateRoleRequest;
import com.example.prm392_flood_secure.domain.model.User;
import com.example.prm392_flood_secure.data.remote.BaseResponse;
import com.example.prm392_flood_secure.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminUserViewModel extends ViewModel {

    private final MutableLiveData<List<User>> _users = new MutableLiveData<>();
    public LiveData<List<User>> users = _users;

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading = _isLoading;

    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();
    public LiveData<String> errorMessage = _errorMessage;

    private final MutableLiveData<Boolean> _roleUpdateSuccess = new MutableLiveData<>();
    public LiveData<Boolean> roleUpdateSuccess = _roleUpdateSuccess;

    public void fetchUsers(int page, int limit, String search, String role) {
        _isLoading.setValue(true);
        RetrofitClient.getApiService().getUsers(page, limit, search, role).enqueue(new Callback<BaseResponse<List<User>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<User>>> call, Response<BaseResponse<List<User>>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful() && response.body() != null) {
                    _users.setValue(response.body().getData());
                } else {
                    String errorMsg = "Failed to fetch users";
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
            public void onFailure(Call<BaseResponse<List<User>>> call, Throwable t) {
                _isLoading.setValue(false);
                _errorMessage.setValue("Network error: " + t.getMessage());
            }
        });
    }

    public void updateUserRole(String userId, String newRole) {
        _isLoading.setValue(true);
        RetrofitClient.getApiService().updateUserRole(userId, new UpdateRoleRequest(newRole)).enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                _isLoading.setValue(false);
                if (response.isSuccessful()) {
                    _roleUpdateSuccess.setValue(true);
                } else {
                    _errorMessage.setValue("Failed to update role");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<User>> call, Throwable t) {
                _isLoading.setValue(false);
                _errorMessage.setValue("Network error: " + t.getMessage());
            }
        });
    }

    public void resetRoleUpdateSuccess() {
        _roleUpdateSuccess.setValue(false);
    }
}
