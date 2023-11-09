package com.hamidul.searchview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<HashMap<String, String>> itemList;
    private ArrayList<HashMap<String, String>> filteredList;
    LayoutInflater layoutInflater;

    public CustomAdapter(Context context, ArrayList<HashMap<String, String>> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.filteredList = new ArrayList<>(itemList);
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Implement your view creation logic here
        // Example:
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View myView = layoutInflater.inflate(R.layout.item,parent,false);

        // Populate the view with data from your HashMap
        HashMap<String, String> item = filteredList.get(position);
        TextView text1 = myView.findViewById(R.id.itemCat);
        TextView text2 = myView.findViewById(R.id.itemTitle);

        text1.setText(item.get("cat"));
        text2.setText(item.get("title"));

        return myView;
    }

    // Filter method to update the filtered list based on user input
    public void filter(String query) {
        query = query.toLowerCase();
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(itemList);
        } else {
            for (HashMap<String, String> item : itemList) {
                // Implement your search logic here
                // For example, search in specific keys within the HashMap
                if (item.get("cat").toLowerCase().contains(query)
                        || item.get("title").toLowerCase().contains(query)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}

