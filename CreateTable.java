import java.sql.*;
import java.util.*;


public class CreateTable
{
    public static void main(String args[])
    {
    	try{
	Scanner sc = new Scanner(System.in);
	DATA_de d = new DATA_de();	
        Connection c = null;
        Statement stmt = null;
    	String CreateSql = null;

	Class.forName("org.postgresql.Driver");
        c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        System.out.println("Database Connected ..");
	int a = 0 ;
	while(a!=-1)
	{
    		System.out.println("choose an action: 1) Create Table...2)Add values..3) display...");
		a = sc.nextInt();
		switch(a)
		{
			case 1:
				System.out.println("\n");
				d.table_Cre();
				break;
			case 2:
				System.out.println("\n");
				d.insert_val();
				break;
			case 3:     
				System.out.println("\n"); 
				d.display();
				break;
			default:
				System.out.println("Enter values between 1-3");
			
		}
	}
	
    		} catch (Exception e){
        			System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        			System.exit(0);
        		}	
	    }
	

}
	
class DATA_de
{
			
			 public static void table_Cre()
			{
				try{
					Scanner sc = new Scanner(System.in);	
        				Connection c = null;
       					Statement stmt = null;
    					String CreateSql = null;
					
					Class.forName("org.postgresql.Driver");
       					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        				//System.out.println("Database Connected ..");
		
				
        				stmt = c.createStatement(); 
   
        				//CreateSql = "Create Table  Student(E_no varchar(20) not null primary key,name varchar(25),DOB date, F_name varchar(25), M_name varchar(25), Sex char(1), As_id numeric(1), Email varchar(30), Dept_id varchar(5), Mob_no varchar(10)) ";      
        				
				//CreateSql = "Create Table Department(Dept_id varchar(5) not null primary key,Dept_name varchar(25)) ";      
        				
				//CreateSql ="Create Table Subject(As_id numeric(1)  references Academic_Session(As_id),Dept_id varchar(10) references Department(Dept_id),Sub_name varchar(20),Sub_code varchar(8),Credit_T numeric(2),Credit_P numeric(2),Sem numeric(1),primary key(As_id,Sub_code))";
				
				CreateSql ="Create Table Marks(Sub_code varchar(8),As_id numeric(1),E_no varchar(20),Sem numeric(2),Practical_marks numeric(3),Theory_marks numeric(3),Mid_term_marks numeric(3),Grand_total numeric(3),TCP numeric(2,1),primary key(Sub_code,As_id,E_no),foreign key(As_id,Sub_code) references Subject(As_id,Sub_code),foreign key(E_no) references Student(E_no))";

				//CreateSql ="Create Table Result(E_no varchar(20),sem1 numeric(2,2),sem2 numeric(2,2),sem3 numeric(2,2),sem4 numeric(2,2),sem5 numeric(2,2),sem6 numeric(2,2),sem7 numeric(2,2),sem8 numeric(2,2),OGPA numeric(2,2),primary key(E_no),foreign key(E_no) references Student(E_no))";  
                                                                              //String query = "ALTER TABLE Student ADD Dept_id varchar(5) REFERENCES Department(Dept_id) on UPDATE CASCADE on DELETE RESTRICT ";
     				 //Executing the query
      				//stmt.executeUpdate(query);
         				//CreateSql ="Create Table Academic_Session(As_id numeric(1),Enroll_year numeric(4),primary key(As_id))";
				stmt.executeUpdate(CreateSql);
				System.out.println("Table created successfully");
				} catch (Exception e){
        						System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        						System.exit(0);
        					 }
			}
			public static void insert_val()
			{
				try{
  					Scanner sc = new Scanner(System.in);	
        				Connection c = null;
       					Statement stmt = null;
    					String CreateSql = null;
				
					Class.forName("org.postgresql.Driver");
        				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        				//System.out.println("Database Connected ..");
	
					System.out.println("Enter Dept_id");
					String Dept_id = sc.nextLine();
					System.out.println("Enter Dept_name");
					String Dept_name = sc.nextLine();
					String sql = "INSERT INTO Department " + "VALUES (?,?)";
			
					//stmt.executeUpdate(sql);
			
					PreparedStatement myStmt = c.prepareStatement(sql); 
					myStmt.setString(1,Dept_id);    
					myStmt.setString(2,Dept_name);
				
					System.out.println("values added successfuly"); 
					myStmt.executeUpdate();   
					//stmt.close();
					//c.close();
				}catch (Exception e){
        					System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        					System.exit(0);
        				}
			}
				public static void display()
      				{
        				try{
  						Scanner sc = new Scanner(System.in);
        					Connection c = null;
       						Statement stmt = null;
    						String CreateSql = null;


						Class.forName("org.postgresql.Driver");
        					c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "postgres");
        					//System.out.println("Database Connected ..");
	
                				PreparedStatement pst = c.prepareStatement("SELECT * FROM Department");
                        			ResultSet rs = pst.executeQuery();
						

						
          					System.out.println("Dept_id         Dept_name");
          					while (rs.next()) {
                  					System.out.print(rs.getString(1));
                  					System.out.print("      :     ");
                  					System.out.println(rs.getString(2));
                                                                                                System.out.println("\n");
              					}
						
					}catch (Exception e){
        					System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        					System.exit(0);
        			}	
			

       		
	
      
		

         	
		
	    	

     	
}
  	

}