package cs.gtstudent.zwaste;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Controller class that defines format and actions of items listed.
 */
class ItemRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<ItemRecycleViewItem> items;

    static class ItemRecycleViewHolder extends RecyclerView.ViewHolder {
        final View view;
        ItemRecycleViewItem item;
        final ImageView itemImage;
        final TextView itemName;
        final TextView itemType;

        ItemRecycleViewHolder (View view) {
            super(view);
            this.view = view;
            itemImage = view.findViewById(R.id.itemImage);
            itemName = view.findViewById(R.id.itemName);
            itemType = view.findViewById(R.id.itemType);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String thisItemName = item.getItemName();

                    LayoutInflater inflater = (LayoutInflater) view.getContext()
                                            .getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customView = inflater.inflate(R.layout.location_pop_up, null);
                    TextView locText = customView.findViewById(R.id.fullLocData);
                    locText.setText(thisItemName);
                    PopupWindow mPopupWindow = new PopupWindow(
                            customView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

                    mPopupWindow.setBackgroundDrawable(new BitmapDrawable(view.getContext()
                                                                            .getResources(),
                            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)));
                    mPopupWindow.setOutsideTouchable(true);
                    mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
                }
            });
        }

        /**
         * Receives data of the item and sets it to the view adapter.
         * @param item instance of ItemRecycleViewItem, holds information about the location.
         */
        void setData(ItemRecycleViewItem item) {
            this.item = item;
            itemImage.setImageDrawable(ContextCompat.getDrawable(view.getContext(),
                                                                    item.getImageID()));
            itemName.setText(item.getItemName());
            itemType.setText(item.getItemType());
        }
    }
    /**
     * Constructor for ItemRecycleViewAdapter.
     * Receives and stores list of instances holding information of each item.
     * @param items lists of ItemRecycleViewItem instances.
     */
    public ItemRecycleViewAdapter(List<ItemRecycleViewItem> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_style,
                                                                        parent, false);
        return new ItemRecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        ItemRecycleViewHolder locationRecycleViewHolder = (ItemRecycleViewHolder) holder;
        locationRecycleViewHolder.setData(items.get(pos));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
