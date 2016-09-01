package android.eisti.fr.pokedex.model.core;

import android.eisti.fr.pokedex.model.api.IPokemon;
import android.eisti.fr.pokedex.model.api.Type;

import java.text.DecimalFormat;

/*
    Custom pokemon class
 */
public class Pokemon implements IPokemon
{
    static private final String FORMAT_SID = "000";
    static private final DecimalFormat _formatter = new DecimalFormat(FORMAT_SID);

    private final int _id;
    private final String _name;
    private final String _family;
    private final Double _height;
    private final Double _weight;
    private final Type _type1;
    private final Type _type2;
    private final String _description;
    private byte[] _image;

    public Pokemon(
            int id,
            String name,
            String family,
            Double height,
            Double weight,
            Type type1,
            Type type2,
            String description,
            byte[] image)
    {
        _id     = id;
        _name   = name;
        _family = family;
        _height = height;
        _weight = weight;
        _type1  = type1;
        _type2  = type2;
        _description = description;
        _image = image;
    }

    public int id()        { return _id; }
    public String name()   { return _name; }
    public String family() { return _family; }
    public Double height() { return _height; }
    public Double weight() { return _weight; }
    public Type type1()    { return _type1; }
    public Type type2()    { return _type2; }
    public String description() { return _description; }
    public byte[] image()  { return _image; }

    public String fid()
    {
        return _formatter.format(_id);
    }

    public String fname()
    {
        String name = "";

        if (_name != null)
        {
            name = _name.replaceFirst(".", (_name.charAt(0) + "").toUpperCase());
        }

        return name;
    }

    public String ffamily()
    {
        String family = "";

        if (_family != null)
        {
            family = String.format("Pokemon %s",
                    _family.replaceFirst(".", (_family.charAt(0) + "").toUpperCase()));
        }

        return family;
    }

    public String fheight()
    {
        String height = "";

        if (_height != null)
        {
            height = String.format("%1$,.1f m", _height);
        }

        return height;
    }

    public String fweight()
    {
        String weight = "";

        if (_weight != null)
        {
            weight = String.format("%1$,.1f kg", _weight);
        }

        return weight;
    }

    public String ftype1()
    {
        String type = "";

        if (_type1 != null)
        {
            type = _type1.toString();
        }

        return type;
    }

    public String ftype2()
    {
        String type = "";

        if (_type2 != null)
        {
            type = _type2.toString();
        }

        return type;
    }

    public String fdescription()
    {
        String description = "";

        if (_description != null)
        {
            description = _description;
        }

        return description;
    }
}
