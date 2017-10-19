package com.cmpe496.ekrem.e_commerce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ekrem on 06/03/2017.
 */

public class ItemAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Item> itemList = new ArrayList<Item>();

    public ItemAdapter(Context context, ArrayList<Item> itemList) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        if (itemList != null)
            return itemList.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return itemList.indexOf(itemList.get(position));
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else holder = (ViewHolder) view.getTag();

        Item item = itemList.get(position);


        holder.ivItemImage.setImageResource(item.getPhoto());
        holder.tvItemName.setText(item.getName());
        holder.tvYear.setText(item.getYear());
        holder.tvLength.setText(item.getLength());
        holder.tvLocation.setText(item.getLocation());
        holder.tvMaterial.setText(item.getMaterial());
        String price = "" + item.getPrice() + "$";
        holder.tvPrice.setText(price);

        return view;
    }

    static class ViewHolder {
        @Bind(R.id.iv_item_image)
        ImageView ivItemImage;
        @Bind(R.id.tv_item_name)
        TextView tvItemName;
        @Bind(R.id.tv_year)
        TextView tvYear;
        @Bind(R.id.tv_length)
        TextView tvLength;
        @Bind(R.id.tv_location)
        TextView tvLocation;
        @Bind(R.id.tv_material)
        TextView tvMaterial;
        @Bind(R.id.tv_price)
        TextView tvPrice;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
