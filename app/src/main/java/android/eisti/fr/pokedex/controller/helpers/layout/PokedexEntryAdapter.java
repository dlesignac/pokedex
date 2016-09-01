package android.eisti.fr.pokedex.controller.helpers.layout;

import android.content.Context;
import android.eisti.fr.pokedex.R;
import android.eisti.fr.pokedex.model.api.IPokemon;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
    This class allows us to add custom strings and images to our list which can normally only contain standard strings
 */
public class PokedexEntryAdapter extends ArrayAdapter<IPokemon>
{
    public PokedexEntryAdapter(Context context, List<IPokemon> entries)
    {
        super(context, 0, entries);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_pokedexentry, parent, false);
        }

        PokedexEntryHolder viewHolder = (PokedexEntryHolder) convertView.getTag();

        if (viewHolder == null)
        {
            viewHolder = new PokedexEntryHolder();
            viewHolder.miniature = (ImageView) convertView.findViewById(R.id.miniature);
            viewHolder.number    = (TextView)  convertView.findViewById(R.id.number);
            viewHolder.name      = (TextView)  convertView.findViewById(R.id.name);
            convertView.setTag(viewHolder);
        }

        IPokemon entry = getItem(position);

        String nname = "#" + entry.fid();
        String pname = entry.fname();

        viewHolder.miniature.setImageBitmap(BitmapFactory.decodeByteArray(entry.image(),
                0, entry.image().length));

        viewHolder.number.setText(nname);
        viewHolder.name.setText(pname);

        return convertView;
    }

    private class PokedexEntryHolder
    {
        public ImageView miniature;
        public TextView  number;
        public TextView  name;
    }
}
