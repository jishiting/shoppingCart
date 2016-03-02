package com.jist.wg.entity;

import java.util.Date;

import com.jist.wg.factory.IProducts;

/**
 * 作者：jist 时间：下午02:45:13 操作：打折信息
 **/
public class DiscountInformation {
	public static final int DISCOUNTINFORMATIONSIZE=3;

	// 有效日期
	private Date effectiveDate;

	// 折扣
	private float discount;

	// 产品类型
	private IProducts product;

	public Date getEffectiveDate() {

		return effectiveDate;
	}

	public void setEffectiveDate( Date effectiveDate ) {

		this.effectiveDate = effectiveDate;
	}

	public float getDiscount() {

		return discount;
	}

	public void setDiscount( float discount ) {

		this.discount = discount;
	}

	public IProducts getProduct() {

		return product;
	}

	public void setProduct( IProducts product ) {

		this.product = product;
	}

	public DiscountInformation( Date effectiveDate, float discount, IProducts product ) {

		super();
		this.effectiveDate = effectiveDate;
		this.discount = discount;
		this.product = product;
	}

	public DiscountInformation() {

	}

}
