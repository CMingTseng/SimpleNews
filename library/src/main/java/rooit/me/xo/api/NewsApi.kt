package rooit.me.xo.api

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

interface NewsApi {
    @GET("$API_VERSION${News.TOP_HEADLINES}")
    suspend fun fetchTopHeadlines(@Query("country") country:String,@Query("apiKey") apiKey:String): Response<rooit.me.xo.model.News>
}