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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Timestamp;
import javax.swing.JComboBox;

public class InsertTire extends JFrame {

	private JPanel contentPane;
	private final String[] tireOrientation = new String[]{"N/A","Set","Pair","Single"};
	private static final String[] tireBrandz = new String[]{"Select Brand", "ADVANTA", "AVON TYRES", "BFGOODRICH", "BRIDGESTONE", "COOPER TIRES", "CONTINENTAL", "DUNLOP TIRES", 
															"DURO", "DICK CEPEK", "FALKEN TIRES", "FUZION", "FIRESTONE", "GENERAL TIRE", "GOOD YEAR", "HANKOOK", "HOOSIER RACING TIRE", 
															"KUMHO TIRE", "LAUFENN", "MICHELIN", "MILESTAR", "NEXEN TIRE", "PIRELLI", "POWER KING", "RIKEN", "SUMITOMO TIRES", "TOYOTIRES",
															"UNIROYAL", "VREDESTEIN TIRES", "WEST LAKE", "YOKOHAMA"};
	private static ArrayList<String> brandList = new ArrayList<>(Arrays.asList(tireBrandz));
	private final String[] tireCondition = new String[]{"Select Condition","A","B","C","New"};

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	private JLabel lblNewLabel_1;
	private JLabel addNewTrIcon;
	private JLabel label;
	private JButton insertBtn;
	private JComboBox tire_orientation;
	private JComboBox tireBrands;
	private JComboBox conditionBox;

	/**
	 * Create the frame.
	 */
	public InsertTire() {
		connection = SqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 643, 507);
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
		lblSetpairsingle.setBounds(47, 308, 122, 31);
		contentPane.add(lblSetpairsingle);
		
		tire_orientation = new JComboBox(tireOrientation);
		tire_orientation.setBounds(178, 298, 174, 55);
		contentPane.add(tire_orientation);
		
		tireBrands = new JComboBox(brandList.toArray());
		tireBrands.setBounds(176, 365, 174, 55);
		contentPane.add(tireBrands);
		
		conditionBox = new JComboBox(tireCondition);
		conditionBox.setBounds(178, 243, 174, 43);
		contentPane.add(conditionBox);
		
		lblNewLabel_1 = new JLabel("Add a New Tire to Inventory");
		lblNewLabel_1.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		lblNewLabel_1.setBounds(158, 8, 305, 55);
		contentPane.add(lblNewLabel_1);
		
		addNewTrIcon = new JLabel("");
		Image insertPgIcon = new ImageIcon(this.getClass().getResource("/tireIconBigger.png")).getImage();
		addNewTrIcon.setIcon(new ImageIcon(insertPgIcon));
		addNewTrIcon.setBounds(393, 98, 244, 265);
		contentPane.add(addNewTrIcon);
		
		label = new JLabel("Chicago Tires LLC");
		label.setFont(new Font("Kokonor", Font.BOLD, 16));
		label.setBounds(427, 452, 210, 27);
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
			
			Integer.parseInt(sizeTxtField.getText());
			
			if(conditionBox.getSelectedItem().equals("Select Condition")){
				throw new Exception();
			}
			
			if(tire_orientation.getSelectedItem().toString().equals("N/A")){
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
			JOptionPane.showMessageDialog(null, "Added " + sizeTxtField.getText() + " to database.");
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
	
}
