package Controller;

import GUI.DesktopPane;
import Model.SVGRender;
import Model.SVGReader;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuAction implements ActionListener 
{
	public void actionPerformed(ActionEvent e) {
		
		// Teach user basic functions on how to use program
		if (e.getSource() == DesktopPane.helpItem) {
		
		}
		// About software : version and etc
		else if (e.getSource() == DesktopPane.aboutItem) {
				
		}
		// Save SVG
		else if (e.getSource() == DesktopPane.saveItem) {
		
		}
		// Exit software
		else if (e.getSource() == DesktopPane.exitItem) {
			Object[] choices = {"Yes", "No"};
			int dialogButton = JOptionPane.showOptionDialog(null, "Are You Sure You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION, 
				JOptionPane.PLAIN_MESSAGE, null, choices, "Yes");
			if(dialogButton == 0)
			{
                System.exit(0);
			}
		}
		// Open new SVG
		else if (e.getSource() == DesktopPane.openItem) {
			
			final JInternalFrame internal = new JInternalFrame("Open", true, true, true, true);
			final JFileChooser fileChooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter("SVG files","svg");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fileChooser.setFileFilter(fileFilter);
			
			internal.add(fileChooser, BorderLayout.CENTER);
			internal.pack();
			DesktopPane.desktopPane.add(internal);
			internal.setVisible(true);
			
			fileChooser.addActionListener(
					
					new ActionListener() {
						
						public void actionPerformed(ActionEvent e) {
							
							String path;
							
							if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) {

								internal.setVisible(false);
								internal.dispose();
							}
							
							File selectedFile = fileChooser.getSelectedFile();
							
							/*if ((selectedFile == null)||(selectedFile.getName().equals(""))) {
								
								//internal.setVisible(false);
								
								internal.dispose();
								JOptionPane.showInternalMessageDialog(internal, "Invalid Name", "Invalid Name", JOptionPane.ERROR_MESSAGE);
							}*/
							
							internal.setVisible(false);
							internal.dispose();
							
							path = fileChooser.getCurrentDirectory().toString() ;
							//FileHandle.menuOpen(path, selectedFile);
							
							JInternalFrame svgInternal = new JInternalFrame(selectedFile.getName(), true, true, true, true);
							svgInternal.add(new SVGRender(new SVGReader(), FileHandle.setPath(path, selectedFile)), BorderLayout.CENTER);
							svgInternal.pack();
							DesktopPane.desktopPane.add(svgInternal);
							svgInternal.setVisible(true);
							svgInternal.setSize(400,400);
						}
					}
			);
		}
	}
}