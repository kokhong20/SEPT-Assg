package gui;

import java.awt.FlowLayout;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class PAProgressBar extends JInternalFrame
{
//    static JInternalFrame progressFrame;
    static JLabel progressLabel;
//    JDesktopPane parent;

//    public PAProgressBar(JDesktopPane parent)
    public PAProgressBar()
    {
        //this.parent = parent;
        super("Translating...");
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
//        parent.add(progressFrame);
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
