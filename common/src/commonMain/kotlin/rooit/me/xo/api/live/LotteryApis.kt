package rooit.me.xo.api.live

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.Headers
import rooit.me.xo.model.live.LotteryInfo

public interface LotteryApis {
    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    LOTTERY.LIST
    public suspend fun fetchLotteryList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                           @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                           @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<LotteryInfo>>
}