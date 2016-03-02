package com.jist.wg.entity;

import java.util.Date;

/**
 * 作者：jist 时间：下午02:52:23 操作：优惠券类
 **/
public class Coupon {

	// 有效日期
	private Date effectiveDate;

	// 购物总额
	private int totalAmount;

	// 优惠金额
	private int discountAmount;

	public Date getEffectiveDate() {

		return effectiveDate;
	}

	public void setEffectiveDate( Date effectiveDate ) {

		this.effectiveDate = effectiveDate;
	}

	public int getTotalAmount() {

		return totalAmount;
	}

	public void setTotalAmount( int totalAmount ) {

		this.totalAmount = totalAmount;
	}

	public int getDiscountAmount() {

		return discountAmount;
	}

	public void setDiscountAmount( int discountAmount ) {

		this.discountAmount = discountAmount;
	}

	public Coupon( Date effectiveDate, int totalAmount, int discountAmount ) {

		super();
		this.effectiveDate = effectiveDate;
		this.totalAmount = totalAmount;
		this.discountAmount = discountAmount;
	}

	public Coupon() {

	}
	
}
