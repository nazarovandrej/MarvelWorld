package com.github.andrejnazarov.marvelworld;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.andrejnazarov.marvelworld.adapter.CharacterClickListener;
import com.github.andrejnazarov.marvelworld.adapter.ComicsItemAdapter;
import com.github.andrejnazarov.marvelworld.bean.ComicsItem;
import com.github.andrejnazarov.marvelworld.bean.Comics;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComicsFragment extends Fragment implements CharacterClickListener {

    private static final String EXTRA_COMICS = "extra_comics";

    private Comics mComics;

    private OnComicsItemListener mListener;
    private ComicsItemAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    public static ComicsFragment newInstance(Comics comics) {
        ComicsFragment fragment = new ComicsFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_COMICS, comics);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnComicsItemListener) {
            mListener = (OnComicsItemListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnComicsItemListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mComics = getArguments().getParcelable(EXTRA_COMICS);
            mAdapter = new ComicsItemAdapter(mComics.mComicsItemList, this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.single_recycler_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(int position) {
        if (mListener != null) {
            mListener.onComicsItemClick(mComics.mComicsItemList.get(position));
        }
    }

    public interface OnComicsItemListener {
        void onComicsItemClick(ComicsItem comicsItem);
    }
}