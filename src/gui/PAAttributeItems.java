package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author KokHong
 *
 */
public class PAAttributeItems
{
    private JComponent component;

    public PAAttributeItems(JComponent component)
    {
        this.component = component;
    }

    /**
     * Create JSpinner
     *
     * @param spinner A JSpinner
     * @param dim Dimension of the spinner
     * @param borderColor Border's Color
     * @param initialValue initial value of the JSpinner
     */
    public void createSpinner(JSpinner spinner, Dimension dim, Color borderColor, double initialValue)
    {
        spinner.setPreferredSize(dim);
        spinner.setMaximumSize(dim);
        spinner.setMinimumSize(dim);

        SpinnerModel model = new SpinnerNumberModel(initialValue, //initial value
                0f, //min
                100f, //max
                1f); //step

        spinner.setModel(model);

        JTextField textField = ((JSpinner.NumberEditor) spinner.getEditor()).getTextField();
        textField.setBackground(component.getBackground());
        textField.setForeground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    }

    /**
     * Set Each Button tool tips , size and attributes
     *
     * @param toolTip Tool tip text for each button
     * @param button a JButton
     * @param dim Dimension of the JButton
     */
    public void setButtonAttributes(String toolTip, JButton button, Dimension dim)
    {
        button.setPreferredSize(dim);
        button.setMaximumSize(dim);
        button.setMinimumSize(dim);
        button.setBackground(Color.BLACK);// default is black color
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 60, 60), 2));
        button.setToolTipText(toolTip);
    }

    /**
     * Create JCheckBox
     *
     * @param checkBox JCheckBox
     * @param title The title of the check box
     * @param foreground The Color of the title
     */
    public void createCheckBox(JCheckBox checkBox, String title, Color foreground)
    {
        checkBox.setText(title);
        checkBox.setForeground(Color.WHITE);
        checkBox.setContentAreaFilled(false);
        checkBox.setOpaque(false);
        checkBox.setSelected(true);
        checkBox.putClientProperty("JComponent.sizeVariant", "small");
    }

    /**
     * Create JLabel with text
     *
     * @param label JLabel
     * @param foreground Set Color of the title
     * @param font Set Font of the title
     */
    public void createLabel(JLabel label, Color foreground, Font font)
    {
        label.setForeground(foreground);
        label.setFont(font);
    }

    /**
     * Create JLabel with Image Icons
     *
     * @param label JLabel
     * @param dim The Dimension of the JLabel
     */
    public void createLabel(JLabel label, Dimension dim)
    {
        label.setPreferredSize(dim);
        label.setMaximumSize(dim);
        label.setMinimumSize(dim);
        label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
    }

    /**
     * Create JTextField
     *
     * @param textField JTextField
     * @param dim Dimension of text field
     * @param border Color of border of text field
     */
    public void createTextField(JTextField textField, Dimension dim, Color border)
    {
        textField.setPreferredSize(dim);
        textField.setMaximumSize(dim);
        textField.setMinimumSize(dim);
        textField.setBackground(Color.WHITE);
        textField.setBorder(BorderFactory.createLineBorder(border, 2));
    }

}
