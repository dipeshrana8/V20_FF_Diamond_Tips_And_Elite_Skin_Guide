package com.fansquad.ffdiatips.skinrewards.spalsh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fansquad.ffdiatips.skinrewards.databinding.ActivityCharacterListBinding;
import com.fansquad.ffdiatips.skinrewards.model.Character;
import com.fansquad.ffdiatips.skinrewards.model.CharacterAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class CharacterListActivity extends BaseActivity {
    private ActivityCharacterListBinding binding;
    private ArrayList<com.fansquad.ffdiatips.skinrewards.model.Character> characterList;
    private CharacterAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String category = getIntent().getStringExtra("category");
        toolbarHeaderText = category;
        showSettings = true;
        super.onCreate(savedInstanceState);

        binding = ActivityCharacterListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.toolbarLayout.btnBack.setOnClickListener(v -> myBackActivity());

        setupToolbar(binding.toolbarLayout.headerTitle, binding.toolbarLayout.btnBack, binding.toolbarLayout.btnSettings);

        characterList = loadCharactersFromJson(category);
        setupRecyclerView();
    }

    private ArrayList<com.fansquad.ffdiatips.skinrewards.model.Character> loadCharactersFromJson(String category) {
        ArrayList<com.fansquad.ffdiatips.skinrewards.model.Character> characters = new ArrayList<>();
        try {
            String json = loadJSONFromAsset("characters.json");
            JSONObject jsonObject = new JSONObject(json);
            JSONArray categories = jsonObject.getJSONArray("categories");
            for (int i = 0; i < categories.length(); i++) {
                JSONObject cat = categories.getJSONObject(i);
                if (cat.getString("name").equals(category)) {
                    JSONArray chars = cat.getJSONArray("characters");
                    for (int j = 0; j < chars.length(); j++) {
                        JSONObject character = chars.getJSONObject(j);
                        characters.add(new Character(
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
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new CharacterAdapter(characterList, character -> {
            SharedPreferences prefs = getSharedPreferences("bundle_data", MODE_PRIVATE);
            prefs.edit()
                    .putString("name", character.getName())
                    .putString("desc", character.getDescription())
                    .putString("ability", character.getAbility())
                    .putString("imagePath", character.getImagePath())
                    .apply();
            Intent intent = new Intent(CharacterListActivity.this, A21_DetailsActivity.class);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        myBackActivity();
    }
}