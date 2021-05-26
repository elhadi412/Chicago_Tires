import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * This class backs up the tire data to a local excel sheet that way if something happens to the database, the owner still has access to 
 * data of the tires.
 * @author Elhadi
 *
 */
public class BackUp {
	private  String data = "SELECT * FROM inventory";
	private String excelFile = "data.xlsx";
	Connection connection = null;
	public void export() throws SQLException{
		connection = SqliteConnection.dbConnector();
		try {
			PreparedStatement pStatement = connection.prepareStatement(data);
			ResultSet resultSet = pStatement.executeQuery();
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("BackUpData");
			
			writeColumnHeaders(sheet);
			writeData(resultSet, workbook, sheet);
			
			FileOutputStream outputStream = new FileOutputStream(excelFile);
			workbook.write(outputStream);
			workbook.close();
			pStatement.close();
			
			System.out.println("Successfully backed up database");
			
			
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);

		}catch (SQLException e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e2);

		}
		
		
	}
	
	private void writeColumnHeaders(XSSFSheet sheet){
		Row iDRow = sheet.createRow(0);
		Cell header = iDRow.createCell(0);
		header.setCellValue("ID");
		
		header = iDRow.createCell(1);
		header.setCellValue("Size");
		
		header = iDRow.createCell(2);
		header.setCellValue("Brand");
		
		header = iDRow.createCell(3);
		header.setCellValue("Location");
		
		header = iDRow.createCell(4);
		header.setCellValue("Condition");
		
		header = iDRow.createCell(5);
		header.setCellValue("SetPairSingle");
		
		header = iDRow.createCell(6);
		header.setCellValue("Timestamp");
		
	}
	
	private void writeData(ResultSet resultSet, XSSFWorkbook workbook, XSSFSheet sheet) throws SQLException {
		int row = 1;
		
		while(resultSet.next()){
			int id = resultSet.getInt("ID");
			String size = resultSet.getString("Size");
			String brand = resultSet.getString("Brand");
			String location = resultSet.getString("Location");
			String condition = resultSet.getString("Condition");
			String setSinglePair = resultSet.getString("SetPairSingle");
			String timestamp = resultSet.getString("Timestamp");
			
			Row rowCount = sheet.createRow(row++);
			
			int column = 0;
			Cell currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(id);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(size);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(brand);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(location);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(condition);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(setSinglePair);
			
			currentCell = rowCount.createCell(column++);
			currentCell.setCellValue(timestamp);
			
			
			
		}
	}
	public static void main(String[] args) throws SQLException{
		BackUp backUp = new BackUp();
		backUp.export();
	}

}
