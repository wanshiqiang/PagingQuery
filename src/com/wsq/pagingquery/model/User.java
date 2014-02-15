package com.wsq.pagingquery.model;

import java.util.Date;
import java.util.List;

public class User {
	private String name;
	private int age = -1;
	private String pwd;
	private Date birthday;
	private String sex;
	private String[] aihaos;
	private String xueli;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String[] getAihaos() {
		return aihaos;
	}
	public void setAihaos(String[] aihaos) {
		this.aihaos = aihaos;
	}
	public String getXueli() {
		return xueli;
	}
	public void setXueli(String xueli) {
		this.xueli = xueli;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	

}
