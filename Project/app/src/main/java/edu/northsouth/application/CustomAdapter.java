package edu.northsouth.application;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Store> {

    private Activity context;
    private List<Store> storeList;

    public CustomAdapter(Activity context, List<Store> storeList) {
        super(context, R.layout.store_list_layout, storeList);
        this.context = context;
        this.storeList = storeList;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = context.getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.store_list_layout,null,true);

        Store store = storeList.get(position);

        TextView stName = view.findViewById(R.id.layoutName);
        TextView stLocatio = view.findViewById(R.id.layoutLocation);
        TextView stPrice = view.findViewById(R.id.layoutPrice);

        return view;
    }
}
