/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PADrawingItem;
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
import model.PASystem;

/**
 *
 * @author LiHao
 */
public abstract class PAMenuAction extends AbstractAction
{
    private int keyMask;
    private KeyStroke keyStroke;

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

    /**
     *
     * action class for menu item "New"
     */
    public static class NewFile extends PAMenuAction
    {
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
            PANewFileSetting newFileSetting;

            if (startMenu != null)
            {
                newFileSetting = new PANewFileSetting(parent, startMenu);
            }
            else
            {
                newFileSetting = new PANewFileSetting(parent);
            }

            parent.add(newFileSetting);

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
            JInternalFrame fcInternal = new JInternalFrame("Open...");
            JFileChooser fileChooser = new JFileChooser();
            FileFilter allFilter = new FileNameExtensionFilter("All files", "svg", "xml");
            FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
            FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");
            Dimension screenResolution = PASystem.getScreenDimension();
            PAFileChooserAction fcAction;

            if (startMenu != null)
            {
                fcAction = new PAFileChooserAction(parent, startMenu, fileChooser, fcInternal);
            }
            else
            {
                fcAction = new PAFileChooserAction(parent, fileChooser, fcInternal);
            }

            fileChooser.addActionListener(fcAction);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(xmlFilter);
            fileChooser.setFileFilter(svgFilter);
            fileChooser.setFileFilter(allFilter);
            fcInternal.add(fileChooser);
            fcInternal.pack();

            int startX = (int) (screenResolution.getWidth() - fcInternal.getWidth()) / 2;
            int startY = (int) (screenResolution.getHeight() - fcInternal.getHeight()) / 2;
            Point startPoint = new Point(startX, startY);
            fcInternal.setLocation(startPoint);
            fcInternal.setVisible(true);
            parent.add(fcInternal);

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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            int selectedOption =
                    JOptionPane.showConfirmDialog(parent, "Are You Sure You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (selectedOption == 0)
            {
                System.exit(0);
            }
        }

    }

    public static class SelectAll extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public SelectAll(JDesktopPane parent)
        {
            super(KeyEvent.VK_A, "Select All");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static class DeselectAll extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public DeselectAll(JDesktopPane parent)
        {
            super(KeyEvent.VK_A, Event.SHIFT_MASK, "Deselect All");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            if (button != null)
            {
                JToggleButton zoomIn = new JToggleButton();
                zoomIn.setToolTipText("Zoom In");
                PADrawingItem.buttonSelected = zoomIn;
            }

            double scale = drawPanel.getScale();
            scale += 0.1;
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
            if (button != null)
            {
                JToggleButton zoomOut = new JToggleButton();
                zoomOut.setToolTipText("Zoom Out");
                PADrawingItem.buttonSelected = zoomOut;
            }

            double scale = drawPanel.getScale();
            scale -= 0.1;
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

    public static class RemoveAction extends PAMenuAction
    {
        private int keyEvent;
        private String name;

        public RemoveAction(int keyEvent, String name)
        {
            super(keyEvent, name);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
        }

    }
}
