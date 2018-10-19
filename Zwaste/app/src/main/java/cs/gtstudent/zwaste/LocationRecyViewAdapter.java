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

public class LocationRecyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<LocationRecyViewItem> items;


    public static class LocationRecyViewHolder extends RecyclerView.ViewHolder{
        View view;
        LocationRecyViewItem item;
        ImageView locationPic;
        TextView locationName;
        TextView locationInfo;

        LocationRecyViewHolder(View view){
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


        public void setData(LocationRecyViewItem item) {
            this.item = item;
            locationPic.setImageDrawable(ContextCompat.getDrawable(view.getContext(), item.getImageID()));
            locationName.setText(item.getLocationName());
            locationInfo.setText(item.getLocationType());
        }
    }

    public LocationRecyViewAdapter(List<LocationRecyViewItem> items) {
        this.items = items;
    }

    @Override
    public LocationRecyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_style, parent, false);
        return new LocationRecyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        LocationRecyViewHolder locationRecyViewHolder = (LocationRecyViewHolder) holder;
        locationRecyViewHolder.setData(items.get(pos));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public LocationRecyViewItem getItem(int i) {
        return items.get(i);
    }

}
