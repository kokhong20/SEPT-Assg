package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JInternalFrame;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class DesktopPane extends JFrame {
	
	private JDesktopPane desktopPane;
	
	public DesktopPane() {
		
		super("Assignment");
		
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenuItem openItem = new JMenuItem("Open...");
		desktopPane = new JDesktopPane();
		
		fileMenu.add(openItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		add(desktopPane);
		
	 	openItem.addActionListener(
	 	
	 		new ActionListener() {
	 		
	 			public void actionPerformed(ActionEvent e) {
	 				
	 				final JInternalFrame internal = new JInternalFrame("Open", true, true, true, true);
					JFileChooser fileChooser = new JFileChooser();
					FileFilter fileFilter = new FileNameExtensionFilter("SVG files",".svg");
					fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					fileChooser.setFileFilter(fileFilter);
					
					internal.add(fileChooser, BorderLayout.CENTER);
					internal.pack();
					desktopPane.add(internal);
					internal.setVisible(true);
					
					fileChooser.addActionListener(
						
						new ActionListener() {
							
							public void actionPerformed(ActionEvent e) {
								
								if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {

									internal.setVisible(false);
									internal.dispose();
								}
							}
						}
					);
				}	
	 		}
		);
	}
}
