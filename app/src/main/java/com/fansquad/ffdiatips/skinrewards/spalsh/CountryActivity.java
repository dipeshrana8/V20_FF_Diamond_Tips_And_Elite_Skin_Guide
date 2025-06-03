package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityLanguagesBinding;
import com.fansquad.ffdiatips.skinrewards.databinding.ItemLanguageBinding;
import com.fansquad.ffdiatips.skinrewards.model.LanguageModel;

import java.util.ArrayList;
import java.util.List;

public class CountryActivity extends BaseActivity {
    private final List<LanguageModel> languageList = new ArrayList<>();
    private ActivityLanguagesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        toolbarHeaderText = "Select Languages";
        super.onCreate(savedInstanceState);


        binding = ActivityLanguagesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showSettings = true;


        setupLanguageList();
        LanguageAdapter adapter = new LanguageAdapter(languageList);


        binding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        binding.languageRecyclerView.setAdapter(adapter);


        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CountryActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        languageList.add(new LanguageModel("Hindi", R.drawable.img_hindi, true));
        languageList.add(new LanguageModel("Japan", R.drawable.img_japan, false));
        languageList.add(new LanguageModel("Korea", R.drawable.img_korea, false));
        languageList.add(new LanguageModel("French", R.drawable.img_french, false));
        languageList.add(new LanguageModel("German", R.drawable.img_german, false));
        languageList.add(new LanguageModel("Russian", R.drawable.img_russian, false));
        languageList.add(new LanguageModel("Italian", R.drawable.img_italian, false));
        languageList.add(new LanguageModel("Chinese", R.drawable.img_chinese, false));
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }

    public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

        private final List<LanguageModel> languageList;
        private int selectedPosition = 0;

        public LanguageAdapter(List<LanguageModel> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            ItemLanguageBinding binding = ItemLanguageBinding.inflate(inflater, parent, false);
            return new LanguageViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
            LanguageModel model = languageList.get(position);
            holder.binding.languageName.setText(model.getLanguageName());
            holder.binding.languageFlag.setImageResource(model.getFlagResId());

            if (model.isSelected()) {
                holder.binding.getRoot().setBackgroundResource(R.drawable.rectangle_country);
                holder.binding.imgSelect.setImageResource(R.drawable.ic_selected);
                holder.binding.languageName.setTextColor(getColor(R.color.white));
            } else {
                holder.binding.getRoot().setBackgroundResource(R.drawable.rectangle_country);
                holder.binding.imgSelect.setImageResource(R.drawable.ic_unselected);
                holder.binding.languageName.setTextColor(getColor(R.color.white));
            }

            holder.binding.getRoot().setOnClickListener(v -> {
                if (selectedPosition != position) {
                    languageList.get(selectedPosition).setSelected(false);
                    model.setSelected(true);
                    notifyItemChanged(selectedPosition);
                    notifyItemChanged(position);
                    selectedPosition = position;
                }
            });
        }


        @Override
        public int getItemCount() {
            return languageList.size();
        }

        class LanguageViewHolder extends RecyclerView.ViewHolder {
            ItemLanguageBinding binding;

            LanguageViewHolder(ItemLanguageBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }
        }
    }
}