package gui;

import java.awt.FlowLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PAProgressBar
{
    static JFrame progressFrame;
    static JLabel progressLabel;
    static JProgressBar progressBar;

    public PAProgressBar()
    {
        init();
        setUp();
    }

    private void init()
    {
        progressFrame = new JFrame("Translating...");
        progressLabel = new JLabel("0% completed...");
        progressBar = new JProgressBar(0, 100);
        progressFrame.setLayout(new FlowLayout());
    }

    private void setUp()
    {
        System.out.println(progressBar);
        progressFrame.setSize(500, 250);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        progressFrame.add(progressBar);
        progressFrame.add(progressLabel);
        progressBar.setVisible(true);
        progressFrame.setVisible(true);

    }

    public static void setProgress(int add)
    {
        System.out.println(add);
        System.out.println(progressBar);
        progressBar.setValue(add);
        progressLabel.setText(String.format("%d%% completed...", add));
    }
}
