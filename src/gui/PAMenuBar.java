/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PAMenuAction;
import java.awt.event.KeyEvent;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import model.PASystem;

/**
 *
 * @author LiHao
 */
public class PAMenuBar extends JMenuBar
{
    private static JMenuItem selectAllEdit;
    private static JMenuItem deleteEdit;
    private static JMenuItem zoomInView;
    private static JMenuItem zoomOutView;
    private static JMenuItem actualSizeView;
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
    private JMenuItem deselectAllEdit;
    private JMenuItem cutEdit;
    private JMenuItem copyEdit;
    private JMenuItem pasteEdit;
    private JMenuItem imageSizeImage;
    private JMenuItem viewBoxImage;
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
        initKeyStroke();
    }

    public static void updateAction(PAMainFrame mainFrame)
    {
        PAMenuAction.SelectAll selectAllAction = new PAMenuAction.SelectAll(mainFrame);
        PAMenuAction.Delete deleteAction = new PAMenuAction.Delete(mainFrame.svgPanel);
        PAMenuAction.ZoomIn zoomInAction = new PAMenuAction.ZoomIn(mainFrame.svgPanel);
        PAMenuAction.ZoomOut zoomOutAction = new PAMenuAction.ZoomOut(mainFrame.svgPanel);
        PAMenuAction.ActualSize actualSizeAction = new PAMenuAction.ActualSize(mainFrame.svgPanel);
        selectAllEdit.setAction(selectAllAction);
        deleteEdit.setAction(deleteAction);
        zoomInView.setAction(zoomInAction);
        zoomOutView.setAction(zoomOutAction);
        actualSizeView.setAction(actualSizeAction);
        selectAllEdit.setEnabled(true);
        deleteEdit.setEnabled(true);
        zoomInView.setEnabled(true);
        zoomOutView.setEnabled(true);
        actualSizeView.setEnabled(true);
    }

    public static void removeUpdatedAction()
    {
        PAMenuAction.RemoveAction removeSelectAllAction = new PAMenuAction.RemoveAction(KeyEvent.VK_A, "Select All");
        PAMenuAction.RemoveAction removeDeleteAction = new PAMenuAction.RemoveAction(KeyEvent.VK_DELETE, "Delete");
        PAMenuAction.RemoveAction removeZoomInAction = new PAMenuAction.RemoveAction(KeyEvent.VK_PLUS, "Zoom In");
        PAMenuAction.RemoveAction removeZoomOutAction = new PAMenuAction.RemoveAction(KeyEvent.VK_MINUS, "Zoom Out");
        PAMenuAction.RemoveAction removeActualSizeAction = new PAMenuAction.RemoveAction(KeyEvent.VK_0, "Actual Size");
        selectAllEdit.setAction(removeSelectAllAction);
        deleteEdit.setAction(removeDeleteAction);
        zoomInView.setAction(removeZoomInAction);
        zoomOutView.setAction(removeZoomOutAction);
        actualSizeView.setAction(removeActualSizeAction);
        selectAllEdit.setEnabled(false);
        deleteEdit.setEnabled(false);
        zoomInView.setEnabled(false);
        zoomOutView.setEnabled(false);
        actualSizeView.setEnabled(false);
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
        
        deleteEdit.setEnabled(false);
        zoomInView.setEnabled(false);
        zoomOutView.setEnabled(false);
        actualSizeView.setEnabled(false);
    }

    /**
     * add menu items to menu
     */
    private void addMenuItem()
    {
        fileMenu.add(newFile);
        fileMenu.add(openFile);
        fileMenu.addSeparator();
        fileMenu.add(saveFile);
        fileMenu.add(saveAsFile);
        fileMenu.addSeparator();
        fileMenu.add(exitFile);

        editMenu.add(selectAllEdit);
        editMenu.add(deselectAllEdit);
        editMenu.addSeparator();
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
        PAMenuAction.NewFile newAction = new PAMenuAction.NewFile(parent);
        PAMenuAction.OpenFile openAction = new PAMenuAction.OpenFile(parent);
        PAMenuAction.SaveFile saveAction = new PAMenuAction.SaveFile(parent);
        PAMenuAction.SaveAsFile saveAsAction = new PAMenuAction.SaveAsFile(parent);
        PAMenuAction.ExitProgram exitAction = new PAMenuAction.ExitProgram(parent);
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

    private void initKeyStroke()
    {
        int keyMask = PASystem.keyMask;
        selectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, keyMask));
        deleteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
        zoomInView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, keyMask));
        zoomOutView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, keyMask));
        actualSizeView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, keyMask));
    }
}
