import java.awt.Desktop;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Tezt {

	private  String data = "SELECT * FROM inventory";
	Connection connection = null;
	
	private static String[] tireBrandz = new String[]{"Select Brand", "ADVANTA", "AVON TYRES", "BFGOODRICH", "BRIDGESTONE", "COOPER TIRES", "CONTINENTAL", "DUNLOP TIRES", 
	"DURO", "DICK CEPEK", "FALKEN TIRES", "FUZION", "FIRESTONE", "GENERAL TIRE", "GOOD YEAR", "HANKOOK", "HERCULES TIRES", "HOOSIER RACING TIRE", "IRONMAN TIRES", 
	"KUMHO TIRE", "LAUFENN", "MICHELIN", "MILESTAR", "NEXEN TIRE", "PIRELLI", "POWER KING", "RIKEN", "SUMITOMO TIRES", "TOYOTIRES",
	"UNIROYAL", "VREDESTEIN TIRES", "WEST LAKE", "YOKOHAMA"};
	
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
	
	public InputStream test(String filename){
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(filename);
		if(inputStream==null){
			throw new IllegalArgumentException("file not found! " + filename);
		}
		else{
			return inputStream;
		}

	}
	
	public static boolean doesFileExist(){
		try{
			String pathname = System.getProperty("user.home");
			String slash = File.separator;
			String fullPath = pathname + slash + "tire_brands.txt";
			File file = new File(fullPath);
			if(file.createNewFile()){
				writeFile(tireBrandz);
				System.out.println("FILE CREATED");
				
			}
			else{
				System.out.println("FILE EXISTS");
				return true;
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.print("WE HAVE A PROBLEM");
		}
		return false;
	}
	public static void readFile() throws FileNotFoundException, IOException{
		String pathname = System.getProperty("user.home");
		String slash = File.separator;
		String fullPath = pathname + slash + "tire_brands.txt";
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fullPath));
		String line;
		while((line = bufferedReader.readLine())!=null){
			System.out.println(line);
		}
		bufferedReader.close();
		
	}
	
	public static void writeFile(String brand){
		 try {
			 	String pathname = System.getProperty("user.home");
				String slash = File.separator;
				String fullPath = pathname + slash +  "tire_brands.txt";
				FileWriter myWriter = new FileWriter(fullPath,true);
			      myWriter.write("brand");
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		    }
	}
	public static void writeFile(String[] brands){
		 try {
			 	String pathname = System.getProperty("user.home");
				String slash = File.separator;
				String fullPath = pathname + slash +  "tire_brands.txt";
				FileWriter myWriter = new FileWriter(fullPath,true);
			    for(String brand : brands){
				      myWriter.write(brand+"\n");
			    }
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		    }
	}
	
	
	
	public static void main(String[]args) throws IOException{
		Tezt cTezt = new Tezt();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//		System.out.println(simpleDateFormat.format(timestamp));
//		//cTezt.export();
//		String[] tireBrandz = new String[]{"Select Brand","ADVANTA","AVON TYRES","BFGoodrich","BRIDGESTONE","Continental","COOPER TIRES", "Dick CEPEK","DUNLOP TIRES","FALKEN TIRES","Firestone","FUZION","GENERAL TIRE", "GOOD YEAR", "Hankook","Hoosier RACING TIRE","KUMHO TIRE", "Laufenn","MICHELIN","NEXEN TIRE","PIRELLI","POWER KING","RIKEN", "SUMITOMO TIRES","TOYOTIRES","UNIROYAL","VREDESTEIN TIRES", "YOKOHAMA", "MILESTAR", "WEST LAKE", "DURO"};
//		Arrays.sort(tireBrandz);
//		System.out.println("TIRES: " + Arrays.toString(tireBrandz));

		//cTezt.backUpDataFile();
		
//		String fileName = "tire_brands.txt";
//		
//		InputStream is = cTezt.test(fileName);
//		StringBuilder out = new StringBuilder();
//		 try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
//	            String line;
//	            while ((line = reader.readLine()) != null) {
//	                out.append(line + "\n");
//	            }
//	        }
//        System.out.println(out.toString());
//		
//		System.out.println(is.toString());
//		
//		
//		
//		
//		
//		
//		
//		String dString = "    elhadi elhadi";
//		System.out.print(dString.trim());
//		try {
//			File file = new File("tire_brands.txt");
//			System.out.println("absolute: " + file.getAbsolutePath());
//			System.out.println("relative?: " + file.getPath());
//
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		//Tezt.readFile();
		Tezt.doesFileExist();
		//Tezt.writeFile("ELHADI TIREZ");
	}
}
