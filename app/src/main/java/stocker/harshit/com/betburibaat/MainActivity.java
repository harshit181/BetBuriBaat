package stocker.harshit.com.betburibaat;

import android.net.Uri;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import stocker.harshit.com.betburibaat.Db.DbCollection;
import stocker.harshit.com.betburibaat.homeScreen.AddMatch;
import stocker.harshit.com.betburibaat.homeScreen.IndividualMatch;

public class MainActivity extends AppCompatActivity implements Home.OnFragmentInteractionListener ,
        Stats.OnFragmentInteractionListener,Notification.OnFragmentInteractionListener ,
        AddMatch.OnFragmentInteractionListener, IndividualMatch.OnFragmentInteractionListener {
    private ActionBar toolbar;
    Fragment fragment;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    toolbar.setTitle(R.string.title_home);
                    fragment = new Home();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_dashboard:
                    toolbar.setTitle(R.string.title_dashboard);
                    fragment = new Stats();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_notifications:
                    toolbar.setTitle(R.string.title_notifications);
                    fragment = new Notification();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        toolbar = getSupportActionBar();
        loadFragment(new Home());
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DbCollection.initLogin();
    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
