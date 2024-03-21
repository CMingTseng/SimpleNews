package rooit.me.xo.api

import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("${API_VERSION}${News.TOP_HEADLINES}")
    suspend fun fetchTopHeadlines(@Query("country") country:String,@Query("apiKey") apiKey:String): Response<rooit.me.xo.model.News>
}