/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
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
        rootView.setSize(PASystem.getScreenDimension());
//        rootView.add(startMenu);
        JInternalFrame fr = new JInternalFrame("");
        fr.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
        fr.add(toolBar);
        //fr.pack();
        fr.setMaximumSize(new Dimension(80,240));
        fr.setPreferredSize(new Dimension(80,240));
        fr.setMinimumSize(new Dimension(80,240));
        fr.setSize(new Dimension(80,240));
        fr.setVisible(true);
        rootView.add(fr);
        //rootView.add(toolBar);
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
        setSize(PASystem.getScreenDimension());
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

}
