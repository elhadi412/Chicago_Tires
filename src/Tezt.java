import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

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
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		System.out.println(simpleDateFormat.format(timestamp));
//		//cTezt.export();
//		String[] tireBrandz = new String[]{"Select Brand","ADVANTA","AVON TYRES","BFGoodrich","BRIDGESTONE","Continental","COOPER TIRES", "Dick CEPEK","DUNLOP TIRES","FALKEN TIRES","Firestone","FUZION","GENERAL TIRE", "GOOD YEAR", "Hankook","Hoosier RACING TIRE","KUMHO TIRE", "Laufenn","MICHELIN","NEXEN TIRE","PIRELLI","POWER KING","RIKEN", "SUMITOMO TIRES","TOYOTIRES","UNIROYAL","VREDESTEIN TIRES", "YOKOHAMA", "MILESTAR", "WEST LAKE", "DURO"};
//		Arrays.sort(tireBrandz);
//		System.out.println("TIRES: " + Arrays.toString(tireBrandz));

		//cTezt.backUpDataFile();
		String dString = "    elhadi elhadi";
		System.out.print(dString.trim());
	}
}
