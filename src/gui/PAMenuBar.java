/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author LiHao
 */
public class PAMenuBar extends JMenuBar
{
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu imageMenu;
    private JMenu viewMenu;
    private JMenu helpMenu;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem saveFile;
    private JMenuItem saveAsFile;
    
    public PAMenuBar()
    {
        initMenu();
        initMenuItem();
        addMenuItem();
        setUpMenuBar();
    }
    
    private void initMenu()
    {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        imageMenu = new JMenu("Image");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");
    }
    
    private void initMenuItem()
    {
        // File's MenuItem
        newFile = new JMenuItem("New...");
        openFile = new JMenuItem("Open...");
        saveFile = new JMenuItem("Save");
        saveAsFile = new JMenuItem("Save As...");
    }
    
    private void addMenuItem()
    {
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
    }
    
    private void setUpMenuBar()
    {
        add(fileMenu);
        add(editMenu);
        add(imageMenu);
        add(viewMenu);
        add(helpMenu);
    }
}
