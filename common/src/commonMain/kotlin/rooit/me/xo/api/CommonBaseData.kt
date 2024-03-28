package rooit.me.xo.api

import kotlinx.serialization.Serializable

@Serializable
public class CommonBaseData<T> : BaseRespBean<BaseDataBean<T>>() {

    public fun onCommonDataSuccess(block: (data: T?) -> Unit) {
        onSuccess { baseDataBean ->
            if (baseDataBean != null && baseDataBean.code == 0) {
                block.invoke(baseDataBean.info?.firstOrNull())
            }
        }
    }

    public fun onCommonDataFailure(block: (msg: String?) -> Unit) {
        onFailure {
            block.invoke(it)
        }
        onSuccess { baseDataBean ->
            if (baseDataBean != null) {
                if (baseDataBean.code != 200) {
                    block(baseDataBean.msg)
                }
            } else {
                block("return data error , code = $ret")
            }
        }
    }
}