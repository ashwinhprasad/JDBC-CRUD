import java.util.Scanner;
import java.sql.*;

public class Main {
	public static void main(String[] args) throws Exception {
		
		// data base connection
		String dburl = "jdbc:mysql://localhost:3306/studentsDB";
		String uname = "ashwinhprasad";
		String passw = Creds.passw;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(dburl,uname,passw);
		Statement st = con.createStatement();
		
		Scanner sc =  new Scanner(System.in);
		
		int ch=0;
		
		do {
			System.out.println("1. Add Student");
			System.out.println("2. Remove Student");
			System.out.println("3. Display Students");
			System.out.println("4. Exit");
			System.out.println("Enter your Choice: ");
			ch = sc.nextInt();
			
			switch(ch) {
			
				// create
				case 1:{
					System.out.print("Enter The Student Name: ");
					String studName = sc.next();
					System.out.print("Enter The Student age: ");
					int studAge = sc.nextInt();
					System.out.print("Enter The Student year: ");
					int studYear = sc.nextInt();
					System.out.print("Enter The Student Email: ");
					String studEmail = sc.next();
					PreparedStatement pstmt = con.prepareStatement("INSERT INTO stud_info (name,age,year,email) VALUES(?,?,?,?);");
					pstmt.setString(1, studName);
					pstmt.setInt(2, studAge);
					pstmt.setInt(3, studYear);
					pstmt.setString(4, studEmail);
					pstmt.executeUpdate();
					System.out.println("Student Added...");
					pstmt.close();
					break;
				}
				
				// Delete
				case 2: {
					System.out.print("Enter the ID of the student to be removed: ");
					int studID = sc.nextInt();
					PreparedStatement pstmt = con.prepareStatement("DELETE from stud_info WHERE id=?;");
					pstmt.setInt(1, studID);
					pstmt.executeUpdate();
					System.out.println("Student Deleted...");
					pstmt.close();
					break;
				}
				
				// Retrieve
				case 3: {
					ResultSet rs = st.executeQuery("SELECT * FROM stud_info");
					while(rs.next()) {
						System.out.println("Id: "+rs.getString("id")+" - "+rs.getString("name")+" - "+rs.getString("year")+" Year");
					}
					break;
				}
				
				case 4:{
					System.out.println("Terminating...");
					break;
				}
				
				default:{
					System.out.println("Invlaid Choice, Try Again Please..");
				}
			
			}
		} while(ch!=4);
		
		st.close();
		con.close();
		sc.close();
		
		
	}

}
