package android.eisti.fr.pokedex.controller.helpers.file;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    Simple file handler class
 */
public class FileReadingHelper
{
    private BufferedReader _reader;

    public FileReadingHelper(Context context, String type, String filename) throws FileNotFoundException
    {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(filename, type, context.getPackageName());
        InputStreamReader reader = new InputStreamReader(resources.openRawResource(id));
        _reader = new BufferedReader(reader);
    }

    public String readLine() throws IOException
    {
        return _reader.readLine();
    }

    public String readFile() throws IOException
    {
        String result = "";
        String line;

        while ((line = readLine()) != null)
        {
            result += line;
        }

        close();
        return result;
    }

    public void close()
    {
        try
        {
            _reader.close();
        }
        catch (IOException e)
        {
            System.err.println("failed to close file after reading");
        }
    }
}
