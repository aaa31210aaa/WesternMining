package utils;

import android.content.Context;

public class PortIpAddress {
    public static String SUCCESS_CODE = "true";

    //外网
    public static String host = "http://112.74.124.217:9797/lczy/mobile/";

    public static String getUserId(Context context) {
        return SharedPrefsUtil.getValue(context, "userInfo", "userid", "");
    }

    public static String getUserType(Context context) {
        return SharedPrefsUtil.getValue(context, "userInfo", "usertype", "");
    }

    public static String getUserTypeName(Context context) {
        return SharedPrefsUtil.getValue(context, "userInfo", "usertypename", "");
    }

    public static String getUserName(Context context) {
        return SharedPrefsUtil.getValue(context, "userInfo", "USER_NAME", "");
    }

    //登录
    public static String Login() {
        return host + "login.action";
    }
}
