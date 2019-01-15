package com.divakrishna.museumpos.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.divakrishna.museumpos.R;
import com.divakrishna.museumpos.ViewDataActivity;
import com.divakrishna.museumpos.model.Koleksi;
import com.squareup.picasso.Picasso;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import java.util.ArrayList;
import java.util.List;

public class KoleksiAdapter extends RecyclerView.Adapter<KoleksiAdapter.ViewHolderKoleksi>{

    List<Koleksi> mKoleksiList;

    public KoleksiAdapter(List<Koleksi> KoleksiList){
        mKoleksiList = KoleksiList;
    }

    public class ViewHolderKoleksi extends RecyclerView.ViewHolder{

        public TextView namaKoleksi;
        public JustifiedTextView deskripsi;
        public ImageView foto;

        public ViewHolderKoleksi(View itemView){
            super(itemView);

            namaKoleksi = itemView.findViewById(R.id.tvTitle);
            deskripsi = itemView.findViewById(R.id.tvDesc);
            foto = itemView.findViewById(R.id.ivImage);
        }

    }

    @Override
    public ViewHolderKoleksi onCreateViewHolder(ViewGroup parent, int viewType){
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_koleksi, parent, false);
        return new ViewHolderKoleksi(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderKoleksi holder, final int position) {
        holder.namaKoleksi.setText(mKoleksiList.get(position).getNamaKoleksi());

        String desc = mKoleksiList.get(position).getDeskripsi();

        if (desc.length()>250) {
            desc=desc.substring(0,250)+" ...Read More";
            Spannable spannable = new SpannableString(desc);
            spannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), desc.indexOf(" ...Read More"),desc.indexOf(" ...Read More") + " ...Read More".length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.deskripsi.setText(spannable);

        }else{
            if(desc.length() != 0){
                holder.deskripsi.setText(desc);
            }
            else{
                holder.deskripsi.setText("Tidak ada deskripsi");
            }

        }

        if (mKoleksiList.get(position).getFoto() != null) {
            Picasso.get().load(mKoleksiList.get(position).getFoto())
                    .resize(700, 650)
                    .centerCrop()
                    .into(holder.foto);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(view.getContext(), ViewDataActivity.class);
                mIntent.putExtra("no_inventaris", mKoleksiList.get(position).getNoInventaris().toString());
                view.getContext().startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mKoleksiList.size();
    }

    public void setCari(List<Koleksi> filterList){
        mKoleksiList = new ArrayList<>();
        mKoleksiList.addAll(filterList);
        notifyDataSetChanged();
    }

}
