/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.KeyStroke;
import model.PASystem;

/**
 *
 * @author LiHao
 */
public abstract class PAFileMenuAction extends AbstractAction
{
    private int keyMask;
    private KeyStroke keyStroke;
    
    public PAFileMenuAction(int keyEvent)
    {
        keyMask = PASystem.setKeyMask();
        keyStroke = KeyStroke.getKeyStroke(keyEvent, keyMask);
        
        //putValue(ACCELERATOR_KEY, );
        putValue(MNEMONIC_KEY, keyEvent);
    }
    
    /**
     *
     */
    public static class OpenFileMenu extends PAFileMenuAction
    {
        public OpenFileMenu()
        {
            super(KeyEvent.VK_O);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
}
