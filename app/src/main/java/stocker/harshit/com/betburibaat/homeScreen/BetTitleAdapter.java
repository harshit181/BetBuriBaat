package stocker.harshit.com.betburibaat.homeScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stocker.harshit.com.betburibaat.R;


public class BetTitleAdapter extends RecyclerView.Adapter<MatchesViewHolder> {

    List<Matches> matches;
    Context context;

    public BetTitleAdapter(List<Matches> matches)
    {
        this.matches = matches;
    }

    @Override
    public MatchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_matches,parent,false);
        MatchesViewHolder matchesViewHolder = new MatchesViewHolder(view);
        context = parent.getContext();
        return matchesViewHolder;
    }

    @Override
    public void onBindViewHolder(MatchesViewHolder holder, final int position) {
        Matches matches = this.matches.get(position);

        holder.matchName.setText(matches.getMatchName());
        holder.matchId.setImageResource(matches.getMatchId());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Fragment fragment = new IndividualMatch(matches);
                loadFragment(fragment,view);
            }
        });


    }
    private void loadFragment(Fragment fragment,View view) {
        // load fragment
        FragmentTransaction transaction =  ((FragmentActivity)view.getContext()).getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

}