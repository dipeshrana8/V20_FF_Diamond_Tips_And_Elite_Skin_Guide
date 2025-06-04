package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.RowActorBriefBinding;
import com.fansquad.ffdiatips.skinrewards.databinding.UiAvatarChooserBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.LocaleCore;

import java.util.ArrayList;
import java.util.List;

public class CharacterSelectActivity extends CoreHostFx {
    private final List<LocaleCore> localeCores = new ArrayList<>();
    private UiAvatarChooserBinding uiAvatarChooserBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Choose Locale";
        super.onCreate(savedInstanceState);


        uiAvatarChooserBinding = UiAvatarChooserBinding.inflate(getLayoutInflater());
        setContentView(uiAvatarChooserBinding.getRoot());

        enableSettingsNav = true;


        setupLanguageList();
        LanguageAdapter adapter = new LanguageAdapter(localeCores);


        uiAvatarChooserBinding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        uiAvatarChooserBinding.languageRecyclerView.setAdapter(adapter);


        uiAvatarChooserBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CharacterSelectActivity.this, MainCoreNav.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        localeCores.add(new LocaleCore("Ford", "(Hello)", R.drawable.drx_moziqon4, true));
        localeCores.add(new LocaleCore("Kapella", "(Hola)", R.drawable.drx_moziqon6, false));
        localeCores.add(new LocaleCore("Kairos", "(नमस्ते)", R.drawable.drx_moziqon5, false));
        localeCores.add(new LocaleCore("Luna", "(مرحبًا)", R.drawable.drx_moziqon7, false));
        localeCores.add(new LocaleCore("Oscar ", "(Olá)", R.drawable.drx_moziqon11, false));
        localeCores.add(new LocaleCore("Olivia", "(Привет)", R.drawable.drx_moziqon9, false));
        localeCores.add(new LocaleCore("Kapella", "(Bonjour)", R.drawable.drx_moziqon6, false));
        localeCores.add(new LocaleCore("Notora", "(你好)", R.drawable.drx_moziqon8, false));
        localeCores.add(new LocaleCore("Orion", "(হ্যালো)", R.drawable.drx_moziqon10, false));
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

    public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder> {

        private final List<LocaleCore> languageList;
        private int selectedPosition = 0;

        public LanguageAdapter(List<LocaleCore> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            RowActorBriefBinding binding = RowActorBriefBinding.inflate(inflater, parent, false);
            return new LanguageViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
            LocaleCore model = languageList.get(position);
            holder.rowActorBriefBinding.languageName.setText(model.getLanguageName());
            holder.rowActorBriefBinding.languageFlag.setImageResource(model.getFlagResId());

            holder.rowActorBriefBinding.languageName.setSelected(true);
            if (model.isSelected()) {
                holder.rowActorBriefBinding.getRoot().setBackgroundResource(R.drawable.drx_moziqon73);
                holder.rowActorBriefBinding.imgSelect.setImageResource(R.drawable.drx_moziqon12);
                holder.rowActorBriefBinding.languageName.setTextColor(getColor(R.color.drx_moziqon));
            } else {
                holder.rowActorBriefBinding.getRoot().setBackgroundResource(R.drawable.drx_moziqon68);
                holder.rowActorBriefBinding.imgSelect.setImageResource(R.drawable.drx_moziqon17);
                holder.rowActorBriefBinding.languageName.setTextColor(getColor(R.color.drx_moziqonwhite));
            }

            holder.rowActorBriefBinding.getRoot().setOnClickListener(v -> {
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
            RowActorBriefBinding rowActorBriefBinding;

            LanguageViewHolder(RowActorBriefBinding binding) {
                super(binding.getRoot());
                this.rowActorBriefBinding = binding;
            }
        }
    }
}