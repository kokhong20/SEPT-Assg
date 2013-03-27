package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import GUI.InternalFrame;
import GUI.SVGDisplay;
import GUI.ViewMenu;
import Model.modelMain;

public class FileChooserAction implements ActionListener
{
	private JDesktopPane desktopPane;
	private JInternalFrame fcInternal;
	private JFileChooser fileChooser;
	private ViewMenu viewMenu;

	public FileChooserAction(JInternalFrame fcInternal, JFileChooser fileChooser)
	{
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
			return;
		}

		File selectedFile = this.fileChooser.getSelectedFile();
		path = this.fileChooser.getCurrentDirectory().toString() ;

		this.fcInternal.setVisible(false);
		this.fcInternal.dispose();

		modelMain mMain = new modelMain();
		SVGDisplay display = new SVGDisplay(new SVGRender(mMain.setPath(path, selectedFile)));
		display.setPreferredSize(display.getRender().getPreferredSize());
		InternalFrame svgInternal = new InternalFrame(this.desktopPane,display,selectedFile.getName());
		svgInternal.setViewMenu(viewMenu);
	}
	
	public void setParentPane(JDesktopPane desktopPane)
	{
		this.desktopPane = desktopPane;
	}
	
	public void activeViewMenu(ViewMenu viewMenu)
	{
		this.viewMenu = viewMenu;
	}
}
