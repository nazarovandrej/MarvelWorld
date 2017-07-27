package com.github.andrejnazarov.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.github.andrejnazarov.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nazarov on 27.07.17.
 */

public class ComicsItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private CharacterClickListener mClickListener;

    @BindView(R.id.comics_item_text_view)
    TextView mComicsItemTextView;

    public ComicsItemViewHolder(View itemView, CharacterClickListener listener) {
        super(itemView);
        mClickListener = listener;
        ButterKnife.bind(this, itemView);
        mComicsItemTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}