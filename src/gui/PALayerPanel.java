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

    public PALayerPanel()
    {
        initilize();
        customize();
        setUpLayout();
        setUpLayerPanel();
    }

    private void initilize()
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

    private void customize()
    {
        title.setForeground(Color.white);
        titleBar.setMinimumSize(new Dimension(defaultWidth, title.getHeight()));
        titleBar.setBackground(new Color(30, 30, 30));
        bottomBar.setMinimumSize(new Dimension(defaultWidth, 35));
        bottomBar.setBackground(new Color(30, 30, 30));

        // need a method to handle list of layers, so than layer will auto generate
        exampleLayer.setMaximumSize(new Dimension(defaultWidth, 150));
        exampleLayer.setBackground(Color.blue);

        box.setBackground(new Color(38, 38, 38));
        box.add(exampleLayer);
        box.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
        // scrollpane will display scroo;bar when only box size large thab viewport
        box.setPreferredSize(new Dimension(250, 100));

        layerPane.setPreferredSize(new Dimension(defaultWidth, defaultHeight - 65));
        layerPane.revalidate();
        layerPane.setBackground(new Color(0, 0, 0, 0));
        layerPane.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        layerPane.getViewport().setBackground(new Color(0, 0, 0, 0));

        setImageIcon("resources/plus.png", addLayerButton, "Add");
        setImageIcon("resources/minus.png", deleteLayerButton, "Delete");

        titleBar.add(title);
    }

    private void setUpLayout()
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

    private void setUpLayerPanel()
    {
        setBackground(new Color(38, 38, 38, 240));
        setSize(defaultWidth, defaultHeight);
        setVisible(true);
        setLocation(1000, 20);
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

}
