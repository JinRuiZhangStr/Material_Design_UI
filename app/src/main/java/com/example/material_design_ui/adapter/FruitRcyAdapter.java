package com.example.material_design_ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.material_design_ui.FruitActivity;
import com.example.material_design_ui.R;
import com.example.material_design_ui.bean.Fruit;

/**
 * Created by 张金瑞 on 2017/11/16.
 */

public class FruitRcyAdapter extends RecyclerView.Adapter<FruitRcyAdapter.FruitRcyViewholder> {

    private Context context;
    private Fruit fruit;

    static class FruitRcyViewholder extends RecyclerView.ViewHolder{

        CardView cardView;
        ImageView fruit_image;
        TextView fruit_name;

        public FruitRcyViewholder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            fruit_image = itemView.findViewById(R.id.fruit_img);
            fruit_name = itemView.findViewById(R.id.fruit_name);
        }
    }

    public FruitRcyAdapter (Context context,Fruit fruit){
        this.context = context;
        this.fruit = fruit;
    }
    @Override
    public FruitRcyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.furit_item,parent,false);
        final FruitRcyViewholder holder = new FruitRcyViewholder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int adapterPosition = holder.getAdapterPosition();
                Intent intent = new Intent( context , FruitActivity.class);
                intent.putExtra("fruit_name",fruit.getData().getNameList().get(adapterPosition));
                intent.putExtra("fruit_image",fruit.getData().getPicList().get(adapterPosition));
                context.startActivity(intent);
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(FruitRcyViewholder holder, final int position) {

        holder.fruit_name.setText(fruit.getData().getNameList().get(position));

        Glide.with(context).load(fruit.getData().getPicList().get(position)).into(holder.fruit_image);


    }

    @Override
    public int getItemCount() {
        return fruit.getData().getNameList().size();
    }
}
