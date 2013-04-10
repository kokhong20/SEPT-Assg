/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author LiHao
 */
public class PAStartMenu extends JPanel
{
    private JPanel mainPanel;
    private JPanel buttonPanel;
    private JButton openFileButton;
    private JButton newFileButton;

    public PAStartMenu()
    {
        //initialize();
        //customize();
        setUpStartMenu();
    }

    private void initialize()
    {
        //buttonPanel = setUpButtonPanel();
    }

    /*private JPanel setUpButtonPanel()
    {
        return new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                Graphics2D g2d = (Graphics2D) g;
                Point2D start = new Point2D.Float(0, 0);
                Point2D end = new Point2D.Float(0, 180);
                float[] dist =
                {
                    0.00f, 0.1f, 0.4f
                };
                Color[] colors =
                {
                    Color.black, Color.white, Color.white
                };
                LinearGradientPaint gPaint = new LinearGradientPaint(start, end, dist, colors);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setPaint(gPaint);
                g2d.fillRect(0, 0, 600, 180);
            }
        };
    }*/

    private JButton setUpButtonWithBounds(Image img, int x, int y)
    {
        ImageIcon icon = new ImageIcon(img);
        JButton button = new JButton(icon);
        button.setBorder(null);
        button.setBorderPainted(false);
        button.setBounds(x, y, img.getWidth(button), img.getHeight(button));
        button.setVisible(true);

        return button;
    }

    private void setUpButton()
    {      
        /*buttonPanel.setLayout(null);
        buttonPanel.setBackground(Color.white);
        buttonPanel.setSize(600, 180);
        buttonPanel.setBounds(0, 220, buttonPanel.getWidth(), buttonPanel.getHeight());
        buttonPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.gray));
        buttonPanel.setVisible(true);*/

        try
        {
            Image openFileImg = ImageIO.read(new File("resources/OpenFile 128x128.png"));
            Image newFileImg = ImageIO.read(new File("resources/NewFile 128x128.png"));

            openFileButton = setUpButtonWithBounds(openFileImg, 86, 26);
            newFileButton = setUpButtonWithBounds(newFileImg, 386, 26);

            add(openFileButton);
            add(newFileButton);
        }
        catch (NullPointerException | IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
    
    private void setUpStartMenu()
    {
        setLayout(null);
        setUpButton();
        setOpaque(false);
        setSize(600, 400);
        setLocation(360,200);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        /*Graphics2D g2d = (Graphics2D) g;
        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, 250);
        float[] dist =
        {
            0.0f, 0.2f, 0.8f, 1.0f
        };
        Color[] colors =
        {
           // new Color(170, 170, 170), new Color(210, 210, 210), new Color(230, 230, 230), new Color(220, 220, 220), new Color(185, 185, 185)
           new Color(90, 90, 90), new Color(100, 100, 100), new Color(100, 100, 100), new Color(90, 90, 90)     
        };
        LinearGradientPaint gPaint = new LinearGradientPaint(start, end, dist, colors);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(gPaint);
        g2d.fillRect(0, 0, 600, 220);*/

        try
        {
            Image img = ImageIO.read(new File("resources/mainLogo 128x128.png"));
            g.drawImage(img, 80, 20, 180, 180, null);
        }
        catch (IOException ex)
        {
            Logger.getLogger(PAStartMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
