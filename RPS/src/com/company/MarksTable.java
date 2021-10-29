package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class MarksTable extends JFrame implements ActionListener{
   private static final long serialVersionUID = 1L;
   JButton addmarks;
   JLabel[] label=new JLabel[8];
   JLabel mt,t,p;
   JTextField[][] txtfld =new JTextField[8][3]; 
   Container c;
   String eno;
   String[] s=new String[9];
   StudentLogic student;
   int sem,i,as_id;
   double sem2;
   
	MarksTable(String semester,String dept,String eno,String ass_id){
		super("Enter Marks");
		super.setVisible(true);
		super.setBounds(100,100,500,500);
		c=getContentPane();
		
		t=new JLabel("Theory");
		t.setBounds(85,0,80,30);
		c.add(t);
		
		p=new JLabel("Practical");
		p.setBounds(200,0,80,30);
		c.add(p);
		
		mt=new JLabel("Mid Term");
		mt.setBounds(320,0,80,30);
		c.add(mt);
		
		this.as_id=Integer.parseInt(ass_id);
		this.eno=eno;
		 this.sem=Integer.parseInt(semester);
		try{
			String sql = "select sub_code from subject where sem=? and dept_id=? and as_id=?";

			PreparedStatement preparedStatement = Main.con.prepareStatement(sql);

			preparedStatement.setInt(1,sem );
			preparedStatement.setString(2, dept);
			preparedStatement.setInt(3, as_id);
			ResultSet r = preparedStatement.executeQuery();
			int i=0;
        
        
        while(r.next()){
              s[i] = r.getString(1);
            label[i]=new JLabel(s[i]);
            label[i].setBounds(20 , (30 + 50*i), 80 , 30 );
            c.add(label[i]);
            
            txtfld[i][0]= new JTextField();
            txtfld[i][0].setBounds(70,(30 + 50*i),100,30);
            c.add(txtfld[i][0]);
           
            
            txtfld[i][1]= new JTextField();
            txtfld[i][1].setBounds(185,(30 + 50*i),100,30);
            c.add(txtfld[i][1]);
            
            
            txtfld[i][2]= new JTextField();
            txtfld[i][2].setBounds(305,(30 + 50*i),100,30);
            c.add(txtfld[i][2]);
            
            
            
             i++;}
            this.i=i;
            JButton btn=new JButton("submit");
            btn.setBounds(200, 420, 100,30);
            c.add(btn);
            btn.addActionListener(this);
            JLabel labl=new JLabel("");
            c.add(labl);
            }
        catch(SQLException e1){
            e1.printStackTrace();
        }
           
	
		
		
		}
	
	
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
				for(int j=0;j<i;j++) {
					try {
							PreparedStatement st = Main.con.prepareStatement("INSERT INTO marks (sub_code,e_no,theory_marks,practical_marks,mid_term_marks,as_id,sem ) VALUES (?,?,?,?, ?, ?, ?)");
							
							st.setString(1, s[j]);
							st.setString(2, eno);
							st.setInt(3, Integer.parseInt(txtfld[j][0].getText()));
							st.setInt(4,  Integer.parseInt(txtfld[j][1].getText()));
							st.setInt(5,  Integer.parseInt(txtfld[j][2].getText()));
							st.setInt(6, as_id);
							st.setInt(7,sem );
							st.executeUpdate();
							st.close();
						    }
					
					catch(SQLException e1){
						
				        e1.printStackTrace();
					} 
			}
		

                
		
		
		
		
		
		try {
			Statement stmt = Main.con.createStatement();
           
           String s1="update marks set grand_total=(theory_marks+practical_marks+mid_term_marks) ";
           String   s2="update marks   set credit = subject.credit_t + subject.credit_p  from subject where marks.sub_code=subject.sub_code" ;
           stmt.addBatch(s1);
           stmt.addBatch(s2);
           stmt.executeBatch();
           
           String sql = "select sum(grand_total*credit),sum(credit) from marks where e_no=? and sem=?";

			PreparedStatement preparedStatement = Main.con.prepareStatement(sql);
			preparedStatement.setString(1, eno);
			preparedStatement.setInt(2,sem );
			double[] arr=new double[2];

		ResultSet r = preparedStatement.executeQuery();
           while(r.next())
           {
        	   double gt = r.getDouble(1);
        	   double tc =r.getDouble(2);
        	   double sgpa=gt/(tc*10);
        	   arr[0]=sgpa;
        	    
           }
           
           double[] sgpa=new double[10];
        if(sem==1) {
        	String sql3 = "insert into result(e_no,sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8,ogpa) values(?,?,?,?,?,?,?,?,?,?)";
        	sgpa[0]=arr[0];		
              
            PreparedStatement preparedStatement3 = Main.con.prepareStatement(sql3);
            preparedStatement3.setDouble(2,sgpa[0]);
            preparedStatement3.setString(1,eno); 
            preparedStatement3.setDouble(3,0.0);
            preparedStatement3.setDouble(4,0.0);
            preparedStatement3.setDouble(5,0.0);
            preparedStatement3.setDouble(6,0.0);
            preparedStatement3.setDouble(7,0.0);
            preparedStatement3.setDouble(8,0.0);
            preparedStatement3.setDouble(9,0.0);
            preparedStatement3.setDouble(10,0.0);
            preparedStatement3.executeUpdate();
        }
        else{ String sql1 = "select sem1,sem2,sem3,sem4,sem5,sem6,sem7,sem8 from result where e_no=? ";

		PreparedStatement preparedStatement1 = Main.con.prepareStatement(sql1);

		preparedStatement1.setString(1,eno);

		ResultSet rs1 = preparedStatement1.executeQuery();
          
          while(rs1.next()){
              
               
                sgpa[1] = rs1.getDouble(1);
                sgpa[2] = rs1.getDouble(2);
                sgpa[3] = rs1.getDouble(3);
                sgpa[4] = rs1.getDouble(4);
                sgpa[5] = rs1.getDouble(5); 
                sgpa[6] = rs1.getDouble(6);
                sgpa[7] = rs1.getDouble(7);
                sgpa[8] = rs1.getDouble(8); }
                
              
                	sgpa[sem]=arr[0];
              
            String sql2 = "UPDATE RESULT set sem1=?,sem2=?,sem3=?,sem4=?,sem5=?,sem6=?,sem7=?,sem8=? where e_no=? ";

            PreparedStatement preparedStatement2 = Main.con.prepareStatement(sql2);

            preparedStatement2.setDouble(1,sgpa[1]);
            preparedStatement2.setDouble(2,sgpa[2]);
            preparedStatement2.setDouble(3,sgpa[3]);
            preparedStatement2.setDouble(4,sgpa[4]);
            preparedStatement2.setDouble(5,sgpa[5]);
            preparedStatement2.setDouble(6,sgpa[6]);
            preparedStatement2.setDouble(7,sgpa[7]);
            preparedStatement2.setDouble(8,sgpa[8]);
            preparedStatement2.setString(9,eno); 
            preparedStatement2.executeUpdate();
                
        }}catch(SQLException e1){
	            e1.printStackTrace();
	            
	        }	
		
		JOptionPane.showMessageDialog(null,"Data inserted Successfully");
	    	
	}
	}

