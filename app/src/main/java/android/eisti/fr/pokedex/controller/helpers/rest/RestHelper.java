package android.eisti.fr.pokedex.controller.helpers.rest;

import android.eisti.fr.pokedex.model.api.exception.RestException;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/*
    Helper class to use a REST architecture
 */
public abstract class RestHelper
{
    static public String getJSON(String url)
            throws RestException
    {
        try
        {
            URL imageUrl = new URL(url);
            URLConnection con = imageUrl.openConnection();

            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int current;
            while ((current = bis.read()) != -1)
            {
                out.write((byte) current);
            }

            out.close();
            return out.toString();
        }
        catch (Exception e)
        {
            throw new RestException(e.getMessage());
        }
    }

    static public byte[] getImage(String url)
            throws RestException
    {
        try
        {
            URL imageUrl = new URL(url);
            URLConnection con = imageUrl.openConnection();

            InputStream is = con.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            int current;
            while ((current = bis.read()) != -1)
            {
                out.write((byte) current);
            }

            return out.toByteArray();
        }
        catch (Exception e)
        {
            throw new RestException(e.getMessage());
        }
    }
}
