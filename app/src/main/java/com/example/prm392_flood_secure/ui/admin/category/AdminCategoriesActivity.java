package com.example.prm392_flood_secure.ui.admin.category;

import android.view.LayoutInflater;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_flood_secure.databinding.ActivityAdminCategoriesBinding;
import com.example.prm392_flood_secure.ui.base.BaseActivity;
import java.util.List;

public class AdminCategoriesActivity extends BaseActivity<ActivityAdminCategoriesBinding> {

    private AdminCategoriesViewModel viewModel;
    private CategoryAdapter adapter;

    @Override
    protected ActivityAdminCategoriesBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityAdminCategoriesBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(this).get(AdminCategoriesViewModel.class);
        adapter = new CategoryAdapter();
        binding.rvCategories.setAdapter(adapter);
        fetchCategories();
    }

    private void fetchCategories() {
        viewModel.fetchCategories();
    }

    @Override
    protected void setupObservers() {
        viewModel.categories.observe(this, categories -> {
            adapter.setCategories(categories);
        });

        viewModel.errorMessage.observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
