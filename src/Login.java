import java.awt.EventQueue;
import java.awt.Image;
import java.sql.Connection;
import javax.swing.*;
import java.sql.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	JFrame getFrame(){return frame;}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection connection = null;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JLabel pageLbl;
	private JLabel lblNewLabel;
	private JLabel label;
	private JLabel pageLbl3;
	static Boolean isUsernameSet=false;
	static String userName;
	
	
	
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection = SqliteConnection.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 153, 255));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userNm = new JLabel("Username");
		userNm.setForeground(Color.BLACK);
		userNm.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		userNm.setBounds(168, 95, 94, 16);
		frame.getContentPane().add(userNm);
		
		JLabel pssWrd = new JLabel("Password");
		pssWrd.setForeground(Color.BLACK);
		pssWrd.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		pssWrd.setBounds(168, 150, 71, 16);
		frame.getContentPane().add(pssWrd);
		
		userNameField = new JTextField();
		userNameField.setBounds(251, 86, 130, 35);
		frame.getContentPane().add(userNameField);
		userNameField.setColumns(10);
		
		JButton btnNewButton = new JButton("      Login");
		btnNewButton.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		Image image = new ImageIcon(this.getClass().getResource("/loginButtonIcon.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(image));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from EmployeeInfo where Username=? and password=?";
					PreparedStatement pStatement = connection.prepareStatement(query);
					String hashedpassword = PasswordHash.hashPassword(passwordField.getText());
					pStatement.setString(1, userNameField.getText().trim());
					//pStatement.setString(2, passwordField.getText());
					pStatement.setString(2, hashedpassword);

					
					ResultSet rSet = pStatement.executeQuery();
					int count = 0;
					while(rSet.next()){
						count = count + 1;
					}
					if(count==1){
						//JOptionPane.showMessageDialog(null, "Username and Password is correct");
						frame.dispose();
						isUsernameSet = true;
						userName = userNameField.getText();
						Tires employeInfo = new Tires();
						employeInfo.setVisible(true);
					}
					else if(count > 1){
						JOptionPane.showConfirmDialog(null, "Duplicate name and Password");
					}
					else{ 
						JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
					}
					rSet.close();
					pStatement.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
			
			}
		});
		btnNewButton.setBounds(266, 200, 113, 29);
		frame.getContentPane().add(btnNewButton);
		
		//Enabling us to use the enter key as well as the login button to 'submit' password and username
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					try {
						String query = "select * from EmployeeInfo where Username=? and password=?";
						String hashedpassword = PasswordHash.hashPassword(passwordField.getText());
						PreparedStatement pStatement = connection.prepareStatement(query);
						pStatement.setString(1, userNameField.getText().trim());
						//pStatement.setString(2, passwordField.getText());
						pStatement.setString(2, hashedpassword);
						
						ResultSet rSet = pStatement.executeQuery();
						int count = 0;
					
						while(rSet.next()){
							count = count + 1;
						}
						if(count==1){
							//JOptionPane.showMessageDialog(null, "Username and Password is correct");
							frame.dispose();
							isUsernameSet = true;
							userName = userNameField.getText();
							Tires employeInfo = new Tires();
							employeInfo.setVisible(true);
						}
						else if(count > 1){
							JOptionPane.showConfirmDialog(null, "Duplicate name and Password");
						}
						else{ 
							JOptionPane.showMessageDialog(null, "Username or Password is incorrect");
						}
						rSet.close();
						pStatement.close();
						
					} catch (Exception e2) {
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, e2);
					}
				}
				
				
				
				
			}
		});
		passwordField.setBounds(251, 141, 130, 35);
		frame.getContentPane().add(passwordField);
		
		pageLbl = new JLabel("");
		Image image1 = new ImageIcon(this.getClass().getResource("/loginTireIcon.png")).getImage();
		pageLbl.setIcon(new ImageIcon(image1));
		pageLbl.setBounds(0, -21, 71, 132);
		frame.getContentPane().add(pageLbl);
		
		lblNewLabel = new JLabel("Chicago Tires Inventory System");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 19));
		lblNewLabel.setBounds(111, 17, 333, 35);
		frame.getContentPane().add(lblNewLabel);
		
		label = new JLabel("Chicago Tires LLC");
		label.setFont(new Font("Kokonor", Font.BOLD, 16));
		label.setBounds(0, 251, 133, 27);
		frame.getContentPane().add(label);
		
		JLabel pageLbl2 = new JLabel("");
		pageLbl2.setIcon(new ImageIcon(image1));
		pageLbl2.setBounds(64, 47, 71, 120);
		frame.getContentPane().add(pageLbl2);
		
		pageLbl3 = new JLabel("");
		pageLbl3.setIcon(new ImageIcon(image1));
		pageLbl3.setBounds(10, 127, 61, 102);
		frame.getContentPane().add(pageLbl3);
		
		
	}
	
}
