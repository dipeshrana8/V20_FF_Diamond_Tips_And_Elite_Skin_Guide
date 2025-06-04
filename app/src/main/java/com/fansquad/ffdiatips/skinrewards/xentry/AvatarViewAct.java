package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.CoreActorGridxBinding;

public class AvatarViewAct extends CoreHostFx {

    CoreActorGridxBinding coreActorGridxBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coreActorGridxBinding = CoreActorGridxBinding.inflate(getLayoutInflater());
        setContentView(coreActorGridxBinding.getRoot());


        titleBarTextDyn = "Game Persona";


        initTitleBarUx(
                coreActorGridxBinding.toolbarLayout.headerTitle,
                coreActorGridxBinding.toolbarLayout.btnBack,
                coreActorGridxBinding.toolbarLayout.btnSettings
        );
        coreActorGridxBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        coreActorGridxBinding.btnCharacterMale.setOnClickListener(v -> {
            Intent intent = new Intent(AvatarViewAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Male");
            startActivity(intent);
        });
        coreActorGridxBinding.btnCharacterFemale.setOnClickListener(v -> {
            Intent intent = new Intent(AvatarViewAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Female");
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}
