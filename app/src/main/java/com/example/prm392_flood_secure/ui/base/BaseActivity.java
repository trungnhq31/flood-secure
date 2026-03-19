package com.example.prm392_flood_secure.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

public abstract class BaseActivity<VB extends ViewBinding> extends AppCompatActivity {
    
    protected VB binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = inflateBinding(getLayoutInflater());
        setContentView(binding.getRoot());
        setupUI();
        setupObservers();
    }

    /**
     * Inflate the specific ViewBinding for this Activity
     * Example: return ActivityLoginBinding.inflate(layoutInflater);
     */
    protected abstract VB inflateBinding(LayoutInflater layoutInflater);

    /**
     * Setup UI interactions, listeners, adapters 
     */
    protected abstract void setupUI();

    /**
     * Setup ViewModel LiveData observers
     */
    protected abstract void setupObservers();
}
