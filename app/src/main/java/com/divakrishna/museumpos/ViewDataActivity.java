package com.divakrishna.museumpos;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.divakrishna.museumpos.model.GetKoleksi;
import com.divakrishna.museumpos.model.Koleksi;
import com.divakrishna.museumpos.rest.ApiClient;
import com.divakrishna.museumpos.rest.ApiInterface;
import com.squareup.picasso.Picasso;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDataActivity extends AppCompatActivity {

    ApiInterface mApiInterface;
    ImageView ivFoto;
    TextView tvNamaKoleksi;
    TextView tvJenis;
    JustifiedTextView tvDeskripsi;
    JustifiedTextView tvSejarah;
    MediaPlayer mPlayer;
    ImageView btnPlay;
    ImageView btnPause;
    ImageView btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivFoto = findViewById(R.id.ivFoto);
        tvNamaKoleksi = findViewById(R.id.tvNamaKoleksi);
        tvJenis = findViewById(R.id.tvJenis);
        tvDeskripsi = findViewById(R.id.tvDeskripsi);
        tvSejarah = findViewById(R.id.tvSejarah);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnStop = findViewById(R.id.btnStop);

        String noInventaris = getIntent().getStringExtra("no_inventaris");

        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<GetKoleksi> koleksiCall = mApiInterface.getKoleksiDetail(noInventaris);
        koleksiCall.enqueue(new Callback<GetKoleksi>(){
            @Override
            public void onResponse(Call<GetKoleksi> call, Response<GetKoleksi> response){
                List<Koleksi> KoleksiList = response.body().getListDataKoleksi();
                    for (final Koleksi data : KoleksiList){
                        getSupportActionBar().setTitle(data.getNamaKoleksi());
                        tvNamaKoleksi.setText(data.getNamaKoleksi());

                        String jenis = data.getJenis().substring(0, 1).toUpperCase() + data.getJenis().substring(1);
                        tvJenis.setText(jenis);

                        if(data.getDeskripsi().length() == 0){
                            tvDeskripsi.setText("Tidak ada deskripsi");
                        }else{
                            String deskripsi = data.getDeskripsi().substring(0, 1).toUpperCase() + data.getDeskripsi().substring(1);
                            tvDeskripsi.setText(deskripsi);
                        }

                        if(data.getSejarah().length() == 0){
                            tvSejarah.setText("Tidak ada sejarah");
                        }else {
                            String sejarah = data.getSejarah().substring(0, 1).toUpperCase() + data.getSejarah().substring(1);
                            tvSejarah.setText(sejarah);
                        }

                        try{
                            mPlayer = new MediaPlayer();
                            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mPlayer.setDataSource(data.getAudio());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                mPlayer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            btnPlay.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mPlayer.start();
                                }
                            });

                            btnPause.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mPlayer.pause();
                                }
                            });

                            btnStop.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    mPlayer.stop();
                                    mPlayer = new MediaPlayer();
                                    mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                                    try {
                                        mPlayer.setDataSource(data.getAudio());
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    try {
                                        mPlayer.prepare();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }catch (Exception e){
                            Toast.makeText(getApplicationContext(), "File audio tidak tersedia", Toast.LENGTH_LONG).show();
                        }

                        if (data.getFoto() != null) {
                            Picasso.get().load(data.getFoto())
                                    .resize(1280, 880)
                                    .centerCrop()
                                    .into(ivFoto);
                        }
                    }
            }

            @Override
            public void onFailure(Call<GetKoleksi> call, Throwable t){
                Toast.makeText(getApplicationContext(), "Gagal mengambil data", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            mPlayer.stop();
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        mPlayer.stop();
        super.onDestroy();
    }
}
