package com.skyform.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AccountsUtil {
	
	/**
	 * 获取上月月份
	 * @return String ("yyyy-MM")
	 */
	public static String lastMonth(String repeatDate) {
		String lastMonth = "";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM");
        int year = Integer.parseInt(repeatDate.substring(0, 4));
        String monthsString = repeatDate.substring(5, 7);
        int month;
        if ("0".equals(monthsString.substring(0, 1))) {
            month = Integer.parseInt(monthsString.substring(1, 2));
        } else {
            month = Integer.parseInt(monthsString.substring(0, 2));
        }
        cal.set(year,month-2,Calendar.DATE);
        lastMonth = dft.format(cal.getTime());
        return lastMonth;
	}
	
	/**
	 * 个人五险一金计算
	 * @param gjjBase
	 * @param gjjRatio
	 * @param yanglaoBase
	 * @param yanglaoRatioSelf
	 * @param yiliaoBase
	 * @param yiliaoRatioSelf
	 * @param yiliaoBaseSelf
	 * @param shiyeBase
	 * @param shiyeRatioSelf
	 * @return fiveAndOneSelf
	 */
	public static double fiveAndOneSelf(double gjjBase, double gjjRatio,
										double yanglaoBase, double yanglaoRatioSelf,
										double yiliaoBase, double yiliaoRatioSelf, double yiliaoBaseSelf,
										double shiyeBase, double shiyeRatioSelf) {
		
		double fiveAndOneSelf = Math.round(gjjBase*gjjRatio/100) +
								yanglaoBase*yanglaoRatioSelf/100 +
							    yiliaoBase*yiliaoRatioSelf/100+yiliaoBaseSelf +
							    shiyeBase*shiyeRatioSelf/100;
		return fiveAndOneSelf;
	}
	
	/**
	 * 个人五险计算
	 * @param yanglaoBase
	 * @param yanglaoRatioSelf
	 * @param yiliaoBase
	 * @param yiliaoRatioSelf
	 * @param yiliaoBaseSelf
	 * @param shiyeBase
	 * @param shiyeRatioSelf
	 * @return fiveAndOneSelf
	 */
	public static double fiveSelf(double yanglaoBase, double yanglaoRatioSelf,
								  double yiliaoBase, double yiliaoRatioSelf, double yiliaoBaseSelf,
								  double shiyeBase, double shiyeRatioSelf) {
		
		double fiveSelf = yanglaoBase*yanglaoRatioSelf/100 +
						  yiliaoBase*yiliaoRatioSelf/100+yiliaoBaseSelf +
						  shiyeBase*shiyeRatioSelf/100;
		return fiveSelf;
	}
	
	/**
	 * 单位五险一金计算
	 * @param gjjBase
	 * @param gjjRatio
	 * @param yanglaoBase
	 * @param yanglaoRatioCompany
	 * @param yiliaoBase
	 * @param yiliaoRatioCompany
	 * @param shiyeBase
	 * @param shiyeRatioCompany
	 * @param gongshangBase
	 * @param gongshangRatioCompany
	 * @param shengyuBase
	 * @param shengyuRatioCompany
	 * @return fiveAndOneCompany
	 */
	public static double fiveAndOneCompany(double gjjBase, double gjjRatio,
										   double yanglaoBase, double yanglaoRatioCompany,
										   double yiliaoBase, double yiliaoRatioCompany,
										   double shiyeBase, double shiyeRatioCompany,
										   double gongshangBase, double gongshangRatioCompany,
										   double shengyuBase, double shengyuRatioCompany) {
		
		double fiveAndOneCompany = Math.round(gjjBase*gjjRatio/100) +
				yanglaoBase*yanglaoRatioCompany/100 +
			    yiliaoBase*yiliaoRatioCompany/100 +
			    shiyeBase*shiyeRatioCompany/100 +
			    gongshangBase*gongshangRatioCompany/100 +
			    shengyuBase*shengyuRatioCompany/100;
		return fiveAndOneCompany;
	}
	
	/**
	 * 单位五险计算
	 * @param yanglaoBase
	 * @param yanglaoRatioCompany
	 * @param yiliaoBase
	 * @param yiliaoRatioCompany
	 * @param shiyeBase
	 * @param shiyeRatioCompany
	 * @param gongshangBase
	 * @param gongshangRatioCompany
	 * @param shengyuBase
	 * @param shengyuRatioCompany
	 * @return fiveAndOneCompany
	 */
	public static double fiveCompany(double yanglaoBase, double yanglaoRatioCompany,
										   double yiliaoBase, double yiliaoRatioCompany,
										   double shiyeBase, double shiyeRatioCompany,
										   double gongshangBase, double gongshangRatioCompany,
										   double shengyuBase, double shengyuRatioCompany) {
		
		double fiveAndOneCompany = yanglaoBase*yanglaoRatioCompany/100 +
			    yiliaoBase*yiliaoRatioCompany/100 +
			    shiyeBase*shiyeRatioCompany/100 +
			    gongshangBase*gongshangRatioCompany/100 +
			    shengyuBase*shengyuRatioCompany/100;
		return fiveAndOneCompany;
	}
	
	/**
	 * 薪资所得税
	 * 本月应纳税额=累计应纳税所得额*预扣率-速算扣除数-累计已预扣税额
	 * @return
	 */
	public static double getTaxXinzi(double nashui, double tax_sum) {
		if(nashui>=0 && nashui<36000) {
			return nashui * 0.03 - tax_sum;
		}else if(nashui>=36000 && nashui<144000) {
			return nashui * 0.1 - 2520 - tax_sum;
		}else if(nashui>=144000 && nashui<300000) {
			return nashui * 0.2 - 16920 - tax_sum;
		}else if(nashui>=300000 && nashui<420000) {
			return nashui * 0.25 - 31920 - tax_sum;
		}else if(nashui>=420000 && nashui<660000) {
			return nashui * 0.3 - 52920 - tax_sum;
		}else if(nashui>=660000 && nashui<960000) {
			return nashui * 0.35 - 85920 - tax_sum;
		}else if(nashui>=960000) {
			return nashui * 0.45 - 181920 - tax_sum;
		}else {
			return 0.0;
		}
	}
	
	/**
	 * 劳务报酬所得税
	 * @return
	 */
	public static double getTaxLaowu(double nashui) {
		if(nashui>=0 && nashui<4000) {
			return (nashui - 800) * 0.2;
		}else if(nashui>=4000 && nashui<20000) {
			return nashui * (1 - 0.2) * 0.2;
		}else if(nashui>=20000 && nashui<50000) {
			return nashui * (1 - 0.2) * 0.3 - 2000;
		}else if(nashui>=50000) {
			return nashui * (1 - 0.2) * 0.4 - 7000;
		}else {
			return 0.0;
		}
	}
	
	public static void main(String[] args) {
        System.out.println(AccountsUtil.lastMonth("2019-01"));
	}
}
