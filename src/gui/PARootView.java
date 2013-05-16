/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.w3c.dom.Document;

import controller.PAFileChooserAction;
import model.PASVGContainer;
import model.PASVGImport;
import model.PASystem;

/**
 * 
 * @author LiHao
 */
public class PARootView extends JFrame
{
    private JDesktopPane rootView;
    private PAMenuBar menuBar;
    private PAStartMenu startMenu;
    private String fileName;

    /**
     * constructor to create PARootView
     */
    public PARootView(String[] args)
    {
        PASystem.setLookandFeel();
        if (args.length == 1)
        {
            fileName = args[0];

            init();
        }
        else if (args.length > 1)
        {
            JOptionPane
                    .showMessageDialog(
                            null,
                            "Wrong command line argument entered. System will start in default configuration.",
                            "Wrong Command Line Argument",
                            JOptionPane.WARNING_MESSAGE);
            initialize();
            customize();
        }
        else
        {
            initialize();
            customize();

        }
        setUpRootView();
        /*
         * Alt+F4 doesn't close application, use this to closes application not
         * using exit function from menu bar
         */
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
    }

    /**
     * initialization of direct child items in PARootView
     */
    private void initialize()
    {
        rootView = new JDesktopPane();
        menuBar = new PAMenuBar(rootView);
        startMenu = new PAStartMenu(rootView);
        // toolBar = new PADrawingKit();
        // mainPanel = new PAMainFrame(rootView);
        // layerPanel = new PALayerPanel();
        // newFileSetting = new PANewFileSetting();
        // inspectFrame = new PAInspectFrame(rootView);
    }

    public void init()
    {

        File cmdFile = new File(fileName);
        Document svgDoc = PASVGImport.processFiletoDoc(cmdFile);
        if (svgDoc != null)
        {
            rootView = new JDesktopPane();
            menuBar = new PAMenuBar(rootView);
            PAFileChooserAction cmd = new PAFileChooserAction(rootView,
                    fileName);
            PASVGContainer svgContainer = cmd.setUpContainer(svgDoc, cmdFile);
            PAMainFrame svgDisplay = new PAMainFrame(rootView, svgContainer,
                    fileName);
            rootView.add(svgDisplay);
            PADrawingKit drawingKit = new PADrawingKit(svgDisplay);
            rootView.add(drawingKit);
            cust();
        }
        else
        {
            initialize();
            customize();
        }
    }

    public void cust()
    {
        rootView.setSize(PASystem.getScreenDimension());
        rootView.setVisible(true);
    }

    /**
     * customization of PARootView
     */
    private void customize()
    {
        rootView.setSize(PASystem.getScreenDimension());
        rootView.add(startMenu);
        rootView.setVisible(true);
    }

    private void designForMac()
    {
        if (PASystem.getCurrentOS().indexOf("mac") >= 0)
        {
            rootView.setOpaque(false);
            setBackground(new Color(0, 0, 0, 0));
        }
    }

    /**
     * settings for PARootView
     */
    private void setUpRootView()
    {
        add(rootView);
        setJMenuBar(menuBar);
        setSize(PASystem.getScreenDimension());
        setUndecorated(true);
        designForMac();
        setVisible(true);
    }
}
