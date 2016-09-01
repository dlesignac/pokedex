package android.eisti.fr.pokedex.controller.helpers.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.eisti.fr.pokedex.controller.helpers.file.FileReadingHelper;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.eisti.fr.pokedex.model.api.PokemonFactory;
import android.eisti.fr.pokedex.model.api.Type;
import android.eisti.fr.pokedex.model.api.exception.DatabaseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    This class allows us to manage the database of our application in order to retrieve and add new pokemons
 */
public class DatabaseHelper extends SQLiteOpenHelper
{
    static private final int    DATABASE_VERSION = 1;
    static private final String DATABASE_NAME    = "db_pokedex";
    static private final String DATABASE_CREATE  = "db_create";
    static private final String DATABASE_DELETE  = "db_delete";

    static private DatabaseHelper _instance = null;

    private Context _context;

    private DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            FileReadingHelper reader = new FileReadingHelper(_context, "raw", DATABASE_CREATE);

            String line;
            while ((line = reader.readLine()) != null)
            {
                db.execSQL(line);
            }

            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File " + DATABASE_CREATE + " was not found during database " +
                    "creation. Message:" + e.getMessage());
        }
        catch (IOException e)
        {
            System.err.println("IO exception caught during database creation. Message:"
                    + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try
        {
            FileReadingHelper reader = new FileReadingHelper(_context, "raw", DATABASE_DELETE);

            String line;
            while ((line = reader.readLine()) != null)
            {
                db.execSQL(line);
            }

            reader.close();
        }
        catch (FileNotFoundException e)
        {
            System.err.println("File " + DATABASE_DELETE + " was not found during database " +
                    "deletion. Message:" + e.getMessage());
        }
        catch (IOException e)
        {
            System.err.println("IO exception caught during database deletion. Message:"
                    + e.getMessage());
        }

        onCreate(db);
    }

    public void add(IPokemon pokemon)
            throws DatabaseException
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("id", pokemon.id());
        values.put("name", pokemon.name());
        values.put("family", pokemon.family());
        values.put("height", pokemon.height());
        values.put("weight", pokemon.weight());
        values.put("type1", pokemon.type1().id());

        if (pokemon.type2() != null)
        {
            values.put("type2", pokemon.type2().id());
        }

        values.put("description", pokemon.description());
        values.put("image", pokemon.image());

        if (db.insert("Pokemon", null, values) == -1)
        {
            throw new DatabaseException(DatabaseException.failed_insertion_message());
        }

        db.close();
    }

    public List<IPokemon> getPokemons()
            throws DatabaseException
    {
        SQLiteDatabase db   = this.getReadableDatabase();
        List<IPokemon> list = new ArrayList<>();

        String query = String.format(
                "SELECT %s FROM Pokemon p " +
                "LEFT JOIN PokemonType t1 ON t1.id = p.type1 " +
                "LEFT JOIN PokemonType t2 ON t2.id = p.type2",
                "p.id, p.name, p.family, p.height, p.weight, t1.label, T2.label, p.description," +
                        "p.image"
        );

        Cursor cursor = db.rawQuery(query, null);

        if (cursor == null)
        {
            throw new DatabaseException(DatabaseException.null_cursor_message());
        }

        cursor.moveToFirst();

        while (!cursor.isAfterLast())
        {
            IPokemon pokemon = PokemonFactory.getPokemon(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getDouble(3),
                    cursor.getDouble(4),
                    Type.valueOf(cursor.getString(5)),
                    cursor.getString(6) == null ? null : Type.valueOf(cursor.getString(6)),
                    cursor.getString(7),
                    cursor.getBlob(8)
            );

            list.add(pokemon);
            cursor.moveToNext();
        }

        cursor.close();
        return list;
    }

    static public synchronized DatabaseHelper get(Context context)
    {
        if (_instance == null)
        {
            _instance = new DatabaseHelper(context);
        }

        return _instance;
    }
}
