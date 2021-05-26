import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolbarDemo {
	 
	          JMenu menu, submenu;  
	          JMenuItem i1, i2, i3, i4, i5;  
	          public void MenuExample(){  
	          JFrame f= new JFrame("Menu and MenuItem Example");  
	          JMenuBar mb=new JMenuBar();  
	          menu=new JMenu("Menu");  
	          submenu=new JMenu("Sub Menu");  
	          i1=new JMenuItem("Item 1");  
	          i2=new JMenuItem("Item 2");  
	          i3=new JMenuItem("Item 3");  
	          i4=new JMenuItem("Item 4");  
	          i5=new JMenuItem("Item 5");  
	          menu.add(i1); menu.add(i2); menu.add(i3);  
	          submenu.add(i4); submenu.add(i5);  
	          menu.add(submenu);  
	          mb.add(menu);  
	          f.setJMenuBar(mb);  
	          f.setSize(400,400);  
	          f.setLayout(null);  
	          f.setVisible(true);  
	}  

    public static void main(String[] args) {
    	ToolbarDemo demo = new ToolbarDemo();
    	demo.MenuExample();
        final JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(600, 400));
        final JToolBar toolBar = new JToolBar();

        //Create the popup menu.
        final JPopupMenu popup = new JPopupMenu();
        popup.add(new JMenuItem(new AbstractAction("Option 1") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Option 1 selected");
            }
        }));
        popup.add(new JMenuItem(new AbstractAction("Option 2") {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Option 2 selected");
            }
        }));

        final JButton button = new JButton("Options");
        button.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        toolBar.add(button);

        frame.getContentPane().add(toolBar, BorderLayout.NORTH);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}