/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.BorderLayout;
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
    
    public PARootView()
    {
        PASystem.setLookandFeel();
        initialize();
        getScreenSize();
        customize();
        setUpRootView();
    }
    
    private void initialize()
    {
        rootView = new JDesktopPane();
        menuBar = new PAMenuBar();
        startMenu = new PAStartMenu();
        toolBar = new PADrawingKit();
        mainPanel = new PAMainFrame();
    }
    
    private void customize()
    {
        rootView.setOpaque(false);
        rootView.setSize(userScreenSize);
//        rootView.add(startMenu);
        rootView.add(toolBar);
        rootView.add(mainPanel);
        rootView.setVisible(true);
    }
    
    private void getScreenSize()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        userScreenSize = toolkit.getScreenSize();
    }
    
    private void setUpRootView()
    {
        add(rootView);
        setJMenuBar(menuBar);
        setSize(userScreenSize);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }
}
