package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;

import Model.SVGReader;
import Model.SVGRender;

public class FileChooserAction implements ActionListener
{
	private JDesktopPane desktopPane;
	private JInternalFrame fcInternal;
	private JFileChooser fileChooser;
	
	public FileChooserAction(JDesktopPane desktopPane, JInternalFrame fcInternal, JFileChooser fileChooser)
	{
		this.desktopPane = desktopPane;
		this.fcInternal = fcInternal;
		this.fileChooser = fileChooser;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String path;
		
		if (e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION)) 
		{
			this.fcInternal.setVisible(false);
			this.fcInternal.dispose();
		}
		
		try
		{
			File selectedFile = this.fileChooser.getSelectedFile();
			path = this.fileChooser.getCurrentDirectory().toString() ;
			
			this.fcInternal.setVisible(false);
			this.fcInternal.dispose();
			
			SVGRender render = new SVGRender(new SVGReader(), FileHandle.setPath(path, selectedFile));
			JScrollPane scrollPane = new JScrollPane(render, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setBounds(new Rectangle(10,10,100,100));
			render.setPreferredSize(render.getPreferredSize());
			JInternalFrame svgInternal = new JInternalFrame(selectedFile.getName(), true, true, true, true);
			svgInternal.add(scrollPane, BorderLayout.CENTER);
			svgInternal.pack();
			this.desktopPane.add(svgInternal);
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
