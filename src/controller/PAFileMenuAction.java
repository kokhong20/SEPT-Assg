/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import javax.swing.AbstractAction;
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

    /**
     *
     */
    public static class OpenFileMenu extends PAFileMenuAction
    {
        private JDesktopPane parent;

        public OpenFileMenu(JDesktopPane parent)
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

    public static class ExitProgram extends PAFileMenuAction
    {
        public ExitProgram()
        {
            super(KeyEvent.VK_W, "Exit");
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int selectedOption = 
                    JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Exit?", "Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (selectedOption == 0)
            {
                System.exit(0);
            }
        }

    }
}
