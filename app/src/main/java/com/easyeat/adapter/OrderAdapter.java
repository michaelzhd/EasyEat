package com.easyeat.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easyeat.Config;
import com.easyeat.R;
import com.easyeat.RestaurantIntroActivity;
import com.easyeat.bean.ReservationInfo;

/**
 * Created by chenglongwei on 11/22/16.
 */

public class OrderAdapter extends BaseAdapter {
    private ReservationInfo[] reservationInfos;
    private Context context;

    public OrderAdapter(Context context, ReservationInfo[] reservationInfos) {
        this.context = context;
        this.reservationInfos = reservationInfos;
    }

    @Override
    public int getCount() {
        return reservationInfos.length;
    }

    @Override
    public Object getItem(int position) {
        if (position >= 0 && position < reservationInfos.length) {
            return reservationInfos[position];
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        Object obj = getItem(position);
        if (obj != null) {
            return ((ReservationInfo) obj).reservation.id;
        }
        return -1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);
            Holder holder = new Holder();
            holder.tv_date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.tv_slot = (TextView) convertView.findViewById(R.id.tv_slot);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_price);

            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            holder.tv_address = (TextView) convertView.findViewById(R.id.tv_address);
            holder.tv_status = (TextView) convertView.findViewById(R.id.tv_status);
            convertView.setTag(holder);
        }

        initData(convertView, position);
        return convertView;
    }

    private void initData(final View convertView, final int position) {
        // String demoUrl = "https://static1.squarespace.com/static/56f43a462eeb819927f7039d/56f5e1e11d07c03c85cd8d1c/5713e8e122482eca2d3bcdf6/1460922599327/dining+hall+3.jpg";
        Holder holder = (Holder) convertView.getTag();
        final ReservationInfo item = (ReservationInfo) getItem(position);

        holder.tv_date.setText(item.reservation.date);
        holder.tv_slot.setText(item.reservation.timeSlot);
        holder.tv_price.setText("$" + item.reservation.price);

        holder.tv_name.setText(item.restaurant.name);
        holder.tv_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RestaurantIntroActivity.class);
                intent.putExtra(Config.key_restaurant, item.restaurant);
                context.startActivity(intent);
            }
        });
        holder.tv_address.setText(item.restaurant.address);
        holder.tv_status.setText(item.reservation.status);
    }

    private class Holder {
        TextView tv_date;
        TextView tv_slot;
        TextView tv_price;

        TextView tv_name;
        TextView tv_address;
        TextView tv_status;
    }
}