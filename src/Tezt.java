import java.awt.Desktop;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tezt {

	private  String data = "SELECT * FROM inventory";
	Connection connection = null;
	
	public void export(){
		connection = SqliteConnection.dbConnector();
		try {
			PreparedStatement pStatement = connection.prepareStatement(data);
			ResultSet resultSet = pStatement.executeQuery();
			
			File file = new File("data.xlsx");
			Desktop desktop = Desktop.getDesktop();
			desktop.open(file);

			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public static void main(String[]args){
		Tezt cTezt = new Tezt();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		System.out.println(simpleDateFormat.format(timestamp));
		//cTezt.export();
	}
}
