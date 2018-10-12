package cs.gtstudent.zwaste;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationListAdapter extends BaseAdapter {
    private List<LocationListViewItem> items;

    public LocationListAdapter() {
        items = new ArrayList<>();
    }

    public void addItem(Drawable icon, String location, String locType, LocationData locData) {
        LocationListViewItem item = new LocationListViewItem();
        item.setImageID(icon);
        item.setLocationName(location);
        item.setLocationType(locType);
        item.setLocationData(locData);
        items.add(item);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.location_list_style, viewGroup, false);
        }

        ImageView locationImage = (ImageView) view.findViewById(R.id.locationImage);
        locationImage.setMaxWidth(10);
        locationImage.setMaxHeight(10);
        TextView locationName = (TextView) view.findViewById(R.id.location);
        TextView locationType = (TextView) view.findViewById(R.id.locType);

        LocationListViewItem item = items.get(i);

        locationImage.setImageDrawable(item.getImageID());
        locationName.setText(item.getLocationName());
        locationType.setText(item.getLocationType());

        return view;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

}
