package stocker.harshit.com.betburibaat;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteFindIterable;

import org.bson.Document;

import java.util.ArrayList;

import stocker.harshit.com.betburibaat.Db.DbCollection;
import stocker.harshit.com.betburibaat.homeScreen.AddMatch;
import stocker.harshit.com.betburibaat.homeScreen.Matches;
import stocker.harshit.com.betburibaat.homeScreen.BetTitleAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Home.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView recyclerView;
    BetTitleAdapter betTitleAdapter;
    ArrayList<Matches> matches = new ArrayList<Matches>();
    private FloatingActionButton fab;

    public static final int[] TvShowImgs = {R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp, R.drawable.ic_home_black_24dp};


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View RootView = inflater.inflate(R.layout.fragment_home, container, false);
        fetchMatchesAndUpdateView(RootView);
        FloatingActionButton fab = (FloatingActionButton) RootView.findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Fragment  fragment = new AddMatch();
              loadFragment(fragment);
            }
        });
        return RootView;
    }

    public void fetchMatchesAndUpdateView(View RootView) {
        RemoteFindIterable query = DbCollection.getColl().find();
        ArrayList<Document> result = new ArrayList<Document>();
        matches.clear();
        query.into(result).addOnSuccessListener(new OnSuccessListener<ArrayList<Document>>() {

                                                    @Override
                                                    public void onSuccess(final ArrayList<Document> ignored) {

                                                        for (int i = 0; i < result.size(); i++) {
                                                            Matches matches = new Matches();
                                                            Document x = result.get(i);
                                                            Log.d("MatchID is", x.getString("Match_Name"));
                                                            matches.setMatchName(x.getString("Match_Name"));
                                                            matches.setMatchId(TvShowImgs[0]);
                                                            Home.this.matches.add(matches);
                                                        }
                                                        betTitleAdapter = new BetTitleAdapter(matches);
                                                        recyclerView = (RecyclerView) RootView.findViewById(R.id.Matches);
                                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                                                        recyclerView.setAdapter(betTitleAdapter);

                                                    }

                                                }
        );
        Log.d("Result Size is ", "" + result.size());
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
