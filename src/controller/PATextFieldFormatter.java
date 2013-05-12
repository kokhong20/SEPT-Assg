package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class PATextFieldFormatter implements KeyListener
{
    private JTextField textField;
    public PATextFieldFormatter (JTextField textField)
    {
        this.textField = textField;
    }
    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyChar()>='0' && ke.getKeyChar()<='9')
        {
            textField.setEditable(true);
        }
        else
        {
            textField.setEditable(false);
        }
        
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        textField.setEditable(true);
        
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        if(ke.getKeyChar()>='0' && ke.getKeyChar()<='9')
        {
            textField.setEditable(true);
            
        }
        else
        {
            textField.setEditable(false);
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
        
    }
    
}
