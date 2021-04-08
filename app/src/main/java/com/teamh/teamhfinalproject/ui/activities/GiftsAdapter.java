package com.teamh.teamhfinalproject.ui.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.teamh.teamhfinalproject.R;
import com.teamh.teamhfinalproject.api.models.EtsyProduct;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class GiftsAdapter extends BaseAdapter {

    private Context context;
    private List<EtsyProduct> gifts;
    private LayoutInflater layoutInflater;
    private String price;

    public GiftsAdapter(Context context, List<EtsyProduct> gifts){
        this.context = context;
        this.gifts = gifts;
    }

    @Override
    public int getCount() {
        return gifts.size();
    }

    @Override
    public Object getItem(int position) {
        return gifts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.from(context).inflate(R.layout.gift_layout, parent, false);
            holder = new ViewHolder();
            holder.imageGift = (ImageView) convertView.findViewById(R.id.imageGift);
            holder.nameGift = (TextView) convertView.findViewById(R.id.nameGift);
            holder.priceGift = (TextView) convertView.findViewById(R.id.priceGift);
            holder.likeGift = (RadioButton) convertView.findViewById(R.id.likeGift);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        EtsyProduct gift = this.gifts.get(position);
        holder.nameGift.setText(gift.getTitle());
        price = Double.toString(gift.getPrice());
        holder.priceGift.setText(price);
        new DownLoadImageTask(holder.imageGift).execute(gift.getImgUrl());

        return convertView;
    }

    static class ViewHolder{
        ImageView imageGift;
        TextView nameGift;
        TextView priceGift;
        RadioButton likeGift;
    }
}

class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
    ImageView imageView;

    public DownLoadImageTask(ImageView imageView){
        this.imageView = imageView;
    }

    /*
        doInBackground(Params... params)
            Override this method to perform a computation on a background thread.
     */
    protected Bitmap doInBackground(String...urls){
        String urlOfImage = urls[0];
        Bitmap logo = null;
        try{
            InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
            logo = BitmapFactory.decodeStream(is);
        }catch(Exception e){ // Catch the download exception
            e.printStackTrace();
        }
        return logo;
    }

    /*
        onPostExecute(Result result)
            Runs on the UI thread after doInBackground(Params...).
     */
    protected void onPostExecute(Bitmap result){
        imageView.setImageBitmap(result);
    }
}

