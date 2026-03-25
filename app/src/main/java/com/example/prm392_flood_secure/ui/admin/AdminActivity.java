package com.example.prm392_flood_secure.ui.admin;

import android.content.Intent;
import android.view.LayoutInflater;
import com.example.prm392_flood_secure.databinding.ActivityAdminBinding;
import com.example.prm392_flood_secure.ui.admin.category.AdminCategoriesActivity;
import com.example.prm392_flood_secure.ui.admin.config.AdminConfigActivity;
import com.example.prm392_flood_secure.ui.admin.user.AdminUserListActivity;
import com.example.prm392_flood_secure.ui.base.BaseActivity;

public class AdminActivity extends BaseActivity<ActivityAdminBinding> {

    @Override
    protected ActivityAdminBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityAdminBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        binding.cardUsers.setOnClickListener(v -> {
            startActivity(new Intent(this, AdminUserListActivity.class));
        });

        binding.cardCategories.setOnClickListener(v -> {
            startActivity(new Intent(this, AdminCategoriesActivity.class));
        });

        binding.cardConfig.setOnClickListener(v -> {
            startActivity(new Intent(this, AdminConfigActivity.class));
        });
    }

    @Override
    protected void setupObservers() {
        // No observers needed for simple dashboard
    }
}
