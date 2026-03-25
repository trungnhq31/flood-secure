package com.example.prm392_flood_secure.ui.admin.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;
import com.example.prm392_flood_secure.domain.model.User;
import com.example.prm392_flood_secure.databinding.ActivityAdminUserListBinding;
import com.example.prm392_flood_secure.ui.base.BaseActivity;
import java.util.List;

public class AdminUserListActivity extends BaseActivity<ActivityAdminUserListBinding> implements UserAdapter.OnUserClickListener {

    private AdminUserViewModel viewModel;
    private UserAdapter adapter;
    private String currentRoleFilter = null;
    private String currentSearch = null;

    @Override
    protected ActivityAdminUserListBinding inflateBinding(LayoutInflater layoutInflater) {
        return ActivityAdminUserListBinding.inflate(layoutInflater);
    }

    @Override
    protected void setupUI() {
        viewModel = new ViewModelProvider(this).get(AdminUserViewModel.class);
        adapter = new UserAdapter(this);
        binding.rvUsers.setAdapter(adapter);

        setupRoleSpinner();
        setupSearchView();
        
        fetchUsers();
    }

    private void setupRoleSpinner() {
        String[] roles = {"All Roles", "Citizen", "Rescue Team", "Rescue Coordinator", "Manager", "Admin"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, roles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRole.setAdapter(spinnerAdapter);

        binding.spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentRoleFilter = position == 0 ? null : roles[position];
                fetchUsers();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    private void setupSearchView() {
        binding.searchView.setOnQueryTextListener(new androidx.appcompat.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                currentSearch = (query == null || query.isEmpty()) ? null : query;
                fetchUsers();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                currentSearch = (newText == null || newText.isEmpty()) ? null : newText;
                return false;
            }
        });
    }

    private void fetchUsers() {
        viewModel.fetchUsers(1, 100, currentSearch, currentRoleFilter);
    }

    @Override
    public void onUserClick(User user) {
        showUpdateRoleDialog(user);
    }

    private void showUpdateRoleDialog(User user) {
        String[] roles = {"Citizen", "Rescue Team", "Rescue Coordinator", "Manager", "Admin"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Role for " + user.getFullName());
        builder.setItems(roles, (dialog, which) -> {
            viewModel.updateUserRole(user.getId(), roles[which]);
        });
        builder.show();
    }

    @Override
    protected void setupObservers() {
        viewModel.users.observe(this, users -> {
            adapter.setUsers(users);
        });

        viewModel.isLoading.observe(this, isLoading -> {
            binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });

        viewModel.errorMessage.observe(this, message -> {
            if (message != null && !message.isEmpty()) {
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.roleUpdateSuccess.observe(this, success -> {
            if (success) {
                Toast.makeText(this, "Role updated successfully", Toast.LENGTH_SHORT).show();
                viewModel.resetRoleUpdateSuccess();
                fetchUsers();
            }
        });
    }
}
