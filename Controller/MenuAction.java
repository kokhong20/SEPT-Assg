package Controller;

import GUI.DesktopPane;
import Model.SVGRender;
import Model.SVGReader;

import java.io.File;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
		// About software : version and etc
		else if (e.getSource() == DesktopPane.newItem) {
				
		}
		// About software : version and etc
		else if (e.getSource() == DesktopPane.editItem) {
				
		}
		// Save SVG
		else if (e.getSource() == DesktopPane.saveItem) {
		
		}
		// Save as SVG
		else if (e.getSource() == DesktopPane.saveAsItem) {

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
							
							if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) 
							{
								internal.setVisible(false);
								internal.dispose();
							}
							
							try
							{
								File selectedFile = fileChooser.getSelectedFile();
								path = fileChooser.getCurrentDirectory().toString() ;
								
								internal.setVisible(false);
								internal.dispose();
								
								SVGRender render = new SVGRender(new SVGReader(), FileHandle.setPath(path, selectedFile));
								JScrollPane scrollPane = new JScrollPane(render, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
								scrollPane.setBounds(new Rectangle(10,10,100,100));
								render.setPreferredSize(render.getPreferredSize());
								JInternalFrame svgInternal = new JInternalFrame(selectedFile.getName(), true, true, true, true);
								svgInternal.add(scrollPane, BorderLayout.CENTER);
								svgInternal.pack();
								DesktopPane.desktopPane.add(svgInternal);
								svgInternal.setVisible(true);
								svgInternal.setSize(render.getPreferredSize());
								
								Toolkit toolkit =  Toolkit.getDefaultToolkit ();
								if((toolkit.getScreenSize().height <= render.getPreferredSize().height)
										|| (toolkit.getScreenSize().width <= render.getPreferredSize().width))
								{
									try 
									{
										svgInternal.setMaximum(true);
									} 
									catch (PropertyVetoException pve) {
										// TODO Auto-generated catch block
										pve.printStackTrace();
									}
								}
								
								else if(svgInternal.getSize().equals(new Dimension(0,0)))
								{
									svgInternal.setSize(500,500);
								}
							}
							
							catch (NullPointerException ex)
							{
								System.out.println("Cancel Button");
							}
						}
					}
			);
		}
	}
}