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
                    String itemData = item.getItemInfo();

                    LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                    View customView = inflater.inflate(R.layout.location_pop_up,null);
                    TextView locText = customView.findViewById(R.id.fullLocData);
                    locText.setText(itemData);
                    PopupWindow mPopupWindow = new PopupWindow(
                            customView,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT
                    );

                    mPopupWindow.setBackgroundDrawable(new BitmapDrawable(view.getContext().getResources(),
                            Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)));
                    mPopupWindow.setOutsideTouchable(true);
                    mPopupWindow.showAtLocation(view, Gravity.CENTER,0,0);
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
