package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.GearEnumShellBinding;

public class GearVaultAct extends CoreHostFx {

    GearEnumShellBinding gearEnumShellBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Battle Tools";
        enableSettingsNav = true;
        super.onCreate(savedInstanceState);


        gearEnumShellBinding = GearEnumShellBinding.inflate(getLayoutInflater());
        setContentView(gearEnumShellBinding.getRoot());
        gearEnumShellBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        initTitleBarUx(
                gearEnumShellBinding.toolbarLayout.headerTitle,
                gearEnumShellBinding.toolbarLayout.btnBack,
                gearEnumShellBinding.toolbarLayout.btnSettings
        );
        gearEnumShellBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());


        gearEnumShellBinding.btnWeaponsGuns.setOnClickListener(v -> {
            Intent intent = new Intent(GearVaultAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Guns");
            startActivity(intent);
        });


        gearEnumShellBinding.btnProjectiles.setOnClickListener(v -> {
            Intent intent = new Intent(GearVaultAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Projectiles");
            startActivity(intent);
        });


    }


    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }

}