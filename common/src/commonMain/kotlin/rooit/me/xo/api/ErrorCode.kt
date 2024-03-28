package rooit.me.xo.api

public enum class ErrorCode(public val code:Int, public val display: String) {
    CODE_200(0,""),
    CODE_1001(1001,"请先获取验证码"),
    CODE_1002(1002,"验证码5分钟有效，请勿多次发送"),
}