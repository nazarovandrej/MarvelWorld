package com.github.andrejnazarov.marvelworld.net;

import com.github.andrejnazarov.marvelworld.bean.BaseResponse;
import com.github.andrejnazarov.marvelworld.bean.MarvelCharacter;
import com.github.andrejnazarov.marvelworld.bean.MarvelResource;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @author Nazarov on 26.07.17.
 */

public interface MarvelService {

    @GET("v1/public/characters")
    Call<BaseResponse<MarvelCharacter>> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                                      @Query("apikey") String apiKey,
                                                      @Query("ts") int timeStamp,
                                                      @Query("hash") String hash);

    @GET
    Call<BaseResponse<MarvelResource>> getMarvelResources(@Url String path,
                                                          @Query("ts") int timeStamp,
                                                          @Query("hash") String hash,
                                                          @Query("apikey") String apiKey);
}