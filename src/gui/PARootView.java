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

/**
 *
 * @author LiHao
 */
public class PARootView extends JFrame
{
    private Dimension userScreenSize;
    private JDesktopPane rootView;
    private PAStartMenu startMenu;
    private PADrawingKit toolBar;
    
    public PARootView()
    {
        initialize();
        getScreenSize();
        customize();
        setUpRootView();
    }
    
    private void initialize()
    {
        rootView = new JDesktopPane();
        startMenu = new PAStartMenu();
        toolBar = new PADrawingKit();
    }
    
    private void customize()
    {
        rootView.setOpaque(false);
        rootView.setSize(userScreenSize);
        rootView.add(startMenu);
        rootView.add(toolBar);
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
        setSize(userScreenSize);
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }
}
