package android.eisti.fr.pokedex.controller.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.eisti.fr.pokedex.model.api.IPokedex;
import android.eisti.fr.pokedex.model.api.PokedexFactory;
import android.eisti.fr.pokedex.model.api.PokedexInfo;

/*
    A class that uses the alarm to plan the discovery of a new pokemon chosen at random
 */
public class AlarmReceiver extends BroadcastReceiver
{
    public static final int REQUEST_CODE = 12345;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        IPokedex pokedex = PokedexFactory.getPokedex(context);
        int rand_id = (int) (1 + (Math.random() * PokedexInfo.n_max_entries));

        if (!pokedex.met()[rand_id])
        {
            Intent i = new Intent(context, ServicePokedex.class);
            i.putExtra("id", rand_id);
            context.startService(i);
        }
    }
}