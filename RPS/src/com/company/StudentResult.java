package com.company;

// This file explains the Student's details

public class StudentResult {
    final private double sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8,ogpa;
    final private String e_no;
   

    StudentResult(String e_no,double sem1,double sem2,double sem3,double sem4,double sem5,double sem6,double sem7,double sem8,double ogpa){
        this.e_no = e_no;
        this.sem1= sem1;
        this.sem2= sem2;
        this.sem3= sem3;
        this.sem4= sem4;
        this.sem5= sem5;
        this.sem6= sem6;
        this.sem7= sem7;
        this.sem8= sem8;
        this.ogpa= ogpa;
        
        
    }

    public String gete_no() {
        return e_no;
    }
   public double getsem1()
   {
	   return sem1;
   }
   public double getsem2()
   {
	   return sem2;
   }
   public double getsem3()
   {
	   return sem3;
   }
   public double getsem4()
   {
	   return sem4;
   }
   public double getsem5()
   {
	   return sem5;
   }
   public double getsem6()
   {
	   return sem6;
   }
   public double getsem7()
   {
	   return sem7;
   }
   
   public double getsem8()
   {
	   return sem8;
   }
   public double getogpa()
   {
	   return ogpa;
   }    
}

