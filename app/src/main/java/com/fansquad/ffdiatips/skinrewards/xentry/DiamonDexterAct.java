package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.fansquad.ffdiatips.skinrewards.R;
import com.fansquad.ffdiatips.skinrewards.databinding.DiscoveryRootpanelBinding;

public class DiamonDexterAct extends CoreHostFx {

    DiscoveryRootpanelBinding discoveryRootpanelBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        discoveryRootpanelBinding = DiscoveryRootpanelBinding.inflate(getLayoutInflater());
        setContentView(discoveryRootpanelBinding.getRoot());
        Glide.with(this)
                .asGif()
                .load(R.drawable.sample_gif)  // Use your GIF file here
                .into(discoveryRootpanelBinding.imgGif);

        discoveryRootpanelBinding.imgGif.setOnClickListener(v -> {
            Intent intent = new Intent(DiamonDexterAct.this, BonusFetchAct.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}