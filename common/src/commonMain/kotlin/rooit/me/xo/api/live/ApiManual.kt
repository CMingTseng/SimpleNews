package rooit.me.xo.api.live

public const val API_VERSION: String = "api/"
public const val API_PUBLIC: String = "public/"

public object AUTH {
    public const val GUEST: String = "Login.guestLogin"
    public const val USER : String = "Login.userLogin"
    public const val DEVICE : String = GUEST
    public const val CODE : String = "Login.getCode"
    public const val CODE_EMAIL : String = "Login.getCodeByEmail"
    public const val EMAIL: String = "recipient_email"
    public const val EMAIL_LOGIN: String = "User.setEmail"
    public const val REFRESH_TOKEN: String = "auth/refresh/token"
}

public typealias LOGIN = AUTH

public object SELF {
    public const  val BASE_INFO : String = "User.getBaseInfo"
    public const  val WITHDRAW : String = "User.getWithdraw"
    public const  val BONUS : String = "User.Bonus"
    public const  val PAY_CONFIG: String = "User.getPayConfig"
    public const  val PLATS : String = "User.getPlats"
    public const  val UPDATE_FIELDS : String = "User.updateFields"
    public const  val LABEL : String = "User.GetMyLabel"
    public const  val PRE_SETTING : String = "User.getPerSetting"
    public const  val CHAT_CONFIG : String = "User.getChatConfigInfo"
    public const  val BANK_ACCOUNT_LIST : String = "User.GetUserAccountList"
    public const  val COIN_RECORD : String = "User.getCoinRecord"
}
public typealias USER = SELF

public object HOME {
    public const  val CONFIG : String ="Home.getConfig"
    public const  val HOT: String ="Home.getHot"
    public const  val FOLLOW : String = "Home.getFollow"
    public const  val LIVELIST_BY_TYPE : String ="Home.getLiveListByType"
}

public object MESSAGE {
    public const  val LISTG : String = "Message.GetList"
}

public object KINGREWARD {
    public const  val LIST : String = "Kingreward.getList"
}

public object LOTTERY {
    public const  val LIST : String = "Lottery.getLotteryList"
}