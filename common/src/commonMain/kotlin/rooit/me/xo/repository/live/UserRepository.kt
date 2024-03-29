package rooit.me.xo.repository.live

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rooit.me.xo.api.live.SELF

import rooit.me.xo.api.live.UserApis
import rooit.me.xo.model.live.UserInfo

public class UserRepository(private val api: UserApis) {
    public fun fetchUserInfo(): Flow<UserInfo> = flow {
        try {
            val response = api.fetchUserInfo(
                SELF.BASE_INFO,
                "zh-cn",
                "2.7.9(tag1700)",
                "android",
                "601",
                "91e2e7b6f4ba4eaa9c482511dc209e9e",
                "Nokia%20Nokia%203.1",
                "1707326556",
                "8b7ced270d29fdb74a3828d0262f8c9d",
                "5446009",
                "a8ed4284485ff0087def65cf325e1b6a"
            )

            if (response.isSuccessful) {
                response.body()?.data?.let {
                    println("Show me data msg : ${it.msg}")
                    println("Show me data code : ${it.code}")
                    println("Show me data info : ${it.info}")
                }
            }
        } catch (throwable: Throwable) {
            when (throwable) {
//                is IOException -> {
//
//                }

                else -> {

                }
            }
        }
    }
}