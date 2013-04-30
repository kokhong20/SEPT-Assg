/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controller.PANewFileSettingAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.PASystem;

/**
 *
 * @author LiHao
 */
public class PANewFileSetting extends JInternalFrame
{
    private JPanel formPane;
    private JLabel fileName;
    private JLabel width;
    private JLabel height;
    private JLabel unit;
    private JButton cancelButton;
    private JButton okButton;
    private JTextField fileNameField;
    private JTextField widthField;
    private JTextField heightField;
    private JComboBox unitSelection;
    private JDesktopPane parent;
    private PAStartMenu startMenu;

    public PANewFileSetting(JDesktopPane parent)
    {
        super("New");
        this.parent = parent;
        initialize();
        customize();
        setAction();
        setUpMainLayout();
        setUpNewFile();
    }

    public PANewFileSetting(JDesktopPane parent, PAStartMenu startMenu)
    {
        super("New");
        this.parent = parent;
        this.startMenu = startMenu;
        initialize();
        customize();
        setAction();
        setUpMainLayout();
        setUpNewFile();
    }

    public HashMap getFieldText()
    {
        HashMap<String, String> textMap = new HashMap<>();
        textMap.put("fileName", fileNameField.getText());
        textMap.put("width", widthField.getText());
        textMap.put("height", heightField.getText());
        textMap.put("unit", (String) unitSelection.getSelectedItem());

        return textMap;
    }

    private JLabel setUpLabel(String name)
    {
        JLabel label = new JLabel(name);
        label.setMaximumSize(new Dimension(80, 0));
        label.setForeground(Color.white);

        return label;
    }

    private JTextField setUpTextField()
    {
        JTextField textField = new JTextField();
        textField.setMaximumSize(new Dimension(200, 0));
        textField.setBackground(new Color(50, 50, 50));
        textField.setForeground(Color.white);

        return textField;
    }

    private void initialize()
    {
        String[] unitList =
        {
            "em", "ex", "px", "in", "cm", "mm", "pt", "pc", "%"
        };
        formPane = new JPanel();
        fileNameField = setUpTextField();
        widthField = setUpTextField();
        heightField = setUpTextField();
        unitSelection = new JComboBox(unitList);
        cancelButton = new JButton("Cancel");
        okButton = new JButton("OK");

        fileName = setUpLabel("Name: ");
        width = setUpLabel("Width: ");
        height = setUpLabel("Height: ");
        unit = setUpLabel("Unit: ");
    }

    private void customize()
    {
        Dimension textFieldMax = new Dimension(200, 0);

        formPane.setSize(new Dimension(380, 250));
        formPane.setBackground(new Color(28, 28, 28, 240));
        formPane.setVisible(true);
        fileNameField.setText("Untitled-1");
        widthField.setText("500");
        heightField.setText("500");
        unitSelection.setSelectedIndex(2);
        unitSelection.setMaximumSize(new Dimension(120, 0));
        okButton.setSelected(true);
    }

    private void setAction()
    {
        okButton.setActionCommand("OK");
        cancelButton.setActionCommand("CANCEL");
        PANewFileSettingAction newFileSettingAction;

        if (startMenu != null)
        {
            newFileSettingAction = new PANewFileSettingAction(parent, this, startMenu);
        }
        else
        {
            newFileSettingAction = new PANewFileSettingAction(parent, this);
        }
        
        okButton.addActionListener(newFileSettingAction);
        cancelButton.addActionListener(newFileSettingAction);
    }

    private void setUpMainLayout()
    {
        GroupLayout layout = new GroupLayout(formPane);
        formPane.setLayout(layout);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();

        hGroup.addGap(30);
        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(fileName)
                .addComponent(width)
                .addComponent(height)
                .addComponent(unit));

        hGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(fileNameField)
                .addComponent(widthField)
                .addComponent(heightField)
                .addComponent(unitSelection)
                .addGroup(layout.createSequentialGroup()
                .addComponent(cancelButton)
                .addComponent(okButton)));

        layout.setHorizontalGroup(hGroup);

        // Create a sequential group for the vertical axis.
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();

        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(fileName)
                .addComponent(fileNameField));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(width)
                .addComponent(widthField));

        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(height)
                .addComponent(heightField));

        vGroup.addGap(5);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(unit)
                .addComponent(unitSelection));

        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(cancelButton)
                .addComponent(okButton));

        layout.setVerticalGroup(vGroup);
    }

    private void setUpNewFile()
    {
        add(formPane);
        setSize(new Dimension(380, 250));

        Dimension screenResolution = PASystem.getScreenDimension();
        int startX = (int) (screenResolution.getWidth() - this.getWidth()) / 2;
        int startY = (int) (screenResolution.getHeight() - this.getHeight()) / 2;
        Point startPoint = new Point(startX, startY);

        setLocation(startPoint);
        setVisible(true);
    }

}
