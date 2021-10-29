package com.company;

public class MarksDetails {

	 private int mt,p,t,c_t,c_p;
	 private String code;
	 
	
	 MarksDetails(String code,int c_t,int c_p,int t,int p,int mt)
	{   this.code=code;
		this.t=t;
		this.p=p;
		this.mt=mt;
		this.c_p=c_p;
		this.c_t=c_t;
	}
	
	int getheory()
	{
		return t;
	}
	int getpractical()
	{
		return p;
	}
	int getmidterm()
	{
		return mt;
	}
	String getcode()
	{
		return code;
	}
	int getcredit_t()
	{
		return c_t;
	}
	int getcredit_p()
	{
		return c_p;
	}
}
