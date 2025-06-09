package com.fansquad.ffdiatips.skinrewards.xentry;

import static android.view.View.GONE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityPlayerLevelBinding;
import com.fansquad.ffdiatips.skinrewards.databinding.LangCardFrameBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.LocaleCore;

import java.util.ArrayList;
import java.util.List;

public class PlayerLevelActivity extends CoreHostFx {
    private final List<LocaleCore> localeCores = new ArrayList<>();
    boolean aBoolean = true;
    private ActivityPlayerLevelBinding activityRplevelBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityRplevelBinding = ActivityPlayerLevelBinding.inflate(getLayoutInflater());
        setContentView(activityRplevelBinding.getRoot());

        enableSettingsNav = true;

        activityRplevelBinding.toolbarLayout.headerTitle.setText("Player Level");
        activityRplevelBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        setupLanguageList();
        RPAdapter localeTuneAdapter = new RPAdapter(localeCores);


        activityRplevelBinding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        activityRplevelBinding.languageRecyclerView.setAdapter(localeTuneAdapter);


        activityRplevelBinding.btnDefault.setOnClickListener(v -> {
            if (aBoolean) {
                activityRplevelBinding.btnDefault.setImageResource(R.drawable.drx_moziqon46);
            } else {
                activityRplevelBinding.btnDefault.setImageResource(R.drawable.drx_moziqon48);
            }
            aBoolean = !aBoolean;
        });

        activityRplevelBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayerLevelActivity.this, PlayerIdActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        localeCores.add(new LocaleCore("Pro Player", "(Hello)", R.mipmap.drx_moziqon, true));
        localeCores.add(new LocaleCore("Mix Player", "(Hola)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("New Player", "(नमस्ते)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Casual Player", "(مرحبًا)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Basic Player", "(Olá)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Advance Player", "(Привет)", R.mipmap.drx_moziqon, false));

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

    public class RPAdapter extends RecyclerView.Adapter<RPAdapter.RPViewHolder> {

        private final List<LocaleCore> languageList;
        private int selectedPosition = 0;

        public RPAdapter(List<LocaleCore> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public RPAdapter.RPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            LangCardFrameBinding langCardFrameBinding = LangCardFrameBinding.inflate(inflater, parent, false);
            return new RPAdapter.RPViewHolder(langCardFrameBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull RPAdapter.RPViewHolder holder, int position) {
            LocaleCore localeCore = languageList.get(position);
            holder.langCardFrameBinding.languageName.setText(localeCore.getLanguageName());
            holder.langCardFrameBinding.languageFlag.setImageResource(localeCore.getFlagResId());
            holder.langCardFrameBinding.languagePreview.setVisibility(GONE);

            if (localeCore.isSelected()) {
                holder.langCardFrameBinding.getRoot().setBackgroundResource(R.drawable.drx_moziqon73);
                holder.langCardFrameBinding.imgSelect.setImageResource(R.drawable.drx_moziqon12);
                holder.langCardFrameBinding.languageName.setTextColor(getColor(R.color.drx_moziqon));
            } else {
                holder.langCardFrameBinding.getRoot().setBackgroundResource(R.drawable.drx_moziqon68);
                holder.langCardFrameBinding.imgSelect.setImageResource(R.drawable.drx_moziqon17);
                holder.langCardFrameBinding.languageName.setTextColor(getColor(R.color.drx_moziqonwhite));
            }

            holder.langCardFrameBinding.getRoot().setOnClickListener(v -> {
                if (selectedPosition != position) {
                    languageList.get(selectedPosition).setSelected(false);
                    localeCore.setSelected(true);
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

        class RPViewHolder extends RecyclerView.ViewHolder {
            LangCardFrameBinding langCardFrameBinding;

            RPViewHolder(LangCardFrameBinding binding) {
                super(binding.getRoot());
                this.langCardFrameBinding = binding;
            }
        }
    }
}