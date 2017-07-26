package com.github.andrejnazarov.marvelworld.net;

import com.github.andrejnazarov.marvelworld.bean.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Nazarov on 26.07.17.
 */

public interface MarvelService {

    @GET("v1/public/characters")
    Call<BaseResponse> getCharacters(@Query("nameStartsWith") String nameStartsWith,
                                     @Query("apikey") String apiKey,
                                     @Query("ts") int timeStamp,
                                     @Query("hash") String hash);


}
