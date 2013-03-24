package Controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
<<<<<<< HEAD
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
=======
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
>>>>>>> 019b8536711e2bce461b55a4a49cc69e0b703a2a

import Model.SVGReader;
import Model.SVGRender;
import Model.SVGSaver;

public class FileChooserAction implements ActionListener
{
	private JDesktopPane desktopPane;
	private JInternalFrame fcInternal;
	private JFileChooser fileChooser;
	private int keyMask;
	
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
			
			//Determine Key Mask
			if (System.getProperty("os.name").equals("Mac OS X"))
			{
				// Mac OS command
				keyMask = Event.META_MASK;
			}
			
			// Window OS
			else
			{
				keyMask = Event.CTRL_MASK;
			}
			
			// Zoom In Zoom Out 
			JMenuBar menuBar = new JMenuBar();
			JMenu viewMenu = new JMenu("View");
			JMenuItem zoomIn = new JMenuItem("Zoom In",KeyEvent.VK_EQUALS);
			zoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_EQUALS,keyMask));
			JMenuItem zoomOut = new JMenuItem("Zoom Out",KeyEvent.VK_MINUS);
			zoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,keyMask));
			JMenuItem backOriginalSize = new JMenuItem("Orginal Size",KeyEvent.VK_L);
			backOriginalSize.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,keyMask));
			JMenuItem backOrginalPosition = new JMenuItem("Orginal Position",KeyEvent.VK_K);
			backOrginalPosition.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K,keyMask));
			viewMenu.add(zoomIn);
			viewMenu.add(zoomOut);
			viewMenu.add(backOriginalSize);
			viewMenu.add(backOrginalPosition);
			menuBar.add(viewMenu);
			svgInternal.setJMenuBar(menuBar);
			
			ZoomInOutAction zoomAction = new ZoomInOutAction(render,zoomIn,zoomOut,backOriginalSize,backOrginalPosition);
			zoomIn.addActionListener(zoomAction);
			zoomOut.addActionListener(zoomAction);
			backOriginalSize.addActionListener(zoomAction);
			backOrginalPosition.addActionListener(zoomAction);
			
			PanAction panAction = new PanAction(render);
			render.addMouseListener(panAction);
			
			svgInternal.add(scrollPane, BorderLayout.CENTER);
			svgInternal.pack();
			this.desktopPane.add(svgInternal);
			svgInternal.setVisible(true);
			svgInternal.setSize(render.getPreferredSize());
			svgInternal.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
			svgInternal.addInternalFrameListener(new JInternalFrameAction(svgInternal, render));			
			
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

class JInternalFrameAction implements InternalFrameListener {

	private JInternalFrame internalFrame;
	private SVGRender render;
	
	public JInternalFrameAction(JInternalFrame svgInternal, SVGRender render) {
		// TODO Auto-generated constructor stub
		this.internalFrame = svgInternal;
		this.render = render;
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		Object[] choices = {"Yes", "No", "Cancel"};
		int dialogButton = JOptionPane.showOptionDialog(null, "Do you want to save before closing this frame?", "Save before closing", JOptionPane.YES_NO_OPTION, 
			JOptionPane.PLAIN_MESSAGE, null, choices, "Yes");
		
		if (dialogButton == 0)
		{
			System.out.println("save");
			new SVGSaver(render.getDrawings(), render.getPath());
			this.internalFrame.dispose();
		}
		else if(dialogButton == 1)
		{
			System.out.println("don't save");
			this.internalFrame.dispose();
		}
		else
		{
			System.out.println("do nothing");
		}
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
}

	
