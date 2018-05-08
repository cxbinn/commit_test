package jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static final String URL="jdbc:postgresql://127.0.0.1:5432/";
	private static final String USR="postgres";
	private static final String PWD="foxconn.88";
	
	static{
		try{
			Class.forName("org.postgresql.Driver");
		}catch(ClassNotFoundException e){
			System.err.println("数据加载出错");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection con=null;
		try{
			con=DriverManager.getConnection(URL,USR,PWD);			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}
	

}
