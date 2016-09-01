package android.eisti.fr.pokedex.model.core.json;


public class JSONGenus
{
    private String genus;
    private JSONLanguage language;

    public JSONGenus(String genus, JSONLanguage language)
    {
        this.genus    = genus;
        this.language = language;
    }

    public String genus()    { return genus; }
    public String language() { return language.name(); }
}
