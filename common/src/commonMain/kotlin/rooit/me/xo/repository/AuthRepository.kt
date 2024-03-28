package rooit.me.xo.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import rooit.me.xo.api.AuthApis
import rooit.me.xo.api.LOGIN

public class AuthRepository(private val api: AuthApis) {
    public fun fetchCodeForMobile(): Flow<String> = flow {
        try {
            val response = api.fetchCodeForMobile(LOGIN.CODE,phone_number = "955991445", country_code = "886","23842037ea2a1b88a44bc2c6f470080b","2.8.4(tag1700)","zh-cht","android","601","wewewfewf1c959395f","Pixel","1711591509","a02bf89645030bd03ab5cb733345ee1f")

            if (response.isSuccessful) {
                 println("Show me ret : ${response.body()?.ret}")
                println("Show me msg : ${response.body()?.msg}")
                response.body()?.data?.let{
                    println("Show me data msg : ${it.msg}")
                    println("Show me data code : ${it.code}")
                    println("Show me data info : ${it.info}")
                }
            }
        } catch (throwable: Throwable) {
            // TODO here emit Throwable
            //     Ref : https://blog.csdn.net/jungle_pig/article/details/105725160
            //     Ref : https://blog.csdn.net/zhaoyanjun6/article/details/121754941
            //     Ref : https://blog.csdn.net/qq_42467528/article/details/127225747
            when (throwable) {
//                is IOException -> {
//
//                }

                else -> {

                }
            }
        }
    }

    public fun fetchCodeForEmail(): Flow<String> = flow {
        try {
            val response = api.fetchCodeForEmail(LOGIN.CODE_EMAIL, recipient_email= "EEEE@ggg.com","23842037ea2a1b88a44bc2c6f470080b","2.8.4(tag1700)","zh-cht","android","601","wewewfewf1c959395f","Pixel","1711591509","a02bf89645030bd03ab5cb733345ee1f")

            if (response.isSuccessful) {
                println("Show me ret : ${response.body()?.ret}")
                println("Show me msg : ${response.body()?.msg}")
                response.body()?.data?.let{
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