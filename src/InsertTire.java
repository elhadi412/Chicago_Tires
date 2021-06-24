import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.Timestamp;
import javax.swing.JComboBox;

public class InsertTire extends JFrame {

	private JPanel contentPane;
	private final String[] tireOrientation = new String[]{"N/A","Set","Pair","Single"};
	
	private static String[] tireBrandz = new String[]{"Select Brand", "ADVANTA", "AVON TYRES", "BFGOODRICH", "BRIDGESTONE", "COOPER TIRES", "CONTINENTAL", "DUNLOP TIRES", 
														"DURO", "DICK CEPEK", "FALKEN TIRES", "FUZION", "FIRESTONE", "GENERAL TIRE", "GOOD YEAR", "HANKOOK", "HERCULES TIRES", 
														"HOOSIER RACING TIRE", "IRONMAN TIRES", 
														"KUMHO TIRE", "LAUFENN", "MICHELIN", "MILESTAR", "NEXEN TIRE", "PIRELLI", "POWER KING", "RIKEN", "SUMITOMO TIRES", "TOYOTIRES",
														"UNIROYAL", "VREDESTEIN TIRES", "WEST LAKE", "YOKOHAMA"};
	private static ArrayList<String> brandList = readTireBrandFile();
	final String[] tireCondition = new String[]{"Select Condition","A","B","C","New"};
//	private static File tireBrandFile = new File("Resources/tire_brands.txt");
//	private static final String fullPath = System.getProperty("user.home") + File.separator +  "tire_brands.txt";
	

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args)  {
		Tires.setTheLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertTire frame = new InsertTire();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField sizeTxtField;
	private JLabel locationJLabel;
	private JTextField locationTxtField;
	private JLabel lblCondition;
	private JLabel lblSetpairsingle;
	private JLabel addNewTire;
	private JLabel addNewTrIcon;
	private JLabel label;
	private JButton insertBtn;
	private JComboBox<String> tire_orientation;
	private JComboBox<String> tireBrands;
	private JComboBox<String> conditionBox;

	/**
	 * Create the frame.
	 * @throws InterruptedException 
	 */
	
	public InsertTire() {
		connection = SqliteConnection.dbConnector();
		
		//readTireBrandFile();
		System.out.println("EARLY ON, BRANDLIST IS: " + brandList.size());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 526);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton goBckBtn = new JButton("Back");
		goBckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Tires employeInfo = new Tires();
				employeInfo.setVisible(true);
			}
		});
		Image goBackImg = new ImageIcon(this.getClass().getResource("/goBack.png")).getImage();
		goBckBtn.setIcon(new ImageIcon(goBackImg));
		goBckBtn.setBounds(21, 19, 96, 38);
		contentPane.add(goBckBtn);
		
		sizeTxtField = new JTextField();
		sizeTxtField.setForeground(new Color(0, 153, 255));
		sizeTxtField.setBackground(Color.WHITE);
		sizeTxtField.setBounds(178, 84, 184, 46);
		contentPane.add(sizeTxtField);
		sizeTxtField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Size:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(133, 91, 47, 31);
		contentPane.add(lblNewLabel);
		
		locationJLabel = new JLabel("Location:");
		locationJLabel.setForeground(Color.BLACK);
		locationJLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		locationJLabel.setBounds(97, 173, 88, 31);
		contentPane.add(locationJLabel);
		
		locationTxtField = new JTextField();
		locationTxtField.setForeground(new Color(0, 153, 255));
		locationTxtField.setColumns(10);
		locationTxtField.setBackground(Color.WHITE);
		locationTxtField.setBounds(178, 166, 184, 46);
		contentPane.add(locationTxtField);
		
		lblCondition = new JLabel("Condition (A-C):");
		lblCondition.setForeground(Color.BLACK);
		lblCondition.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblCondition.setBounds(47, 247, 133, 31);
		contentPane.add(lblCondition);
		
		lblSetpairsingle = new JLabel("Set/Pair/Single:");
		lblSetpairsingle.setForeground(Color.BLACK);
		lblSetpairsingle.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblSetpairsingle.setBounds(47, 316, 122, 31);
		contentPane.add(lblSetpairsingle);
		
		tire_orientation = new JComboBox(tireOrientation);
		tire_orientation.setBounds(188, 306, 174, 55);
		contentPane.add(tire_orientation);
		
		tireBrands = new JComboBox(brandList.toArray());
		tireBrands.setBounds(176, 365, 210, 55);
		contentPane.add(tireBrands);
		
		conditionBox = new JComboBox(tireCondition);
		conditionBox.setBounds(188, 243, 174, 43);
		contentPane.add(conditionBox);
		
		
		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		addNewTire = new JLabel("  Add a New Tire to Inventory");
		addNewTire.setIcon(new ImageIcon(image));
		addNewTire.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		addNewTire.setBounds(158, 8, 305, 55);
		contentPane.add(addNewTire);
		
		addNewTrIcon = new JLabel("");
		Image insertPgIcon = new ImageIcon(this.getClass().getResource("/tireIconBigger.png")).getImage();
		addNewTrIcon.setIcon(new ImageIcon(insertPgIcon));
		addNewTrIcon.setBounds(393, 98, 244, 265);
		contentPane.add(addNewTrIcon);
		
		label = new JLabel("Chicago Tires LLC");
		label.setFont(new Font("Kokonor", Font.BOLD, 16));
		label.setBounds(465, 450, 210, 27);
		contentPane.add(label);
		
	
		
		insertBtn = new JButton("Add");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertTires();
			}
		});
		
		
		insertBtn.setForeground(new Color(51, 204, 51));
		insertBtn.setBounds(236, 432, 63, 36);
		contentPane.add(insertBtn);
		
		JLabel brandLbl = new JLabel("Brand:");
		brandLbl.setForeground(Color.BLACK);
		brandLbl.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		brandLbl.setBounds(118, 378, 56, 31);
		contentPane.add(brandLbl);
		
		JButton btnAddTireBrand = new JButton(" Add Tire Brand");
		Image newTireIcon = new ImageIcon(this.getClass().getResource("/addTireBetter.png")).getImage();
		btnAddTireBrand.setIcon(new ImageIcon(newTireIcon));
		btnAddTireBrand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddTireBrand tireBrand = new AddTireBrand();
				tireBrand.setVisible(true);				
				
			}
		});
		btnAddTireBrand.setForeground(new Color(51, 204, 51));
		btnAddTireBrand.setBounds(483, 21, 154, 36);
		contentPane.add(btnAddTireBrand);	
		
	}
	
	
	/**************************************************************BUTTON LOGIC & Methods************************************************/

	public static ArrayList<String> getTireList(){
		return brandList;
	}
	
	private void insertTires(){
		try {
			String query = "INSERT INTO Inventory(Size, Location, Condition, SetPairSingle, Brand,Timestamp)" + " values(?,?,?,?,?,?)";
			
			//Integer.parseInt(sizeTxtField.getText());
			
			if(sizeTxtField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please enter a tire size");
			}
			else if(! sizeTxtField.getText().trim().equals("") && locationTxtField.getText().trim().equals("")){
				JOptionPane.showMessageDialog(null, "Please enter a tire location");

			}
			else if((!sizeTxtField.getText().trim().equals("") && !locationTxtField.getText().trim().equals(""))   &&   conditionBox.getSelectedItem().equals("Select Condition")){
				throw new Exception();
			}
			
			else if((!sizeTxtField.getText().trim().equals("") && !locationTxtField.getText().trim().equals("") && !conditionBox.getSelectedItem().equals("Select Condition")) && tire_orientation.getSelectedItem().toString().equals("N/A")){
				JOptionPane.showMessageDialog(null, "Please select either Set, Pair, Single");
			}
			else if(tireBrands.getSelectedItem().toString().equals("Select Brand")){
				JOptionPane.showMessageDialog(null, "Please select tire brand");
			}
			
			else{
			
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1, sizeTxtField.getText());
			pStatement.setString(2,locationTxtField.getText());
			pStatement.setString(3, conditionBox.getSelectedItem().toString());
			pStatement.setString(4, tire_orientation.getSelectedItem().toString());
			pStatement.setString(5, tireBrands.getSelectedItem().toString());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			pStatement.setString(6, simpleDateFormat.format(timestamp));
			//String UniqueID = sizeTxtField.getText() + locationTxtField.getText();
			//pStatement.setString(5, locationTxtField.getText()+sizeTxtField.getText());
			
			pStatement.execute();
			
			pStatement.close();
			JOptionPane.showMessageDialog(null, "<html>Added <b><u>" + sizeTxtField.getText() + "</u></b> to database.</html>");
			BackUp backUp = new BackUp();
			backUp.export();
				
			sizeTxtField.setText("");
			locationTxtField.setText("");
			dispose();
			Tires employeInfo = new Tires();
			employeInfo.setVisible(true);
			Tires.btnLoadAllTires.doClick();
		}
			
		} catch (Exception e2) {
			// TODO: handle exception
			if(e2.getClass().getName().equals("java.lang.NumberFormatException")){
				JOptionPane.showMessageDialog(null,"Invalid Tire Size");
				sizeTxtField.setText("");
			}
			else{
				JOptionPane.showMessageDialog(null,"Select Condition");	
			}
		}
	}
	
	
	public static void updateTireBrandFile(String brand){
		 try {		
			 String fullPath = System.getProperty("user.home") + File.separator +  "ChicagoTiresLLC" + File.separator + "tire_brands.txt";
	
			 //String fullPath = System.getProperty("user.home") + File.separator +  "tire_brands.txt";
			 	FileWriter fileWriter = new FileWriter(fullPath,true);
			 	fileWriter.write("\r\n");
			 	fileWriter.write(brand);
			 	fileWriter.close();
			    System.out.println("Successfully wrote to the file.");
			    updateBrandList(brand);	
		    } 
		 catch (IOException e) {
				e.printStackTrace();
				String msg = e.toString() + " \n (updateTireBrandFile)";
	 			JOptionPane.showMessageDialog(null, msg);
		    }
	}
//	public static void updateTireBrandFile(String brand) throws IOException{
//		try {
//			FileWriter writer = new FileWriter(tireBrandFile,true);
//			writer.write(brand+"\n");
//			writer.close();
//			System.out.println("\nSuccessfully updated file with brand " + brand);
//			updateBrandList(brand);			
//
//			
//		} catch (IOException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			String msg = e.toString() + " \n (updateTireBrandFile)";
// 			JOptionPane.showMessageDialog(null, msg);
//		}
//		
//	}
	private static void updateBrandList(String brand){
		brandList.add(brand);
		brandList.remove("Select Brand");
		Collections.sort(brandList);
		brandList.add(0,"Select Brand");
		System.out.println("UPDATE BRANDLIST: " + brandList.size());
		
	}
	
	public static void doesFileExist(){
		try{
			String fullPath = System.getProperty("user.home") + File.separator +  "ChicagoTiresLLC" + File.separator + "tire_brands.txt";
			File file = new File(fullPath);
			if(file.createNewFile()){
				writeFile(tireBrandz);
				System.out.println("FILE CREATED and wrote tire brands to file tire_brands.txt");
			}
			else{
				System.out.println("FILE EXISTS ALREADY!");
			}
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.print("WE HAVE A PROBLEM");
		}
	}
	public static void writeFile(String[] brands){
		 try {
				String fullPath = System.getProperty("user.home") + File.separator +  "ChicagoTiresLLC" + File.separator + "tire_brands.txt";
				FileWriter myWriter = new FileWriter(fullPath,true);
				int i = 0;
			    for(String brand : brands){
			    	if(i==brands.length-1){
			    		myWriter.write(brand);
			    	}
			    	else{
			    		myWriter.write(brand+"\r\n");
			    	}
			    	i++;
			    }
			      myWriter.close();
			      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		    }
	}
	
	
	
	
	
	public static ArrayList<String> readTireBrandFile(){
		ArrayList<String> brands = new ArrayList<>();
		String fullPath = System.getProperty("user.home") + File.separator +  "ChicagoTiresLLC" + File.separator + "tire_brands.txt";

		File file = new File(fullPath);
		if(!file.isFile()){
			doesFileExist();
		}

		try{
			BufferedReader bufferedReader = new BufferedReader(new FileReader(fullPath));
			String line, selectBrand="";
			while((line = bufferedReader.readLine())!=null){
				if(line.equals("Select Brand")){
					selectBrand = line;
				}
				else{
					brands.add(line);
				}
		}
		 Collections.sort(brands);
     	brands.add(0,selectBrand);
        System.out.println("\nSUCCESSFULLY READ FILE: ");
		bufferedReader.close();
        return brands;

		}
		catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		}
		return brands;
	}
	
//	public static ArrayList<String> readTireBrandFile(){
//		String filename = "tire_brands.txt";
//		ArrayList<String> brandzz = new ArrayList<>();
//		try {
//			ClassLoader classLoader = InsertTire.class.getClassLoader();			//getClass().getClassLoader();
//			InputStream inputStream = classLoader.getResourceAsStream(filename); 
//			if(inputStream == null){
//				throw new IllegalArgumentException();
//			}
//			else{
//				try {
//					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//		            String line, selectBrand="";
//		            while ((line = reader.readLine()) != null) {
//		            	if(line.equals("Select Brand")){
//							selectBrand = line;
//						}
//						else{
//							brandzz.add(line);
//						}
//		            }
//		            Collections.sort(brandzz);
//	            	brandzz.add(0,selectBrand);
//		            inputStream.close();
//		            reader.close();
//		            System.out.println("\nSUCCESSFULLY READ FILE: ");
//		            return brandzz;
//		            
//					
//				} catch (Exception e) {
//					// TODO: handle exception
//					JOptionPane.showMessageDialog(null, e);
//				}
//				
//	           	}
//			
//			
//		} catch (IllegalArgumentException e) {
//			// TODO: handle exception
//			JOptionPane.showMessageDialog(null, e);
//		}
//		return brandzz;
//		
//		
//		
//		
////		try {
////			brandList = new ArrayList<>();
////			Scanner scanner = new Scanner(tireBrandFile);
////			String selectBrand = "";
////			while(scanner.hasNextLine()){
////				String line = scanner.nextLine();
////				if(line.equals("Select Brand")){
////					selectBrand = line;
////				}
////				else{
////					brandList.add(line);
////				}
////				Collections.sort(brandList);
////			}
////			scanner.close();
////			brandList.add(0,selectBrand);
////			System.out.println("Successfully read brands from file");
////
////			
////		} catch (FileNotFoundException e) {
////			// TODO: handle exception
////			e.printStackTrace();
////			String msg = e.toString() + " \n(readTireBrandFile)";
//// 			JOptionPane.showMessageDialog(null, msg);
////
////		}
//	}
	
}
