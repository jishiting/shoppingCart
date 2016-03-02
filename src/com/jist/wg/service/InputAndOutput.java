package com.jist.wg.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import com.jist.wg.entity.CommonTools;
import com.jist.wg.entity.Coupon;
import com.jist.wg.entity.DiscountInformation;
import com.jist.wg.entity.ProductsItems;
import com.jist.wg.entity.ShoppingCart;

public class InputAndOutput {

	private static PackupObject dealCase = new PackupObject();

	private static int flag = 0;// 只能有一个优惠券信息

	public void calculateResults() throws IOException {

		String userdir = System.getProperty("user.dir");
		File file = new File(userdir + "/src/input.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
		String line = null;
		ShoppingCart shoppingCart = new ShoppingCart();
		while ((line = br.readLine()) != null) {// 使用readLine方法，一次读一行
			System.out.println(line);

			// 判断是否为折扣信息
			if (CommonTools.checkDiscountInformationInput(line)) {
				DiscountInformation discountInformation = dealCase
						.packupDiscountInformation(line);
				shoppingCart.addDiscountInformationList(discountInformation);
			} else if (CommonTools.checkProductsItemsInput(line)) {
				// 判断是否为产品
				ProductsItems productsItems = dealCase
						.packupProductsItems(line);
				shoppingCart.addProductItemsList(productsItems);
			} else if (CommonTools.checkCouponInput(line)) {
				// 判断是否有优惠券
				Coupon coupon = dealCase.packupCoupon(line);
				if (flag == 0) {
					shoppingCart.setCoupon(coupon);
				}
				flag = flag + 1;
			} else if (CommonTools.checkDeadlineInput(line)) {
				Date settlementDate = CommonTools.parseStringToDate(line);
				shoppingCart.setSettlementDate(settlementDate);
			}
			if ("calc".equals(line)) { // 输入calc开始计算
				System.out.println(CommonTools.formattedResult(shoppingCart
						.resultCalculation())+"\n");
				 shoppingCart = new ShoppingCart();
			}

		}
		br.close();
	}
}
