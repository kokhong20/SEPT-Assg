package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PAProgressBar extends JInternalFrame
{
    static JInternalFrame progressFrame;
    static JLabel progressLabel;

    public PAProgressBar()
    {
        init();
        setUp();
    }

    private void init()
    {
        progressFrame = new JInternalFrame("Translating...");
        progressLabel = new JLabel("Translating");
        progressFrame.setLayout(new FlowLayout());
    }

    private void setUp()
    {
        progressFrame.add(progressLabel);
        progressFrame.setVisible(true);
        progressFrame.setSize(200, 100);
    }

    public void changeText()
    {

        switch (progressLabel.getText())
        {
        case "Translating....":
        {
            progressLabel.setText("Translating");
            progressFrame.repaint();
            break;
        }
        case "Translating":
        {
            progressLabel.setText("Translating.");
            progressFrame.repaint();
            break;
        }
        case "Translating.":
        {
            progressLabel.setText("Translating..");
            progressFrame.repaint();
            break;
        }
        case "Translating..":
        {
            progressLabel.setText("Translating...");
            progressFrame.repaint();
            break;
        }
        case "Translating...":
        {
            progressLabel.setText("Translating....");
            progressFrame.repaint();
            break;
        }
        }

    }
    public void dispose()
    {
        progressFrame.dispose();
    }
}
