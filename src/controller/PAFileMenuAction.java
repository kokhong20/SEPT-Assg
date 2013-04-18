/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.Event;
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
public abstract class PAFileMenuAction extends AbstractAction
{
    private int keyMask;
    private KeyStroke keyStroke;

    public PAFileMenuAction(int keyEvent, String name)
    {
        keyMask = PASystem.setKeyMask();
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }
    
    public PAFileMenuAction(int keyEvent, int event, String name)
    {
        keyMask = PASystem.setKeyMask();
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask | event);

        putValue(ACCELERATOR_KEY, keyStroke);
        putValue(MNEMONIC_KEY, keyEvent);
        putValue(NAME, name);
    }
    
    public static class NewFile extends PAFileMenuAction
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
     */
    public static class OpenFile extends PAFileMenuAction
    {
        private JDesktopPane parent;

        public OpenFile(JDesktopPane parent)
        {
            super(KeyEvent.VK_O, "Open...");
            this.parent = parent;
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            JInternalFrame fcInternal = new JInternalFrame("Open...");
            JFileChooser fileChooser = new JFileChooser();
            FileFilter allFilter = new FileNameExtensionFilter("All files", "svg", "xml");
            FileFilter svgFilter = new FileNameExtensionFilter("SVG files", "svg");
            FileFilter xmlFilter = new FileNameExtensionFilter("XML files", "xml");

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(xmlFilter);
            fileChooser.setFileFilter(svgFilter);
            fileChooser.setFileFilter(allFilter);
            fcInternal.add(fileChooser);
            fcInternal.pack();
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
    
    public static class SaveFile extends PAFileMenuAction
    {
        private JDesktopPane parent;
        private JInternalFrame onFocusFrame;
        
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
    
    public static class SaveAsFile extends PAFileMenuAction
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

    public static class ExitProgram extends PAFileMenuAction
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
}
