/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;

/**
 *
 * @author LiHao
 */
public class PAFileChooserAction implements ActionListener
{
    private JFileChooser fileChooser;
    private JInternalFrame frame;

    public PAFileChooserAction(JFileChooser fileChooser, JInternalFrame frame)
    {
        this.fileChooser = fileChooser;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (!e.getActionCommand().equals(JFileChooser.CANCEL_SELECTION))
        {
            File selectedFile = fileChooser.getSelectedFile();
            String path = fileChooser.getCurrentDirectory().toString() ;
        }
        
        frame.setVisible(false);
        frame.dispose();
    }

}
