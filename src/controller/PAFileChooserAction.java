/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PADrawingKit;
import gui.PAMainFrame;
import gui.PAStartMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyVetoException;
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
//    private int svgWidth;
//    private int svgHeight;
    public BufferedImage svgImage;
    private JDesktopPane parent;
    private JFileChooser fileChooser;
    private JInternalFrame frame;
//    private PASVGImport svgImport;
    private PAStartMenu startMenu;
    private String fileName;
    private Document svgDoc;

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

    public PAFileChooserAction(JDesktopPane parent, String fileName)
    {
        this.fileName = fileName;
        this.parent = parent;

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

            fileName = selectedFile.getName();
            svgDoc = PASVGImport.processFiletoDoc(selectedFile);
            boolean check = false;

            if (svgDoc != null)
            {
                PASVGContainer svgContainer = setUpContainer(svgDoc, selectedFile);
                PAMainFrame svgDisplay = new PAMainFrame(parent, svgContainer, fileName);

                try
                {
                    svgDisplay.setSelected(true);
                }
                catch (PropertyVetoException ex)
                {
                    System.err.println(ex.getMessage());
                }

                parent.add(svgDisplay);
                svgDisplay.toFront();

                if (startMenu != null)
                {
                    startMenu.setVisible(false);
                    startMenu.dispose();
                }

                for (int i = 0; i < parent.getComponentCount(); i++)
                {
                    if (parent.getComponent(i) instanceof PADrawingKit)
                    {
                        check = true;
                    }
                }
                if (check == false)
                {
                    PADrawingKit drawingKit = new PADrawingKit(svgDisplay);
                    parent.add(drawingKit);
                }

            }
        }

        // need to do for both Cancel and Open button.
        frame.setVisible(false);
        frame.dispose();
        PAMenuAction.OpenFile.fcInternal = null;
    }

    public PASVGContainer setUpContainer(Document svgDoc, File svgFile)
    {
        Node svgNode = svgDoc.getElementsByTagName("svg").item(0);
        PASVGTag svgTag = new PASVGTag(svgNode);
//        svgWidth = (int) svgTag.getWidth();
//        svgHeight = (int) svgTag.getHeight();
        LinkedList<PASVGElement> elementCollection = PASVGImport.readSVGElements(svgDoc);
        PASVGContainer svgContainer = new PASVGContainer(svgFile, svgTag, fileName, elementCollection);

        return svgContainer;
    }

}
