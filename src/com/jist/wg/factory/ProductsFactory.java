package com.jist.wg.factory;

import com.jist.wg.products.AlcoholProducts;
import com.jist.wg.products.DailyNecessityesProducts;
import com.jist.wg.products.ElectronicProducts;
import com.jist.wg.products.FoodProducts;

/**
 * 作者：jist 时间：下午03:34:09 操作：TODO（描述操作原因）
 **/
public class ProductsFactory {

	public static IProducts getProducts( String productName ) {

		IProducts products = null;
		// TODO Auto-generated method stub
		if (IProducts.ELECTRONICPRODUCTS.equals(productName)) {
			products = new ElectronicProducts();
		} else if (IProducts.FOODPRODUCTS.equals(productName)) {
			products = new FoodProducts();
		} else if (IProducts.ALCOHOLPRODUCTS.equals(productName)) {
			products = new AlcoholProducts();
		} else if (IProducts.DAILYNECESSITYESPRODUCTS.equals(productName)) {
			products = new DailyNecessityesProducts();
		} else {
			throw new IllegalArgumentException("不支持的商品类别");
		}
		return products;
	}

}
