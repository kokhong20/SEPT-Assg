/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author LiHao
 */
public class PAStartMenu extends JInternalFrame
{
    private JPanel mainPanel;
    private JButton openFileButton;
    private JButton newFileButton;

    public PAStartMenu()
    {
        initialize();
        customize();
        setUpStartMenu();
    }

    /**
     * 
     * initialize component.
     */
    private void initialize()
    {
        mainPanel = setUpMainPanel();
    }

    /**
     * 
     * draw Logo to Panel.
     * @return JPanel
     */
    private JPanel setUpMainPanel()
    {
        return new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                try
                {
                    Image background = ImageIO.read(new File("resources/background.png"));
                    Image img = ImageIO.read(new File("resources/mainLogo 128x128.png"));
                    g.drawImage(background, 0, 0, 650, 450, null);
                    g.drawImage(img, 80, 20, 180, 180, null);
                }
                catch (IOException ex)
                {
                    System.err.println(ex.getMessage());
                }
            }

        };
    }

    /**
     * 
     * initialize button and set size, bounds and icon.
     * @param img
     * @param x
     * @param y
     * @return JButton
     */
    private JButton setUpButtonWithBounds(Image img, int x, int y)
    {
        ImageIcon icon = new ImageIcon(img);
        JButton button = new JButton(icon);
        button.setBackground(new Color(0,0,0,0));
        button.setBorderPainted(false);
        button.setBounds(x, y, img.getWidth(button), img.getHeight(button));
        button.setVisible(true);

        return button;
    }

    /**
     * 
     * load image to button
     * add button to JPanel.
     */
    private void setUpButton()
    {
        try
        {
            Image openFileImg = ImageIO.read(new File("resources/OpenFile 150x150.png"));
            Image newFileImg = ImageIO.read(new File("resources/NewFile 150x150.png"));

            openFileButton = setUpButtonWithBounds(openFileImg, 386, 226);
            newFileButton = setUpButtonWithBounds(newFileImg, 86, 226);

            mainPanel.add(openFileButton);
            mainPanel.add(newFileButton);
        }
        catch (NullPointerException | IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    /**
     * 
     * customize mainPanel and add to InternalFrame.
     */
    private void customize()
    {
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        mainPanel.setSize(640,450);
        setUpButton();
        add(mainPanel);
    }

    /**
     * 
     * to show the InternalFrame and set size to it.
     */
    private void setUpStartMenu()
    {
        //setBackground(Color.white);
        setSize(650, 450);
        setVisible(true);
    }
}
