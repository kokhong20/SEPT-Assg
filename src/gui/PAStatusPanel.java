package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.PASystem;

/**
 *
 * @author KokHong
 *
 */
public class PAStatusPanel extends JPanel
{
    /**
     *
     */
    private static final long serialVersionUID = 7022375506076289923L;
    private PAMainFrame mainFrame;
    private JLabel statusMsg;

    /**
     * constructor to define PAStatusPanel for PAMainFrame
     * @param mainFrame
     */
    public PAStatusPanel(PAMainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        initPanel();
    }

    /**
     * Init status panel
     */
    private void initPanel()
    {
        statusMsg = new JLabel(" ", JLabel.LEFT);
        statusMsg.setVerticalTextPosition(JLabel.BOTTOM);
        statusMsg.setHorizontalTextPosition(JLabel.CENTER);
        statusMsg.setFont(new Font("Helvetica", 10, 12));
        statusMsg.setForeground(Color.WHITE);

        setBackground(new Color(77, 77, 77));
        setPreferredSize(new Dimension(mainFrame.getWidth(), 20));
        setMaximumSize(new Dimension((int) PASystem.getScreenDimension().getWidth(), 20));
        setMinimumSize(new Dimension(mainFrame.getWidth(), 20));

        add(statusMsg);

    }

    /**
     * Set Status Message
     *
     * @param msg Message to be shown
     */
    public void setLabelText(String msg)
    {
        statusMsg.setText(msg);
    }

}
