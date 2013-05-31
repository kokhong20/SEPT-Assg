package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PAProgressBar extends JInternalFrame
{
    static JLabel progressLabel;

    public PAProgressBar()
    {
        super("Translating");
        init();
        setUp();
    }

    private void init()
    {
        progressLabel = new JLabel("Translating");
        setLayout(new FlowLayout());
    }

    private void setUp()
    {
        add(progressLabel);
        setVisible(true);
        setSize(200, 100);
    }

    public void changeText()
    {

        switch (progressLabel.getText())
        {
        case "Translating....":
        {
            progressLabel.setText("Translating");

            break;
        }
        case "Translating":
        {
            progressLabel.setText("Translating.");

            break;
        }
        case "Translating.":
        {
            progressLabel.setText("Translating..");

            break;
        }
        case "Translating..":
        {
            progressLabel.setText("Translating...");

            break;
        }
        case "Translating...":
        {
            progressLabel.setText("Translating....");

            break;
        }
        }

    }
}
