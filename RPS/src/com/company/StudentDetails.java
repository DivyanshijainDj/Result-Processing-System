package com.company;

public class StudentDetails {
private String name,fname,mname,e_no,dept_id;

StudentDetails(String name,String fname,String mname,String e_no,String dept_id){
	this.name=name;
	this.fname=fname;
	this.mname=mname;
	this.e_no=e_no;
	this.dept_id=dept_id;
}

String getname()
{
	return name;
}
String getfname()
{
	return fname;
}
String getmname()
{
	return mname;
}
String geteno()
{
	return e_no;
}
String getdeptid()
{
	return dept_id;
}

}
