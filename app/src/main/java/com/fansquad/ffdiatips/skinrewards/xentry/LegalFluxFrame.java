package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.os.Bundle;

import com.fansquad.ffdiatips.skinrewards.databinding.LegalAcceptformBinding;

public class LegalFluxFrame extends CoreHostFx {

    LegalAcceptformBinding legalAcceptformBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        legalAcceptformBinding = LegalAcceptformBinding.inflate(getLayoutInflater());
        setContentView(legalAcceptformBinding.getRoot());


        legalAcceptformBinding.btnNext.setOnClickListener(v -> {
            Intent intent = new Intent(LegalFluxFrame.this, LocaleTuneAct.class);
            startActivity(intent);
        });

    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}