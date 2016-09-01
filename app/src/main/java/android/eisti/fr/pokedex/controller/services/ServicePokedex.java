package android.eisti.fr.pokedex.controller.services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.eisti.fr.pokedex.R;
import android.eisti.fr.pokedex.controller.activities.ActivityPokedexFrame;
import android.eisti.fr.pokedex.controller.helpers.database.DatabaseHelper;
import android.eisti.fr.pokedex.controller.helpers.rest.RestServiceHolder;
import android.eisti.fr.pokedex.model.api.IPokedex;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.eisti.fr.pokedex.model.api.PokedexFactory;
import android.support.v4.app.NotificationCompat;

public class ServicePokedex extends IntentService
{
    public ServicePokedex()
    {
        super("ServicePokedex");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        try
        {
            int id = intent.getIntExtra("id", 0);

            if (id > 0)
            {
                Context context = getApplicationContext();
                DatabaseHelper dbh = DatabaseHelper.get(context);
                IPokedex pokedex = PokedexFactory.getPokedex(context);

                IPokemon pokemon = RestServiceHolder.getPokemon(id);
                dbh.add(pokemon);
                pokedex.met()[pokemon.id()] = true;

                NotificationCompat.Builder builder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.notification_icon)
                                .setContentTitle("Met a new pokemon")
                                .setContentText("Congratulations, you found a " + pokemon.fname());
                builder.setAutoCancel(true);

                Intent resultIntent = new Intent(context, ActivityPokedexFrame.class);
                resultIntent.putExtra(ActivityPokedexFrame.EXTRA_POKEMON, pokemon);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(ActivityPokedexFrame.class);
                stackBuilder.addNextIntent(resultIntent);

                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

                builder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(
                                Context.NOTIFICATION_SERVICE);

                // TODO Really ? It seems no better way has been planned to identify a notification
                mNotificationManager.notify(pokemon.id(), builder.build());
            }
        }
        catch (Exception e)
        {
            System.out.println("Failed parsing while TaskFind : " + e.getMessage());
        }

        stopSelf();
    }
}
