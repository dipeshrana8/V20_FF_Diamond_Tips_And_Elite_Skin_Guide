package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.ActivityDetailsBinding;

import java.io.IOException;
import java.io.InputStream;

public class A21_DetailsActivity extends BaseActivity {

    ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        showSettings = true;

        setupToolbar(
                binding.toolbarLayout.headerTitle,
                binding.toolbarLayout.btnBack,
                binding.toolbarLayout.btnSettings
        );

        binding.btnSeeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(0, 0);
            }
        });

        binding.btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                View dialogView = LayoutInflater.from(A21_DetailsActivity.this).inflate(R.layout.dialog_active, null);
                AlertDialog dialog = new AlertDialog.Builder(A21_DetailsActivity.this)
                        .setView(dialogView)
                        .create();

                if (dialog.getWindow() != null) {
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                }
                TextView etCal = dialogView.findViewById(R.id.txtCollect);
                etCal.setText("Your Claim Activated in 24 to 48 Hours.");
                dialogView.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                        prefs.edit().putBoolean("spin_dialog_shown", true).apply();


                        boolean alreadyShown = prefs.getBoolean("spin_shown", false);
                        if (!alreadyShown) {
                            dialog.dismiss();
                            Intent intent = new Intent(A21_DetailsActivity.this, HomeActivity.class);
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


        binding.toolbarLayout.headerTitle.setText(str_name);
        binding.txtNames.setText(str_name);
        binding.txtAbilities.setText(str_ability);
        binding.txtDesc.setText(str_desc);


        if (imagePath.length() != 0) {
            try {
                InputStream is = getAssets().open(imagePath);
                Drawable drawable = Drawable.createFromStream(is, null);

                Glide.with(this)
                        .load(drawable)
                        .into(binding.imgPreview);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            binding.imgPreview.setImageResource(R.mipmap.ic_launcher);
        }
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}