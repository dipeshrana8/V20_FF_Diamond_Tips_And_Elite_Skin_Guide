package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.PanelInfoShellBinding;

import java.io.IOException;
import java.io.InputStream;

public class CoreDetailsFx extends CoreHostFx {

    PanelInfoShellBinding panelInfoShellBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        panelInfoShellBinding = PanelInfoShellBinding.inflate(getLayoutInflater());
        setContentView(panelInfoShellBinding.getRoot());
        panelInfoShellBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());

        enableSettingsNav = true;

        initTitleBarUx(
                panelInfoShellBinding.toolbarLayout.headerTitle,
                panelInfoShellBinding.toolbarLayout.btnBack,
                panelInfoShellBinding.toolbarLayout.btnSettings
        );

        panelInfoShellBinding.btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        panelInfoShellBinding.btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View dialogView = LayoutInflater.from(CoreDetailsFx.this).inflate(R.layout.modal_twentyfourcore, null);
                AlertDialog dialog = new AlertDialog.Builder(CoreDetailsFx.this)
                        .setView(dialogView)
                        .create();

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }


                dialogView.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences prefs = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
                        prefs.edit().putBoolean("spin_dialog_shown", true).apply();


                        boolean alreadyShown = prefs.getBoolean("spin_shown", false);
                        if (!alreadyShown) {
                            dialog.dismiss();
                            Intent intent = new Intent(CoreDetailsFx.this, MainCoreNav.class);
                            startActivity(intent);
                            finish();
                            overridePendingTransition(0, 0);
                        } else {

                            dialog.dismiss();
                            finish();
                            overridePendingTransition(0, 0);
                        }

                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
        SharedPreferences prefs = getSharedPreferences("bundle_data", MODE_PRIVATE);

        String str_name = prefs.getString("name", "");
        String str_desc = prefs.getString("desc", "");
        String str_ability = prefs.getString("ability", "");
        String imagePath = prefs.getString("imagePath", "");


        panelInfoShellBinding.toolbarLayout.headerTitle.setText(str_name);
        panelInfoShellBinding.txtNames.setText(str_name);
        panelInfoShellBinding.txtAbilities.setText(str_ability);
        panelInfoShellBinding.txtDesc.setText(str_desc);


        if (imagePath.length() != 0) {
            try {
                InputStream is = getAssets().open(imagePath);
                Drawable drawable = Drawable.createFromStream(is, null);

                Glide.with(this)
                        .load(drawable)
                        .into(panelInfoShellBinding.imgPreview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            panelInfoShellBinding.imgPreview.setImageResource(R.mipmap.drx_moziqon);
        }
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}