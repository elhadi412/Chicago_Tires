import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Window;

import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;

public class Delete {

	private JFrame frame;
	private JTextField uniqueTxtFLD;
	private JLabel lblNewLabel;
	int queryResult = 1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete window = new Delete();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	Connection connection = null;
	public Delete() {
		initialize();
		getFrame();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = SqliteConnection.dbConnector();
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 153, 255));
		frame.setBounds(100, 100, 323, 164);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		uniqueTxtFLD = new JTextField();
		uniqueTxtFLD.setBounds(123, 50, 88, 38);
		frame.getContentPane().add(uniqueTxtFLD);
		uniqueTxtFLD.setColumns(10);
		
		uniqueTxtFLD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					Delete delete = new Delete();
					String query = "DELETE FROM Inventory WHERE ID = ?";
					//String query__ = "Select * from Inventory where ID = ?";
					try{
					Integer.parseInt(uniqueTxtFLD.getText());
					PreparedStatement pStatement = connection.prepareStatement(query);

					//1=no, 0=yes, 2=cancel
					int input = JOptionPane.showConfirmDialog(null, "Delete Tire ID " + uniqueTxtFLD.getText() + "?");
					if(input == 0){
					pStatement.setInt(1, Integer.parseInt(uniqueTxtFLD.getText()));
					int result = pStatement.executeUpdate();
					if (result == 0){
						queryResult=0;
						throw new Exception();
					}
					frame.dispose();
					JOptionPane.showMessageDialog(null, "Tire ID " + uniqueTxtFLD.getText() + " removed.");
					frame.dispose();
					
					BackUp backUp = new BackUp();
					backUp.export();
					}
					
		
					}
					catch (Exception e1) {
						// TODO: handle exception
						if(queryResult == 0){
							JOptionPane.showMessageDialog(null, "No such ID in database");
						}
						else{
						JOptionPane.showMessageDialog(null, "Please Enter a number");
						}
						uniqueTxtFLD.setText("");
						//e1.printStackTrace();
					}
					
				}
			}
		});

		
		JLabel tiresInStockLbl = new JLabel("Chicago Tires LLC");
		tiresInStockLbl.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 14));
		tiresInStockLbl.setBounds(6, 0, 171, 38);
		frame.getContentPane().add(tiresInStockLbl);

		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		tiresInStockLbl.setIcon(new ImageIcon(image));
		
		lblNewLabel = new JLabel("Enter ID");
		lblNewLabel.setBounds(64, 61, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(132, 100, 71, 29);
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Delete delete = new Delete();
				String query = "DELETE FROM Inventory WHERE ID = ?";
				//String query__ = "Select * from Inventory where ID = ?";
				try{
				Integer.parseInt(uniqueTxtFLD.getText());
				PreparedStatement pStatement = connection.prepareStatement(query);

				//1=no, 0=yes, 2=cancel
				int input = JOptionPane.showConfirmDialog(null, "Delete Tire ID " + uniqueTxtFLD.getText() + "?");
				if(input == 0){
				pStatement.setInt(1, Integer.parseInt(uniqueTxtFLD.getText()));
				int result = pStatement.executeUpdate();
				if (result == 0){
					queryResult=0;
					throw new Exception();
				}
				frame.dispose();
				JOptionPane.showMessageDialog(null, "Tire ID " + uniqueTxtFLD.getText() + " removed.");
				
				BackUp backUp = new BackUp();
				backUp.export();
				frame.dispose();
				}
				
	
				}
				catch (Exception e1) {
					// TODO: handle exception
					if(queryResult == 0){
						JOptionPane.showMessageDialog(null, "No such ID in database");
					}
					else{
					JOptionPane.showMessageDialog(null, "Please Enter a number");
					}
					uniqueTxtFLD.setText("");
					//e1.printStackTrace();
				}
			}
		});
		
		frame.getContentPane().add(btnDelete);
	}
	public JFrame getFrame(){
		return frame;
	}
}
