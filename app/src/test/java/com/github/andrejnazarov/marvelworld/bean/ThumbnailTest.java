package com.github.andrejnazarov.marvelworld.bean;

import com.github.andrejnazarov.marvelworld.BaseJsonParserTest;

import org.junit.Test;

import java.io.IOException;

/**
 * Test for {@link Thumbnail}
 *
 * @author Nazarov on 02.09.17.
 */
public class ThumbnailTest extends BaseJsonParserTest {

    private static final String TEST_FILE = "thumbnail.json";

    @Test
    public void testParseThumbnail() throws IOException {
        testParse(TEST_FILE, getThumbnail(), Thumbnail.class);
    }

    private Thumbnail getThumbnail() {
        Thumbnail thumbnail = new Thumbnail();
        thumbnail.mExtension = "jpg";
        thumbnail.mPath = "http://i.annihil.us/u/prod/marvel/i/mg/2/30/531771c2ab020";
        return thumbnail;
    }
}