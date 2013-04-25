/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PAMainFrame;
import gui.PAStartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import model.PASVGContainer;
import model.PASVGElement;
import model.PASVGImport;
import model.PASVGTag;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author LiHao
 */
public class PAFileChooserAction implements ActionListener
{
    private JDesktopPane parent;
    private JFileChooser fileChooser;
    private JInternalFrame frame;
    private PASVGImport svgImport;
    private PAStartMenu startMenu;

    /**
     *
     * Contructor for file chooser's action.
     *
     * @param fileChooser pass existing file chooser.
     * @param frame internal frame which store file chooser.
     */
    public PAFileChooserAction(JDesktopPane parent, JFileChooser fileChooser, JInternalFrame frame)
    {
        this.parent = parent;
        this.fileChooser = fileChooser;
        this.frame = frame;
    }
    
    public PAFileChooserAction(JDesktopPane parent, PAStartMenu startMenu, JFileChooser fileChooser, JInternalFrame frame)
    {
        this.parent = parent;
        this.fileChooser = fileChooser;
        this.frame = frame;
        this.startMenu = startMenu;
    }

    /**
     * action in file chooser (Cancel and Open button)
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Open Button Action
        if (!e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
        {
            File selectedFile = fileChooser.getSelectedFile();
            Document svgDoc = PASVGImport.processFiletoDoc(selectedFile);
            LinkedList<PASVGElement> elementCollection = PASVGImport.readSVGElements(svgDoc);
            Node svgNode = svgDoc.getElementsByTagName("svg").item(0);
            PASVGTag svgTag = new PASVGTag(svgNode);
            PASVGContainer svgContainer = new PASVGContainer(svgTag, elementCollection);
            PAMainFrame svgDisplay = new PAMainFrame(parent, svgContainer);
            parent.add(svgDisplay);
            
            if(startMenu != null)
            {
                startMenu.setVisible(false);
                startMenu.dispose();
            }
        }

        // need to do for both Cancel and Open button.
        frame.setVisible(false);
        frame.dispose();
    }

}
