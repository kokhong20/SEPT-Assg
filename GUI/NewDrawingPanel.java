package GUI;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import Model.Coloring;

public class NewDrawingPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 607136078604036258L;
	JButton rectButton, circButton, lineButton;
	
	public NewDrawingPanel() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout());
		addButtons(this);
	}
	
	private void addButtons(NewDrawingPanel panel)
	{
		// Uncomment this when image resources are added
        /*try {
        BufferedImage wp = null;
        wp = ImageIO.read(this.getClass().getResource("/resources/drawIcon/rect.png"));
		rectButton = new JButton(new ImageIcon(wp));
        wp = ImageIO.read(this.getClass().getResource("/resources/drawIcon/circ.png"));
		circButton = new JButton(new ImageIcon(wp));
        wp = ImageIO.read(this.getClass().getResource("/resources/drawIcon/line.png"));
		lineButton = new JButton(new ImageIcon(wp));
	    }
	    catch(IOException ioe) {
	        
	    }*/
		
		// Comment this when image resources are added
		rectButton = new JButton("rect");
		circButton = new JButton("circle");
		lineButton = new JButton("line");
		
		panel.add(rectButton);
		panel.add(circButton);
		panel.add(lineButton);
	}
	
	public String getButtonDrawing()
	{
		return "";
	}

}
