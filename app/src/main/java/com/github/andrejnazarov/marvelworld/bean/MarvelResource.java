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
public class MarvelResource implements Parcelable {

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("title")
    public String mTitle;

    @JsonProperty("description")
    public String mDescription;

    @JsonProperty("thumbnail")
    public Thumbnail mThumbnail;

    @JsonProperty("images")
    public List<Thumbnail> mImages;

    @JsonProperty("resourceURI")
    public String mResourceUri;

    @JsonProperty("urls")
    public List<MarvelUrl> mUrls;

    public MarvelResource() {
        //Empty constructor needed by Jackson
    }

    protected MarvelResource(Parcel in) {
        mTitle = in.readString();
        mDescription = in.readString();
        mThumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        mImages = in.createTypedArrayList(Thumbnail.CREATOR);
        mResourceUri = in.readString();
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
        dest.writeString(mTitle);
        dest.writeString(mDescription);
        dest.writeParcelable(mThumbnail, flags);
        dest.writeTypedList(mImages);
        dest.writeString(mResourceUri);
        dest.writeTypedList(mUrls);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarvelResource that = (MarvelResource) o;
        return Objects.equal(mTitle, that.mTitle) &&
                Objects.equal(mDescription, that.mDescription) &&
                Objects.equal(mThumbnail, that.mThumbnail) &&
                Objects.equal(mImages, that.mImages) &&
                Objects.equal(mResourceUri, that.mResourceUri) &&
                Objects.equal(mUrls, that.mUrls);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mTitle,
                mDescription,
                mThumbnail,
                mImages,
                mResourceUri,
                mUrls);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mTitle", mTitle)
                .add("mDescription", mDescription)
                .add("mThumbnail", mThumbnail)
                .add("mImages", mImages)
                .add("mResourceUri", mResourceUri)
                .add("mUrls", mUrls)
                .toString();
    }

    private static final class ClassCreator implements Creator<MarvelResource> {
        @Override
        public MarvelResource createFromParcel(Parcel in) {
            return new MarvelResource(in);
        }

        @Override
        public MarvelResource[] newArray(int size) {
            return new MarvelResource[size];
        }
    }
}