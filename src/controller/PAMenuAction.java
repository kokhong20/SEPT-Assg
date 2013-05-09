/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PAMainFrame;
import gui.PANewFileSetting;
import gui.PASVGPanel;
import gui.PAStartMenu;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import static javax.swing.Action.ACCELERATOR_KEY;
import static javax.swing.Action.MNEMONIC_KEY;
import static javax.swing.Action.NAME;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.PASVGElement;
import model.PASVGGroup;
import model.PASystem;

/**
 * 
 * @author LiHao
 */
public abstract class PAMenuAction extends AbstractAction
{
    protected int keyMask;
    protected KeyStroke keyStroke;

    /**
     * 
     * Constructor of Menu Action.
     * 
     * @param keyEvent
     *            keyEvent want to use
     * @param name
     *            menu item's name
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
     * @param keyEvent
     *            keyEvent want to use
     * @param event
     *            event want to add
     * @param name
     *            menu item's name
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
                FileFilter allFilter = new FileNameExtensionFilter("All files",
                        "svg", "xml");
                FileFilter svgFilter = new FileNameExtensionFilter("SVG files",
                        "svg");
                FileFilter xmlFilter = new FileNameExtensionFilter("XML files",
                        "xml");
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
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public SaveFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_S, "Save");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
        }

    }

    /**
     * 
     * action class for menu item "Save As..."
     */
    public static class SaveAsFile extends PAMenuAction
    {
        private JDesktopPane parent;
        private JInternalFrame onFocusFrame;

        public SaveAsFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_S, Event.SHIFT_MASK, "Save As...");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
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
            PASelectCursorAction.elementTemp = drawPanel.elementCollection;
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
            JToggleButton virtualButton = new JToggleButton();
            PASVGPanel drawPanel = mainFrame.svgPanel;
            PASelectCursorAction selectAllAction = new PASelectCursorAction
                    (drawPanel, virtualButton, mainFrame.attributeBar);
            PASelectCursorAction.elementTemp.clear();
            int panelWidth = drawPanel.getWidth();
            int panelHeight = drawPanel.getHeight();
            selectAllAction.startSelect = new Point(0, 0);
            selectAllAction.endSelect = new Point(panelWidth, panelHeight);
            selectAllAction.drawBoundsForElements();
            drawPanel.drawToImage();
            drawPanel.repaint();
        }

    }

    public static class Cut extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public Cut(JDesktopPane parent)
        {
            super(KeyEvent.VK_X, "Cut");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
        }

    }

    public static class Copy extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public Copy(JDesktopPane parent)
        {
            super(KeyEvent.VK_C, "Copy");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
        }

    }

    public static class Paste extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public Paste(JDesktopPane parent)
        {
            super(KeyEvent.VK_V, "Cut");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
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
                for (int index = selectedList.size() - 1; index >= 0; index--)
                {
                    PASVGElement element = selectedList.get(index);

                    for (int j = mainList.size() - 1; j >= 0; j--)
                    {
                        if (element == mainList.get(j))
                        {
                            mainList.remove(j);
                        }
                    }
                }

                drawPanel.drawToImage();
                drawPanel.repaint();
            }
        }

    }

    public static class ImageSize extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public ImageSize(JDesktopPane parent)
        {
            super(KeyEvent.VK_I, Event.ALT_MASK, "Image Size...");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
        }

    }

    public static class Viewbox extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public Viewbox(JDesktopPane parent)
        {
            super(KeyEvent.VK_V, Event.ALT_MASK, "Viewbox");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); // To
            // change
            // body
            // of
            // generated
            // methods,
            // choose
            // Tools
            // |
            // Templates.
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
                    scale -= 0.5;
                else
                    scale -= 0.1;
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
            // this.elementList = elementList;
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> elementList = PASelectCursorAction.elementTemp;

            if (elementList != null)
            {
                PASVGGroup newGroup = new PASVGGroup(elementList);
                LinkedList<PASVGElement> groupList = newGroup
                        .getGroupElementList();
                mainList.addLast(newGroup);

                for (int index = groupList.size() - 1; index >= 0; index--)
                {
                    groupList.get(index).setGrouped(true);
                    groupList.get(index).setParentGroup(newGroup);

                    for (int j = mainList.size() - 1; j >= 0; j--)
                    {
                        if (groupList.get(index) == mainList.get(j))
                        {
                            mainList.remove(j);
                            break;
                        }
                    }
                }
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
            PASVGGroup selectedGroup = PASelectCursorAction.headGroup;
            LinkedList<PASVGElement> mainList = drawPanel.elementCollection;
            LinkedList<PASVGElement> groupList = selectedGroup
                    .getGroupElementList();

            for (int index = mainList.size() - 1; index >= 0; index--)
            {
                PASVGElement element = mainList.get(index);
                System.out.println("main :" + mainList.size());
                if (element == selectedGroup)
                {
                    mainList.remove(index);
                    System.out.println("remove :" + mainList.size());
                    System.out.println("groupList :" + groupList.size());
                    for (int j = groupList.size() - 1; j >= 0; j--)
                    {
                        groupList.get(j).setGrouped(false);
                        groupList.get(j).setParentGroup(null);
                    }

                    mainList.addAll(index, groupList);
                    System.out.println("add :" + mainList.size());
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
}
