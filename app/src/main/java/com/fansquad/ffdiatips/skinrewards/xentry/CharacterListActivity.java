package com.fansquad.ffdiatips.skinrewards.xentry;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.databinding.ScreenHeroEnumBinding;
import com.fansquad.ffdiatips.skinrewards.datazone.AvatarBinder;
import com.fansquad.ffdiatips.skinrewards.datazone.AvatarMeta;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CharacterListActivity extends CoreHostFx {
    private ScreenHeroEnumBinding screenHeroEnumBinding;
    private ArrayList<AvatarMeta> avatarMetas;
    private AvatarBinder avatarBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String category = getIntent().getStringExtra("SectionType");
        titleBarTextDyn = category;
        enableSettingsNav = true;
        super.onCreate(savedInstanceState);

        screenHeroEnumBinding = ScreenHeroEnumBinding.inflate(getLayoutInflater());
        setContentView(screenHeroEnumBinding.getRoot());
        screenHeroEnumBinding.toolbarLayout.btnBack.setOnClickListener(v -> exitWithBridgeAd());

        initTitleBarUx(screenHeroEnumBinding.toolbarLayout.headerTitle, screenHeroEnumBinding.toolbarLayout.btnBack, screenHeroEnumBinding.toolbarLayout.btnSettings);

        avatarMetas = loadCharactersFromJson(category);
        setupRecyclerView();
    }

    private ArrayList<AvatarMeta> loadCharactersFromJson(String category) {
        ArrayList<AvatarMeta> characters = new ArrayList<>();
        try {
            String json = loadJSONFromAsset("avt_deck.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray categories = jsonObject.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                JSONObject cat = categories.getJSONObject(i);
                if (cat.getString("name").equals(category)) {
                    JSONArray chars = cat.getJSONArray("characters");
                    for (int j = 0; j < chars.length(); j++) {
                        JSONObject character = chars.getJSONObject(j);
                        characters.add(new AvatarMeta(
                                character.getString("name"),
                                character.getString("description"),
                                character.getString("ability"),
                                character.getString("imagePath")
                        ));
                    }
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return characters;
    }

    private String loadJSONFromAsset(String fileName) {
        String json = null;
        try {
            InputStream is = getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = screenHeroEnumBinding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        avatarBinder = new AvatarBinder(avatarMetas, character -> {
            SharedPreferences prefs = getSharedPreferences("bundle_data", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", character.getName())
                    .putString("desc", character.getDescription())
                    .putString("ability", character.getAbility())
                    .putString("imagePath", character.getImagePath())
                    .apply();
            Intent intent = new Intent(CharacterListActivity.this, GameModeActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(avatarBinder);
    }

    @Override
    public void onBackPressed() {
        exitWithBridgeAd();
    }
}