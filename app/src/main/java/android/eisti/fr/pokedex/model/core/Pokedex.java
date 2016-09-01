package android.eisti.fr.pokedex.model.core;

import android.content.Context;
import android.eisti.fr.pokedex.controller.helpers.database.DatabaseHelper;
import android.eisti.fr.pokedex.model.api.IPokedex;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.eisti.fr.pokedex.model.api.PokedexInfo;
import android.eisti.fr.pokedex.model.api.exception.DatabaseException;

import java.util.ArrayList;
import java.util.List;

/*
    Custom pokedex class
 */
public class Pokedex implements IPokedex
{
    private List<IPokemon> _entries;
    private boolean[]      _met;

    public Pokedex(Context context)
    {
        _entries  = new ArrayList<>();
        _met      = new boolean[PokedexInfo.n_max_entries];

        for (int i = 0; i < _met.length; i++)
            _met[i] = false;

        DatabaseHelper dbh = DatabaseHelper.get(context);

        try
        {
            this.add(dbh.getPokemons());
        }
        catch (DatabaseException e)
        {
            e.printStackTrace();
        }
    }

    public List<IPokemon> entries() { return _entries; }
    public boolean[]      met()     { return _met; }

    public void add(IPokemon pokemon)
    {
        _entries.add(pokemon);
        _met[pokemon.id()] = true;
    }

    public void add(List<IPokemon> pokemons)
    {
        for (IPokemon current : pokemons)
        {
            add(current);
        }
    }

    public void reset()
    {
        _entries  = new ArrayList<>();

        for (int i = 0; i < PokedexInfo.n_max_entries; i++)
            _met[i] = false;
    }
}
