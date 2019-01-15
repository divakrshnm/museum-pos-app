package com.divakrishna.museumpos.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.divakrishna.museumpos.MainActivity;
import com.divakrishna.museumpos.R;
import com.divakrishna.museumpos.adapter.KoleksiAdapter;
import com.divakrishna.museumpos.model.GetKoleksi;
import com.divakrishna.museumpos.model.Koleksi;
import com.divakrishna.museumpos.rest.ApiClient;
import com.divakrishna.museumpos.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends Fragment {

    ApiInterface mApiInterface;
    private RecyclerView mRecyclerView;//
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;//

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Beranda");

        View frag_main = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        mRecyclerView = frag_main.findViewById(R.id.rvKoleksi);

        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetKoleksi> koleksiCall = mApiInterface.getKoleksi();
        koleksiCall.enqueue(new Callback<GetKoleksi>(){
            @Override
            public void onResponse(Call<GetKoleksi> call, Response<GetKoleksi> response){
                List<Koleksi> KoleksiList = response.body().getListDataKoleksi();
                mAdapter = new KoleksiAdapter(KoleksiList);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetKoleksi> call, Throwable t){
                //Log.e("Retrofit Get", t.toString());
                Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_LONG).show();
            }
        });

        return frag_main;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView  = new SearchView(getActivity());
        searchView.setQueryHint("Cari Koleksi");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
//
            @Override
            public boolean onQueryTextChange(final String nextText) {

                mApiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<GetKoleksi> koleksiCall = mApiInterface.getKoleksiNama(nextText.toLowerCase());
                koleksiCall.enqueue(new Callback<GetKoleksi>(){
                    @Override
                    public void onResponse(Call<GetKoleksi> call, Response<GetKoleksi> response){
                        List<Koleksi> KoleksiList = response.body().getListDataKoleksi();
                        mAdapter = new KoleksiAdapter(KoleksiList);
                        mRecyclerView.setAdapter(mAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetKoleksi> call, Throwable t){
                        //Log.e("Retrofit Get", t.toString());
                        Toast.makeText(getContext(), "Gagal mengambil data", Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            }
        });
        searchItem.setActionView(searchView);
    }


}
