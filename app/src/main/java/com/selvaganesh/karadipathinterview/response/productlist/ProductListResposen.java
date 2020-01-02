package com.selvaganesh.karadipathinterview.response.productlist;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProductListResposen{

	@SerializedName("errcode")
	private String errcode;

	@SerializedName("ResultCnt")
	private int resultCnt;

	@SerializedName("SearchList")
	private List<SearchListItem> searchList;

	@SerializedName("error")
	private String error;

	public void setErrcode(String errcode){
		this.errcode = errcode;
	}

	public String getErrcode(){
		return errcode;
	}

	public void setResultCnt(int resultCnt){
		this.resultCnt = resultCnt;
	}

	public int getResultCnt(){
		return resultCnt;
	}

	public void setSearchList(List<SearchListItem> searchList){
		this.searchList = searchList;
	}

	public List<SearchListItem> getSearchList(){
		return searchList;
	}

	public void setError(String error){
		this.error = error;
	}

	public String getError(){
		return error;
	}

	@Override
 	public String toString(){
		return 
			"ProductListResposen{" + 
			"errcode = '" + errcode + '\'' + 
			",resultCnt = '" + resultCnt + '\'' + 
			",searchList = '" + searchList + '\'' + 
			",error = '" + error + '\'' + 
			"}";
		}
}