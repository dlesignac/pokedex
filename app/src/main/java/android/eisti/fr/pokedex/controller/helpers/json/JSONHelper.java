package android.eisti.fr.pokedex.controller.helpers.json;

import android.eisti.fr.pokedex.model.core.json.JSONPokemon;
import android.eisti.fr.pokedex.model.core.json.JSONSpecies;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/*
    Simple Json helper class
 */
public abstract class JSONHelper
{
    static private final Gson gson = new GsonBuilder().create();

    static public JSONPokemon pokemon(String json)
    {
        return gson.fromJson(json, JSONPokemon.class);
    }

    static public JSONSpecies species(String json)
    {
        return gson.fromJson(json, JSONSpecies.class);
    }
}
