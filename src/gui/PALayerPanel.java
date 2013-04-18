/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author LiHao
 */
public class PALayerPanel extends JPanel
{
    private int defaultWidth;
    private int defaultHeight;
    private Box box;
    private JLabel title;
    private JPanel titleBar;
    private JPanel bottomBar;
    private JPanel exampleLayer;
    private JButton addLayerButton;
    private JButton deleteLayerButton;
    private JScrollPane layerPane;

    /**
     * constructor to define PALayerPanel to show layers
     */
    public PALayerPanel()
    {
        initialize();
        customize();
        setUpMainLayout();
        setUpBottomBarLayout();
        setUpLayerPanel();
    }

    /**
     * 
     * initialize LayerPane's attribute.
     */
    private void initialize()
    {
        defaultWidth = 270;
        defaultHeight = 300;
        box = new Box(BoxLayout.Y_AXIS);
        title = new JLabel("Layers");
        titleBar = new JPanel();
        bottomBar = new JPanel();
        exampleLayer = new JPanel();
        addLayerButton = new JButton();
        deleteLayerButton = new JButton();
        layerPane = new JScrollPane(box, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }

    /**
     * 
     * customize LayerPane.
     */
    private void customize()
    {
        title.setForeground(Color.white);
        titleBar.setMinimumSize(new Dimension(defaultWidth, title.getHeight()));
        titleBar.setBackground(new Color(30, 30, 30));
        bottomBar.setMinimumSize(new Dimension(defaultWidth, 35));
        bottomBar.setBackground(new Color(30, 30, 30));

        // need a method to handle list of layers, so than layer will auto generate
        //exampleLayer.setMaximumSize(new Dimension(defaultWidth, 150));
        //exampleLayer.setBackground(Color.blue);

        //box.setBackground(new Color(38, 38, 38));
        //box.add(exampleLayer);
        //box.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        // scrollpane will display scroo;bar when only box size large thab viewport
        //box.setPreferredSize(new Dimension(250, 100));

        layerPane.setPreferredSize(new Dimension(defaultWidth, defaultHeight - 65));
        layerPane.revalidate();
        layerPane.setBackground(new Color(0, 0, 0, 0));
        layerPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        layerPane.getViewport().setBackground(new Color(0, 0, 0, 0));

        setImageIcon("resources/plus 20x20.png", addLayerButton, "Add");
        setImageIcon("resources/minus 20x20.png", deleteLayerButton, "Delete");

        titleBar.add(title);
    }

    /**
     * 
     * set up the main layout.
     */
    private void setUpMainLayout()
    {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(titleBar)
                .addComponent(layerPane)
                .addComponent(bottomBar));

        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(titleBar));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(layerPane));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(bottomBar));

        layout.setVerticalGroup(vGroup);
    }

    /**
     * 
     * add button to bottomBar and setting layout.
     */
    private void setUpBottomBarLayout()
    {
        BoxLayout layout = new BoxLayout(bottomBar, BoxLayout.LINE_AXIS);

        bottomBar.setLayout(layout);
        bottomBar.add(Box.createRigidArea(new Dimension(10, 0)));
        bottomBar.add(addLayerButton);
        bottomBar.add(Box.createRigidArea(new Dimension(15, 0)));
        bottomBar.add(deleteLayerButton);
    }
    
    /**
     * 
     * set Image Icon to button.
     * @param imgPath Image's Path.
     * @param button button want to set the image.
     * @param toolTip the button's name.
     */
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
     * set LayerPane size and background color.
     */
    private void setUpLayerPanel()
    {
        setBackground(new Color(38, 38, 38, 240));
        setSize(defaultWidth, defaultHeight);
        setVisible(true);
        setLocation(1000, 20);
    }

}
