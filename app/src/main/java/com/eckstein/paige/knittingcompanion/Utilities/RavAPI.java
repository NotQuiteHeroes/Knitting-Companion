package com.eckstein.paige.knittingcompanion.Utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RavAPI {

    int MAX_NUMBER_OF_PATTERNS = 50;
    int NUMBER_OF_COLORS = 1;

    // This query "/patterns/search.json" can be used just like the Ravelry pattern search,
    // adding in any parameters. This specific call defines number of colors, number of patterns wanted,
    // and the yarn weight required.
    @GET("/yarns/search.json")
    Call<YarnSearchResult> getYarns(@Query("query") String yarnName,
                                    @Header("Authorization") String authHeader);

    // This query returns detailed Pattern objects.
    // ids should be in form "1 2 3 4", here it is parsed to "1+2+3+4"
    @GET("/yarns.json")
    Call<FullYarnsResult> getYarnsById(@Query("ids") String ids,
                                       @Header("Authorization") String authHeader);

}

