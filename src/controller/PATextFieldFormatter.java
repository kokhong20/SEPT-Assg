package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

/**
 * 
 * @author SaiHoo
 * @version 1.1
 * @since 1.1
 * 
 */

public abstract class PATextFieldFormatter implements KeyListener
{
    protected JTextField textField;

    public PATextFieldFormatter(JTextField textField)
    {
        this.textField = textField;
    }
    
    
    public static class PANameFormatter extends PATextFieldFormatter
    {

        public PANameFormatter(JTextField textField)
        {
            super(textField);
        }

        public void keyPressed(KeyEvent ke)
        {

            if (ke.getKeyChar() == '/' || ke.getKeyChar() == '\\'
                    || ke.getKeyChar() == ':' || ke.getKeyChar() == '*'
                    || ke.getKeyChar() == '?' || ke.getKeyChar() == '"'
                    || ke.getKeyChar() == '<' || ke.getKeyChar() == '>'
                    || ke.getKeyChar() == '|')
            {
                textField.setEditable(false);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

            else
            {
                textField.setEditable(true);

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
            if (ke.getKeyChar() == '/' || ke.getKeyChar() == '\\'
                    || ke.getKeyChar() == ':' || ke.getKeyChar() == '*'
                    || ke.getKeyChar() == '?' || ke.getKeyChar() == '"'
                    || ke.getKeyChar() == '<' || ke.getKeyChar() == '>'
                    || ke.getKeyChar() == '|')
            {
                textField.setEditable(false);
                java.awt.Toolkit.getDefaultToolkit().beep();
            }

            else
            {
                textField.setEditable(true);

            }

        }

    }
    
    
    public static class PANumberFormatter extends PATextFieldFormatter
    {

        public PANumberFormatter(JTextField textField)
        {
            super(textField);
        }

        @Override
        public void keyPressed(KeyEvent ke)
        {
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
            {
                textField.setEditable(true);
            }
            else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE
                    || ke.getKeyCode() == KeyEvent.VK_TAB
                    || ke.getKeyCode() == KeyEvent.VK_DELETE
                    || ke.getKeyCode() == KeyEvent.VK_LEFT
                    || ke.getKeyCode() == KeyEvent.VK_RIGHT)
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
            if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9')
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

}

