package com.jist.wg.service;

import java.util.Date;

import com.jist.wg.entity.CommonTools;
import com.jist.wg.entity.Coupon;
import com.jist.wg.entity.DiscountInformation;
import com.jist.wg.entity.ProductsItems;
import com.jist.wg.factory.IProducts;
import com.jist.wg.factory.ProductsFactory;

/**
 * 作者：jist 时间：下午03:00:59 操作：TODO（描述操作原因）
 **/
public class PackupObject {

	/**
	 * 根据输入参数封装折扣类
	 * 
	 * @param infor
	 * @return
	 */
	public DiscountInformation packupDiscountInformation( String infor ) {

		// 2013.11.11 | 0.7 | 电⼦
		infor = infor.replace(" ", "");
		String[] discountInformationAttributes = infor.split("\\|");
		Date effectiveDate = CommonTools
				.parseStringToDate(discountInformationAttributes[0]);
		Float discount = Float.parseFloat(discountInformationAttributes[1]);
		String productType = discountInformationAttributes[2];
		IProducts product = ProductsFactory.getProducts(CommonTools
				.readProterties(productType));

		DiscountInformation discountInformation = new DiscountInformation(
				effectiveDate, discount, product);
		return discountInformation;

	}

	/**
	 * 根据输入参数封装产品记录
	 * 
	 * @param infor
	 * @return
	 */
	public ProductsItems packupProductsItems( String infor ) {

		// 1 * ipad : 2399.00
		infor = infor.replace(" ", "").replace(":", "*");
		String[] productsItemsAttributes = infor.split("\\*");
		int num = Integer.parseInt(productsItemsAttributes[0]);
		String productType = productsItemsAttributes[1];
		IProducts products = ProductsFactory.getProducts(CommonTools
				.readProterties(productType));
		float price = Float.parseFloat(productsItemsAttributes[2]);
		return new ProductsItems(num, products, price);

	}

	/**
	 * 封装优惠券信息
	 * 
	 * @param infor
	 * @return
	 */
	public Coupon packupCoupon( String infor ) {

		// 2014.3.2 1000 200
		infor = infor.replaceAll(" ", "*");
		String[] couponAttributes = infor.split("\\*");

		return new Coupon(CommonTools.parseStringToDate(couponAttributes[0]),
				Integer.parseInt(couponAttributes[1]),
				Integer.parseInt(couponAttributes[2]));

	}
}
