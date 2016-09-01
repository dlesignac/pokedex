package android.eisti.fr.pokedex.controller.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.eisti.fr.pokedex.R;
import android.eisti.fr.pokedex.controller.helpers.layout.PokedexEntryAdapter;
import android.eisti.fr.pokedex.controller.services.AlarmReceiver;
import android.eisti.fr.pokedex.model.api.IPokedex;
import android.eisti.fr.pokedex.model.api.PokedexFactory;
import android.eisti.fr.pokedex.model.api.PokedexInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/*
    This class describes the activity that shows the list of pokemons the user has found so far
    It has the form of a list that one can scroll down and clicking any item of the list allows
    you to read more about one particular pokemon
 */
public class ActivityPokedexList extends AppCompatActivity
{
    private IPokedex pokedex;
    private ListView l_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedexlist);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // time elapsed since the last time we found a pokemon
        scheduleAlarm(PokedexInfo.delay_finding);

        l_view = (ListView) findViewById(R.id.listView);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        pokedex = PokedexFactory.getPokedex(getApplicationContext());
        PokedexEntryAdapter adapter = new PokedexEntryAdapter(ActivityPokedexList.this, pokedex.entries());
        l_view.setAdapter(adapter);

        l_view.setClickable(true);
        l_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(ActivityPokedexList.this, ActivityPokedexFrame.class);
                intent.putExtra(ActivityPokedexFrame.EXTRA_POKEMON, pokedex.entries().get(position));
                startActivity(intent);
            }
        });
    }

    public void scheduleAlarm(long interval)
    {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);

        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                interval, pIntent);
    }

    public void cancelAlarm()
    {
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }
}
