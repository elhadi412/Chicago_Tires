import java.awt.EventQueue;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ColorUIResource;

import net.proteanit.sql.DbUtils;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.table.DefaultTableCellRenderer;


public class Tires extends JFrame {

	private JPanel contentPane;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Tires.setTheLookAndFeel();
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
	static JButton btnLoadAllTires;
	
	
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
		setSize(1033,797);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(0, 132, 1033, 478);
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
		numOfTires.setBounds(864, 664, 73, 38);
		numOfTires.setEditable(false);
		contentPane.add(numOfTires);
		numOfTires.setColumns(10);
		
		JButton searchBtn = new JButton("Search");
		searchBtn.setForeground(new Color(51, 153, 102));
		searchBtn.setBackground(new Color(51, 153, 102));
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		
		searchBtn.setBounds(550, 82, 107, 35);
		contentPane.add(searchBtn);
		
		JLabel tireInstck = new JLabel("Chicago Tires LLC");
		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		tireInstck.setIcon(new ImageIcon(image));
		tireInstck.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		tireInstck.setBounds(373, 6, 398, 38);
		contentPane.add(tireInstck);
		
		txtPlaceholder = new JTextField();
		txtPlaceholder.setForeground(new Color(0, 153, 255));
		txtPlaceholder.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {		
				if(e.getKeyCode() == KeyEvent.VK_ENTER){					
					search();
				}					
			}
		});

		txtPlaceholder.setBackground(Color.WHITE);
		txtPlaceholder.setBounds(247, 82, 291, 38);
		contentPane.add(txtPlaceholder);
		txtPlaceholder.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Chicago Tires LLC");
		lblNewLabel.setFont(new Font("Kokonor", Font.BOLD, 16));
		lblNewLabel.setBounds(860, 714, 183, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblEnterTireSize = new JLabel("Tire Size:");
		lblEnterTireSize.setForeground(Color.BLACK);
		lblEnterTireSize.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblEnterTireSize.setBounds(166, 88, 78, 25);
		contentPane.add(lblEnterTireSize);
		
		JButton btnAddNewTire = new JButton("  Add Tire");
		btnAddNewTire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addTire();
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
				removeTire();
			}
		});
		Image rmvTireIcon = new ImageIcon(this.getClass().getResource("/removeTire.png")).getImage();
		btnRemoveTire.setIcon(new ImageIcon(rmvTireIcon));
		btnRemoveTire.setForeground(new Color(255, 51, 51));
		btnRemoveTire.setBackground(new Color(51, 153, 102));
		btnRemoveTire.setBounds(125, 664, 163, 38);
		contentPane.add(btnRemoveTire);
		
		btnLoadAllTires = new JButton("Load All Tires");
		Image imageLoad = new ImageIcon(this.getClass().getResource("/reload (1).png")).getImage();
		btnLoadAllTires.setIcon(new ImageIcon(imageLoad));

		btnLoadAllTires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadAllTires();	
			}	
		});
		btnLoadAllTires.setForeground(new Color(51, 153, 102));
		btnLoadAllTires.setBackground(new Color(51, 153, 102));
		btnLoadAllTires.setBounds(294, 664, 133, 38);
		contentPane.add(btnLoadAllTires);
		
	
		
		JLabel lblNewLabel_1 = new JLabel("Tires");
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(949, 668, 78, 29);
		contentPane.add(lblNewLabel_1);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtPlaceholder.setText("");
			}
		});
		btnClear.setForeground(new Color(255, 0, 51));
		btnClear.setBackground(new Color(51, 153, 102));
		btnClear.setBounds(669, 82, 107, 35);
		contentPane.add(btnClear);
		
		JButton btnNewButton = new JButton("LOG OUT");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logOut();
			}
		});
		btnNewButton.setForeground(Color.RED);
		btnNewButton.setBounds(922, 23, 84, 35);
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
		btnExportToExcel.setBounds(439, 664, 127, 38);
		contentPane.add(btnExportToExcel);
		
		JLabel user = new JLabel("USER");
		user.setForeground(Color.WHITE);
		user.setBounds(960, 6, 107, 16);
//		System.out.println("USERNAMESET: " + Login.isUsernameSet);
//		System.out.println("USERNAME: " + Login.userName);
		if(Login.isUsernameSet){
			user.setText(Login.userName);
		}
		contentPane.add(user);
		
		JLabel lblUser = new JLabel("USER:");
		lblUser.setForeground(new Color(0, 0, 0));
		lblUser.setBounds(922, 6, 40, 16);
		contentPane.add(lblUser);
		
		
		JButton btnBackUpData = new JButton("  BACK UP DATA");
		btnBackUpData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				backUpDataFile();
			}
		});
		Image downloadIcon = new ImageIcon(this.getClass().getResource("/download (1).png")).getImage();
		btnBackUpData.setIcon(new ImageIcon(downloadIcon));
		btnBackUpData.setForeground(new Color(51, 153, 0));
		btnBackUpData.setBounds(8, 12, 154, 35);
		contentPane.add(btnBackUpData);
		
		
	}
	
	/**************************************************************BUTTON LOGIC & Methods************************************************/
	
	
	private static void backUpDataFile(){
		try {
			File file = new File("./data.xlsx");
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
			String homeDirectory = System.getProperty("user.home");
			FileOutputStream fileOutputStream = new FileOutputStream(homeDirectory + "/Downloads/data.xlsx");
			byte data[] = new byte[1024];
			int byteContent;
			while((byteContent = inputStream.read(data,0,1024)) != -1){
				fileOutputStream.write(data,0,byteContent);
			}
			inputStream.close();
			fileOutputStream.close();
			JOptionPane.showMessageDialog(null,"Successfully Backed up Data");	

			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,"Unable to Backup");	
			e.printStackTrace();

		}
	}
	
	private void setColumnWidt(JTable table){
		for(int i=0; i<table.getColumnModel().getColumnCount(); i++){
			if(i == 0){
				table.getColumnModel().getColumn(i).setPreferredWidth(100);
			}
			else if(i == 4){
				table.getColumnModel().getColumn(i).setPreferredWidth(200);
			}
			else{
				table.getColumnModel().getColumn(i).setPreferredWidth(300);
			}
			//Center align text
		      DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)table.getDefaultRenderer(Object.class);
		      renderer.setHorizontalAlignment( SwingConstants.CENTER );
		      ((DefaultTableCellRenderer)table.getTableHeader().getDefaultRenderer())
		      .setHorizontalAlignment(JLabel.CENTER);
			
		}
	}
	

	private void loadAllTires(){
		try {
			txtPlaceholder.setText("");
			String query = "select * from Inventory";
			PreparedStatement pStatement = connection.prepareStatement(query);
			ResultSet rSet = pStatement.executeQuery();
			
			table_1.setModel(DbUtils.resultSetToTableModel(rSet));
			setColumnWidt(table_1);
			//table_1.getColumnModel().getColumn(table_1.getColumnCount()-1).setPreferredWidth(150);
			
			System.out.println ("ROW COUNT " + table_1.getRowCount());
			
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
	
	
	private void search(){
		String value = txtPlaceholder.getText().trim();
		
		try {
			Integer.parseInt(value);
			
			String query = "select * from Inventory where Size = ?";
			PreparedStatement pStatement = connection.prepareStatement(query);
			pStatement.setString(1,txtPlaceholder.getText());
			ResultSet rSet = pStatement.executeQuery();				

			table_1.setModel(DbUtils.resultSetToTableModel(rSet));	
			setColumnWidt(table_1);

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
			}
			
		 catch (Exception e2) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Please Enter a Valid Tire Size");
			txtPlaceholder.setText("");
		 }
	}
	
	private void logOut(){
		dispose();
		Login login = new Login();
		login.getFrame().setVisible(true);
	}
	
	private void removeTire(){
		InsertTire obj = new InsertTire();
		obj.dispose();
		if( Delete.getFrame() != null){
			Delete.getFrame().dispose();
		}
		Delete delete = new Delete();
		delete.getFrame().setVisible(true);
	}
	
	private void addTire(){
		if(Delete.getFrame()!=null){
			Delete.getFrame().dispose();
		}
		dispose();
		InsertTire insertTire = new InsertTire();
		insertTire.setVisible(true);
	}
	
	public static void setTheLookAndFeel(){
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		} catch (UnsupportedLookAndFeelException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (ClassNotFoundException e2){
			e2.printStackTrace();
		}catch(InstantiationException e3){
			e3.printStackTrace();
			
		}catch (IllegalAccessException e4){
			e4.printStackTrace();
		}
	}
}
