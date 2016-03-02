package com.jist.wg.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 作者：jist 时间：下午02:43:28 操作：TODO（描述操作原因）
 **/
public class ShoppingCart {

	// 打折信息单
	private Map<String, Float> discountInformationMap;

	// 存优惠信息列表
	private List<DiscountInformation> discountInformationList;

	// 产品列单
	private Map<String, Float> productItemsMap;

	// 存优惠信息列表
	private List<ProductsItems> productItemsList;

	// 结算日期
	private Date settlementDate;

	// 优惠券
	private Coupon coupon;

	// 总金额
	private Float total = 0f;

	public Map<String, Float> getDiscountInformationMap() {

		return discountInformationMap;
	}

	public void setDiscountInformationMap(
			Map<String, Float> discountInformationMap ) {

		this.discountInformationMap = discountInformationMap;
	}

	public Map<String, Float> getProductItemsMap() {

		return productItemsMap;
	}

	public void setProductItemsMap( Map<String, Float> productItemsMap ) {

		this.productItemsMap = productItemsMap;
	}

	public Date getSettlementDate() {

		return settlementDate;
	}

	public void setSettlementDate( Date settlementDate ) {

		this.settlementDate = settlementDate;
	}

	public Coupon getCoupon() {

		return coupon;
	}

	public void setCoupon( Coupon coupon ) {

		if (CommonTools.whetherExpired(coupon.getEffectiveDate(),
				settlementDate)) {
			this.coupon = coupon;
		}

	}

	public Float getTotal() {

		return total;
	}

	public void setTotal( Float total ) {

		this.total = total;
	}

	public ShoppingCart( Map<String, Float> discountInformationMap,
			Map<String, Float> productItemsMap, Date settlementDate,
			Coupon coupon, Float total ) {

		super();
		this.discountInformationMap = discountInformationMap;
		this.productItemsMap = productItemsMap;
		this.settlementDate = settlementDate;
		this.coupon = coupon;
		this.total = total;
	}

	public ShoppingCart() {

		this.discountInformationMap = new HashMap<String, Float>();
		this.productItemsMap = new HashMap<String, Float>();
		this.discountInformationList = new ArrayList<DiscountInformation>();
		this.productItemsList = new ArrayList<ProductsItems>();
	}

	/**
	 * 添加产品
	 * 
	 * @param productsItems
	 */
	public void addProductItems( ProductsItems productsItems ) {

		String produnctType = CommonTools.getProductType(productsItems
				.getProducts());
		if (productItemsMap.containsKey(produnctType)) {
			Float value = productItemsMap.get(produnctType);
			value = value + productsItems.getSumPrice();
			productItemsMap.put(produnctType, value);
		} else {
			productItemsMap.put(produnctType, productsItems.getSumPrice());
		}
	}

	/**
	 * 添加折扣信息
	 * 
	 * @param discountInformation
	 */
	public void addDiscountInformation( DiscountInformation discountInformation ) {

		String produnctType = CommonTools.getProductType(discountInformation
				.getProduct());
		// 判断折扣是否在有效期内
		if (CommonTools.whetherExpired(discountInformation.getEffectiveDate(),
				settlementDate)) {
			if (discountInformationMap.containsKey(produnctType)) {
				Float value = discountInformationMap.get(produnctType);
				value = value * discountInformation.getDiscount();
				discountInformationMap.put(produnctType, value);
			} else {
				discountInformationMap.put(produnctType,
						discountInformation.getDiscount());
			}
		}

	}

	public double resultCalculation() {// 计算最终结果

		Float productsItemPrice;
		if (null == productItemsList || productItemsList.size() == 0) {
			throw new IllegalArgumentException("产品列表为空");
		} else {
			for (ProductsItems productItems : productItemsList) {
				addProductItems(productItems);
			}
		}
		if (null != discountInformationList
				|| discountInformationList.size() > 0) {
			for (DiscountInformation discountInformation : discountInformationList) {
				addDiscountInformation(discountInformation);
			}
		}
		Iterator<String> productTypeIte = productItemsMap.keySet().iterator();

		while (productTypeIte.hasNext()) {
			String productsKey = productTypeIte.next();
			Logger log = LogTools.getInstance();
			log.info("shoppingCart" + total);
			productsItemPrice = productItemsMap.get(productsKey);
			if (discountInformationMap.containsKey(productsKey)) {
				productsItemPrice = productsItemPrice
						* discountInformationMap.get(productsKey);
			}
			total = total + productsItemPrice;
		}

		if (null != coupon) {
			if (total > coupon.getTotalAmount()) {
				total = total - coupon.getDiscountAmount();
			}
		}
		return total;
	}

	/***
	 * 存储折扣信息
	 * 
	 * @param discountInformation
	 */
	public void addDiscountInformationList(
			DiscountInformation discountInformation ) {

		this.discountInformationList.add(discountInformation);
	}

	/**
	 * 存储产品条目
	 * 
	 * @param productsItems
	 */
	public void addProductItemsList( ProductsItems productsItems ) {

		this.productItemsList.add(productsItems);
	}
}
