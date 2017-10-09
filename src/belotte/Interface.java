package belotte;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Interface {
	
	private JLabel emptyLabel;
	
	public JLabel getEmptyLabel() {
		return emptyLabel;
	}

	public void setEmptyLabel(String string) {
		this.emptyLabel.setText(string);
	}

	
	
	public Interface(){
		JFrame frame = new JFrame("FrameDemo");
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		emptyLabel = new JLabel("label test");
        emptyLabel.setPreferredSize(new Dimension(175, 100));
		frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		JLayeredPane layeredPane;
		layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(300, 310));
		layeredPane.setBorder(BorderFactory.createTitledBorder(
		                                    "Move the Mouse to Move Duke"));
		frame.setSize(500, 250);
		frame.setVisible(true);
	}
}
