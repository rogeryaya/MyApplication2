package com.example.user.myapplication2.model;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by user on 2016/9/5.
 */
public class OwnedPokemonInfoDataManager {

    Context mContgext;
    ArrayList<OwnedPokemonInfo> ownedPokemonInfos;
    public OwnedPokemonInfoDataManager(Context context) {
        mContgext = context;
    }

    public void loadListViewData() {
        ownedPokemonInfos = new ArrayList<>();

        BufferedReader reader;
        String line = null;
        String[] dataFields = null;

        try {
            reader = new BufferedReader(new InputStreamReader(
                    mContgext.getAssets().open("pokemon_data.csv")));
            while( (line = reader.readLine())!= null) {
                dataFields = line.split(",");
                Log.d("Roger", dataFields[0]);
                Log.d("Roger", dataFields[1]);
                Log.d("Roger", dataFields[2]);

                ownedPokemonInfos.add(constructPokemonInfo(dataFields));             //construct a object from dataFields
            }
            reader.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

    }

    OwnedPokemonInfo constructPokemonInfo(String[] dataFields) {
        OwnedPokemonInfo ownedPokemonInfo = new OwnedPokemonInfo();
        ownedPokemonInfo.pokemonId = Integer.valueOf(dataFields[0]);
        ownedPokemonInfo.name = dataFields[2];
        ownedPokemonInfo.level = Integer.valueOf(dataFields[3]);
        ownedPokemonInfo.currentHP = Integer.valueOf(dataFields[4]);
        ownedPokemonInfo.maxHP = Integer.valueOf(dataFields[5]);
        ownedPokemonInfo.type1Index = Integer.valueOf(dataFields[6]);
        ownedPokemonInfo.type2Index = Integer.valueOf(dataFields[7]);
        String[] skills = new String[OwnedPokemonInfo.maxNumSkills];
        for (int i = 8; i < dataFields.length; i++) {
            skills[i-8] = dataFields[i];
        }
        ownedPokemonInfo.skills = skills;
        return ownedPokemonInfo;
    }

    public ArrayList<OwnedPokemonInfo> getOwnedPokemonInfos() {
        return ownedPokemonInfos;
    }

}

