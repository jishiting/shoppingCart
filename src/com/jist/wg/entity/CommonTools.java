package com.jist.wg.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jist.wg.factory.IProducts;
import com.jist.wg.products.AlcoholProducts;
import com.jist.wg.products.DailyNecessityesProducts;
import com.jist.wg.products.ElectronicProducts;
import com.jist.wg.products.FoodProducts;

public class CommonTools {

	// 产品正则
	public static final String PRODUCTSITEMSINPUTREG = "^\\d+\\*{1}([a-zA-Z\u4E00-\u9FFF]*):\\d*.\\d{2}";

	// 优惠券
	public static final String COUPONREG = "^\\d{4}.\\d{1,2}.\\d{1,2} \\d{4} \\d{3} *";
	//截止日期
	public static final String DEADLINE = "^\\d{4}.\\d{1,2}.\\d{1,2}";

	
	/**
	 * 判断是否为折扣信息
	 * 
	 * @param discountInformationInput
	 * @return
	 */
	public static Boolean checkDiscountInformationInput(
			String discountInformationInput ) {

		if (null == discountInformationInput
				|| "".equals(discountInformationInput)) {
			throw new IllegalAccessError("折扣信息输入有误");
		} else {
			discountInformationInput = discountInformationInput
					.replace(" ", "");
			String[] discountInfors = discountInformationInput.split("\\|");
			List<String> sList = Arrays.asList(discountInfors);
			List<String> tList = new ArrayList<String>();
			for (String string : sList) {
				if (!"\\|".equals(string) && !"".equals(string)) {
					tList.add(string);
				}
			}
			if (tList.size() == DiscountInformation.DISCOUNTINFORMATIONSIZE) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * 判断是否为产品信息
	 * 
	 * @param productsItemsInput
	 * @return
	 */
	public static Boolean checkProductsItemsInput( String productsItemsInput ) {

		if (null == productsItemsInput || "".equals(productsItemsInput)) {
			throw new IllegalAccessError("产品为空");
		} else {
			productsItemsInput = productsItemsInput.replace(" ", "");
			Pattern r = Pattern.compile(CommonTools.PRODUCTSITEMSINPUTREG);
			Matcher m = r.matcher(productsItemsInput);
			return m.matches();
		}

	}
	/**
	 * 判断是否为截止日期
	 * 
	 * @param productsItemsInput
	 * @return
	 */
	public static Boolean checkDeadlineInput( String productsItemsInput ) {

		if (null == productsItemsInput || "".equals(productsItemsInput)) {
			throw new IllegalAccessError("截止日期输入有误");
		} else {
			productsItemsInput = productsItemsInput.replace(" ", "");
			Pattern r = Pattern.compile(CommonTools.DEADLINE);
			Matcher m = r.matcher(productsItemsInput);
			return m.matches();
		}

	}
	/**
	 * 判断是否为优惠券
	 * 
	 * @param couponInput
	 * @return
	 */
	public static Boolean checkCouponInput( String couponInput ) {

		if (null == couponInput || "".equals(couponInput)) {
			throw new IllegalAccessError("优惠券信息有误");
		} else {
			Pattern r = Pattern.compile(CommonTools.COUPONREG);
			Matcher m = r.matcher(couponInput);
			return m.matches();
		}
	}

	/**
	 * 格式化输出
	 * 
	 * @param result
	 * @return
	 */
	public static String formattedResult( Double result ) {

		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(result);
	}

	/**
	 * 是否过期
	 * 
	 * @param disCountDate
	 *            折扣优惠信息日期
	 * @param countDate
	 *            结账日期
	 * @return
	 */
	public static Boolean whetherExpired( Date disCountDate, Date countDate ) {

		if (null == disCountDate || countDate == null
				|| "".equals(disCountDate) || "".equals(countDate)) {
			throw new IllegalArgumentException("非法的输入信息");
		}
		if (disCountDate.after(countDate)||disCountDate.equals(countDate)) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 转换日期
	 * 
	 * @param inputString
	 * @return
	 */
	public static Date parseStringToDate( String inputString ) {

		try {
			return new SimpleDateFormat("yyyy.mm.dd").parse(inputString);
		} catch (ParseException e) {
			throw new IllegalArgumentException("不能转换为日期");
		}

	}

	/**
	 * 读取信息
	 * @param inputString
	 * @return
	 */
	public static String readProterties( String inputString ) {

		Properties pro = new Properties();
		InputStream inStream = CommonTools.class
				.getResourceAsStream("products.properties");
		try {
			pro.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			inputString = new String(inputString.getBytes("UTF-8"),
					"ISO-8859-1");
			String words = pro.getProperty(inputString);
			return new String(words.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalAccessError("不能读取信息");
		}
	}

	/**
	 * 判断类型
	 * @param <T> 
	 * @param t
	 * @return
	 */
	public static <T> String getProductType( T t ) {

		if (t instanceof AlcoholProducts) {
			return IProducts.ALCOHOLPRODUCTS;
		} else if (t instanceof DailyNecessityesProducts) {
			return IProducts.DAILYNECESSITYESPRODUCTS;
		} else if (t instanceof ElectronicProducts) {
			return IProducts.ELECTRONICPRODUCTS;
		} else if (t instanceof FoodProducts) {
			return IProducts.FOODPRODUCTS;
		} else {
			throw new IllegalArgumentException("产品类型不存在");
		}
	}

}
