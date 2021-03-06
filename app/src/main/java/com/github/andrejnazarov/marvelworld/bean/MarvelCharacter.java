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
@JsonIgnoreProperties(ignoreUnknown = true)
public class MarvelCharacter implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("name")
    public String mName;

    @JsonProperty("description")
    public String mDescription;

    @JsonProperty("thumbnail")
    public Thumbnail mThumbnail;

    @JsonProperty("comics")
    public Comics mComics;

    @JsonProperty("urls")
    public List<MarvelUrl> mUrls;

    public MarvelCharacter() {
        //Empty constructor needed by Jackson
    }

    protected MarvelCharacter(Parcel in) {
        mName = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mComics = in.readParcelable(Comics.class.getClassLoader());
        mUrls = new ArrayList<>();
        in.readTypedList(mUrls, MarvelUrl.CREATOR);
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeParcelable(mComics, flags);
        dest.writeTypedList(mUrls);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelCharacter that = (MarvelCharacter) o;
        return Objects.equal(mName, that.mName) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mComics, that.mComics) &&
                Objects.equal(mUrls, that.mUrls);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mName,
                mDescription,
                mThumbnail,
                mComics,
                mUrls);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mName", mName)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mComics", mComics)
                .add("mUrls", mUrls)
                .toString();
    }

    private static final class ClassCreator implements Creator<MarvelCharacter> {
        @Override
        public MarvelCharacter createFromParcel(Parcel in) {
            return new MarvelCharacter(in);
        }

        @Override
        public MarvelCharacter[] newArray(int size) {
            return new MarvelCharacter[size];
        }
    }
}