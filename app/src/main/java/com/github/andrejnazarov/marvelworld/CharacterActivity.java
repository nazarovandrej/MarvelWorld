package com.github.andrejnazarov.marvelworld;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.andrejnazarov.marvelworld.adapter.ComicsPagerAdapter;
import com.github.andrejnazarov.marvelworld.bean.BaseResponse;
import com.github.andrejnazarov.marvelworld.bean.ComicsItem;
import com.github.andrejnazarov.marvelworld.bean.MarvelCharacter;
import com.github.andrejnazarov.marvelworld.bean.MarvelResource;
import com.github.andrejnazarov.marvelworld.bean.MarvelUrl;
import com.github.andrejnazarov.marvelworld.bean.Thumbnail;
import com.github.andrejnazarov.marvelworld.net.ApiClient;
import com.github.andrejnazarov.marvelworld.net.MarvelService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity implements MarvelUrlFragment.OnItemClickListener, ComicsFragment.OnComicsItemListener {

    public static final String TAG = CharacterActivity.class.getSimpleName();

    private static final String API_KEY = "8ce13978a563fbc8c0353ac9008e8bfd";
    private static final String HASH = "da3930ee8a5cf7ae5bce3dc296b6d6b6";
    private static final String EXTRA_MARVEL_CHARACTER = "extra_marvel_character";

    private MarvelService mService;

    private MarvelCharacter mMarvelCharacter;
    private FragmentManager mFragmentManager;

    @BindView(R.id.character_image_view)
    ImageView mImageView;

    @BindView(R.id.character_name_text_view)
    TextView mNameTextView;

    @BindView(R.id.description_text_view)
    TextView mDescriptionTextView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.view_pager)
    ViewPager mViewPager;

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
        mFragmentManager = getSupportFragmentManager();
        fillUI();
        fillComicsContainer();
        fillUrlContainer();
    }

    @Override
    public void onComicsItemClick(ComicsItem comicsItem) {
        showProgressBar();
        getResource(comicsItem.mResourceUri);
    }

    @Override
    public void onMarvelUrlClick(MarvelUrl marvelUrl) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(marvelUrl.mUrl)));
    }

    private void fillUI() {
        Picasso.with(this)
                .load(mMarvelCharacter.mThumbnail.getFullSize())
                .into(mImageView);
        mNameTextView.setText(mMarvelCharacter.mName);
        mDescriptionTextView.setText(mMarvelCharacter.mDescription);
        mViewPager.setVisibility(View.GONE);
    }

    private void fillComicsContainer() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.comics_container, ComicsFragment.newInstance(mMarvelCharacter.mComics))
                .commit();
    }

    private void fillUrlContainer() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.urls_container, MarvelUrlFragment.newInstance(new ArrayList<>(mMarvelCharacter.mUrls)))
                .commit();
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }


    private void getResource(String resourceUri) {
        mService = ApiClient.getClient().create(MarvelService.class);
        Call<BaseResponse<MarvelResource>> responseCall = mService.getMarvelResources(resourceUri, 1, HASH, API_KEY);

        responseCall.enqueue(new Callback<BaseResponse<MarvelResource>>() {
            @Override
            public void onResponse(Call<BaseResponse<MarvelResource>> call, Response<BaseResponse<MarvelResource>> response) {
                BaseResponse<MarvelResource> baseResponse = response.body();
                hideProgressBar();
                if (baseResponse != null) {
                    List<Thumbnail> images = baseResponse.mResponseData.mCharacterList.get(0).mImages;
                    ComicsPagerAdapter adapter = new ComicsPagerAdapter(getApplicationContext(), images);
                    mViewPager.setVisibility(View.VISIBLE);
                    mViewPager.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<MarvelResource>> call, Throwable t) {
                hideProgressBar();
                Log.e(TAG, "failure with message " + t.getMessage());
            }
        });

    }
}