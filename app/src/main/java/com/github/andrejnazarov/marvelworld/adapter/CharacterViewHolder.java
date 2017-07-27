package com.github.andrejnazarov.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrejnazarov.marvelworld.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nazarov on 26.07.17.
 */

public class CharacterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private CharacterClickListener mClickListener;

    @BindView(R.id.character_image_view)
    ImageView mImageView;

    @BindView(R.id.character_name_text_view)
    TextView mNameTextView;

    @BindView(R.id.description_text_view)
    TextView mDescriptionTextView;

    public CharacterViewHolder(View itemView, CharacterClickListener listener) {
        super(itemView);
        mClickListener = listener;
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mClickListener.onItemClick(getLayoutPosition());
    }
}