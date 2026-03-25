package com.example.prm392_flood_secure.ui.admin.config;

import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_flood_secure.databinding.ActivityAdminConfigBinding;
import com.example.prm392_flood_secure.ui.base.BaseActivity;
import java.util.List;

public class AdminConfigActivity extends BaseActivity<ActivityAdminConfigBinding> {

    private AdminConfigViewModel viewModel;
    private ConfigAdapter adapter;

    @Override
    protected ActivityAdminConfigBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityAdminConfigBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(this).get(AdminConfigViewModel.class);
        adapter = new ConfigAdapter();
        binding.rvConfig.setAdapter(adapter);
        fetchConfig();
    }

    private void fetchConfig() {
        viewModel.fetchConfig();
    }

    @Override
    protected void setupObservers() {
        viewModel.configs.observe(this, configs -> {
            adapter.setConfigs(configs);
        });

        viewModel.errorMessage.observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
