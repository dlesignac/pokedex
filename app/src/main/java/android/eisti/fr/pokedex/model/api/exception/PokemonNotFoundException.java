package android.eisti.fr.pokedex.model.api.exception;


/*
    Hand made class to handle exceptions thrown when a pokemon is not found
 */
public class PokemonNotFoundException extends Exception
{
    static private final String _rest_message = "pokemon not found from rest service";

    public PokemonNotFoundException(String msg)
    {
        super(msg);
    }

    static public String rest_message() { return _rest_message; }
}
