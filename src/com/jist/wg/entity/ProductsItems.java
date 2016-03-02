package com.jist.wg.entity;

import com.jist.wg.factory.IProducts;

/**
 * 作者：jist 时间：下午02:41:17 操作：购物列表
 **/
public class ProductsItems {

	// 购物数量
	private int num;

	// 购物产品
	private IProducts products;

	// 产品单价
	private float price;

	private float sumPrice;

	public int getNum() {

		return num;
	}

	public void setNum( int num ) {

		this.num = num;
	}

	public IProducts getProducts() {

		return products;
	}

	public void setProducts( IProducts products ) {

		this.products = products;
	}

	public float getPrice() {

		return price;
	}

	public void setPrice( float price ) {

		this.price = price;
	}

	public float getSumPrice() {

		return sumPrice;
	}

	public void setSumPrice( float sumPrice ) {

		this.sumPrice = sumPrice;
	}

	public ProductsItems( int num, IProducts products, float price ) {

		super();
		this.num = num;
		this.products = products;
		this.price = price;
		this.sumPrice = num * price;
	}

	public ProductsItems() {

	}

}
