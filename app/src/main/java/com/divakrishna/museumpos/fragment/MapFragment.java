package com.divakrishna.museumpos.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.divakrishna.museumpos.MainActivity;
import com.divakrishna.museumpos.R;

public class MapFragment extends Fragment {

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Denah");

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

}
