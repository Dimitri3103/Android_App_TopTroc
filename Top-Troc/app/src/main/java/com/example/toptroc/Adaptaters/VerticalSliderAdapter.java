package com.example.toptroc.Adaptaters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toptroc.Models.VerticalSliderModel;
import com.example.toptroc.R;

import java.util.List;

public class VerticalSliderAdapter extends RecyclerView.Adapter<VerticalSliderAdapter.ObjectViewHolder>{

    private List<VerticalSliderModel> verticalSliderModelList;
    private Context context;

    public VerticalSliderAdapter(List<VerticalSliderModel> verticalSliderModelList, Context context) {
        this.verticalSliderModelList =verticalSliderModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public VerticalSliderAdapter.ObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.vertical_slider,viewGroup,false);
        return new ObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VerticalSliderAdapter.ObjectViewHolder holder, int position) {

        VerticalSliderModel verticalSliderModel = verticalSliderModelList.get(position);

        Glide.with(context).load(verticalSliderModel.getObject_img()).into(holder.objImg);
        Glide.with(context).load(verticalSliderModel.getObject_message()).into(holder.object_message);
        holder.nameUser.setText(verticalSliderModel.getNomUser());
        holder.nameObject.setText(verticalSliderModel.getNomObjet());
        holder.descriptionObject.setText(verticalSliderModel.getDescriptionObjet());
        holder.contactUser.setText(verticalSliderModel.getContactUser());

    }

    @Override
    public int getItemCount() {
        return verticalSliderModelList.size();
    }

    public class ObjectViewHolder extends RecyclerView.ViewHolder {

        private ImageView objImg,object_message;
        private TextView nameUser,nameObject,descriptionObject,contactUser;


        public ObjectViewHolder(@NonNull View itemView) {
            super(itemView);

            object_message = (ImageView) itemView.findViewById(R.id.objectMessage);
            objImg = (ImageView) itemView.findViewById(R.id.imageViewObjectSlider);
            nameUser = (TextView) itemView.findViewById(R.id.nomUser);
            nameObject = (TextView) itemView.findViewById(R.id.nomObjet);
            descriptionObject = (TextView) itemView.findViewById(R.id.descriptionObjet);
            contactUser = (TextView) itemView.findViewById(R.id.contactUser);
        }
    }
}
