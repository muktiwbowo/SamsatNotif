package com.dabudabu.samsatnotif.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dabudabu.samsatnotif.R;
import com.dabudabu.samsatnotif.model.ItemInformasi;

import java.util.List;

public class InformasiAdapter extends RecyclerView.Adapter<InformasiAdapter.ItemInfo> {
    private List<ItemInformasi> informasis;
    private Context context;
    private static int currentPosition = 0;

    public InformasiAdapter(List<ItemInformasi> informasis, Context context) {
        this.informasis = informasis;
        this.context = context;
    }

    @Override
    public InformasiAdapter.ItemInfo onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_informasi, parent,false);
        return new ItemInfo(view);
    }

    @Override
    public void onBindViewHolder(final InformasiAdapter.ItemInfo holder, final int position) {
        ItemInformasi informasi = informasis.get(position);
        holder.txtTitle.setText(informasi.getTitle());
        holder.txtContent.setText(informasi.getContent());
        holder.linearLayout.setVisibility(View.GONE);
        if (currentPosition == position){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim);
            holder.linearLayout.setVisibility(View.VISIBLE);
            holder.linearLayout.startAnimation(animation);
        }
        holder.expandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return informasis.size();
    }

    class ItemInfo extends RecyclerView.ViewHolder{
        private TextView txtTitle, txtContent;
        private ImageView expandMore;
        LinearLayout linearLayout;
        public ItemInfo(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txttitle);
            txtContent = itemView.findViewById(R.id.txtcontent);
            expandMore = itemView.findViewById(R.id.imgexpandmore);
            linearLayout = itemView.findViewById(R.id.linearlayout);
        }
    }
}
