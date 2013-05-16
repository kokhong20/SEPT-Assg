package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JDesktopPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import gui.PAColorChooser;

/**
 *
 * @author KokHong
 * @since 1.1
 * <p>This class creates a PAShapeBarAction which get attributes for fill,stroke
 * and stroke width </p>
 */
public abstract class PAShapeBarAction extends AbstractAction
{
    /**
     *
     */
    private static final long serialVersionUID = -4244444316412694205L;
    protected JButton button;
    protected JSpinner spinner;
    protected JCheckBox checkBox;
    protected PAColorChooser colorChooser;
    protected JDesktopPane rootView;
    protected JColorChooser colorChooserObject;
    protected String title;

    /**
     * Constructor for Stroke
     *
     * @param rootView JDesktopPane
     * @param button JButton
     * @param title String
     * @param checkBox JCheckBox
     * @param spinner JSpinner
     */
    public PAShapeBarAction(JDesktopPane rootView, JButton button, String title, JCheckBox checkBox, JSpinner spinner)
    {
        this.title = title;
        this.button = button;
        this.rootView = rootView;
        this.checkBox = checkBox;
        this.spinner = spinner;

        settingAction();
    }

    /**
     * Constructor for Fill
     *
     * @param rootView JDesktopPane
     * @param button JButton
     * @param title String
     * @param checkBox JCheckBox
     */
    public PAShapeBarAction(JDesktopPane rootView, JButton button, String title, JCheckBox checkBox)
    {
        this.title = title;
        this.button = button;
        this.rootView = rootView;
        this.checkBox = checkBox;

        settingAction();
    }

    private void setDefaultIcon()
    {

        if (!checkBox.isSelected())
        {
            ImageIcon icon = new ImageIcon("resources/none.png");
            button.setIcon(icon);

            if (colorChooser != null)
            {
                colorChooser.dispose();
            }
        }
        else
        {
            button.setIcon(null);
        }

    }

    private void setFillEnable()
    {
        button.setEnabled(checkBox.isSelected());
        setDefaultIcon();

    }

    private void setStrokeEnable()
    {
        button.setEnabled(checkBox.isSelected());
        spinner.setEnabled(checkBox.isSelected());
        setDefaultIcon();
    }

    private void setColorChooser()
    {
        if (colorChooser == null)
        {
            colorChooser = new PAColorChooser(rootView);
            colorChooser.addInternalFrameListener(new InternalFrameAdapter()
            {
                @Override
                public void internalFrameClosed(InternalFrameEvent e)
                {
                    if (e.getSource() == colorChooser)
                    {
                        colorChooser = null;
                    }
                }

            });
            colorChooser.setTitle(title);
            colorChooserObject = colorChooser.getColorChooser();
            colorChooserObject.setColor(button.getBackground());
        }

    }

    /**
     * Set action for each check box and color button
     */
    public abstract void settingAction();

    /**
     * This class is for handle fill(Color) attributes for elements
     */
    public static class FillCheckAction extends PAShapeBarAction
    {
        /**
         * constructor for fill
         *
         * @param rootView JDesktopPane
         * @param button JButton
         * @param title String
         * @param checkBox JCheckBox
         */
        public FillCheckAction(JDesktopPane rootView, JButton button, String title, JCheckBox checkBox)
        {
            super(rootView, button, title, checkBox);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == checkBox)
            {
                super.setFillEnable();
            }
            else if (e.getSource() == button)
            {
                super.setColorChooser();

                colorChooserObject.getSelectionModel().addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        Color color = colorChooserObject.getColor();
                        button.setBackground(color);
                    }

                });
            }
        }

        @Override
        public void settingAction()
        {
            button.setAction(this);
            checkBox.setAction(this);
            checkBox.setText("Fill:");
        }

    }

    /**
     * This class is for handle stroke(Color) and stroke width(double)
     * attributes for elements
     */
    public static class StrokeCheckAction extends PAShapeBarAction
    {
        /**
         * constructor for stroke
         *
         * @param rootView JDesktopPane
         * @param button JButton
         * @param title String
         * @param checkBox JCheckBox
         * @param spinner JSpinner
         */
        public StrokeCheckAction(JDesktopPane rootView, JButton button, String title, JCheckBox checkBox, JSpinner spinner)
        {
            super(rootView, button, title, checkBox, spinner);
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == checkBox)
            {
                super.setStrokeEnable();
            }
            else if (e.getSource() == button)
            {
                super.setColorChooser();

                colorChooserObject.getSelectionModel().addChangeListener(new ChangeListener()
                {
                    @Override
                    public void stateChanged(ChangeEvent e)
                    {
                        Color color = colorChooserObject.getColor();
                        button.setBackground(color);
                    }

                });
            }
        }

        @Override
        public void settingAction()
        {
            button.setAction(this);
            checkBox.setAction(this);
            checkBox.setText("Stroke:");
        }

    }
}
