package rooit.me.xo.api.live

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Query
import rooit.me.xo.model.live.FollowList
import rooit.me.xo.model.live.HomePageInfo
import rooit.me.xo.model.live.LiveStudioInfo
import rooit.me.xo.model.live.MessageInfo

import rooit.me.xo.model.live.UserInfo

public interface HomeLiveApis {
    @POST("$API_VERSION$API_PUBLIC")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection & Content-Type & Content-Length
    //HOME.CONFIG
    @FormUrlEncoded
    public suspend fun fetchPageConfig(@Query("service") api_service_path:String, @Query("l") lang:String, @Field("version") version:String, @Field("plat") plat:String, @Field("plat_id") plat_id:String,
                                       @Field("device") device:String, @Field("devicetype") devicetype:String, @Field("rtm") rtm:String, @Field("_sign") _sign:String,
                                       @Field("uid") uid:String, @Field("token") token:String, @Field("package_name") package_name:String): Response<CommonBaseData<HomePageInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    HOME.HOT
    public suspend fun fetchHotList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                        @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                        @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<LiveStudioInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    HOME.FOLLOW
    public suspend fun fetchFollowList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                              @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                              @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<FollowList>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    HOME.HOT
    public suspend fun fetchLiveStudioListByType(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                    @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                    @Query("uid") uid:String, @Query("token") token:String, @Query("type") type:Int, @Query("p") page:Int): Response<CommonBaseData<LiveStudioInfo>>
}