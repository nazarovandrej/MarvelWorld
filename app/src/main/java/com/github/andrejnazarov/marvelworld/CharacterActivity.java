package com.github.andrejnazarov.marvelworld;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrejnazarov.marvelworld.bean.MarvelCharacter;
import com.github.andrejnazarov.marvelworld.bean.MarvelUrl;
import com.github.andrejnazarov.marvelworld.bean.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CharacterActivity extends AppCompatActivity implements MarvelUrlFragment.OnItemClickListener {

    private static final String EXTRA_MARVEL_CHARACTER = "extra_marvel_character";

    private MarvelCharacter mMarvelCharacter;

    @BindView(R.id.character_image_view)
    ImageView mImageView;

    @BindView(R.id.character_name_text_view)
    TextView mNameTextView;

    @BindView(R.id.description_text_view)
    TextView mDescriptionTextView;

    public static Intent createExplicitIntent(Context context, MarvelCharacter character) {
        Intent intent = new Intent(context, CharacterActivity.class);
        intent.putExtra(EXTRA_MARVEL_CHARACTER, character);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ButterKnife.bind(this);
        mMarvelCharacter = getIntent().getExtras().getParcelable(EXTRA_MARVEL_CHARACTER);
        System.out.println(mMarvelCharacter.toString());
        fillUI();
        fillUrlContainer();
    }

    private void fillUI() {
        Picasso.with(this)
                .load(mMarvelCharacter.mThumbnail.getFullPath(Thumbnail.LANDSCAPE_INCREDIBLE))
                .into(mImageView);
        mNameTextView.setText(mMarvelCharacter.mName);
        mDescriptionTextView.setText(mMarvelCharacter.mDescription);
    }

    private void fillUrlContainer() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.urls_container, MarvelUrlFragment.newInstance(new ArrayList<>(mMarvelCharacter.mUrls)))
                .commit();
    }

    @Override
    public void onMarvelUrlClick(MarvelUrl marvelUrl) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marvelUrl.mUrl)));
    }
}