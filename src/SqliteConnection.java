import java.io.File;
import java.sql.*;
import javax.swing.*;

import org.apache.batik.apps.rasterizer.Main;

public class SqliteConnection {
	Connection connection = null; 
	
	public static Connection dbConnector(){
		try{
			Class.forName("org.sqlite.JDBC");
			//Connection connection = DriverManager.getConnection("jdbc:sqlite:/Users/Elhadi/Downloads/sqlite-tools-osx-x86-3310100/EmployeeData(forGUI).db");
//			Connection connection = DriverManager.getConnection("jdbc:sqlite::resource:EmployeeData(forGUI).sqlite");
			String pathToDB = "jdbc:sqlite:" + System.getProperty("user.home") + File.separator + "ChicagoTiresLLC" + File.separator + "EmployeeData(forGUI).sqlite";
			Connection connection = DriverManager.getConnection(pathToDB);

			System.out.println("Connection Successful");
			return connection;
		}
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
	 		return null;
		}
		
	}

}
