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
import com.dabudabu.samsatnotif.model.ItemSamsatDesa;

import java.util.List;

public class SamsatDesaAdapter extends RecyclerView.Adapter<SamsatDesaAdapter.DesaHolder> {
    private List<ItemSamsatDesa> desas;
    private Context context;
    private static int currentPosition = 0;

    public SamsatDesaAdapter(List<ItemSamsatDesa> desas, Context context) {
        this.desas = desas;
        this.context = context;
    }

    @Override
    public SamsatDesaAdapter.DesaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_samsatdesa, parent, false);
        return new DesaHolder(view);
    }

    @Override
    public void onBindViewHolder(SamsatDesaAdapter.DesaHolder holder, final int position) {
        ItemSamsatDesa itemSamsatDesa = desas.get(position);
        holder.desaTitle.setText(itemSamsatDesa.getPlace());
        holder.desaALamat.setText(itemSamsatDesa.getAlamatdesa());
        holder.desaLinearLayout.setVisibility(View.GONE);
        if (currentPosition == position) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.anim);
            holder.desaLinearLayout.setVisibility(View.VISIBLE);
            holder.desaLinearLayout.startAnimation(animation);
        }

        holder.desaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return desas.size();
    }

    class DesaHolder extends RecyclerView.ViewHolder{
        private TextView desaTitle, desaALamat;
        private ImageView desaImg;
        LinearLayout desaLinearLayout;
        public DesaHolder(View itemView) {
            super(itemView);
            desaTitle = itemView.findViewById(R.id.desatitle);
            desaALamat = itemView.findViewById(R.id.desaalamat);
            desaImg = itemView.findViewById(R.id.desaimgexpandmore);
            desaLinearLayout = itemView.findViewById(R.id.desalinearlayout);
        }
    }
}
