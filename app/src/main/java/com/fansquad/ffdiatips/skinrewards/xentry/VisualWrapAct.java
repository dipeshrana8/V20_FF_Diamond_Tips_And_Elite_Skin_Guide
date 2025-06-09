package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.fansquad.ffdiatips.skinrewards.databinding.ScreenSkinpanelxBinding;

import java.util.HashMap;
import java.util.Map;

public class VisualWrapAct extends CoreHostFx {


    ScreenSkinpanelxBinding screenSkinpanelxBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        titleBarTextDyn = "Style Collection";


        super.onCreate(savedInstanceState);
        screenSkinpanelxBinding = ScreenSkinpanelxBinding.inflate(getLayoutInflater());
        setContentView(screenSkinpanelxBinding.getRoot());

        initTitleBarUx(
                screenSkinpanelxBinding.toolbarLayout.headerTitle,
                screenSkinpanelxBinding.toolbarLayout.btnBack,
                screenSkinpanelxBinding.toolbarLayout.btnSettings
        );
        screenSkinpanelxBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());
        Map<View, Class<?>> guideMap = new HashMap<>();
        guideMap.put(screenSkinpanelxBinding.btnCharacter, AvatarViewAct.class);


        View.OnClickListener guideClickListener = v -> {
            Class<?> target = guideMap.get(v);
            if (target != null) {
                startActivity(new Intent(this, target));
            }
        };

        for (View view : guideMap.keySet()) {
            view.setOnClickListener(guideClickListener);
        }

        screenSkinpanelxBinding.btnPet.setOnClickListener(v -> {
            Intent intent = new Intent(VisualWrapAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Pet");
            startActivity(intent);
        });

        screenSkinpanelxBinding.btnBundle.setOnClickListener(v -> {
            Intent intent = new Intent(VisualWrapAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Bundel");
            startActivity(intent);
        });
        screenSkinpanelxBinding.btnWeapons.setOnClickListener(v -> {
            Intent intent = new Intent(VisualWrapAct.this, GearVaultAct.class);
            startActivity(intent);
        });

        screenSkinpanelxBinding.btnParachute.setOnClickListener(v -> {
            Intent intent = new Intent(VisualWrapAct.this, CharacterListActivity.class);
            intent.putExtra("SectionType", "Parachute");
            startActivity(intent);
        });

        screenSkinpanelxBinding.btnAppGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualWrapAct.this, AppGuideActivity.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}