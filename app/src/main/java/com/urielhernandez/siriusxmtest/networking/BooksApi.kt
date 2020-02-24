package com.urielhernandez.siriusxmtest.networking

import com.urielhernandez.siriusxmtest.model.Book
import com.urielhernandez.siriusxmtest.utils.API_KEY
import com.urielhernandez.siriusxmtest.utils.END_POINT
import com.urielhernandez.siriusxmtest.utils.KEY
import com.urielhernandez.siriusxmtest.utils.QUERY
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface BooksApi {

    @Headers("Content-Type: application/json")
    @GET(END_POINT)
    fun getBooks(
        @Query(QUERY) parameter: String?,
        @Query(KEY) apiKey: String = API_KEY
    ): Observable<Book>

}