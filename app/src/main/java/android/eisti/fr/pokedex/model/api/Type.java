package android.eisti.fr.pokedex.model.api;


public enum Type
{
    normal(1, "normal"),
    fighting(2, "fighting"),
    flying(3, "flying"),
    poison(4, "poison"),
    ground(5, "ground"),
    rock(6, "rock"),
    bug(7, "bug"),
    ghost(8, "ghost"),
    steel(9, "steel"),
    fire(10, "fire"),
    water(11, "water"),
    grass(12, "grass"),
    electric(13, "electric"),
    psychic(14, "psychic"),
    ice(15, "ice"),
    dragon(16, "dragon"),
    dark(17, "dark"),
    fairy(18, "fairy");

    private int _id = 0;
    private String _name = "";

    Type(int id, String name)
    {
        _id = id; _name = name;
    }

    public int id() { return _id; }
    public String toString() { return _name; }
}
