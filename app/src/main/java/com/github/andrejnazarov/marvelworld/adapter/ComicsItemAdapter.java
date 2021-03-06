package com.github.andrejnazarov.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.andrejnazarov.marvelworld.R;
import com.github.andrejnazarov.marvelworld.bean.ComicsItem;

import java.util.List;

/**
 * @author Nazarov on 27.07.17.
 */

public class ComicsItemAdapter extends RecyclerView.Adapter<ComicsItemViewHolder>{

    private List<ComicsItem> mComicsItems;
    private CharacterClickListener mClickListener;

    public ComicsItemAdapter(List<ComicsItem> comicsItems,
                             CharacterClickListener clickListener) {
        mComicsItems = comicsItems;
        mClickListener = clickListener;
    }

    @Override
    public ComicsItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comics_item_layout, null);
        return new ComicsItemViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(ComicsItemViewHolder holder, int position) {
        ComicsItem comicsItem = mComicsItems.get(position);
        holder.mComicsItemTextView.setText(comicsItem.mName);
    }

    @Override
    public int getItemCount() {
        return mComicsItems.size();
    }
}