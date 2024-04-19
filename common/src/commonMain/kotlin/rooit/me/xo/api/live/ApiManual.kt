package rooit.me.xo.api.live

public const val API_VERSION: String = "api/"
public const val API_PUBLIC: String = "public/"

public object AUTH {
    public const val GUEST: String = "Login.guestLogin"
    public const val USER: String = "Login.userLogin"
    public const val DEVICE: String = GUEST
    public const val BY_THIRD_PARTY: String = "Login.userLoginByThird"
    public const val CODE: String = "Login.getCode"
    public const val CODE_EMAIL: String = "Login.getCodeByEmail"
    public const val LOGOUT: String = "Login.logout"
    public const val FIND_PASSWORD: String = "Login.userFindPass"
}

public typealias LOGIN = AUTH

public object SELF {
    public const val GET_BASE_INFO: String = "User.getBaseInfo"
    public const val GET_USER_HOME: String = "User.getUserHome"
    public const val GET_WITHDRAW: String = "User.getWithdraw"
    public const val GET_BALANCE: String = "User.getBalanceNew"//User.getBalance
    public const val GET_PAY_CONFIG: String = "User.getPayConfig"
    public const val GET_VIP_PAY_CONFIG: String = "User.vipPayLogin2"
    public const val GET_PLATS: String = "User.getPlats"
    public const val GET_LABEL: String = "User.GetMyLabel"
    public const val GET_IMPRESS: String = GET_LABEL
    public const val GET_ALL_IMPRESS: String = "User.GetUserLabel"
    public const val GET_PRE_SETTING: String = "User.getPerSetting"
    public const val GET_CHAT_CONFIG: String = "User.getChatConfigInfo"
    public const val GET_BANK_ACCOUNT_LIST: String = "User.GetUserAccountList"
    public const val GET_COIN_RECORD: String = "User.getCoinRecord"
    public const val GET_FOLLOW_LIST: String = "User.getFollowsList"
    public const val GET_FAN_LIST: String = "User.getFansList"
    public const val GET_LIVE_RECORD: String = "User.getLiverecord"
    public const val GET_PROFIT: String = "User.getProfit"
    public const val GET_PLAT_GAME_BALANCE: String = "User.getPlatGameBalance"
    public const val GET_REGIONS_EXCHANGE_RATE: String = "User.getRegionsExchangeRate"
    public const val GET_BANK_LIST: String = "User.getBankInfo"
    public const val GET_PROVINCE_LIST: String = "User.getBankRegions"
    public const val GET_IM_USER_INFO: String = "User.GetUidsInfo"
    public const val BONUS: String = "User.Bonus"
    public const val GET_BONUS: String = "User.getBonus"
    public const val UPDATE_FIELDS: String = "User.updateFields"
    public const val UPDATE_AVATER: String = "User.updateAvatar"
    public const val UPDATE_PASSWORD: String = "User.updatePass"
    public const val SAVE_CONTACT: String = "User.saveContact"
    public const val CHECK_TOKEN: String = "User.iftoken"
    public const val SET_PHONE_NUMBER: String = "User.setMobile"
    public const val SET_EMAIL: String = "User.setEmail"
    public const val SET_ATTENT: String = "User.setAttent"
    public const val SET_IMPRESS: String = "User.setUserLabel"
    public const val SET_BLOCK: String = "User.setBlack"
    public const val CHECK_BLOCK: String = "User.checkBlack"
    public const val DO_CASH: String = "User.setCash"
    public const val SET_BANK_ACCOUNT: String = "User.SetUserAccount"
    public const val DEL_BANK_ACCOUNT: String = "User.DelUserAccount"
}
public typealias USER = SELF

public object HOME {
    public const val CONFIG: String = "Home.getConfig"
    public const val DOMAIN_LIST: String = "Home.getDomainList"
    public const val HOT: String = "Home.getHot"
    public const val FOLLOW: String = "Home.getFollow"
    public const val LIVELIST_BY_TYPE: String = "Home.getLiveListByType"
    public const val LIVE_INFO: String = "Home.getLiveInfo"
    public const val NEARBY: String = "Home.getNearby"
    public const val LIVE_CLASS: String = "Home.getClassLive"
    public const val PROFIT_LIST: String = "Home.profitList"
    public const val LOTTERY_PROFIT_RANK_LIST: String = "Home.lotteryProfitRankList"
    public const val CONSUME_LIST: String = "Home.consumeList"
    public const val SEARCH: String = "Home.search"
}

public object LIVE {
    public const val CREATE_ROOM: String = "Live.createRoom"
    public const val UP_LIVE_TYPE: String = "Live.uplivetype"
    public const val CHANGE_LIVE_STATUS: String = "Live.changeLive"
    public const val STOP_ROOM: String = "Live.stopRoom"
    public const val GET_LIVE_END_INFO: String = "Live.stopInfo"
    public const val CHECK_LIVE: String = "Live.checkLive"
    public const val CHARGE_ROOM: String = "Live.roomCharge"
    public const val CHARGE_TIME: String = "Live.timeCharge"
    public const val ENTER_ROOM: String = "Live.enterRoom"
    public const val GET_CONTACT: String = "Live.getLiveContact"
    public const val GET_CHAT_CONFIG_INFO: String = "User.getChatConfigInfo"
    public const val GET_LUCKY_DRAW: String = "Live.getLuckydraw"
    public const val GET_LUCKY_DRAW_RECORD: String = "Live.luckydrawRecord"
    public const val DO_LUCKY_DRAW: String = "Live.doLuckydraw"
    public const val SEND_DANMU: String = "Live.sendBarrage"
    public const val GET_GIFT_LIST: String = "Live.getGiftList"
    public const val GET_TOY_INFO: String = "Live.getLiveToyInfo"
    public const val GET_COIN: String = "Live.getCoin"
    public const val GET_POP: String = "Live.getPop"
    public const val GET_ADMIN_LIST: String = "Live.getAdminList"
    public const val SET_ADMIN: String = "Live.setAdmin"
    public const val SEND_GIFT: String = "Live.sendGift"
    public const val KICKING: String = "Live.kicking"
    public const val SHUTUP: String = "Live.setShutUp"
    public const val REPORT: String = "Live.setReport"
    public const val SUPER_STOP_ROOM: String = "Live.superStopRoom"
}

public object MESSAGE {
    public const val LISTG: String = "Message.GetList"
}

public object KINGREWARD {
    public const val LIST: String = "Kingreward.getList"
}

public object LOTTERY {
    public const val LIST: String = "Lottery.getLotteryList"
}