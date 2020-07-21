package com.skyform.utils;

import com.google.common.base.Joiner;

import java.util.*;

/**
 * @version V1.2
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 */
public class MessageUtil {

    //用户名
    private static String Uid = "rjkmvp";

    //接口安全秘钥
    private static String Key = "qwertyuiop";

    //手机号码，多个号码如13800000000,13800000001,13800000002
    private static String smsMob = "13800000000";

    //短信内容
    private static String smsText = "体温检测温度为 52°，测试Abc@123！。";

    public static Map<String, String> sendMessage(String sms_text, String sms_mob){
        Map<String, String> map = new HashMap<String, String>();
        HttpClientUtil client = HttpClientUtil.getInstance();
        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, sms_text, sms_mob);
        if (result > 0) {
            map.put("message", "成功发送" + result + "条信息");
            map.put("data", "");
            map.put("code", "0");
            map.put("time", new Date().toString());
            return map;
        } else {
            map.put("message", client.getErrorMsg(result));
            map.put("data", "");
            map.put("code", "-1");
            map.put("time", new Date().toString());
            return map;
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("13800000000");
        String sms_mob = Joiner.on(",").join(list);
        System.out.println(sms_mob);
        sms_mob = "13800000000";
        System.out.println(MessageUtil.sendMessage(smsText, sms_mob));
    }
}
