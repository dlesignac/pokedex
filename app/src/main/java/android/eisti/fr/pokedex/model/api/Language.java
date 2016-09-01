package android.eisti.fr.pokedex.model.api;


public enum Language
{
    en("en"),
    fr("fr");

    private String _code = "";

    Language(String code)
    {
        _code = code;
    }

    public String toString()
    {
        return _code;
    }
}
