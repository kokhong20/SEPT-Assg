/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import gui.PAMainFrame;
import gui.PAMenuBar;
import gui.PANewFileSetting;
import gui.PASVGPanel;
import gui.PAStartMenu;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.PASVGContainer;
import model.PASVGElement;
import model.PASVGGroup;
import model.PARectangle;
import model.PALine;
import model.PACircle;
import model.PASystem;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author LiHao
 */
public abstract class PAMenuAction extends AbstractAction
{
    protected int keyMask;
    protected KeyStroke keyStroke;

    public PAMenuAction()
    {
    	
    }
    
    /**
     *
     * Constructor of Menu Action.
     *
     * @param keyEvent keyEvent want to use
     * @param name menu item's name
     */
    public PAMenuAction(int keyEvent, String name)
    {
        keyMask = PASystem.keyMask;
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    /**
     *
     * Constructor of Menu Action.
     *
     * @param keyEvent keyEvent want to use
     * @param event event want to add
     * @param name menu item's name
     */
    public PAMenuAction(int keyEvent, int event, String name)
    {
        keyMask = PASystem.keyMask;
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask | event);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    public PAMenuAction(int keyEvent, KeyStroke keyStroke, String name)
    {
        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }

    /**
     *
     * action class for menu item "New"
     */
    public static class NewFile extends PAMenuAction
    {
        protected static PANewFileSetting newFileSetting;
        private JDesktopPane parent;
        private PAStartMenu startMenu;

        public NewFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_N, "New");
            this.parent = parent;
        }

        public NewFile(JDesktopPane parent, PAStartMenu startMenu)
        {
            super(KeyEvent.VK_N, "New");
            this.parent = parent;
            this.startMenu = startMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (newFileSetting == null)
            {
                if (startMenu != null)
                {
                    newFileSetting = new PANewFileSetting(parent, startMenu);
                }
                else
                {
                    newFileSetting = new PANewFileSetting(parent);
                }

                parent.add(newFileSetting);
            }

            try
            {
                newFileSetting.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     *
     * action class for menu item "Open..."
     */
    public static class OpenFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAStartMenu startMenu;

        public OpenFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_O, "Open...");
            this.parent = parent;
        }

        public OpenFile(JDesktopPane parent, PAStartMenu startMenu)
        {
            super(KeyEvent.VK_O, "Open...");
            this.parent = parent;
            this.startMenu = startMenu;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (fcInternal == null)
            {
                fcInternal = new JInternalFrame("Open...");
                JFileChooser fileChooser = new JFileChooser();
                FileFilter allFilter = new FileNameExtensionFilter("All files", "svg", "xml");
                FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                Dimension screenResolution = PASystem.getScreenDimension();
                PAFileChooserAction fcAction;

                if (startMenu != null)
                {
                    fcAction = new PAFileChooserAction(parent, startMenu,
                            fileChooser, fcInternal);
                }
                else
                {
                    fcAction = new PAFileChooserAction(parent, fileChooser,
                            fcInternal);
                }

                fileChooser.addActionListener(fcAction);
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);
                fileChooser.setFileFilter(xmlFilter);
                fileChooser.setFileFilter(svgFilter);
                fileChooser.setFileFilter(allFilter);
                fcInternal.setClosable(true);
                fcInternal.setResizable(false);
                fcInternal.add(fileChooser);
                fcInternal.pack();

                int startX = (int) (screenResolution.getWidth() - fcInternal
                        .getWidth()) / 2;
                int startY = (int) (screenResolution.getHeight() - fcInternal
                        .getHeight()) / 2;
                Point startPoint = new Point(startX, startY);
                fcInternal.setLocation(startPoint);
                fcInternal.setVisible(true);
                parent.add(fcInternal);
            }

            try
            {
                fcInternal.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    /**
     *
     * action class for menu item "Save"
     */
    public static class SaveFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public SaveFile(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_S, "Save");
            this.onFocusFrame = mainFrame;
            this.parent = mainFrame.getParentView();
        }
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
        	File svgFile = null;
        	PASVGContainer container = onFocusFrame.svgPanel.svgContainer;
        	/*
        	 * If saving file is an existing svg
        	 */
        	if((svgFile = container.getSvgFile()) != null)
        	{
                PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, null, fcInternal, container);
                saveFileAction.saveToFile(svgFile, container);
        	}
        	/*
        	 * If saving file is a new svg
        	 */
        	else
        	{
                if (fcInternal == null)
                {
                    fcInternal = new JInternalFrame("Save");
                    JFileChooser fileChooser = new JFileChooser();
                    FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                    FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                    PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, fileChooser, fcInternal, container);
                    fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                    fileChooser.setFileFilter(xmlFilter);
                    fileChooser.setFileFilter(svgFilter);
                    fileChooser.addActionListener(saveFileAction);
                    fcInternal.add(fileChooser);
                    fcInternal.pack();
                    Dimension screenResolution = PASystem.getScreenDimension();
                    int startX = (int) (screenResolution.getWidth() - fcInternal
                            .getWidth()) / 2;
                    int startY = (int) (screenResolution.getHeight() - fcInternal
                            .getHeight()) / 2;
                    Point startPoint = new Point(startX, startY);
                    fcInternal.setLocation(startPoint);
                    fcInternal.setClosable(true);
                    fcInternal.setResizable(false);
                    fcInternal.setVisible(true);
                    parent.add(fcInternal);
                }

                try
                {
                    fcInternal.setSelected(true);
                }
                catch (PropertyVetoException ex)
                {
                    System.err.println(ex.getMessage());
                }
        	}
        }

    }
    
    /**
     *
     * action class for menu item "Save As..."
     */
    public static class SaveAsFile extends PAMenuAction
    {
        protected static JInternalFrame fcInternal;
        private JDesktopPane parent;
        private PAMainFrame mainFrame;

        public SaveAsFile(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_S, Event.SHIFT_MASK, "Save As...");
            this.mainFrame = mainFrame;
            this.parent = mainFrame.getParentView();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        	File svgFile = null;
        	PASVGContainer svgContainer = mainFrame.svgPanel.svgContainer;
            if (fcInternal == null)
            {
                fcInternal = new JInternalFrame("Save As...");
                JFileChooser fileChooser = new JFileChooser();
                FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
                FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
                PASaveFileChooserAction saveFileAction = new PASaveFileChooserAction(parent, fileChooser, fcInternal, svgContainer);
                if((svgFile = svgContainer.getSvgFile()) != null)
                {
                	fileChooser.setCurrentDirectory(svgFile);
                }
                fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
                fileChooser.setFileFilter(xmlFilter);
                fileChooser.setFileFilter(svgFilter);
                fileChooser.addActionListener(saveFileAction);
                fcInternal.add(fileChooser);
                fcInternal.pack();
                Dimension screenResolution = PASystem.getScreenDimension();
                int startX = (int) (screenResolution.getWidth() - fcInternal
                        .getWidth()) / 2;
                int startY = (int) (screenResolution.getHeight() - fcInternal
                        .getHeight()) / 2;
                Point startPoint = new Point(startX, startY);
                fcInternal.setLocation(startPoint);
                fcInternal.setClosable(true);
                fcInternal.setResizable(false);
                fcInternal.setVisible(true);
                parent.add(fcInternal);
            }

            try
            {
                fcInternal.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }
        }

    }

    public static class DocumentProperties extends PAMenuAction
    {
        private JDesktopPane parent;
        private PASVGPanel drawPanel;

        public DocumentProperties(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_D, "Document Properties");
            this.parent = mainFrame.getParentView();
            this.drawPanel = mainFrame.svgPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            PASVGContainer svgContainer = drawPanel.svgContainer;
            PANewFileSetting documentSetting = new PANewFileSetting();
            documentSetting.updateDocument(svgContainer);
            documentSetting.setUpdateDocAction(svgContainer.getSvgTag(), drawPanel);

            try
            {
                documentSetting.setSelected(true);
            }
            catch (PropertyVetoException ex)
            {
                System.err.println(ex.getMessage());
            }

            parent.add(documentSetting);
            documentSetting.toFront();
        }

    }

    /**
     *
     * action class for menu item "Exit"
     */
    public static class ExitProgram extends PAMenuAction
    {
        private JDesktopPane parent;

        public ExitProgram(JDesktopPane parent)
        {
            super(KeyEvent.VK_W, "Exit");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int selectedOption = JOptionPane.showConfirmDialog(parent,
                    "Are You Sure You Want To Exit?", "Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (selectedOption == 0)
            {
                System.exit(0);
            }
        }

    }

    public static class SelectAll extends PAMenuAction
    {
        private PAMainFrame mainFrame;

        public SelectAll(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_A, "Select All");
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JToggleButton virtualButton = new JToggleButton();
            PASVGPanel drawPanel = mainFrame.svgPanel;
            PASelectCursorAction selectAllAction = new PASelectCursorAction(
                    drawPanel, virtualButton, mainFrame.attributeBar);
            int panelWidth = drawPanel.getWidth();
            int panelHeight = drawPanel.getHeight();
            selectAllAction.startSelect = new Point(0, 0);
            selectAllAction.endSelect = new Point(panelWidth, panelHeight);
            selectAllAction.drawBoundsForElements();
            drawPanel.repaint();
        }

    }

    public static class DeselectAll extends PAMenuAction
    {
        private PAMainFrame mainFrame;

        public DeselectAll(PAMainFrame mainFrame)
        {
            super(KeyEvent.VK_A, Event.SHIFT_MASK, "Deselect All");
            this.mainFrame = mainFrame;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            PASVGPanel drawPanel = mainFrame.svgPanel;
            PASelectCursorAction.elementTemp.clear();
            drawPanel.reDrawImage(drawPanel.getScale());
        }

    }

    public static class Cut extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        public Cut(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_X, "Cut");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> eleList = new LinkedList<>();
            eleList.addAll(PASelectCursorAction.elementTemp);
            Paste.storeElement = eleList;
            mainList.removeAll(eleList);

            drawPanel.reDrawImage(drawPanel.getScale());
        }

    }

    public static class Copy extends PAMenuAction
    {
        public Copy()
        {
            super(KeyEvent.VK_C, "Copy");
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> eleList = new LinkedList<>();
            eleList.addAll(PASelectCursorAction.elementTemp);
            Paste.storeElement = eleList;
        }

    }

    public static class Paste extends PAMenuAction
    {
        public static LinkedList<PASVGElement> storeElement;
        private PASVGPanel drawPanel;

        public Paste(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_V, "Paste");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;

            for (int index = storeElement.size() - 1; index >= 0; index--)
            {
                PASVGElement element = storeElement.get(index);

                if (mainList.contains(element))
                {
                    cloneElement(element, mainList);
                }
                else
                {
                    mainList.add(element);
                }
            }

            drawPanel.reDrawImage(drawPanel.getScale());
        }

        private void cloneElement(PASVGElement element, LinkedList<PASVGElement> mainList)
        {
            PASVGElement cloneEle = null;

            if (element instanceof PALine)
            {
                PALine o = (PALine) element;
                cloneEle = new PALine(o.getStroke(), o.getStrokeWidth(),
                        o.getX1() + 5, o.getX2() + 5, o.getY1() + 5, o.getY2() + 5);
            }
            else if (element instanceof PACircle)
            {
                PACircle o = (PACircle) element;
                cloneEle = new PACircle(o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getCx() + 5, o.getCy() + 5, o.getR());
            }
            else if (element instanceof PARectangle)
            {
                PARectangle o = (PARectangle) element;
                cloneEle = new PARectangle(o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getX() + 5, o.getY() + 5, o.getWidth(), o.getHeight());
            }
            else if (element instanceof PASVGGroup)
            {
                PASVGGroup o = (PASVGGroup) element;
                cloneEle = new PASVGGroup(o.getGroupElementList(), o.getFill(), o.getStroke(),
                        o.getStrokeWidth(), o.getParentGroup(), o.getId());
            }

            if (cloneEle != null)
            {
                mainList.add(cloneEle);
            }
        }

    }

    public static class Delete extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        public Delete(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_BACK_SPACE, KeyStroke.getKeyStroke(
                    KeyEvent.VK_BACK_SPACE, 0), "Delete");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> selectedList = PASelectCursorAction.elementTemp;

            if (selectedList != null)
            {
                mainList.removeAll(selectedList);
                drawPanel.reDrawImage(drawPanel.getScale());
            }
        }

    }

    public static class ZoomIn extends PAMenuAction
    {
        private JButton button;
        private PASVGPanel drawPanel;

        public ZoomIn(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_PLUS, "Zoom In");
            this.drawPanel = drawPanel;
        }

        public ZoomIn(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_PLUS, "");
            this.drawPanel = drawPanel;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = drawPanel.getScale();
            DecimalFormat df = new DecimalFormat("##.#");
            scale = Double.parseDouble(df.format(scale));
            if (scale < 10)
            {
                if (scale > 2)
                {
                    scale += 0.5;
                }
                else
                {
                    scale += 0.1;
                }
            }
            drawPanel.zoomInOutSVG(scale);
        }

    }

    public static class ZoomOut extends PAMenuAction
    {
        private JButton button;
        private PASVGPanel drawPanel;

        public ZoomOut(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_MINUS, "Zoom Out");
            this.drawPanel = drawPanel;
        }

        public ZoomOut(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_MINUS, "");
            this.drawPanel = drawPanel;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = drawPanel.getScale();
            DecimalFormat df = new DecimalFormat("##.#");
            scale = Double.parseDouble(df.format(scale));
            if (scale > 0.1)
            {
                if (scale > 2)
                {
                    scale -= 0.5;
                }
                else
                {
                    scale -= 0.1;
                }
            }
            drawPanel.zoomInOutSVG(scale);

        }

    }

    public static class ActualSize extends PAMenuAction
    {
        private PASVGPanel drawPanel;

        public ActualSize(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_0, "Actual Size");
            this.drawPanel = drawPanel;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            double scale = 1.0;
            drawPanel.zoomInOutSVG(scale);
        }

    }

    public static class GroupAction extends PAMenuAction
    {
        private JButton button;
        private PASVGPanel drawPanel;

        public GroupAction(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_G, "Group");
            this.drawPanel = drawPanel;
        }

        public GroupAction(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_G, "");
            this.drawPanel = drawPanel;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> elementList = new LinkedList<>();
            elementList.addAll(PASelectCursorAction.elementTemp);
            PASVGGroup newGroup = new PASVGGroup(elementList);
            LinkedList<PASVGElement> groupList = newGroup
                    .getGroupElementList();
            mainList.addLast(newGroup);
            mainList.removeAll(groupList);

            for (int index = groupList.size() - 1; index >= 0; index--)
            {
                groupList.get(index).setGrouped(true);
                groupList.get(index).setParentGroup(newGroup);
            }

            drawPanel.drawToImage();
            drawPanel.repaint();
        }

    }

    public static class UnGroupAction extends PAMenuAction
    {
        private JButton button;
        private PASVGPanel drawPanel;

        public UnGroupAction(PASVGPanel drawPanel)
        {
            super(KeyEvent.VK_G, Event.SHIFT_MASK, "Ungroup");
            this.drawPanel = drawPanel;
        }

        public UnGroupAction(PASVGPanel drawPanel, JButton button)
        {
            super(KeyEvent.VK_G, Event.SHIFT_MASK, "");
            this.drawPanel = drawPanel;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> elementList = PASelectCursorAction.elementTemp;

            if (elementList.size() == 1 && elementList.getFirst() instanceof PASVGGroup)
            {
                PASVGGroup selectedGroup = (PASVGGroup) elementList.getFirst();
                LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
                LinkedList<PASVGElement> groupList = selectedGroup
                        .getGroupElementList();

                for (int index = mainList.size() - 1; index >= 0; index--)
                {
                    PASVGElement element = mainList.get(index);

                    if (element == selectedGroup)
                    {
                        mainList.remove(index);

                        for (int j = groupList.size() - 1; j >= 0; j--)
                        {
                            groupList.get(j).setGrouped(false);
                            groupList.get(j).setParentGroup(null);
                        }

                        mainList.addAll(index, groupList);
                    }
                }
            }

            drawPanel.drawToImage();
            drawPanel.repaint();
        }

    }

    public static class RemoveAction extends PAMenuAction
    {
        public RemoveAction(int keyEvent, String name)
        {
            super(keyEvent, name);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        }

    }

    public static class RemoveEventAction extends PAMenuAction
    {
        public RemoveEventAction(int keyEvent, int event, String name)
        {
            super(keyEvent, event, name);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        }

    }
    
    public static class LanguageAction extends PAMenuAction
    {
        private PAMenuBar menuBar;
        private String languageToSet;

        public LanguageAction(PAMenuBar menuBar, String languageToSet)
        {
            this.menuBar = menuBar;
            this.languageToSet = languageToSet;
        }

		@Override
		public void actionPerformed(ActionEvent e)
		{
			// TODO Auto-generated method stub
			PASystem.setCurrentResource(languageToSet);
			/*
			 * e.GetSource() returns a null, dunno why?
			 */
			menuBar.resetText();
		}
    }
}
