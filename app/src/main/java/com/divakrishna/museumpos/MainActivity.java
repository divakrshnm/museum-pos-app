package com.divakrishna.museumpos;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.divakrishna.museumpos.fragment.MainFragment;
import com.divakrishna.museumpos.fragment.MapFragment;
import com.divakrishna.museumpos.fragment.ScannerFragment;
import com.divakrishna.museumpos.fragment.SuggestFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView =  findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.main_menu:
                                selectedFragment = MainFragment.newInstance();
                                break;
                            case R.id.scanner_menu:
                                selectedFragment = ScannerFragment.newInstance();
                                break;
                            case R.id.map_menu:
                                selectedFragment = MapFragment.newInstance();
                                break;
                            case R.id.suggest_menu:
                                selectedFragment = SuggestFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, MainFragment.newInstance());
        transaction.commit();
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
}
