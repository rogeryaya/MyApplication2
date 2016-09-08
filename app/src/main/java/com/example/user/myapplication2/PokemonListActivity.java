package com.example.user.myapplication2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.user.myapplication2.model.OwnedPokemonInfo;
import com.example.user.myapplication2.model.OwnedPokemonInfoDataManager;

import java.util.ArrayList;

public class PokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);
        OwnedPokemonInfoDataManager dataManager =
                new OwnedPokemonInfoDataManager(this);

        dataManager.loadListViewData();
        ArrayList<OwnedPokemonInfo> ownedPokemonInfos = dataManager.getOwnedPokemonInfos();

        PokemonListAdapter arrayAdapter = new PokemonListAdapter(
                this,
                R.layout.row_view_of_pokemon_list,
                ownedPokemonInfos
        );

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(arrayAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.selected_pokemon_list_action_bar, menu);
        return true; //menu will always show
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_delete) {
            //TODO: implement deleting selected items
            return true;
        } else if (itemId == R.id.action_settings) {

            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }

    }


}
