/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
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
        startMenu = new PAStartMenu();
        toolBar = new PADrawingKit();
        mainPanel = new PAMainFrame(rootView);
        layerPanel = new PALayerPanel();
        inspectFrame = new PAInspectFrame(rootView);
    }

    /**
     * customization of PARootView
     */
    private void customize()
    {
        rootView.setOpaque(false);
        rootView.setSize(userScreenSize);
//        rootView.add(startMenu);
        rootView.add(toolBar);
        rootView.add(mainPanel);
        rootView.add(layerPanel);
        rootView.setVisible(true);
    }

    /**
     * settings for PARootView
     */
    private void setUpRootView()
    {
        add(rootView);
        setJMenuBar(menuBar);
        setSize(new Dimension(PASystem.getScreenDimension().width, PASystem.getScreenDimension().height));
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

}
