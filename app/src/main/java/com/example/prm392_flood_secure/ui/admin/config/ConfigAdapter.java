package com.example.prm392_flood_secure.ui.admin.config;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prm392_flood_secure.domain.model.SystemConfig;
import com.example.prm392_flood_secure.databinding.ItemConfigBinding;
import java.util.ArrayList;
import java.util.List;

public class ConfigAdapter extends RecyclerView.Adapter<ConfigAdapter.ConfigViewHolder> {

    private List<SystemConfig> configs = new ArrayList<>();

    public void setConfigs(List<SystemConfig> configs) {
        this.configs = configs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ConfigViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemConfigBinding binding = ItemConfigBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ConfigViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfigViewHolder holder, int position) {
        holder.bind(configs.get(position));
    }

    @Override
    public int getItemCount() {
        return configs.size();
    }

    static class ConfigViewHolder extends RecyclerView.ViewHolder {
        private final ItemConfigBinding binding;

        public ConfigViewHolder(ItemConfigBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(SystemConfig config) {
            binding.tvConfigKey.setText(config.getKey());
            binding.tvConfigValue.setText(config.getValue());
            binding.tvConfigDescription.setText(config.getDescription());
        }
    }
}
