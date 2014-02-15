package com.wsq.pagingquery.model;

import java.util.List;

public class PageModel<E> {
	//ʹ�÷��ͣ����ڴ���ĸ���
	private List<E> list;  
	//��ǰҳ��
    private int pageNo;  
    //ÿҳ�Ĵ�С
    private int pageSize;  
    //����
    private int totalNum;  
    //��ҳ��
    private int totalPage;  
  
    public List<E> getList() {  
        return list;  
    }  
  
    public void setList(List<E> list) {  
        this.list = list;  
    }  
  
    public int getPageNo() {  
        return pageNo;  
    }  
  
    public void setPageNo(int pageNo) {  
        this.pageNo = pageNo;  
    }  
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public int getTotalNum() {  
        return totalNum;  
    }  
  
    public void setTotalNum(int totalNum) {  
        this.totalNum = totalNum;  
        setTotalPage((getTotalNum() % pageSize) == 0 ? (getTotalNum() / pageSize)  
                : (getTotalNum() / pageSize + 1));  
    }  
  
    public int getTotalPage() {  
        return totalPage;  
    }  
  
    public void setTotalPage(int totalPage) {  
        this.totalPage = totalPage;  
    }  
  
    // ��ȡ��һҳ   
    public int getFirstPage() {  
        return 1;  
    }  
  
    // ��ȡ���ҳ   
    public int getLastPage() {  
        return totalPage;  
    }  
  
    // ��ȡǰҳ   
    public int getPrePage() {  
        if (pageNo > 1)  
            return pageNo - 1;  
        return 1;  
    }  
  
    // ��ȡ��ҳ   
    public int getBackPage() {  
        if (pageNo < totalPage)  
            return pageNo + 1;  
        return totalPage;  
    }  
  
    // �ж�'��ҳ'����ǰҳ���Ƿ����   
    public String isPreable() {  
        if (pageNo == 1)  
            return "disabled";  
        return "";  
    }  
  
    // �ж�'βҳ'������ҳ���Ƿ����   
    public String isBackable() {  
        if (pageNo == totalPage)  
            return "disabled";  
        return "";  
    }  


}
