package com.example.toptroc.Adaptaters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.toptroc.Models.ObjectModel;
import com.example.toptroc.R;

import java.util.ArrayList;
import java.util.List;

public class ObjectAdaptater extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<ObjectModel> objectList;

    public ObjectAdaptater(Context context, int layout, ArrayList<ObjectModel> objectList) {
        this.context = context;
        this.layout = layout;
        this.objectList = objectList;
    }

    @Override
    public int getCount() {
        return objectList.size();
    }

    @Override
    public Object getItem(int position) {
        return objectList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageViewObjectItem;
        TextView textViewNameObjectItem,textViewDescriptionObjectItem;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);

            holder.imageViewObjectItem = (ImageView) row.findViewById(R.id.imageViewObjectItem);
            holder.textViewNameObjectItem = (TextView) row.findViewById(R.id.textViewNameObjectItem);
            holder.textViewDescriptionObjectItem = (TextView) row.findViewById(R.id.textViewDescriptionObjectItem);

            row.setTag(holder);
        }else {
            holder = (ViewHolder) row.getTag();
        }

        ObjectModel objectModel = objectList.get(position);
        holder.textViewNameObjectItem.setText(objectModel.getName());
        holder.textViewDescriptionObjectItem.setText(objectModel.getDescription());

        byte[] imageObject = objectModel.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageObject,0,imageObject.length);
        holder.imageViewObjectItem.setImageBitmap(bitmap);

        return row;
    }
}
