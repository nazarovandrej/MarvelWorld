package com.github.andrejnazarov.marvelworld.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 26.07.17.
 */

public class ApiClient {
    private static final String BASE_URL = "http://gateway.marvel.com";
    private static Retrofit sRetrofit = null;

    private ApiClient() {
        throw new IllegalStateException("can't create an object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}