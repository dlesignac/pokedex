package android.eisti.fr.pokedex.model.api;

import java.io.Serializable;


public interface IPokemon extends Serializable
{
    int id();
    String name();
    String family();
    Double height();
    Double weight();
    Type type1();
    Type type2();
    String description();
    byte[] image();

    String fid();
    String fname();
    String ffamily();
    String fheight();
    String fweight();
    String ftype1();
    String ftype2();
    String fdescription();
}
