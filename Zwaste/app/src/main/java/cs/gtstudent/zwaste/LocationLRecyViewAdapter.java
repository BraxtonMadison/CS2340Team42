package cs.gtstudent.zwaste;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationLRecyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<LocationRecyViewItem> items;
    private final View.OnClickListener adapterOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Context currentAcitivityContext = view.getContext();
            Intent intent = new Intent(currentAcitivityContext, ShowItemsActivity.class);
            //Add info about clicked item here
            currentAcitivityContext.startActivity(intent);
        }
    };

    public static class LocationRecyViewHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView locationPic;
        TextView locationName;
        TextView locationInfo;

        LocationRecyViewHolder(View view){
            super(view);
            this.view = view;
            locationPic = view.findViewById(R.id.locationImage);
            locationName = view.findViewById(R.id.locationName);
            locationInfo = view.findViewById(R.id.locType);
        }
    }

    public LocationLRecyViewAdapter(List<LocationRecyViewItem> items) {
        this.items = items;
    }

    @Override
    public LocationRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_style, parent, false);
        view.setOnClickListener(adapterOnClickListener);
        return new LocationRecyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        LocationRecyViewHolder locationRecyViewHolder = (LocationRecyViewHolder) holder;

        locationRecyViewHolder.locationPic.setImageDrawable(items.get(pos).getImageID());
        locationRecyViewHolder.locationName.setText(items.get(pos).getLocationName());
        locationRecyViewHolder.locationInfo.setText(items.get(pos).getLocationType());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public LocationRecyViewItem getItem(int i) {
        return items.get(i);
    }

}
