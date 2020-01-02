package com.selvaganesh.karadipathinterview.response.productlist;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SearchListItem implements Serializable {

	@SerializedName("CategoryId")
	private String categoryId;

	@SerializedName("ThumbImg")
	private String thumbImg;

	@SerializedName("OriginalPrice")
	private String originalPrice;

	@SerializedName("StockStatus")
	private String stockStatus;

	@SerializedName("AgeGroup")
	private String ageGroup;

	@SerializedName("ProductId")
	private String productId;

	@SerializedName("CategoryType")
	private String categoryType;

	@SerializedName("SKU")
	private String sKU;

	@SerializedName("ProductTitle")
	private String productTitle;

	@SerializedName("FinalPrice")
	private String finalPrice;

	public void setCategoryId(String categoryId){
		this.categoryId = categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}

	public void setThumbImg(String thumbImg){
		this.thumbImg = thumbImg;
	}

	public String getThumbImg(){
		return thumbImg;
	}

	public void setOriginalPrice(String originalPrice){
		this.originalPrice = originalPrice;
	}

	public String getOriginalPrice(){
		return originalPrice;
	}

	public void setStockStatus(String stockStatus){
		this.stockStatus = stockStatus;
	}

	public String getStockStatus(){
		return stockStatus;
	}

	public void setAgeGroup(String ageGroup){
		this.ageGroup = ageGroup;
	}

	public String getAgeGroup(){
		return ageGroup;
	}

	public void setProductId(String productId){
		this.productId = productId;
	}

	public String getProductId(){
		return productId;
	}

	public void setCategoryType(String categoryType){
		this.categoryType = categoryType;
	}

	public String getCategoryType(){
		return categoryType;
	}

	public void setSKU(String sKU){
		this.sKU = sKU;
	}

	public String getSKU(){
		return sKU;
	}

	public void setProductTitle(String productTitle){
		this.productTitle = productTitle;
	}

	public String getProductTitle(){
		return productTitle;
	}

	public void setFinalPrice(String finalPrice){
		this.finalPrice = finalPrice;
	}

	public String getFinalPrice(){
		return finalPrice;
	}

	@Override
 	public String toString(){
		return 
			"SearchListItem{" + 
			"categoryId = '" + categoryId + '\'' + 
			",thumbImg = '" + thumbImg + '\'' + 
			",originalPrice = '" + originalPrice + '\'' + 
			",stockStatus = '" + stockStatus + '\'' + 
			",ageGroup = '" + ageGroup + '\'' + 
			",productId = '" + productId + '\'' + 
			",categoryType = '" + categoryType + '\'' + 
			",sKU = '" + sKU + '\'' + 
			",productTitle = '" + productTitle + '\'' + 
			",finalPrice = '" + finalPrice + '\'' + 
			"}";
		}
}