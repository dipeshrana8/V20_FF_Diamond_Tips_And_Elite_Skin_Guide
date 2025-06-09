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
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityGameModeBinding;
import com.fansquad.ffdiatips.skinrewards.databinding.LangCardFrameBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.LocaleCore;

import java.util.ArrayList;
import java.util.List;

public class GameModeActivity extends CoreHostFx {
    private final List<LocaleCore> localeCores = new ArrayList<>();
    boolean aBoolean = true;
    private ActivityGameModeBinding activityGameModeBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        activityGameModeBinding = ActivityGameModeBinding.inflate(getLayoutInflater());
        setContentView(activityGameModeBinding.getRoot());

        enableSettingsNav = true;

        activityGameModeBinding.toolbarLayout.headerTitle.setText("Game Mode");
        activityGameModeBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        setupLanguageList();
        GameModeAdapter localeTuneAdapter = new GameModeAdapter(localeCores);


        activityGameModeBinding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        activityGameModeBinding.languageRecyclerView.setAdapter(localeTuneAdapter);


        activityGameModeBinding.btnDefault.setOnClickListener(v -> {
            if (aBoolean) {
                activityGameModeBinding.btnDefault.setImageResource(R.drawable.drx_moziqon46);
            } else {
                activityGameModeBinding.btnDefault.setImageResource(R.drawable.drx_moziqon48);
            }
            aBoolean = !aBoolean;
        });

        activityGameModeBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameModeActivity.this, RPLevelActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        localeCores.add(new LocaleCore("BR Rankd Mode", "(Hello)", R.mipmap.drx_moziqon, true));
        localeCores.add(new LocaleCore("CS Ranked Mode", "(Hola)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Battle Royal", "(नमस्ते)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Clash Squad", "(مرحبًا)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Lone Wolf ", "(Olá)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Zombie Hunt", "(Привет)", R.mipmap.drx_moziqon, false));

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

    public class GameModeAdapter extends RecyclerView.Adapter<GameModeAdapter.LanguageViewHolder> {

        private final List<LocaleCore> languageList;
        private int selectedPosition = 0;

        public GameModeAdapter(List<LocaleCore> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public GameModeAdapter.LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            LangCardFrameBinding langCardFrameBinding = LangCardFrameBinding.inflate(inflater, parent, false);
            return new GameModeAdapter.LanguageViewHolder(langCardFrameBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull GameModeAdapter.LanguageViewHolder holder, int position) {
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

        class LanguageViewHolder extends RecyclerView.ViewHolder {
            LangCardFrameBinding langCardFrameBinding;

            LanguageViewHolder(LangCardFrameBinding binding) {
                super(binding.getRoot());
                this.langCardFrameBinding = binding;
            }
        }
    }
}