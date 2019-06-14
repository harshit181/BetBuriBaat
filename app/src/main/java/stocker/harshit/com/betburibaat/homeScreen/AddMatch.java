package stocker.harshit.com.betburibaat.homeScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.bson.Document;

import java.util.Date;

import stocker.harshit.com.betburibaat.Db.DbCollection;
import stocker.harshit.com.betburibaat.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AddMatch.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AddMatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMatch extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AddMatch() {
        // Required empty public constructor
    }

    public static AddMatch newInstance(String param1, String param2) {
        AddMatch fragment = new AddMatch();
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
        View addView=inflater.inflate(R.layout.fragment_add_match, container, false);
        MaterialButton btn =addView.findViewById(R.id.button2);
        TextInputEditText matchID=(TextInputEditText)addView.findViewById(R.id.text1);
        TextInputEditText matchName=(TextInputEditText)addView.findViewById(R.id.text2);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Document x =new Document();
                x.put("Match_Id",matchID.getText().toString());
                x.put("Match_Name",matchName.getText().toString());
                x.put("owner_id",DbCollection.getClient().getAuth().getUser().getId());
                DbCollection.getColl().insertOne(x);
                getActivity().getSupportFragmentManager().popBackStackImmediate();
            }
        });
        return addView;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
