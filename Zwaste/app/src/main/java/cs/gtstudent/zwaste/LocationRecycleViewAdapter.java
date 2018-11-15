package cs.gtstudent.zwaste;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Controller class that defines format and actions for locations listed.
 */
public class LocationRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final List<LocationRecycleViewItem> items;


    public static class LocationRecycleViewHolder extends RecyclerView.ViewHolder{
        final View view;
        LocationRecycleViewItem item;
        final ImageView locationPic;
        final TextView locationName;
        final TextView locationInfo;

        LocationRecycleViewHolder(View view){
            super(view);
            this.view = view;
            locationPic = view.findViewById(R.id.locationImage);
            locationName = view.findViewById(R.id.locationName);
            locationInfo = view.findViewById(R.id.locType);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context appContext = view.getContext();
                    Intent intent = new Intent(appContext, ShowOptionsActivity.class);
                    intent.putExtra("LOCATION_DATA", item);
                    appContext.startActivity(intent);
                }
            });
        }

        /**
         * Brings data of the location and sets it to the view adapter.
         * @param item instance of LocationRecycleViewItem, which holds information about the item.
         */
        void setData(LocationRecycleViewItem item) {
            locationPic.setImageDrawable(ContextCompat.getDrawable(view.getContext(),
                                                                   item.getImageID()));
            locationName.setText(item.getLocationName());
            locationInfo.setText(item.getLocationType());
            this.item = item;
        }
    }

    /**
     * Constructor for LocationRecycleViewAdapter.
     * Receives and stores list of instances holding information of each location
     * @param items list of LocationRecycleViewItem instances.
     */
    public LocationRecycleViewAdapter(List<LocationRecycleViewItem> items) {
        this.items = items;
    }

    @Override
    public LocationRecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_style,
                                                                        parent, false);
        return new LocationRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        LocationRecycleViewHolder locationRecycleViewHolder = (LocationRecycleViewHolder) holder;
        locationRecycleViewHolder.setData(items.get(pos));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


}
