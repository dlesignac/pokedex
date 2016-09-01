package android.eisti.fr.pokedex.model.api;

import android.content.Context;
import android.eisti.fr.pokedex.model.core.Pokedex;


public class PokedexFactory
{
    static public IPokedex getPokedex(Context context)
    {
        return new Pokedex(context);
    }
}
