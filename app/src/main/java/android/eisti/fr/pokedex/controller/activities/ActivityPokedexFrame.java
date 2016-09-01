package android.eisti.fr.pokedex.controller.activities;

import android.content.Intent;
import android.eisti.fr.pokedex.R;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/*
    This class describes the form of a pokedex page, at the top you can find the id of a pokemon, below an image of the pokemon
    and finally a small description of the pokemon
 */
public class ActivityPokedexFrame extends AppCompatActivity
{
    static public final String EXTRA_POKEMON = "extra_pokemon";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedexframe);

        // display action bar
        ActionBar ab = getSupportActionBar();
        if (ab != null)
        {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        // get views to be updated
        Intent intent = getIntent();
        ImageView v_picture = (ImageView) findViewById(R.id.picture);
        TextView  v_name    = (TextView)  findViewById(R.id.name);
        TextView  v_type1   = (TextView) findViewById(R.id.type1);
        TextView  v_type2   = (TextView) findViewById(R.id.type2);
        TextView  v_family  = (TextView)  findViewById(R.id.family);
        TextView  v_height  = (TextView)  findViewById(R.id.height);
        TextView  v_weight  = (TextView)  findViewById(R.id.weight);
        TextView  v_description = (TextView) findViewById(R.id.description);

        if (intent != null)
        {
            IPokemon pokemon = (IPokemon) intent.getSerializableExtra(EXTRA_POKEMON);

            if (ab != null)
            {
                ab.setTitle("#" + pokemon.fid());
            }

            // gets an image of the pokemon from a byte array
            v_picture.setImageBitmap(BitmapFactory.decodeByteArray(pokemon.image(),
                    0, pokemon.image().length));

            // all immutable text elements are described within the xml files like "Weight:" or "Height:"
            v_name.setText(pokemon.fname());
            v_family.setText(pokemon.ffamily());
            v_height.setText(pokemon.fheight());
            v_weight.setText(pokemon.fweight());
            v_type1.setText(pokemon.ftype1());
            v_type2.setText(pokemon.ftype2());
            v_description.setText(pokemon.fdescription());
        }
    }
}
