package com.example.prm392_flood_secure.ui.auth;

import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.prm392_flood_secure.databinding.ActivityLoginBinding;
import com.example.prm392_flood_secure.ui.base.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected ActivityLoginBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityLoginBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                return;
            }
            // TODO: ViewModel call to network authenticating via API
            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void setupObservers() {
        // Observe ViewModel states here
    }
}
