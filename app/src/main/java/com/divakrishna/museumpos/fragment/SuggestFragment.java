package com.divakrishna.museumpos.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.divakrishna.museumpos.MainActivity;
import com.divakrishna.museumpos.R;
import com.divakrishna.museumpos.model.PostKritikSaran;
import com.divakrishna.museumpos.rest.ApiClient;
import com.divakrishna.museumpos.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

public class SuggestFragment extends Fragment {

    EditText tvNama;
    EditText tvEmail;
    EditText tvNoHP;
    EditText tvKritikSaran;

    Button btnKirim;

    ApiInterface mApiInterface;

    public static SuggestFragment newInstance() {
        SuggestFragment fragment = new SuggestFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ((MainActivity) getActivity()).setActionBarTitle("Kritik dan Saran");

        View frag_suggest = inflater.inflate(R.layout.fragment_suggest, container, false);

        tvNama = frag_suggest.findViewById(R.id.tvNama);
        tvEmail = frag_suggest.findViewById(R.id.tvEmail);
        tvNoHP = frag_suggest.findViewById(R.id.tvNoHP);
        tvKritikSaran = frag_suggest.findViewById(R.id.tvKritikSaran);
        btnKirim = frag_suggest.findViewById(R.id.btnKirim);

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();
                String no_hp = tvNoHP.getText().toString();
                String kritik_saran = tvKritikSaran.getText().toString();

                if(!TextUtils.isEmpty(nama) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(no_hp) && !TextUtils.isEmpty(kritik_saran)){
                    mApiInterface.postKritikSaran(nama, email, no_hp, kritik_saran).enqueue(new Callback<PostKritikSaran>() {
                        @Override
                        public void onResponse(Call<PostKritikSaran> call, Response<PostKritikSaran> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(getContext(),"Telah terkirim", Toast.LENGTH_LONG).show();
                                tvNama.setText("");
                                tvEmail.setText("");
                                tvNoHP.setText("");
                                tvKritikSaran.setText("");
                            }
                        }

                        @Override
                        public void onFailure(Call<PostKritikSaran> call, Throwable t) {
                            Toast.makeText(getContext(), "Gagal terkirim", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "Unable to submit post to API.");
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "Kolom tidak boleh kosong", Toast.LENGTH_LONG).show();
                }
            }
        });

        return frag_suggest;
    }
}
