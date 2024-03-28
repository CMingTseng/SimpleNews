package rooit.me.xo.api

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.Field
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.FormUrlEncoded
import de.jensklingenberg.ktorfit.http.POST

public interface AuthApis {

    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor
    @GET("${API_PUBLIC}")
    public suspend fun fetchCodeForMobile(@Query("service") api_service_path:String, @Query("mobile") phone_number:String, @Query("country_code") country_code:String,@Query("sign") sign:String,@Query("version") version:String,@Query("l") lang:String,@Query("plat") plat:String,@Query("plat_id") plat_id:String,@Query("device") device:String,@Query("devicetype") devicetype:String,@Query("rtm") rtm:String,@Query("_sign") _sign:String): Response<CommonBaseData<String>>

    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor
    @GET("${API_PUBLIC}")
    public suspend fun fetchCodeForEmail(@Query("service") api_service_path:String, @Query("recipient_email") recipient_email:String,@Query("sign") sign:String,@Query("version") version:String,@Query("l") lang:String,@Query("plat") plat:String,@Query("plat_id") plat_id:String,@Query("device") device:String,@Query("devicetype") devicetype:String,@Query("rtm") rtm:String,@Query("_sign") _sign:String): Response<CommonBaseData<String>>

    @POST("${API_PUBLIC}")
    @FormUrlEncoded
    public suspend fun login(@Query("service") api_service_path:String,@Query("l") lang:String,@Field("user_login") user_login: String,@Field("user_pass") user_pass: String,@Field("mobile_code") mobile_code: String,@Field("source") source: String,@Field("appid") appid: String,@Field("net_ip") net_ip: String
                             ,@Field("deviceToken") deviceToken: String,@Field("acd_token") acd_token: String,@Field("acd_device") acd_device: String,@Field("ali_device") ali_device: String,@Field("device_mac") device_mac: String,@Field("webUmidToken") webUmidToken: String
                             ,@Field("uaToken") uaToken: String,@Field("fp") fp: String,@Field("sub_plat") sub_plat: String,@Field("auth_method") auth_method: String,@Field("version") version: String,@Field("plat") plat: String
                             ,@Field("device") device: String,@Field("devicetype") devicetype: String,@Field("rtm") rtm: String,@Field("plat_id") plat_id: String,@Field("_sign") _sign: String): Response<CommonBaseData<String>>

    @POST("${API_PUBLIC}")
    @FormUrlEncoded
    public suspend fun loginGuest(@Query("service") api_service_path:String,@Query("l") lang:String,@Field("guest_pass") guest_pass: String,@Field("pushid") pushid: String,@Field("fp") fp: String,@Field("source") source: String,@Field("net_ip") net_ip: String
                             ,@Field("acd_token") acd_token: String,@Field("acd_device") acd_device: String,@Field("sub_plat") sub_plat: String,@Field("deviceToken") deviceToken: String,@Field("device_mac") device_mac: String,@Field("webUmidToken") webUmidToken: String
                             ,@Field("uaToken") uaToken: String,@Field("version") version: String,@Field("plat") plat: String,@Field("device") device: String,@Field("devicetype") devicetype: String
                                 ,@Field("rtm") rtm: String,@Field("plat_id") plat_id: String,@Field("_sign") _sign: String): Response<CommonBaseData<String>>
}