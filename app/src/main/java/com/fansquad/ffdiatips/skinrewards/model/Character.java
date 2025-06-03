package com.fansquad.ffdiatips.skinrewards.model;

import java.io.Serializable;

public class Character implements Serializable {
    private final String name;
    private final String description;
    private final String ability;
    private final String imagePath;

    public Character(String name, String description, String ability, String imagePath) {
        this.name = name;
        this.description = description;
        this.ability = ability;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAbility() {
        return ability;
    }

    public String getImagePath() {
        return imagePath;
    }
}