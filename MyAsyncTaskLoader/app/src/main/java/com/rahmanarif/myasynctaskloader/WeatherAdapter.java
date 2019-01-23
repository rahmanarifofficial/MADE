package com.rahmanarif.myasynctaskloader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class WeatherAdapter extends BaseAdapter {
    private ArrayList<WeatherItems> data = new ArrayList<>();
    private LayoutInflater inflater;
    private Context context;

    public WeatherAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<WeatherItems> items) {
        data = items;
        notifyDataSetChanged();
    }

    public void addItem(final WeatherItems items) {
        data.add(items);
        notifyDataSetChanged();
    }

    public void clearData() {
        data.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    @Override
    public WeatherItems getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.weather_items, null);
            holder.tvNamaKota = convertView.findViewById(R.id.textKota);
            holder.tvTemperature = convertView.findViewById(R.id.textTemp);
            holder.tvDescription = convertView.findViewById(R.id.textDesc);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvNamaKota.setText(data.get(position).getNama());
        holder.tvTemperature.setText(data.get(position).getTemperature());
        holder.tvDescription.setText(data.get(position).getDescription());
        return convertView;
    }

    private static class ViewHolder {
        TextView tvNamaKota, tvTemperature, tvDescription;
    }
}
