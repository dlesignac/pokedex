package android.eisti.fr.pokedex.model.core.json;


public class JSONTypeHolder
{
    private int slot;
    private JSONType type;

    public JSONTypeHolder(int slot, JSONType type)
    {
        this.slot = slot;
        this.type = type;
    }

    public int slot()      { return slot; }
    public JSONType type() { return type; }
}
