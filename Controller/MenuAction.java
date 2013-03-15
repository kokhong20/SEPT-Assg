package Controller;

import GUI.DesktopPane;
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
		
		if (e.getSource() == DesktopPane.openItem) {
			
			final JInternalFrame internal = new JInternalFrame("Open", true, true, true, true);
			final JFileChooser fileChooser = new JFileChooser();
			FileFilter fileFilter = new FileNameExtensionFilter("SVG files",".svg");
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
							
							if ((selectedFile == null)||(selectedFile.getName().equals(""))) {
								
								//internal.setVisible(false);
								
								internal.dispose();
								JOptionPane.showInternalMessageDialog(internal, "Invalid Name", "Invalid Name", JOptionPane.ERROR_MESSAGE);
							}
							
							internal.setVisible(false);
							internal.dispose();
							

							path = fileChooser.getCurrentDirectory().toString() ;
							
							if(path.endsWith(":\\"))
							{
								path += selectedFile.getName();
							}
							else
							{
								path +=  "\\" + selectedFile.getName();
							}
							
							SVGReader reader = new SVGReader();
							reader.setDoc(path);
						}
					}
			);
		}
	}
}