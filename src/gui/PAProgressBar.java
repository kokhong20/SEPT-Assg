package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PAProgressBar
{
    static JFrame progressFrame;
    static JLabel progressLabel;

    public PAProgressBar()
    {
        init();
        setUp();
    }

    private void init()
    {
        progressFrame = new JFrame("Translating...");
        progressLabel = new JLabel("Translating");
        progressFrame.setLayout(new FlowLayout());
    }

    private void setUp()
    {
        progressFrame.add(progressLabel);
        progressFrame.setVisible(true);
        progressFrame.setSize(300, 300);
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
