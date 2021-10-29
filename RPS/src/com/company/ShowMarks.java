package com.company;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.PreparedStatement;

public class ShowMarks extends JFrame {
	private static final long serialVersionUID = 1L;
	JTableHeader header;
    JTable jtable1,jtable2;
   
    Container c;
   
    
    ShowMarks(String eno,String semester,String ass_id)
    {
    	super("Marks");
        super.setVisible(true);
        super.setSize(800,300);
        super.setLocation(400,200);
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
        
        int sem=Integer.parseInt(semester);
        int as_id=Integer.parseInt(ass_id);
        ArrayList<MarksDetails> marksdata = new ArrayList<>();
        try{
        	String sql = "select s.sub_code ,credit_t,credit_p,theory_marks,practical_marks,mid_term_marks from marks as m ,subject as s where m.e_no=? and m.sem=? and s.sem=? and m.as_id =? and s.sub_code=m.sub_code";

			PreparedStatement preparedStatement = Main.con.prepareStatement(sql);

			
			preparedStatement.setString(1, eno);
			preparedStatement.setInt(2,sem);
			preparedStatement.setInt(3,sem);
			preparedStatement.setInt(4,as_id);

			ResultSet rs = preparedStatement.executeQuery();
            MarksDetails marksdetails;
            while(rs.next()){
               
                String code = rs.getString(1);
                 int c_t = rs.getInt(2);
                 int c_p = rs.getInt(3);
                 int t = rs.getInt(4);
                 int p = rs.getInt(5);
                 int mt = rs.getInt(6);
                 marksdetails = new MarksDetails(code,c_t,c_p,t,p,mt);
                 marksdata.add(marksdetails);
            }
        }catch(SQLException e1){
            e1.printStackTrace();
        }
    
    


   
        Vector<Object> columnData = new Vector<>();
        columnData.add("Sub_code");
        columnData.add("Credit T");
        columnData.add("Credit P");
        columnData.add("Theory");
        columnData.add("Practical");
        columnData.add("Mid_term");

        
        Vector<Vector<Object>> data = new Vector<>();
       
        for (MarksDetails marksdetails : marksdata) {
            Vector<Object> row = new Vector<>();
            row.add(marksdetails.getcode());
            row.add(marksdetails.getcredit_t());
            row.add(marksdetails.getcredit_p());
            row.add(marksdetails.getheory());
            row.add(marksdetails.getpractical());
            row.add(marksdetails.getmidterm());
            
            data.add(row);
        }
        jtable1 = new JTable(data,columnData);
        
        
        ArrayList<StudentDetails> studentdata = new ArrayList<>();
        try{
        	String sql1 = "SELECT sname,fname,mname,e_no,dept_id from student where e_no=?";

			PreparedStatement preparedStatement1 = Main.con.prepareStatement(sql1);
           preparedStatement1.setString(1, eno);
		

			ResultSet rs1 =	preparedStatement1.executeQuery();
            StudentDetails studentdetails;
           while(rs1.next()){
               
                String name = rs1.getString(1);
                String fname = rs1.getString(2);
                String mname = rs1.getString(3);
                String e_no = rs1.getString(4);
                String dept_id = rs1.getString(5);

                 studentdetails = new StudentDetails(name,fname,mname,e_no,dept_id);
                 studentdata.add(studentdetails);}
            
        }catch(SQLException e2){
            e2.printStackTrace();
        }
    
    


   
        Vector<Object> columnData1 = new Vector<>();
        columnData1.add("Name");
        columnData1.add("Fname");
        columnData1.add("Mname");
        columnData1.add("E No.");
        columnData1.add("Dept ID");
        

        
        Vector<Vector<Object>> data1 = new Vector<>();
       
       for (StudentDetails studentdetails1 : studentdata) {
            Vector<Object> row1 = new Vector<>();
            row1.add(studentdetails1.getname());
            row1.add(studentdetails1.getfname());
            row1.add(studentdetails1.getmname());
            row1.add(studentdetails1.geteno());
            row1.add(studentdetails1.getdeptid());
           
            
            data1.add(row1);
        }
        jtable2 = new JTable(data1,columnData1);
        
        
        //header = jtable.getTableHeader();
        c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        //JScrollPane scrollPane1 = new JScrollPane(jtable1);
        //JScrollPane scrollPane2 = new JScrollPane(jtable2);
        //super.add(scrollPane1, BorderLayout.NORTH);
        //super.validate();
        //super.add(scrollPane2, BorderLayout.CENTER);
        c.add(jtable2.getTableHeader());
        c.add(jtable2);
        c.add(jtable1.getTableHeader());
        c.add(jtable1);
       // super.pack();
//        jtable2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
 //       jtable1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
  //      jtable2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
  //      jtable1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
       // super.validate();
        //jtable1;
        //c.add("East",header);
        //c.add("Center",jtable);
        
        
    }
        }
	

