/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PAMenuAction.ExitProgram;
import controller.PAMenuAction.NewFile;
import controller.PAMenuAction.OpenFile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.PASystem;

/**
 *
 * @author LiHao
 */
public class PAStartMenu extends JInternalFrame
{
    private JPanel mainPanel;
    private JButton openFileButton;
    private JButton newFileButton;
    private JDesktopPane parent;

    /**
     * constructor to create PAStartMenu
     */
    public PAStartMenu(JDesktopPane parent)
    {
        super("Start Menu");
        this.parent = parent;
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
        setJMenuBar(setUpMenuBar());
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
     * creates a new JMenuBar with close Menu
     *
     * @return JMenuBar
     */
    private JMenuBar setUpMenuBar()
    {
        return new JMenuBar()
        {
            public JMenu add(JMenu c)
            {
                c = new JMenu("Close");
                c.setAction(new ExitProgram(parent));
                return c;
            }

        };
    }

    /**
     *
     * load image to button add button to JPanel.
     */
    private void setUpButton()
    {
        NewFile newAction = new NewFile(parent, this);
        OpenFile openAction = new OpenFile(parent, this);
        newFileButton.setAction(newAction);
        openFileButton.setAction(openAction);
        setImageIcon("resources/openFile 150x150.png", openFileButton, "Open File");
        setImageIcon("resources/newFile 150x150.png", newFileButton, "New File");
        openFileButton.setBounds(386, 226, 150, 150);
        newFileButton.setBounds(86, 226, 150, 150);
        mainPanel.add(openFileButton);
        mainPanel.add(newFileButton);
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

    /**
     *
     * to show the InternalFrame and set size to it.
     */
    private void setUpStartMenu()
    {
        Dimension screenResolution = PASystem.getScreenDimension();
        int screenWidth = (int) screenResolution.getWidth();
        int screenHeight = (int) screenResolution.getHeight();

        if (PASystem.currentOS.indexOf("mac") >= 0)
        {
            screenHeight -= 86;
        }

        int width = 650;
        int height = 450;
        int startX = (int) (screenWidth - width) / 2;
        int startY = (int) (screenHeight - height) / 2;
        Point startPoint = new Point(startX, startY);
        setClosable(true);
        setResizable(false);
        setSize(width, height);
        setLocation(startPoint);
        setVisible(true);
    }

}
