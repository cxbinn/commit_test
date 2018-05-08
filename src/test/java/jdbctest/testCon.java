package jdbctest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class testCon {
	public static void main(String[] args) {

		String sql_create = "CREATE TABLE STUDENTS (ID TEXT PRIMARY KEY NOT NULL,NAME TEXT NOT NULL,SEX TEXT NOT NULL,AGE TEXT NOT NULL)";

		Connection con = null;

		try {
			con = DBUtil.getConnection();

//			PreparedStatement pstmt = con
//					.prepareStatement();
			
			Statement stmt=con.createStatement();
			stmt.execute(sql_create);
			//ResultSet rs = pstmt.executeUpdate(sql_create);
			int i = 0;
//			while (rs.next()) {
//				System.out.println(i++);
//			}
//
//			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
