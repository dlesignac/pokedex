package android.eisti.fr.pokedex.model.api.exception;

/*
    Hand made class to handle database related exceptions
 */
public class DatabaseException extends Exception
{
    static private final String _null_cursor_message      = "null cursor while retrieving data";
    static private final String _failed_insertion_message = "failed to insert pokemon in database";

    public DatabaseException(String msg)
    {
        super(msg);
    }

    static public String null_cursor_message() { return _null_cursor_message; }
    static public String failed_insertion_message() { return _failed_insertion_message; }
}
