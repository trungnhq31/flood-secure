package com.example.prm392_flood_secure.ui.auth;

import android.content.Intent;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.prm392_flood_secure.data.local.SessionManager;
import com.example.prm392_flood_secure.databinding.ActivityLoginBinding;
import com.example.prm392_flood_secure.ui.admin.AdminActivity;
import com.example.prm392_flood_secure.ui.base.BaseActivity;

import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_flood_secure.domain.model.User;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private AuthViewModel viewModel;

    @Override
    protected ActivityLoginBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityLoginBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);
        
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }
            viewModel.login(email, password);
        });
    }

    @Override
    protected void setupObservers() {
        viewModel.isLoading.observe(this, isLoading -> {
            binding.btnLogin.setEnabled(!isLoading);
            binding.btnLogin.setText(isLoading ? "Logging in..." : "Login");
        });

        viewModel.errorMessage.observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_LONG).show();
            }
        });

        viewModel.loginResult.observe(this, result -> {
            if (result != null) {
                User user = result.getUser();
                if (user != null) {
                    Toast.makeText(this, "Welcome " + user.getFullName(), Toast.LENGTH_SHORT).show();
                    
                    // Navigate based on role
                    Intent intent;
                    if ("Admin".equalsIgnoreCase(user.getRole())) {
                        intent = new Intent(this, AdminActivity.class);
                    } else {
                        // Fallback for other roles or general dashboard
                        intent = new Intent(this, AdminActivity.class); 
                    }
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}
