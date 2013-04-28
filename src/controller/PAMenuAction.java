/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PAMainFrame;
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
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
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

        public NewFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_N, "New");
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
            int startX ,startY;
            Point startPoint;
            
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
            
            startX = (int) (screenResolution.getWidth() - fcInternal.getWidth()) / 2;
            startY = (int) (screenResolution.getHeight() - fcInternal.getHeight()) / 2;
            startPoint = new Point(startX, startY);
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
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public ZoomIn(JDesktopPane parent)
        {
            super(KeyEvent.VK_PLUS, "Zoom In");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static class ZoomOut extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public ZoomOut(JDesktopPane parent)
        {
            super(KeyEvent.VK_MINUS, "Zoom Out");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    public static class ActualSize extends PAMenuAction
    {
        private JDesktopPane parent;
        private PAMainFrame onFocusFrame;

        public ActualSize(JDesktopPane parent)
        {
            super(KeyEvent.VK_0, "Actual Size");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}