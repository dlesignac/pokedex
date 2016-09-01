package android.eisti.fr.pokedex.model.api;

import java.util.List;

/*
    A class to describe the content of a pokedex
 */
public interface IPokedex
{
    List<IPokemon> entries();
    // to check out which pokemons have already been found
    boolean[] met();
    void add(IPokemon pokemon);
    void add(List<IPokemon> pokemons);
    void reset();
}
