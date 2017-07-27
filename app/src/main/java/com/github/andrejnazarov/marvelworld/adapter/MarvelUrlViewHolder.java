package com.github.andrejnazarov.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.github.andrejnazarov.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nazarov on 27.07.17.
 */

public class MarvelUrlViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private CharacterClickListener mClickListener;

    @BindView(R.id.url_button)
    Button mUrlButton;

    public MarvelUrlViewHolder(View itemView, CharacterClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mClickListener = listener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}