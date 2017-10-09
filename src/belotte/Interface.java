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
		frame.setSize(500, 250);
		frame.setVisible(true);
	}
}
