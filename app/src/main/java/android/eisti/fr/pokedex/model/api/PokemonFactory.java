package android.eisti.fr.pokedex.model.api;

import android.eisti.fr.pokedex.model.core.Pokemon;


public class PokemonFactory
{
    static public IPokemon getPokemon(
            int id, String name, String family, double height, double weight,
            Type type1, Type type2, String description, byte[] image)
    {
        return new Pokemon(id, name, family, height, weight, type1, type2, description, image);
    }
}
