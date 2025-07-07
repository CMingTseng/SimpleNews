package rooit.me.xo.api

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("${API_VERSION}${News.TOP_HEADLINES}")
    suspend fun fetchTopHeadlines(@Query("country") country:String,@Query("apiKey") apiKey:String): rooit.me.xo.model.News
}