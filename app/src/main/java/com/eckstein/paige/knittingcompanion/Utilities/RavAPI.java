package com.eckstein.paige.knittingcompanion.Utilities;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Ravelry API queries
 * Credit to Sofivanhanen and her code, from which this was edited
 * From https://github.com/sofivanhanen/Yarnie
 */
public interface RavAPI {

    // This query "/yarns/search.json" can be used just like the Ravelry yarn search,
    // adding in any parameters. This specific call defines name of yarn to search for
    @GET("/yarns/search.json")
    Call<YarnSearchResult> getYarns(@Query("query") String yarnName,
                                    @Header("Authorization") String authHeader);

    // This query returns detailed Yarn objects.
    // ids should be in form "1 2 3 4", here it is parsed to "1+2+3+4"
    @GET("/yarns.json")
    Call<FullYarnsResult> getYarnsById(@Query("ids") String ids,
                                       @Header("Authorization") String authHeader);

}

