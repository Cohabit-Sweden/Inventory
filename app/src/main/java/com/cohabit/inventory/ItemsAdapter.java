package com.cohabit.inventory;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class ItemsAdapter extends FirebaseRecyclerAdapter<Item, ItemsAdapter.ViewHolder> {

    public ItemsAdapter(@NonNull FirebaseRecyclerOptions<Item> options) {
        super(options);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewId;
        private final TextView textViewCategory;
        private final TextView textViewMaterial;
        private final TextView textViewFunctionality;
        private final TextView textViewAesthetic;
        private final TextView textViewColor;
        private final TextView textViewSize;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
           textViewId = itemView.findViewById(R.id.info_SKnumber);
           textViewCategory = itemView.findViewById(R.id.info_category);
           textViewMaterial = itemView.findViewById(R.id.info_material);
           textViewFunctionality = itemView.findViewById(R.id.info_functionality);
           textViewAesthetic = itemView.findViewById(R.id.info_aesthetic);
           textViewColor = itemView.findViewById(R.id.info_color);
           textViewSize = itemView.findViewById(R.id.info_size);
        }

        public TextView getView(){
            return textViewId;
        }
    }

    @Override
    public int getItemViewType(final int position) {
        return R.layout.item_card;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Item model) {
        Log.d("ItemsAdapter", "onBindViewHolder: " + position);
        holder.getView().setText(Integer.toString(model.getId()));
        Log.d("ItemsAdapter", "Category: " + model.getCategory());
        holder.textViewCategory.setText(model.getCategory());
        holder.textViewMaterial.setText(model.getMaterial());
        holder.textViewFunctionality.setText(model.getFunctionality());
        holder.textViewAesthetic.setText(model.getAesthetic());
        holder.textViewColor.setText(model.getColor());
        holder.textViewSize.setText(model.getSize());
    }
}
