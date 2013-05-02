/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import model.PASystem;

/**
 *
 * @author LiHao
 */
public class PARootView extends JFrame
{
    private Dimension userScreenSize;
    private JDesktopPane rootView;
    private PAMenuBar menuBar;
    private PAStartMenu startMenu;
    private PADrawingKit toolBar;
    private PAMainFrame mainPanel;
    private PALayerPanel layerPanel;
    private PANewFileSetting newFileSetting;
    private PAInspectFrame inspectFrame;

    /**
     * constructor to create PARootView
     */
    public PARootView()
    {
        PASystem.setLookandFeel();
        initialize();
        customize();
        setUpRootView();
    }

    /**
     * initialization of direct child items in PARootView
     */
    private void initialize()
    {
        rootView = new JDesktopPane();
        menuBar = new PAMenuBar(rootView);
        startMenu = new PAStartMenu(rootView);
        //toolBar = new PADrawingKit();
        //mainPanel = new PAMainFrame(rootView);
        //layerPanel = new PALayerPanel();
        //newFileSetting = new PANewFileSetting();
        //inspectFrame = new PAInspectFrame(rootView);
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
