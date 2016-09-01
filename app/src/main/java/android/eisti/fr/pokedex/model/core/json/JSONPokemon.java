package android.eisti.fr.pokedex.model.core.json;


public class JSONPokemon
{
    private String name;
    private int height;
    private int weight;
    private JSONTypeHolder[] types;

    public JSONPokemon(String name, int height, int weight, JSONTypeHolder[] types)
    {
        this.name   = name;
        this.height = height;
        this.weight = weight;
        this.types  = types;
    }

    public String name() { return name;   }
    public int height()  { return height; }
    public int weight()  { return weight; }
    public JSONTypeHolder[] types()  { return types;  }
}
