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

    /**
     * constructor to create PAStartMenu
     */
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
        openFileButton = new JButton();
        newFileButton = new JButton();
    }

    /**
     *
     * draw Logo to Panel.
     *
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
     *
     * @param img
     * @param x
     * @param y
     * @return JButton
     */
    private JButton setUpButtonWithBounds(Image img, int x, int y)
    {
        ImageIcon icon = new ImageIcon(img);
        JButton button = new JButton(icon);
        button.setBackground(new Color(0, 0, 0, 0));
        button.setBorderPainted(false);
        button.setBounds(x, y, img.getWidth(button), img.getHeight(button));
        button.setVisible(true);

        return button;
    }

    /**
     *
     * load image to button add button to JPanel.
     */
    private void setUpButton()
    {
        setImageIcon("resources/openFile 150x150.png", openFileButton, "Open File");
        setImageIcon("resources/newFile 150x150.png", newFileButton, "New File");
        openFileButton.setBounds(386, 226, 150, 150);
        newFileButton.setBounds(86, 226, 150, 150);
        mainPanel.add(openFileButton);
        mainPanel.add(newFileButton);
    }

    /**
     *
     * customize mainPanel and add to InternalFrame.
     */
    private void customize()
    {
        mainPanel.setLayout(null);
        mainPanel.setOpaque(false);
        mainPanel.setSize(640, 450);
        setUpButton();
        add(mainPanel);
    }

    private void setImageIcon(String imgPath, JButton button, String toolTip)
    {
        ImageIcon imgIcon = new ImageIcon(imgPath);
        button.setIcon(imgIcon);
        button.setBackground(new Color(40, 40, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setToolTipText(toolTip);
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
