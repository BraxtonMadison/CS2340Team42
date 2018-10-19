package cs.gtstudent.zwaste;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemRecyViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ItemRecyViewItem> items;

    public static class ItemRecyViewHolder extends RecyclerView.ViewHolder {
        View view;
        ItemRecyViewItem item;
        ImageView itemImage;
        TextView itemInfo;

        ItemRecyViewHolder (View view) {
            super(view);
            this.view = view;
            itemImage = view.findViewById(R.id.itemImage);
            itemInfo = view.findViewById(R.id.itemInfo);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        public void setData(ItemRecyViewItem item) {
            this.item = item;
            itemImage.setImageDrawable(ContextCompat.getDrawable(view.getContext(), item.getImageID()));
            itemInfo.setText(item.getItemInfo());
        }
    }

    public ItemRecyViewAdapter(List<ItemRecyViewItem> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_style, parent, false);
        return new ItemRecyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int pos) {
        ItemRecyViewHolder locationRecyViewHolder = (ItemRecyViewHolder) holder;
        locationRecyViewHolder.setData(items.get(pos));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public ItemRecyViewItem getItem(int i) {
        return items.get(i);
    }
}
