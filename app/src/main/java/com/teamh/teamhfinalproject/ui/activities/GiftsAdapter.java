package com.teamh.teamhfinalproject.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import com.squareup.picasso.Picasso;
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
    private String url;
    private Uri uri;
    private String defaultUrl = "https://miro.medium.com/max/800/1*hFwwQAW45673VGKrMPE2qQ.png";

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
        holder.priceGift.setText(price + " $");
        url = gift.getUrl();
        Picasso.get().load(gift.getImgUrl()).resize(370, 220).error(R.drawable.gift_default).into(holder.imageGift);

        holder.imageGift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                url = gift.getUrl();
                uri = Uri.parse(url); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    static class ViewHolder{
        ImageView imageGift;
        TextView nameGift;
        TextView priceGift;
        RadioButton likeGift;
    }
}

