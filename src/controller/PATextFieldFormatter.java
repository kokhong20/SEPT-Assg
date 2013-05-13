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
        System.out.println("press" + ke.getKeyCode());
        if(ke.getKeyChar()>='0' && ke.getKeyChar()<='9')
        {
            textField.setEditable(true);
        }
        else if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE || ke.getKeyCode() == KeyEvent.VK_TAB|| ke.getKeyCode() == KeyEvent.VK_DELETE)
        {
            textField.setEditable(true);
        }
        else if (!textField.getText().contains(".") && ke.getKeyChar() == '.')
        {
            textField.setEditable(true);
        }
        else
        {
            textField.setEditable(false);
            java.awt.Toolkit.getDefaultToolkit().beep();
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
        else if (!textField.getText().contains(".") && ke.getKeyChar() == '.')
        {
            
            textField.setEditable(true);
        }
        else
        {
            textField.setEditable(false);
            
        }
        
    }
    
}
