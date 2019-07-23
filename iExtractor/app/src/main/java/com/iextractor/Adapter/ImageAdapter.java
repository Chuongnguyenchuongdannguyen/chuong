package com.iextractor.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iextractor.Model.Image_From_Video;
import com.iextractor.R;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ThemeViewHolder> {
    private Context mContext;
    private ArrayList<Image_From_Video> mThemelist = new ArrayList<>();
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public ImageAdapter(Context context, ArrayList<Image_From_Video> themeList) {
        mContext = context;
        mThemelist = themeList;
    }

    @NonNull
    @Override
    public ThemeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_from_video_item, viewGroup, false);
        return new ThemeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ThemeViewHolder holder, int position) {
        Image_From_Video currentItem = mThemelist.get(position);
        Bitmap imageGrade = currentItem.getBitmap();
        holder.imageGrade.setImageBitmap(imageGrade);
    }

    @Override
    public int getItemCount() {
        return mThemelist.size();
    }

    public class ThemeViewHolder extends RecyclerView.ViewHolder {
        ImageView imageGrade;

        ThemeViewHolder(View itemView) {
            super(itemView);
            imageGrade = itemView.findViewById(R.id.image_view);
            itemView.setOnClickListener(v -> {
                if (mListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(position);
                    }
                }
            });
        }

    }
}
