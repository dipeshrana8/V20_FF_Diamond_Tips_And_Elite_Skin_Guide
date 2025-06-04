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
import com.fansquad.ffdiatips.skinrewards.databinding.LangCardFrameBinding;
import com.fansquad.ffdiatips.skinrewards.databinding.LangPickerMatrixBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.LocaleCore;

import java.util.ArrayList;
import java.util.List;

public class LocaleTuneAct extends CoreHostFx {
    private final List<LocaleCore> localeCores = new ArrayList<>();
    boolean aBoolean = true;
    private LangPickerMatrixBinding langPickerMatrixBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Choose Locale";
        super.onCreate(savedInstanceState);


        langPickerMatrixBinding = LangPickerMatrixBinding.inflate(getLayoutInflater());
        setContentView(langPickerMatrixBinding.getRoot());

        enableSettingsNav = true;


        setupLanguageList();
        LocaleTuneAdapter localeTuneAdapter = new LocaleTuneAdapter(localeCores);


        langPickerMatrixBinding.languageRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        langPickerMatrixBinding.languageRecyclerView.setAdapter(localeTuneAdapter);


        langPickerMatrixBinding.btnDefault.setOnClickListener(v -> {
            if (aBoolean) {
                langPickerMatrixBinding.btnDefault.setImageResource(R.drawable.drx_moziqon46);
            } else {
                langPickerMatrixBinding.btnDefault.setImageResource(R.drawable.drx_moziqon48);
            }
            aBoolean = !aBoolean;
        });

        langPickerMatrixBinding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocaleTuneAct.this, DiamonDexterAct.class);
                startActivity(intent);
            }
        });
    }

    private void setupLanguageList() {
        localeCores.add(new LocaleCore("English", "(Hello)", R.mipmap.drx_moziqon, true));
        localeCores.add(new LocaleCore("Spanish", "(Hola)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Hindi", "(नमस्ते)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Arabic", "(مرحبًا)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Portuguese ", "(Olá)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Russian", "(Привет)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("French", "(Bonjour)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Chinese", "(你好)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Bengali", "(হ্যালো)", R.mipmap.drx_moziqon, false));
        localeCores.add(new LocaleCore("Indonesian", "(Halo)", R.mipmap.drx_moziqon, false));
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

    public class LocaleTuneAdapter extends RecyclerView.Adapter<LocaleTuneAdapter.LanguageViewHolder> {

        private final List<LocaleCore> languageList;
        private int selectedPosition = 0;

        public LocaleTuneAdapter(List<LocaleCore> languageList) {
            this.languageList = languageList;
            if (!languageList.isEmpty()) languageList.get(0).setSelected(true);
        }

        @NonNull
        @Override
        public LanguageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            LangCardFrameBinding langCardFrameBinding = LangCardFrameBinding.inflate(inflater, parent, false);
            return new LanguageViewHolder(langCardFrameBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull LanguageViewHolder holder, int position) {
            LocaleCore localeCore = languageList.get(position);
            holder.langCardFrameBinding.languageName.setText(localeCore.getLanguageName());
            holder.langCardFrameBinding.languageFlag.setImageResource(localeCore.getFlagResId());
            holder.langCardFrameBinding.languagePreview.setText(localeCore.getHello());

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