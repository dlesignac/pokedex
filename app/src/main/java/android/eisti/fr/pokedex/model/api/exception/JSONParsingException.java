package android.eisti.fr.pokedex.model.api.exception;


/*
    Hand made class to handle Json parsing related exceptions
 */
public class JSONParsingException extends Exception
{
    static private final String _pokemon_message = "failed to parse JSONPokemon";
    static private final String _species_message = "failed to parse JSONSpecies";

    public JSONParsingException(String msg)
    {
        super(msg);
    }

    static public String pokemon_message() { return _pokemon_message; }
    static public String species_message() { return _species_message; }
}
