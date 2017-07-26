package com.github.andrejnazarov.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nazarov on 26.07.17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Comics implements Parcelable{

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("collectionURI")
    public String mCollectionUri;

    @JsonProperty("items")
    public List<Comic> mComicList;

    public Comics() {
        //Empty constructor needed by Jackson
    }

    protected Comics(Parcel in) {
        mCollectionUri = in.readString();
        mComicList = new ArrayList<>();
        in.readList(mComicList, null);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCollectionUri);
        dest.writeList(mComicList);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comics comics = (Comics) o;
        return Objects.equal(mCollectionUri, comics.mCollectionUri) &&
                Objects.equal(mComicList, comics.mComicList);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mCollectionUri, mComicList);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mCollectionUri", mCollectionUri)
                .add("mComicList", mComicList)
                .toString();
    }

    private static final class ClassCreator implements Creator<Comics> {
        @Override
        public Comics createFromParcel(Parcel in) {
            return new Comics(in);
        }

        @Override
        public Comics[] newArray(int size) {
            return new Comics[size];
        }
    }
}