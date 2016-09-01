package android.eisti.fr.pokedex.model.core.json;


public class JSONFlavorTextEntry
{
    private String flavor_text;
    private JSONLanguage language;
    private JSONVersion version;

    public JSONFlavorTextEntry(String flavor_text, JSONLanguage language, JSONVersion version)
    {
        this.flavor_text = flavor_text;
        this.language    = language;
        this.version     = version;
    }

    public String flavor_text() { return flavor_text; }
    public String language()    { return language.name(); }
    public String version()     { return version.name(); }
}
