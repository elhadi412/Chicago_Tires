import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
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

public class AddTireBrand extends JFrame {

	private JPanel contentPane;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Tires.setTheLookAndFeel();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTireBrand tireBrand = new AddTireBrand();
					tireBrand.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection = null;
	private JTextField brandTxtField;
	private JLabel tireBrand;
	private JLabel label;
	private JButton button;

	/**
	 * Create the frame.
	 */
	public AddTireBrand() {
		connection = SqliteConnection.dbConnector();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 214);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton goBckBtn = new JButton("Back");
		goBckBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InsertTire insertTire = new InsertTire();
				insertTire.setVisible(true);
				
			}
		});
		Image goBackImg = new ImageIcon(this.getClass().getResource("/goBack.png")).getImage();
		goBckBtn.setIcon(new ImageIcon(goBackImg));
		goBckBtn.setBounds(6, 19, 96, 38);
		contentPane.add(goBckBtn);
		
		brandTxtField = new JTextField();
		brandTxtField.setForeground(new Color(0, 153, 255));
		brandTxtField.setBackground(Color.WHITE);
		brandTxtField.setBounds(187, 71, 184, 46);
		contentPane.add(brandTxtField);
		brandTxtField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Tire Brand:");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblNewLabel.setBounds(79, 78, 116, 31);
		contentPane.add(lblNewLabel);
		
		tireBrand = new JLabel(" Add Tire Brand");
		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		tireBrand.setIcon(new ImageIcon(image));
		tireBrand.setFont(new Font("Microsoft Sans Serif", Font.BOLD, 20));
		tireBrand.setBounds(158, 8, 217, 55);
		contentPane.add(tireBrand);
		Image insertPgIcon = new ImageIcon(this.getClass().getResource("/tireIconBigger.png")).getImage();
		
		label = new JLabel("Chicago Tires LLC");
		label.setFont(new Font("Kokonor", Font.BOLD, 16));
		label.setBounds(394, 138, 210, 27);
		contentPane.add(label);
		Image newTireIcon = new ImageIcon(this.getClass().getResource("/addTireBetter.png")).getImage();
		
		Image image1 = new ImageIcon(this.getClass().getResource("/loginTireIcon.png")).getImage();
		JLabel pageLbl2 = new JLabel("");
		pageLbl2.setIcon(new ImageIcon(image1));
		pageLbl2.setBounds(479, 0, 71, 93);
		getContentPane().add(pageLbl2);
		
		JLabel pageLbl3 = new JLabel("");
		pageLbl3.setIcon(new ImageIcon(image1));
		pageLbl3.setBounds(435, 0, 61, 83);
		getContentPane().add(pageLbl3);
		
		button = new JButton(" Add Tire Brand");
		button.setIcon(new ImageIcon(newTireIcon));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTireBrand();
			}
		});
		button.setForeground(new Color(51, 204, 51));
		button.setBounds(197, 129, 154, 36);
		contentPane.add(button);
	}
	
	
	/**************************************************************BUTTON LOGIC & Methods************************************************/

	private void addTireBrand(){
//		try {
//			
//			Integer.parseInt(brandTxtField.getText());
//			JOptionPane.showMessageDialog(null, "Invalid Tire Brand");
//			brandTxtField.setText("");
//			
//			
//		} catch (Exception e2) {
//			// TODO: handle exception
//			if(!brandTxtField.getText().matches("\\S+")){
//				JOptionPane.showMessageDialog(null, "Please Input a Tire Brand");
//				brandTxtField.setText("");
//			}
//			else{
//			int input = JOptionPane.showConfirmDialog(null, "Add Tire Brand " + brandTxtField.getText() + "?");
//			if(input == 0){
//				String tireToBeInserted = brandTxtField.getText().toUpperCase();
//				if(InsertTire.getTireList().contains(tireToBeInserted)){
//					JOptionPane.showMessageDialog(null, "Tire Brand Already Exists");
//
//				}
//				else{
//					InsertTire.getTireList().add(tireToBeInserted);
//					Collections.sort(InsertTire.getTireList());
//				}
//			}
//			}
//			
//			
//			
		try{
			if(brandTxtField.getText().matches("^[a-z A-Z]+$")){				
				String tireToBeInserted = brandTxtField.getText().trim().toUpperCase();
				//Check if it's already in the list
				if(InsertTire.getTireList().contains(tireToBeInserted)){
					JOptionPane.showMessageDialog(null, "Tire Brand " +  brandTxtField.getText() + " Already Exists");
					brandTxtField.setText("");
				}
				//else insert it and put it in the sorted order
				else{
					int input = JOptionPane.showConfirmDialog(null, "Add Tire Brand: " + brandTxtField.getText() + "?");
					if(input == 0){
						//InsertTire.updateTireBrandFile(tireToBeInserted);
						dispose();
						InsertTire.doesFileExist();
						InsertTire.updateTireBrandFile(tireToBeInserted);
						InsertTire insertTire = new InsertTire();
						insertTire.setVisible(true);
						
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(null, "Please Input a Valid Tire Brand");
				brandTxtField.setText("");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			String msg = e.toString() + " \n(Add tire brand)";
 			JOptionPane.showMessageDialog(null, msg);
 			e.printStackTrace();
		}
			
	
			
		
	}
}
