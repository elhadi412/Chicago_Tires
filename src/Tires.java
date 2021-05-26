import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tires extends JFrame {

	private JPanel contentPane;
	//private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tires frame = new Tires();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection = null;
	private JTextField txtPlaceholder;
	private JTextField numOfTires;
	
	
	/**
	 * Create the frame.
	 */
	public Tires() {
		connection = SqliteConnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 844, 749);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 153, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 138, 844, 472);
		contentPane.add(scrollPane);
		scrollPane.setBackground(new Color(0, 153, 255));
		
		table_1 = new JTable();
		table_1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		scrollPane.setViewportView(table_1);
		table_1.setBackground(Color.LIGHT_GRAY);
		table_1.setRowHeight(50);
		table_1.getTableHeader().setFont(new Font("Arial",Font.BOLD,14));
		table_1.setSize(1000,1000);

		

		numOfTires = new JTextField();
		numOfTires.setBounds(711, 622, 73, 38);
		numOfTires.setEditable(false);
		contentPane.add(numOfTires);
		numOfTires.setColumns(10);
		
		
		//TableColumnModel columnModel = table_1.getColumnModel();
		//columnModel.getColumn(0).setPreferredWidth(100);
		
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(51, 153, 102));
		searchBtn.setBackground(new Color(51, 153, 102));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				try {
					
					String query = "select * from Inventory where Size = ?";
					PreparedStatement pStatement = connection.prepareStatement(query);
					pStatement.setString(1,txtPlaceholder.getText());
					ResultSet rSet = pStatement.executeQuery();				

					
					table_1.setModel(DbUtils.resultSetToTableModel(rSet));	
					table_1.getColumnModel().getColumn(table_1.getColumnCount()-1).setPreferredWidth(150);



					if(table_1.getRowCount() == 0){
						JOptionPane.showMessageDialog(null, txtPlaceholder.getText() + " not found.");
					}
			
					
					int tireCount = 0;
						for(int row=0; row<table_1.getRowCount(); row++){

							if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Single")){
								tireCount++;
							}
							if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Set")){
								tireCount+=4;
							}
							if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Pair")){
								tireCount+=2;
							}
						}
						numOfTires.setText(Integer.toString(tireCount));

						System.out.println("Count: " + tireCount);
						tireCount = 0;
					
					rSet.close();
					pStatement.close();
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		});

		
		
		
		
		
		
		searchBtn.setBounds(390, 85, 107, 35);
		contentPane.add(searchBtn);
		
		JLabel tireInstck = new JLabel("Chicago Tires LLC");
		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		tireInstck.setIcon(new ImageIcon(image));
		tireInstck.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 17));
		tireInstck.setBounds(0, 6, 195, 38);
		contentPane.add(tireInstck);
		
		txtPlaceholder = new JTextField();
		

		JLabel validationTxt = new JLabel("");
		validationTxt.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		validationTxt.setForeground(new Color(255, 51, 0));
		validationTxt.setBounds(282, 100, 245, 29);
		contentPane.add(validationTxt);
		
		
		txtPlaceholder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				String value = txtPlaceholder.getText();
			
				if((e.getKeyChar() >= '0' && e.getKeyChar() <= '9') || e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					txtPlaceholder.setEditable(true);
					validationTxt.setText(" ");					
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Please Enter Numbers ONLY");
					txtPlaceholder.setText("");

				}
			}
		});

		txtPlaceholder.setBackground(new Color(204, 204, 204));
		txtPlaceholder.setBounds(87, 85, 291, 38);
		contentPane.add(txtPlaceholder);
		txtPlaceholder.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Chicago Tires LLC");
		lblNewLabel.setFont(new Font("Kokonor", Font.BOLD, 16));
		lblNewLabel.setBounds(711, 700, 133, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterTireSize = new JLabel("Tire Size:");
		lblEnterTireSize.setForeground(Color.BLACK);
		lblEnterTireSize.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblEnterTireSize.setBounds(6, 91, 78, 25);
		contentPane.add(lblEnterTireSize);
		
		JButton btnAddNewTire = new JButton("  Add Tire");
		btnAddNewTire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				InsertTire insertTire = new InsertTire();
				insertTire.setVisible(true);
			}
		});
		
		Image newTireIcon = new ImageIcon(this.getClass().getResource("/addTireBetter.png")).getImage();
		btnAddNewTire.setIcon(new ImageIcon(newTireIcon));

		btnAddNewTire.setForeground(new Color(51, 153, 102));
		btnAddNewTire.setBackground(new Color(51, 153, 102));
		btnAddNewTire.setBounds(6, 664, 107, 38);
		contentPane.add(btnAddNewTire);
		
		JButton btnRemoveTire = new JButton("  Remove Tire");
		btnRemoveTire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				InsertTire obj = new InsertTire();
				obj.setVisible(false);
				obj.dispose();
				Delete delete = new Delete();
				delete.getFrame().setVisible(true);
			}
		});
		Image rmvTireIcon = new ImageIcon(this.getClass().getResource("/removeTire.png")).getImage();
		btnRemoveTire.setIcon(new ImageIcon(rmvTireIcon));
		btnRemoveTire.setForeground(new Color(255, 51, 51));
		btnRemoveTire.setBackground(new Color(51, 153, 102));
		btnRemoveTire.setBounds(125, 664, 121, 38);
		contentPane.add(btnRemoveTire);
		
		JButton btnLoadAllTires = new JButton("Load All Tires");
		Image imageLoad = new ImageIcon(this.getClass().getResource("/reload (1).png")).getImage();
		btnLoadAllTires.setIcon(new ImageIcon(imageLoad));

		btnLoadAllTires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				txtPlaceholder.setText("");
				String query = "select * from Inventory";
				PreparedStatement pStatement = connection.prepareStatement(query);
				ResultSet rSet = pStatement.executeQuery();
				
				table_1.setModel(DbUtils.resultSetToTableModel(rSet));
				table_1.getColumnModel().getColumn(table_1.getColumnCount()-1).setPreferredWidth(150);


				
				System.out.println ("ROWCOUNT " + table_1.getRowCount());
				
				int tireCount = 0;
				for(int row=0; row<table_1.getRowCount(); row++){
					if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Single")){
						tireCount++;
					}
					if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Set")){
						tireCount+=4;
					}
					if(table_1.getValueAt(row, table_1.getColumnCount()-2).equals("Pair")){
						tireCount+=2;
					}
				}
				
				numOfTires.setText(Integer.toString(tireCount));

				
				System.out.println("Count: " + tireCount);
				tireCount = 0;
				
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
			
		});
		btnLoadAllTires.setForeground(new Color(51, 153, 102));
		btnLoadAllTires.setBackground(new Color(51, 153, 102));
		btnLoadAllTires.setBounds(257, 664, 133, 38);
		contentPane.add(btnLoadAllTires);
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Tires");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(796, 626, 78, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPlaceholder.setText("");
			}
		});
		btnClear.setForeground(new Color(255, 0, 51));
		btnClear.setBackground(new Color(51, 153, 102));
		btnClear.setBounds(509, 85, 107, 35);
		contentPane.add(btnClear);
		
		JButton btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.getFrame().setVisible(true);
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(746, 30, 71, 35);
		contentPane.add(btnNewButton);
		
		JButton btnExportToExcel = new JButton("Export to Excel");
		btnExportToExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File("data.xlsx");
				Desktop desktop = Desktop.getDesktop();
				try {
					desktop.open(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnExportToExcel.setBounds(402, 664, 127, 38);
		contentPane.add(btnExportToExcel);
		
		JLabel user = new JLabel("USER");
		user.setForeground(Color.WHITE);
		user.setBounds(784, 6, 107, 16);
//		System.out.println("USERNAMESET: " + Login.isUsernameSet);
//		System.out.println("USERNAME: " + Login.userName);
		if(Login.isUsernameSet){
			user.setText(Login.userName);
		}
		contentPane.add(user);
		
		JLabel lblUser = new JLabel("USER:");
		lblUser.setForeground(Color.WHITE);
		lblUser.setBounds(746, 6, 40, 16);
		contentPane.add(lblUser);
		
		
	}
}
