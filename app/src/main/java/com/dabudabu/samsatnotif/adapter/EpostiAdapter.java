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
import com.dabudabu.samsatnotif.model.ItemEposti;

import java.util.List;

public class EpostiAdapter extends RecyclerView.Adapter<EpostiAdapter.EpostiHolder> {
    private List<ItemEposti> epostis;
    private Context context;
    private static int currentPosition = 0;

    public EpostiAdapter(List<ItemEposti> epostis, Context context) {
        this.epostis = epostis;
        this.context = context;
    }

    @Override
    public EpostiAdapter.EpostiHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_eposti, parent, false);
        return new EpostiHolder(view);
    }

    @Override
    public void onBindViewHolder(EpostiAdapter.EpostiHolder holder, final int position) {
        ItemEposti eposti = epostis.get(position);
        holder.eKota.setText(eposti.getPlace());
        holder.eAlamat.setText(eposti.getAlamat());
        holder.eLinearLayout.setVisibility(View.GONE);
        if (currentPosition == position){
            Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim);
            holder.eLinearLayout.setVisibility(View.VISIBLE);
            holder.eLinearLayout.startAnimation(animation);
        }
        holder.imgExpandMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return epostis.size();
    }

    class EpostiHolder extends RecyclerView.ViewHolder{
        private TextView eKota, eAlamat;
        private ImageView imgExpandMore;
        LinearLayout eLinearLayout;
        public EpostiHolder(View itemView) {
            super(itemView);
            eKota = itemView.findViewById(R.id.ekota);
            eAlamat = itemView.findViewById(R.id.ealamat);
            imgExpandMore = itemView.findViewById(R.id.eimgexpandmore);
            eLinearLayout = itemView.findViewById(R.id.elinearlayout);
        }
    }
}
