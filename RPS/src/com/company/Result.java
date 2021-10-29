package com.company;

//This file creates Student Table for storing data whenever we need!

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Vector;
import java.sql.PreparedStatement;


public class Result extends JFrame {
	 private static final long serialVersionUID = 1L;
	JTableHeader header;
    JTable jtable;
    JLabel lbl;
    Container c;
    double ogpa=0;
    
    double[] sgpa=new double[10];
    int sem;
    Result(String enrollno,String semester){
    	   
    	super("Result");
        super.setVisible(true);
        super.setSize(800,200);
        super.setLocation(400,400);
        super.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	sem=Integer.parseInt(semester);
    	
            ArrayList<StudentResult> resultData = new ArrayList<>();
            try{
            	String sql = "select * from result where e_no=? ";

    			PreparedStatement preparedStatement = Main.con.prepareStatement(sql);

    			
    			preparedStatement.setString(1, enrollno);

    			ResultSet rs = preparedStatement.executeQuery();
                StudentResult resultDetails;
                while(rs.next()){
                   
                    String e_no = rs.getString(1);
                     sgpa[1] = rs.getDouble(2);
                     sgpa[2] = rs.getDouble(3);
                     sgpa[3] = rs.getDouble(4);
                     sgpa[4] = rs.getDouble(5);
                     sgpa[5] = rs.getDouble(6); 
                     sgpa[6] = rs.getDouble(7);
                     sgpa[7] = rs.getDouble(8);
                     sgpa[8] = rs.getDouble(9);
                    for(int i=1;i<=sem;i++)
                    		{
                    	ogpa+=sgpa[i];
                    		}
                    ogpa/=sem;
                    
                   resultDetails = new StudentResult(e_no,sgpa[1],sgpa[2],sgpa[3],sgpa[4],sgpa[5],sgpa[6],sgpa[7],sgpa[8],ogpa);
                    resultData.add(resultDetails);
                }
            }catch(SQLException e1){
                e1.printStackTrace();
            }
        
        


       
            Vector<Object> columnData = new Vector<>();
            columnData.add("EnrollNo.");
            columnData.add("sem1");
            columnData.add("sem2");
            columnData.add("sem3");
            columnData.add("sem4");
            columnData.add("sem5");
            columnData.add("sem6");
            columnData.add("sem7");
            columnData.add("sem8");
            columnData.add("ogpa");
            
            Vector<Vector<Object>> data = new Vector<>();
           
            for (StudentResult studentDetails : resultData) {
                Vector<Object> row = new Vector<>();
                row.add(studentDetails.gete_no());
                row.add(studentDetails.getsem1());
                row.add(studentDetails.getsem2());
                row.add(studentDetails.getsem3());
                row.add(studentDetails.getsem4());
                row.add(studentDetails.getsem5());
                row.add(studentDetails.getsem6());
                row.add(studentDetails.getsem7());
                row.add(studentDetails.getsem8());
                row.add(studentDetails.getogpa());
                data.add(row);
            }
            jtable = new JTable(data,columnData);
           
        
    	
    	
    	
          
        header = jtable.getTableHeader();
        c = getContentPane();
        c.setLayout(new BorderLayout());
        jtable.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        jtable.setGridColor(Color.BLACK);
        c.add("North",header);
        c.add("Center",jtable);
        
    }
}
