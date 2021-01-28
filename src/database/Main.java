package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws Exception {
		createTable();
		post();
		get();
	}
	
	public static ArrayList<String> get() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement select = conn.prepareStatement("SELECT * FROM animals ORDER BY species ASC LIMIT 4");
			ResultSet result = select.executeQuery();
			
			ArrayList<String> array = new ArrayList<String>();
			while (result.next()) {
				String s = result.getString("species") + " " + result.getInt("age");
				System.out.println(s);

				array.add(s);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}
	
	public static void post() throws Exception {
		final String var1 = "giant panda";
		final String var2 = "1";
		try {
			Connection conn = getConnection();
			PreparedStatement posted = conn.prepareStatement("INSERT INTO animals (species, age) VALUES ('"+var1+"', '"+var2+"')");
			posted.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Table insertion Completed.");
		}
	}
	
	public static void createTable() throws Exception {
		try {
			Connection conn = getConnection();
			PreparedStatement create = conn.prepareStatement("CREATE TABLE IF NOT EXISTS animals (id int NOT NULL AUTO_INCREMENT, species varchar(255), age int, PRIMARY KEY(id));");
			create.executeUpdate();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			System.out.println("Table creation completed.");
		}
	}
	
	public static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/database1";
			String username = "root";
			String password = "password"; 
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println("Connected");
			return conn;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
	}

}
