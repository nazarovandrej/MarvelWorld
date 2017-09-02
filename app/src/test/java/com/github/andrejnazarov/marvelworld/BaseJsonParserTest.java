package com.github.andrejnazarov.marvelworld;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * @author Nazarov on 02.09.17.
 */

public class BaseJsonParserTest {

    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() {
        mObjectMapper = new ObjectMapper();
    }

    public void testParse(String testFile, Object objectToParse, Class clasz) {
        InputStream stream = clasz.getClassLoader().getResourceAsStream(testFile);
        Object actual = null;
        try {
            actual = mObjectMapper.readValue(stream, clasz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertThat(objectToParse, is(actual));
    }
}