package android.eisti.fr.pokedex.model.core.json;


public class JSONSpecies
{
    private int id;
    private JSONFlavorTextEntry[] flavor_text_entries;
    private JSONGenus[] genera;

    public JSONSpecies(int id, JSONFlavorTextEntry[] entries, JSONGenus[] genera)
    {
        this.id = id;
        this.flavor_text_entries = entries;
        this.genera = genera;
    }

    public int id() { return id; }
    public JSONFlavorTextEntry[] entries() { return flavor_text_entries; }
    public JSONGenus[] genera() { return genera; }
}
