package project_tripview;

import java.sql.DriverManager;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UserDAO {
		
		private Connection conn;
		private ResultSet rs;
		//기본생성자
		public UserDAO() {

			try {
				String dbURL = "jdbc:mariadb://58.229.253.250:3306/student208";
				String dbID = "student208";
				String dbPassword = "1234!!";
				Class.forName("org.mariadb.jdbc.Driver");
				conn = DriverManager.getConnection(dbURL,dbID,dbPassword);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
}
