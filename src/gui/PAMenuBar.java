/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PAMenuAction;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.Set;

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
    /*
     * Temporarily put here until further notice from groupmates
     */
	private JMenu languageMenu;
    private static JMenuItem[] languages;
    /*
     * Temporarily put here until further notice from groupmates
     */
    private static JMenuItem saveFile;
    private static JMenuItem saveAsFile;
    private static JMenuItem docFile;
    private static JMenuItem groupEdit;
    private static JMenuItem ungroupEdit;
    private static JMenuItem selectAllEdit;
    private static JMenuItem deselectAllEdit;
    private static JMenuItem cutEdit;
    private static JMenuItem copyEdit;
    private static JMenuItem pasteEdit;
    private static JMenuItem deleteEdit;
    private static JMenuItem zoomInView;
    private static JMenuItem zoomOutView;
    private static JMenuItem actualSizeView;
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu viewMenu;
    private JMenu helpMenu;
    private JMenuItem newFile;
    private JMenuItem openFile;
    private JMenuItem exitFile;
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
        PAMenuAction.SaveFile saveAction = new PAMenuAction.SaveFile(mainFrame);
        PAMenuAction.SaveAsFile saveAsAction = new PAMenuAction.SaveAsFile(mainFrame);
        PAMenuAction.DocumentProperties docAction = new PAMenuAction.DocumentProperties(mainFrame);
        PAMenuAction.GroupAction groupAction = new PAMenuAction.GroupAction(mainFrame.svgPanel);
        PAMenuAction.UnGroupAction ungroupAction = new PAMenuAction.UnGroupAction(mainFrame.svgPanel);
        PAMenuAction.SelectAll selectAllAction = new PAMenuAction.SelectAll(mainFrame);
        PAMenuAction.DeselectAll deselectAllAction = new PAMenuAction.DeselectAll(mainFrame);
        PAMenuAction.Cut cutAction = new PAMenuAction.Cut(mainFrame.svgPanel);
        PAMenuAction.Copy copyAction = new PAMenuAction.Copy();
        PAMenuAction.Paste pasteAction = new PAMenuAction.Paste(mainFrame.svgPanel);
        PAMenuAction.Delete deleteAction = new PAMenuAction.Delete(mainFrame.svgPanel);
        PAMenuAction.ZoomIn zoomInAction = new PAMenuAction.ZoomIn(mainFrame.svgPanel);
        PAMenuAction.ZoomOut zoomOutAction = new PAMenuAction.ZoomOut(mainFrame.svgPanel);
        PAMenuAction.ActualSize actualSizeAction = new PAMenuAction.ActualSize(mainFrame.svgPanel);
        saveFile.setAction(saveAction);
        saveAsFile.setAction(saveAsAction);
        docFile.setAction(docAction);
        groupEdit.setAction(groupAction);
        ungroupEdit.setAction(ungroupAction);
        selectAllEdit.setAction(selectAllAction);
        deselectAllEdit.setAction(deselectAllAction);
        cutEdit.setAction(cutAction);
        copyEdit.setAction(copyAction);
        pasteEdit.setAction(pasteAction);
        deleteEdit.setAction(deleteAction);
        zoomInView.setAction(zoomInAction);
        zoomOutView.setAction(zoomOutAction);
        actualSizeView.setAction(actualSizeAction);
        saveFile.setEnabled(true);
        saveAsFile.setEnabled(true);
        docFile.setEnabled(true);
        groupEdit.setEnabled(true);
        ungroupEdit.setEnabled(true);
        deselectAllEdit.setEnabled(true);
        selectAllEdit.setEnabled(true);
        cutEdit.setEnabled(true);
        copyEdit.setEnabled(true);
        pasteEdit.setEnabled(true);
        deleteEdit.setEnabled(true);
        zoomInView.setEnabled(true);
        zoomOutView.setEnabled(true);
        actualSizeView.setEnabled(true);
    }

    public static void removeUpdatedAction()
    {
        PAMenuAction.RemoveAction removeDocAction = new PAMenuAction.RemoveAction(KeyEvent.VK_D, "Document Properties");
        PAMenuAction.RemoveAction removeGroupAction = new PAMenuAction.RemoveAction(KeyEvent.VK_G, "Group");
        PAMenuAction.RemoveEventAction removeUngroupAction = new PAMenuAction.RemoveEventAction(KeyEvent.VK_G, Event.SHIFT_MASK, "Ungroup");
        PAMenuAction.RemoveAction removeSelectAllAction = new PAMenuAction.RemoveAction(KeyEvent.VK_A, "Select All");
        PAMenuAction.RemoveEventAction removedeselectAllAction = new PAMenuAction.RemoveEventAction(KeyEvent.VK_A, Event.SHIFT_MASK, "Deselect All");
        PAMenuAction.RemoveAction removeCutAction = new PAMenuAction.RemoveAction(KeyEvent.VK_X, "Cut");
        PAMenuAction.RemoveAction removeCopyAction = new PAMenuAction.RemoveAction(KeyEvent.VK_C, "Copy");
        PAMenuAction.RemoveAction removeDeleteAction = new PAMenuAction.RemoveAction(KeyEvent.VK_DELETE, "Delete");
        PAMenuAction.RemoveAction removeZoomInAction = new PAMenuAction.RemoveAction(KeyEvent.VK_PLUS, "Zoom In");
        PAMenuAction.RemoveAction removeZoomOutAction = new PAMenuAction.RemoveAction(KeyEvent.VK_MINUS, "Zoom Out");
        PAMenuAction.RemoveAction removeActualSizeAction = new PAMenuAction.RemoveAction(KeyEvent.VK_0, "Actual Size");
        docFile.setAction(removeDocAction);
        groupEdit.setAction(removeGroupAction);
        ungroupEdit.setAction(removeUngroupAction);
        selectAllEdit.setAction(removeSelectAllAction);
        deselectAllEdit.setAction(removedeselectAllAction);
        cutEdit.setAction(removeCutAction);
        copyEdit.setAction(removeCopyAction);
        deleteEdit.setAction(removeDeleteAction);
        zoomInView.setAction(removeZoomInAction);
        zoomOutView.setAction(removeZoomOutAction);
        actualSizeView.setAction(removeActualSizeAction);
        docFile.setEnabled(false);
        groupEdit.setEnabled(false);
        ungroupEdit.setEnabled(false);
        selectAllEdit.setEnabled(false);
        deselectAllEdit.setEnabled(false);
        cutEdit.setEnabled(false);
        copyEdit.setEnabled(false);
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
		fileMenu = new JMenu(PASystem.currentResource.getString("File"));
		editMenu = new JMenu(PASystem.currentResource.getString("Edit"));
		viewMenu = new JMenu(PASystem.currentResource.getString("View"));
		helpMenu = new JMenu(PASystem.currentResource.getString("Help"));
		languageMenu = new JMenu(PASystem.currentResource.getString("Language"));
    }

    /**
     * initialize menu items
     */
    private void initMenuItem()
    {
        // File's MenuItem
        newFile = new JMenuItem(PASystem.currentResource.getString("New..."));
        openFile = new JMenuItem(PASystem.currentResource.getString("Open..."));
        saveFile = new JMenuItem(PASystem.currentResource.getString("Save"));
        saveAsFile = new JMenuItem(PASystem.currentResource.getString("SaveAs..."));
        docFile = new JMenuItem(PASystem.currentResource.getString("DocumentProperties"));
        exitFile = new JMenuItem(PASystem.currentResource.getString("Exit"));

        // Edit's MenuItem
        groupEdit = new JMenuItem(PASystem.currentResource.getString("Group"));
        ungroupEdit = new JMenuItem(PASystem.currentResource.getString("Ungroup"));
        selectAllEdit = new JMenuItem(PASystem.currentResource.getString("SelectAll"));
        deselectAllEdit = new JMenuItem(PASystem.currentResource.getString("DeselectAll"));
        cutEdit = new JMenuItem(PASystem.currentResource.getString("Cut"));
        copyEdit = new JMenuItem(PASystem.currentResource.getString("Copy"));
        pasteEdit = new JMenuItem(PASystem.currentResource.getString("Paste"));
        deleteEdit = new JMenuItem(PASystem.currentResource.getString("Delete"));

        // View's MenuItem
        zoomInView = new JMenuItem(PASystem.currentResource.getString("ZoomIn"));
        zoomOutView = new JMenuItem(PASystem.currentResource.getString("ZoomOut"));
        actualSizeView = new JMenuItem(PASystem.currentResource.getString("ActualSize"));
        
        docFile.setEnabled(false);
        groupEdit.setEnabled(false);
        ungroupEdit.setEnabled(false);
        deleteEdit.setEnabled(false);
        zoomInView.setEnabled(false);
        zoomOutView.setEnabled(false);
        actualSizeView.setEnabled(false);
        
        /*
         * 
         */
        Set<String> languageSet = PASystem.allLanguages.keySet();
        Iterator<String> languageIterator = languageSet.iterator();
        languages = new JMenuItem[languageSet.size()];
        int index = 0;
        while(languageIterator.hasNext())
        	languages[index++] = new JMenuItem(languageIterator.next());
        /*
         * 
         */
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
        fileMenu.add(docFile);
        fileMenu.addSeparator();
        fileMenu.add(exitFile);

        editMenu.add(groupEdit);
        editMenu.add(ungroupEdit);
        editMenu.addSeparator();
        editMenu.add(selectAllEdit);
        editMenu.add(deselectAllEdit);
        editMenu.addSeparator();
        editMenu.add(cutEdit);
        editMenu.add(copyEdit);
        editMenu.add(pasteEdit);
        editMenu.add(deleteEdit);

        viewMenu.add(zoomInView);
        viewMenu.add(zoomOutView);
        viewMenu.add(actualSizeView);
        
        /*
         * 
         */
        helpMenu.add(languageMenu);
        for(JMenuItem menuItems : languages)
        	languageMenu.add(menuItems);
        /*
         * 
         */
    }

    /**
     *
     * add action to each menu item.
     */
    private void addAction()
    {
        PAMenuAction.NewFile newAction = new PAMenuAction.NewFile(parent);
        PAMenuAction.OpenFile openAction = new PAMenuAction.OpenFile(parent);
        PAMenuAction.ExitProgram exitAction = new PAMenuAction.ExitProgram(parent);
        newFile.setAction(newAction);
        openFile.setAction(openAction);
        exitFile.setAction(exitAction);
        
        /*
         * 
         */
        for(JMenuItem menuItems : languages)
        	menuItems.setAction(new PAMenuAction.LanguageAction(this, menuItems.getText()));
        /*
         * 
         */
        
    }

    /**
     * add menu to menu bar
     */
    private void setUpMenuBar()
    {
        add(fileMenu);
        add(editMenu);
        add(viewMenu);
        add(helpMenu);
    }

    private void initKeyStroke()
    {
        int keyMask = PASystem.keyMask;
        docFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, keyMask));
        groupEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, keyMask));
        ungroupEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, keyMask|Event.SHIFT_MASK));
        selectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, keyMask));
        deselectAllEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, keyMask|Event.SHIFT_MASK));
        cutEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, keyMask));
        copyEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, keyMask));
        pasteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, keyMask));
        deleteEdit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
        zoomInView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, keyMask));
        zoomOutView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, keyMask));
        actualSizeView.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, keyMask));
    }

	public void resetText()
	{
		// TODO Auto-generated method stub
		
		// All Menu
		fileMenu.setText(PASystem.currentResource.getString("File"));
		editMenu.setText(PASystem.currentResource.getString("Edit"));
		viewMenu.setText(PASystem.currentResource.getString("View"));
		helpMenu.setText(PASystem.currentResource.getString("Help"));
		languageMenu.setText(PASystem.currentResource.getString("Language"));

        // File's MenuItem
        newFile.setText(PASystem.currentResource.getString("New..."));
        openFile.setText(PASystem.currentResource.getString("Open..."));
        saveFile.setText(PASystem.currentResource.getString("Save"));
        saveAsFile.setText(PASystem.currentResource.getString("SaveAs..."));
        docFile.setText(PASystem.currentResource.getString("DocumentProperties"));
        exitFile.setText(PASystem.currentResource.getString("Exit"));

        // Edit's MenuItem
        groupEdit.setText(PASystem.currentResource.getString("Group"));
        ungroupEdit.setText(PASystem.currentResource.getString("Ungroup"));
        selectAllEdit.setText(PASystem.currentResource.getString("SelectAll"));
        deselectAllEdit.setText(PASystem.currentResource.getString("DeselectAll"));
        cutEdit.setText(PASystem.currentResource.getString("Cut"));
        copyEdit.setText(PASystem.currentResource.getString("Copy"));
        pasteEdit.setText(PASystem.currentResource.getString("Paste"));
        deleteEdit.setText(PASystem.currentResource.getString("Delete"));

        // View's MenuItem
        zoomInView.setText(PASystem.currentResource.getString("ZoomIn"));
        zoomOutView.setText(PASystem.currentResource.getString("ZoomOut"));
        actualSizeView.setText(PASystem.currentResource.getString("ActualSize"));
	}
}
