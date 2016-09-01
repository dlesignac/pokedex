package android.eisti.fr.pokedex.controller.helpers.rest;

import android.eisti.fr.pokedex.controller.helpers.json.JSONHelper;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.eisti.fr.pokedex.model.api.Language;
import android.eisti.fr.pokedex.model.api.PokemonFactory;
import android.eisti.fr.pokedex.model.api.Type;
import android.eisti.fr.pokedex.model.api.exception.JSONParsingException;
import android.eisti.fr.pokedex.model.api.exception.PokemonNotFoundException;
import android.eisti.fr.pokedex.model.api.exception.RestException;
import android.eisti.fr.pokedex.model.core.json.JSONPokemon;
import android.eisti.fr.pokedex.model.core.json.JSONSpecies;
import android.eisti.fr.pokedex.model.core.json.JSONTypeHolder;

public abstract class RestServiceHolder
{
    static private final String _root = "http://pokeapi.co";
    static private final String _path_sprite = "media/sprites";
    static private final String _sprite_extension = ".png";
    static private final String _path_api = "api/v2";
    static private final String _path_pokemon = "pokemon";
    static private final String _path_species = "pokemon-species";

    static public JSONPokemon getJSONPokemon(int id)
            throws JSONParsingException
    {
        String request = String.format("%s/%s/%s/%d/", _root, _path_api, _path_pokemon, id);

        try
        {
            String json = RestHelper.getJSON(request);
            return JSONHelper.pokemon(json);
        }
        catch (Exception e)
        {
            String msg = String.format("%s (id %d): ",
                    JSONParsingException.pokemon_message(), id) + e.getMessage();
            throw new JSONParsingException(msg);
        }
    }

    static public JSONSpecies getJSONSpecies(int id)
            throws JSONParsingException
    {
        String request = String.format("%s/%s/%s/%d/", _root, _path_api, _path_species, id);

        try
        {
            String json = RestHelper.getJSON(request);
            return JSONHelper.species(json);
        }
        catch (Exception e)
        {
            String msg = String.format("%s (id %d): ",
                    JSONParsingException.species_message(), id) + e.getMessage();
            throw new JSONParsingException(msg);
        }
    }

    static public IPokemon getPokemon(int id)
            throws JSONParsingException, PokemonNotFoundException
    {
        String name;
        String family;
        int height;
        int weight;
        Type type1 = null;
        Type type2 = null;
        String description;

        JSONPokemon pokeFromJson = getJSONPokemon(id);
        JSONSpecies specFromJson = getJSONSpecies(id);

        if (pokeFromJson == null || specFromJson == null)
        {
            String msg = String.format("%s (id %d)", PokemonNotFoundException.rest_message(), id);
            throw new PokemonNotFoundException(msg);
        }

        name   = pokeFromJson.name();
        height = pokeFromJson.height();
        weight = pokeFromJson.weight();

        for (JSONTypeHolder typeFromJson : pokeFromJson.types())
        {
            if (typeFromJson.slot() == 1)
            {
                type1 = Type.valueOf(typeFromJson.type().name());
            }
            else if (typeFromJson.slot() == 2)
            {
                type2 = Type.valueOf(typeFromJson.type().name());
            }
        }

        family = null;
        description = null;

        for (int i = 0; i < specFromJson.genera().length; i++)
        {
            if (specFromJson.genera()[i].language().equals(Language.en.toString()))
            {
                family = specFromJson.genera()[i].genus();
            }

            String language = specFromJson.entries()[i].language();
            // TODO only alpha-sapphire description data can be retrieved - why ?
            //String version  = specFromJson.entries()[i].version();

            if (language.equals(Language.en.toString()) /*&& version.equals("red")*/)
            {
                description = specFromJson.entries()[i].flavor_text();
            }
        }

        String request = String.format(
                "%s/%s/%s/%d%s", _root, _path_sprite, _path_pokemon, id, _sprite_extension);

        try
        {
            byte[] image = RestHelper.getImage(request);
            return PokemonFactory.getPokemon(
                    id, name, family, height, weight, type1, type2, description, image);
        }
        catch (RestException e)
        {
            String msg = String.format("%s (id %d): ",
                    JSONParsingException.species_message(), id) + e.getMessage();
            throw new JSONParsingException(msg);
        }
    }
}
