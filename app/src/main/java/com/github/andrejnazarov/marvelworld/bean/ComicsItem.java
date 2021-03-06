package com.github.andrejnazarov.marvelworld.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

/**
 * @author Nazarov on 26.07.17.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ComicsItem implements Parcelable{

    public static final ClassCreator CREATOR = new ClassCreator();

    @JsonProperty("resourceURI")
    public String mResourceUri;

    @JsonProperty("name")
    public String mName;

    public ComicsItem() {
        //Empty constructor needed by Jackson
    }

    protected ComicsItem(Parcel in) {
        mResourceUri = in.readString();
        mName = in.readString();
    }

    @JsonIgnore
    @Override
    public int describeContents() {
        return 0;
    }

    @JsonIgnore
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mResourceUri);
        dest.writeString(mName);
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComicsItem comicsItem = (ComicsItem) o;
        return Objects.equal(mResourceUri, comicsItem.mResourceUri) &&
                Objects.equal(mName, comicsItem.mName);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mResourceUri, mName);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mResourceUri", mResourceUri)
                .add("mName", mName)
                .toString();
    }

    private static final class ClassCreator implements Creator<ComicsItem> {
        @Override
        public ComicsItem createFromParcel(Parcel in) {
            return new ComicsItem(in);
        }

        @Override
        public ComicsItem[] newArray(int size) {
            return new ComicsItem[size];
        }
    }
}