package com.company;


import java.awt.Container;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.*;

public class StudentLogic extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
    JButton btnAdd,btnShow,btnMarks,btnDelete;
    JTextField txtRoll,txtAs_id,txtSem,txtDept;
    JLabel lblRoll,lblAs_id,lblSem,lblDept;
    int sem,dept,as_id;
    Container c;

    StudentLogic(){
        //set Title of JFrame
        super("Student Form");
        

        // adding container to JFrame
        c = getContentPane();

        // setting Layout of JFrame to NUll
        c.setLayout(null);
       
        //Creating Label Text for each TextField and adding to container
        lblRoll = new JLabel("EnRollNo : ");
        lblRoll.setBounds(20,50,80,30);
        c.add(lblRoll);
        
        txtRoll =  new JTextField();
        txtRoll.setBounds(120,50,200,30);
        c.add(txtRoll);
        
        lblAs_id =new JLabel("As_id :");
        lblAs_id.setBounds(20,125,100,30);
        c.add(lblAs_id);
        
        txtAs_id =new JTextField();
        txtAs_id.setBounds(120,125,200,30);
        c.add(txtAs_id);
        
        
        lblSem = new JLabel("Semester : ");
        lblSem.setBounds(20,200,100,30);
        c.add(lblSem);
        
        txtSem =  new JTextField();
        txtSem.setBounds(120,200,200,30);
        c.add(txtSem);
        
        lblDept=new JLabel("DepartmentID :");
        lblDept.setBounds(20,275,100,30);
        c.add(lblDept);
        
        txtDept=new JTextField();
        txtDept.setBounds(120,275,200,30);
        c.add(txtDept);
       

        //creating button and adding to container
        btnAdd = new JButton("Add Marks");
        btnAdd.setBounds(20,350,150,40);
        c.add(btnAdd);
        
        btnShow = new JButton("Show Result");
        btnShow.setBounds(220,350,150,40);
        c.add(btnShow);
        
        btnMarks=new JButton("Show Marks");
        btnMarks.setBounds(220,400,150,40);
        c.add(btnMarks);

        btnDelete=new JButton("Delete Marks");
        btnDelete.setBounds(20, 400, 150, 40);
        c.add(btnDelete);
        //adding action Listener to each Button
        btnAdd.addActionListener(this);
        btnShow.addActionListener(this);
        btnMarks.addActionListener(this);
        btnDelete.addActionListener(this);
       
        

    }

    


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnAdd){
        //	String eno= txtRoll.getText();
            as_id=Integer.parseInt(txtAs_id.getText());
        	sem=Integer.parseInt(txtSem.getText());
        	dept=Integer.parseInt(txtDept.getText());
        	//if((eno.substring(0,4)=="2018" && as_id==1)||(eno.substring(0,4)=="2019" && as_id==2)) {
        		
        			if(((sem==1)||(sem==2)||(sem==3)||(sem==4)||(sem==5)||(sem==6)||(sem==7)||(sem==8))&&((dept==1)))
        			{   
        		JOptionPane.showMessageDialog(null,"Do not leave any field blank ,if THEORY credit or PRACTICAL credit of a subject is zero then add 0 to the field");
        		new MarksTable(txtSem.getText(),txtDept.getText(),txtRoll.getText(),txtAs_id.getText());	
        			}
        	
        			else {
        		JOptionPane.showMessageDialog(null,"Invalid Semester or DepartmentID");
        			}
        	//	}
      /*  	else {
    			JOptionPane.showMessageDialog(null,"As_id and enrollment number does not match");
         	}*/
}
        	
        else if(e.getSource()==btnShow){
        	sem=Integer.parseInt(txtSem.getText());
        	dept=Integer.parseInt(txtDept.getText());
        	if(((sem==1)||(sem==2)||(sem==3)||(sem==4)||(sem==5)||(sem==6)||(sem==7)||(sem==8))&&((dept==1)))
        	{
            new Result(txtRoll.getText(),txtSem.getText());}
        	else {
        		JOptionPane.showMessageDialog(null,"Invalid Semester or DepartmentID");
        	}
           
        } else if(e.getSource()== btnMarks) {
        	sem=Integer.parseInt(txtSem.getText());
        	dept=Integer.parseInt(txtDept.getText());
        	if(((sem==1)||(sem==2)||(sem==3)||(sem==4)||(sem==5)||(sem==6)||(sem==7)||(sem==8))&&((dept==1)))
        	{
        		new ShowMarks(txtRoll.getText(),txtSem.getText(),txtAs_id.getText());
        	}else {
        		JOptionPane.showMessageDialog(null,"Invalid Semester or DepartmentID");
        	}
        }
        
        else if(e.getSource()==btnDelete) {
//        	String eno1= txtRoll.getText();
            as_id=Integer.parseInt(txtAs_id.getText());
       //	if((eno1.equals("2018") && as_id==1)||(eno1.equals("2019") && as_id==2)) {
        		try {
					PreparedStatement st = Main.con.prepareStatement("delete from marks where e_no=? and sem=?");
					
					st.setString(1,txtRoll.getText() );
					st.setInt(2,Integer.parseInt(txtSem.getText()));
					st.executeUpdate();
					st.close();
					JOptionPane.showMessageDialog(null,"Marks Deleted successfuly");
				    }
			
			catch(SQLException e1){
		        e1.printStackTrace();
			} 
        /*	}else
        		
        	{
        		JOptionPane.showMessageDialog(null,"As_id and enrollment number does not match");
        		if(eno1.equals("2018")) {
        			System.out.print("hello");
        		}
        		
        		}*/
        	}
    }
}



