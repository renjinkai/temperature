package com.skyform.utils;

import com.google.common.collect.Maps;

import java.util.Map;

public class Hj212 {
    public static Map<String, Object> convertToMap(String msg) {
        Map<String, Object> resovleMap = Maps.newHashMap();
        msg = msg.substring(6, msg.length() - 6);
        String[] arr = msg.split(";");
        Map<String, Object> metrics = Maps.newHashMap();
        String[] var4 = arr;
        int var5 = arr.length;
        for (int var6 = 0; var6 < var5; ++var6) {
            String temp = var4[var6];
            if (temp.startsWith("CP=&&")) {
                temp = temp.replace("CP=&&", "");
            }
            if (temp.indexOf(",") == -1) {
                if (StringUtils.isNotEmpty(temp)) {
                    resovleMap.put(temp.split("=")[0], temp.split("=")[1]);
                }
            } else {
                String polKey = null;
                String[] arr2 = temp.split(",");
                Map<String, String> metricsMap = Maps.newHashMap();
                boolean inculdeFlag = false;
                for (int i = 0; i < arr2.length; ++i) {
                    inculdeFlag = true;
                    String temp2 = arr2[i];
                    String[] split = temp2.split("=")[0].split("-");
                    if (i == 0) {
                        polKey = split[0];
                    }
                    String key = split[1];
                    String value = temp2.split("=")[1];
                    metricsMap.put(key, value);
                }
                if (inculdeFlag) {
                    metrics.put(polKey, metricsMap);
                }
            }
        }
        return resovleMap;
    }

    public static Map<String, Object> toMap(String msg) {
        Map<String, Object> resultMap = Maps.newHashMap();
        Map<String, Object> metricsMap = Maps.newHashMap();
        Map<String, Object> flagMap = Maps.newHashMap();
        String[] arr = msg.split("&&");
        String head = arr[0];
        head = head.substring(6, head.length() - 4);
        String[] headArr = head.split(";");
        String[] var7 = headArr;
        int var8 = headArr.length;
        for (int var9 = 0; var9 < var8; ++var9) {
            String temp = var7[var9];
            resultMap.put(temp.split("=")[0].toLowerCase(), temp.split("=")[1]);
        }
        String middle = arr[1];
        if (StringUtils.isNotEmpty(middle)) {
            String[] includePol = middle.split(";");
            String[] dataTimeArr = includePol[0].split("=");
            resultMap.put(dataTimeArr[0].toLowerCase(), dataTimeArr[1]);
            for (int i = 1; i < includePol.length; ++i) {
                String pol = includePol[i];
                String polKey = null;
                String polZsKey = null;
                Map<String, String> detailMetricsMap = Maps.newHashMap();
                Map<String, String> detailZsMetricsMap = Maps.newHashMap();
                String[] split;
                String key;
                String value;
                if (pol.indexOf(",") == -1) {
                    if (StringUtils.isNotEmpty(pol)) {
                        split = pol.split("=");
                        key = split[0];
                        if (key.contains("-")) {
                            String[] keySplit = key.split("-");
                            String polkey = keySplit[0];
                            key = keySplit[1];
                            if (key.startsWith("Zs") || key.startsWith("zs")) {
                                polZsKey = polkey + key.substring(0, 2);
                                value = key.substring(2, key.length());
                                detailZsMetricsMap.put(value, split[1]);
                                metricsMap.put(polZsKey, detailZsMetricsMap);
                            }
                        } else {
                            resultMap.put(key, split[1]);
                        }
                    }
                } else {
                    split = pol.split(",");
                    int splitlength = split.length;
                    for (int j = 0; j < split.length; ++j) {
                        String detail = split[j];
                        String[] split1 = detail.split("=")[0].split("-");
                        if (j == 0) {
                            polKey = split1[0];
                        }
                        key = split[1];
                        value = detail.split("=")[1];
                        if (!key.startsWith("Zs") && !key.startsWith("zs")) {
                            detailMetricsMap.put(key, value);
                        } else {
                            polZsKey = polKey + key.substring(0, 2);
                            String zsKey = key.substring(2, key.length());
                            detailZsMetricsMap.put(zsKey, value);
                        }
                        if (key.equalsIgnoreCase("flag")) {
                            flagMap.put(polKey, value);
                        }
                    }
                    if (detailZsMetricsMap.size() > 0) {
                        metricsMap.put(polZsKey, detailZsMetricsMap);
                    }
                    if (detailMetricsMap.size() > 0) {
                        metricsMap.put(polKey, detailMetricsMap);
                    }
                }
            }
        }
        return resultMap;
    }

    public static void main(String[] args) {
        String msg1 = "##0081QN=20181111151745000;ST=21;CN=2011;PW=123456;MN=YSRDWQGQ1000001001;Flag=5;CP=&&&&7580";
        String msg2 = "##0180QN=20220418123908000;ST=91;CN=9014;PW=123456;MN=YSRDWQGQ1000001001;Flag=4;CP=&&QnRtn=1;UTP=MICRO_DEV_DOWNLOAD_DATA_YSRDWQGQ1000001001;DTP=MICRO_DEV_UPLOAD_DATA_YSRDWQGQ1000001001&&7200";
        String msg3 = "##0423QN=20220418131201000;ST=21;CN=2011;PW=123456;MN=YSRDWQGQ1000001001;Flag=5;CP=&&PTYPE=1;PNUM=1;PNO=11;DataTime=20220418131200;RestartTime=20220418123908;w01001-Rtd=-99.000000;w01014-Rtd=-99.000000;w01009-Rtd=-99.000000;w21003-Rtd=-99.000000;w01010-Rtd=-99.000000;ys1000-Rtd=160.304;a01001-Rtd=29.7;a01012-Rtd=-99.0;ys1002-Rtd=1.000000;ys1003-Rtd=1.000000;ys1006-Rtd=17;ys1140-Rtd=1;ys1109-Rtd=3&&9B81";
        String msg4 = "##0095QN=20220418131202000;ST=91;CN=9014;PW=123456;MN=YSRDWQGQ1000001001;Flag=4;CP=&&QnRtn=1;PNO=11&&9841";
        Map map1 = Hj212.convertToMap(msg3);
        Map map2 = Hj212.toMap(msg3);
        System.out.println(map1.toString());
        System.out.println(map2.toString());
    }
}
