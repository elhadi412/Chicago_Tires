import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;


import javax.swing.JButton;

public class Delete {

	private static JFrame frame;
	private JTextField uniqueTxtFLD;
	private JLabel lblNewLabel;
	int queryResult = 1;


	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		Tires.setTheLookAndFeel();
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Delete window = new Delete();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	
	Connection connection = null;
	public Delete() {
		initialize();
	}
	
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		connection = SqliteConnection.dbConnector();
				
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 153, 255));
		frame.setBounds(100, 100, 350, 208);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		uniqueTxtFLD = new JTextField();
		uniqueTxtFLD.setForeground(new Color(0, 153, 255));
		uniqueTxtFLD.setBounds(147, 58, 88, 38);
		frame.getContentPane().add(uniqueTxtFLD);
		uniqueTxtFLD.setColumns(10);
		
		uniqueTxtFLD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					deleteTires();
				}
			}
		});

		
		JLabel tiresInStockLbl = new JLabel("Delete Tires");
		tiresInStockLbl.setFont(new Font("Microsoft Sans Serif", Font.BOLD | Font.ITALIC, 20));
		tiresInStockLbl.setBounds(98, 0, 180, 38);
		frame.getContentPane().add(tiresInStockLbl);

		Image image = new ImageIcon(this.getClass().getResource("/tireIcon.png")).getImage();
		tiresInStockLbl.setIcon(new ImageIcon(image));
		
		lblNewLabel = new JLabel("Enter ID");
		lblNewLabel.setBounds(88, 69, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBackground(Color.RED);
		btnDelete.setForeground(Color.RED);
		btnDelete.setBounds(157, 116, 71, 29);
		
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteTires();
			}
		});
		
		frame.getContentPane().add(btnDelete);
		
		JLabel label = new JLabel("Chicago Tires LLC");
		label.setFont(new Font("Kokonor", Font.BOLD, 16));
		label.setBounds(6, 140, 210, 27);
		frame.getContentPane().add(label);
	}
		
	
	
	
	/**************************************************************BUTTON LOGIC & Methods************************************************/

	public static JFrame getFrame(){
		return frame;
	}
	
	private void deleteTires(){
		// TODO Auto-generated method stub
		String query = "DELETE FROM Inventory WHERE ID = ?";
		//String query__ = "Select * from Inventory where ID = ?";
		try{
		Integer.parseInt(uniqueTxtFLD.getText());
		PreparedStatement pStatement = connection.prepareStatement(query);

		//1=no, 0=yes, 2=cancel
		String iD = uniqueTxtFLD.getText();
		int input = JOptionPane.showConfirmDialog(null, "<html> Delete Tire ID <b><u>" + iD + " </u></b>?</html>");
		if(input == 0){
		pStatement.setInt(1, Integer.parseInt(uniqueTxtFLD.getText()));
		int result = pStatement.executeUpdate();
		if (result == 0){
			queryResult=0;
			throw new Exception();
		}
		JOptionPane.showMessageDialog(null, "Tire ID " + uniqueTxtFLD.getText() + " removed.");
		BackUp backUp = new BackUp();
		backUp.export();
		frame.dispose();
		Tires.btnLoadAllTires.doClick();

		}
		

		}
		catch (Exception e1) {
			// TODO: handle exception
			if(queryResult == 0){
				JOptionPane.showMessageDialog(null, "No such ID in database");
			}
			else{
			JOptionPane.showMessageDialog(null, "Please Enter a Tire ID");
			}
			uniqueTxtFLD.setText("");
			//e1.printStackTrace();
		}
	}
}
