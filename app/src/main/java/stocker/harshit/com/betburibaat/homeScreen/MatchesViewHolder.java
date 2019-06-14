package stocker.harshit.com.betburibaat.homeScreen;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import stocker.harshit.com.betburibaat.R;

public class MatchesViewHolder extends RecyclerView.ViewHolder
{
    ImageView matchId;
    TextView matchName;
    CardView cv;

    public MatchesViewHolder(View itemView)
    {
        super(itemView);
        matchId = (ImageView)itemView.findViewById(R.id.matchId);
        matchName = (TextView)itemView.findViewById(R.id.textTvshow);
        cv = (CardView)itemView.findViewById(R.id.cv);
    }

}