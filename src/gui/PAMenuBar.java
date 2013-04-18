/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PAMenuAction.ExitProgram;
import controller.PAMenuAction.NewFile;
import controller.PAMenuAction.OpenFile;
import controller.PAMenuAction.SaveAsFile;
import controller.PAMenuAction.SaveFile;
import javax.swing.JDesktopPane;
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
    private JMenuItem exitFile;
    private JMenuItem editEdit;
    private JMenuItem selectAllEdit;
    private JMenuItem deselectAllEdit;
    private JMenuItem cutEdit;
    private JMenuItem copyEdit;
    private JMenuItem pasteEdit;
    private JMenuItem deleteEdit;
    private JMenuItem imageSizeImage;
    private JMenuItem viewBoxImage;
    private JMenuItem zoomInView;
    private JMenuItem zoomOutView;
    private JMenuItem actualSizeView;
    private JDesktopPane parent;

    /**
     * constructor to create menu bar
     */
    public PAMenuBar(JDesktopPane parent)
    {
        this.parent = parent;

        initMenu();
        initMenuItem();
        addMenuItem();
        setUpMenuBar();
        addAction();
    }

    /**
     * initialize menu
     */
    private void initMenu()
    {
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        imageMenu = new JMenu("Image");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");
    }

    /**
     * initialize menu items
     */
    private void initMenuItem()
    {
        // File's MenuItem
        newFile = new JMenuItem("New...");
        openFile = new JMenuItem("Open...");
        saveFile = new JMenuItem("Save");
        saveAsFile = new JMenuItem("Save As...");
        exitFile = new JMenuItem("Exit");

        // Edit's MenuItem
        editEdit = new JMenuItem("Edit");
        selectAllEdit = new JMenuItem("Select All");
        deselectAllEdit = new JMenuItem("Deselect All");
        cutEdit = new JMenuItem("Cut");
        copyEdit = new JMenuItem("Copy");
        pasteEdit = new JMenuItem("Paste");
        deleteEdit = new JMenuItem("Delete");

        // Image's MenuItem
        imageSizeImage = new JMenuItem("Image Size");
        viewBoxImage = new JMenuItem("ViewBox");

        // View's MenuItem
        zoomInView = new JMenuItem("Zoom In");
        zoomOutView = new JMenuItem("Zoom Out");
        actualSizeView = new JMenuItem("Actual Size");

    }

    /**
     * add menu items to menu
     */
    private void addMenuItem()
    {
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.add(exitFile);

        editMenu.add(editEdit);
        editMenu.add(selectAllEdit);
        editMenu.add(deselectAllEdit);
        editMenu.add(cutEdit);
        editMenu.add(copyEdit);
        editMenu.add(pasteEdit);
        editMenu.add(deleteEdit);

        imageMenu.add(imageSizeImage);
        imageMenu.add(viewBoxImage);

        viewMenu.add(zoomInView);
        viewMenu.add(zoomOutView);
        viewMenu.add(actualSizeView);
    }

    /**
     *
     * add action to each menu item.
     */
    private void addAction()
    {
        NewFile newAction = new NewFile(parent);
        OpenFile openAction = new OpenFile(parent);
        SaveFile saveAction = new SaveFile(parent);
        SaveAsFile saveAsAction = new SaveAsFile(parent);
        ExitProgram exitAction = new ExitProgram(parent);
        newFile.setAction(newAction);
        openFile.setAction(openAction);
        saveFile.setAction(saveAction);
        saveAsFile.setAction(saveAsAction);
        exitFile.setAction(exitAction);
    }

    /**
     * add menu to menu bar
     */
    private void setUpMenuBar()
    {
        add(fileMenu);
        add(editMenu);
        add(imageMenu);
        add(viewMenu);
        add(helpMenu);
    }

}
