package com.github.andrejnazarov.marvelworld.bean;

import android.os.Parcel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Test restoring from Parcel for {@link Thumbnail}
 *
 * @author Nazarov on 02.09.17.
 */
@RunWith(RobolectricTestRunner.class)
public class ThumbnailParcelableTest {

    private Thumbnail mThumbnail;

    @Before
    public void setUp() {
        mThumbnail = getThumbnail();
    }

    @Test
    public void testParcelable() {
        //создаем объект Parcel
        Parcel parcel = Parcel.obtain();
        //записываем наш объект
        parcel.writeParcelable(mThumbnail, 0);
        //устаналиваем позицию в начало для чтения
        parcel.setDataPosition(0);
        //восстанавливаем данные из Parcel
        Thumbnail thumbnail = parcel.readParcelable(Thumbnail.class.getClassLoader());
        //проверка значений
        assertThat(thumbnail, is(mThumbnail));
    }

    private Thumbnail getThumbnail() {
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.mExtension = "jpg";
        thumbnail.mPath = "http://i.annihil.us/u/prod/marvel/i/mg/2/30/531771c2ab020";
        return thumbnail;
    }
}