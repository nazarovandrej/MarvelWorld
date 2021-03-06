package com.github.andrejnazarov.marvelworld.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.andrejnazarov.marvelworld.R;
import com.github.andrejnazarov.marvelworld.bean.MarvelCharacter;
import com.github.andrejnazarov.marvelworld.bean.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * @author Nazarov on 26.07.17.
 */

public class CharacterAdapter extends RecyclerView.Adapter<CharacterViewHolder> {

    private List<MarvelCharacter> mCharacterList;
    private CharacterClickListener mClickListener;

    public CharacterAdapter(List<MarvelCharacter> characterList,
                            CharacterClickListener clickListener) {
        mCharacterList = characterList;
        mClickListener = clickListener;
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_layout, null);
        return new CharacterViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, int position) {
        MarvelCharacter character = mCharacterList.get(position);
        holder.mNameTextView.setText(character.mName);
        holder.mDescriptionTextView.setText(character.mDescription);
        Picasso.with(holder.itemView.getContext())
                .load(character.mThumbnail.getFullPath(Thumbnail.PORTRAIT_FANTASTIC))
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mCharacterList.size();
    }
}