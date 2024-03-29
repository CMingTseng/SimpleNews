package rooit.me.xo.api.live

import de.jensklingenberg.ktorfit.Response
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Headers
import de.jensklingenberg.ktorfit.http.Query
import rooit.me.xo.model.live.BankAccountInfo
import rooit.me.xo.model.live.BonusInfo
import rooit.me.xo.model.live.ChatServiceInfo
import rooit.me.xo.model.live.KingRewardInfo
import rooit.me.xo.model.live.PayConfig
import rooit.me.xo.model.live.SettingPreferences

import rooit.me.xo.model.live.UserInfo
import rooit.me.xo.model.live.WithDrawInfo

public interface UserApis {
//    @GET("${API_VERSION}public/")
    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
    //SELF.BASE_INFO
    public suspend fun fetchUserInfo(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                     @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                     @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<UserInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.WITHDRAW
    public suspend fun fetchUserWithdraw(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                     @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                     @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<WithDrawInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.LABEL
    public suspend fun fetchUserLabel(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                         @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                         @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<String>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.PRE_SETTING
    public suspend fun fetchUserSettingPreferences(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                      @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                      @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<SettingPreferences>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.CHAT_CONFIG
    public suspend fun fetchUserChatConfigInfo(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                                   @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                                   @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<ChatServiceInfo>>//FIXME use "info": {} not "info":[]


    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.PAY_CONFIG
    public suspend fun fetchUserPayConfig(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                                   @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                                   @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<PayConfig>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.BANK_ACCOUNT_LIST
    public suspend fun fetchUserBankAccountList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                          @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                          @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<BankAccountInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.COIN_RECORD
    public suspend fun fetchUserCoinRecord(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                                @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                                @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<String>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    KINGREWARD.LIST
    //FIXME Kingreward?
    public suspend fun fetchKingrewardList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                           @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                           @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<KingRewardInfo>>

    @GET("api/public/")
    @Headers("X-AspNet-Version: 5jZyks1rY/e5Amq38/QsjqyGRvs7zFZIpsNUxZe5IdcB6mCKDa8=")//FIXME use Interceptor &  Accept-Language  & User-Agent & Connection
//    SELF.BONUS
    public suspend fun fetchBonusList(@Query("service") api_service_path:String, @Query("l") lang:String, @Query("version") version:String, @Query("plat") plat:String, @Query("plat_id") plat_id:String,
                                           @Query("device") device:String, @Query("devicetype") devicetype:String, @Query("rtm") rtm:String, @Query("_sign") _sign:String,
                                           @Query("uid") uid:String, @Query("token") token:String): Response<CommonBaseData<BonusInfo>>
}