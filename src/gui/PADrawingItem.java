package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author KokHong
 *
 */
public class PADrawingItem
{
    private final int maxWidth = 40;
    private final int maxHeight = 40;
    private PADrawingKit drawKitPanel;
    private JLabel title;
    public JButton fill;
    public JButton handCursor;
    public JButton selectCursor;
    public JButton line;
    public JButton rectangle;
    public JButton circle;
    public JButton group;
    public JButton ungroup;
    public JButton zoomIn;
    public JButton zoomOut;

    /**
     * construct to define this PADrawingKitButton for the PADrawingKit
     *
     * @param drawKitPanel PADrawingKit
     */
    public PADrawingItem(PADrawingKit drawKitPanel)
    {
        this.drawKitPanel = drawKitPanel;
    }

    /**
     * Initialize Components
     */
    public void initPanel()
    {
        addButton();
        addTitle();
    }

    /**
     * Create title for drawing kit
     */
    private void addTitle()
    {
        title = new JLabel("Tools");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Helvetica", 10, 12));
        title.setBounds(24, 0, 40, 20);
        drawKitPanel.add(title);
    }

    /**
     * add all buttons to drawing kit
     */
    private void addButton()
    {
        this.createButton();

        handCursor.setBounds(0, 20, maxWidth, maxHeight);
        zoomIn.setBounds(0, 60, maxWidth, maxHeight);
        selectCursor.setBounds(40, 20, maxWidth, maxHeight);
        zoomOut.setBounds(40, 60, maxWidth, maxHeight);
        fill.setBounds(0, 100, maxWidth, maxHeight);
        line.setBounds(40, 100, maxWidth, maxHeight);
        rectangle.setBounds(0, 140, maxWidth, maxHeight);
        circle.setBounds(40, 140, maxWidth, maxHeight);
        group.setBounds(0, 180, maxWidth, maxHeight);
        ungroup.setBounds(40, 180, maxWidth, maxHeight);

        drawKitPanel.add(fill);
        drawKitPanel.add(handCursor);
        drawKitPanel.add(selectCursor);
        drawKitPanel.add(line);
        drawKitPanel.add(rectangle);
        drawKitPanel.add(circle);
        drawKitPanel.add(group);
        drawKitPanel.add(ungroup);
        drawKitPanel.add(zoomIn);
        drawKitPanel.add(zoomOut);

    }

    /**
     * create all buttons for drawing kit
     */
    private void createButton()
    {
        fill = new JButton();
        handCursor = new JButton();
        selectCursor = new JButton();
        line = new JButton();
        rectangle = new JButton();
        circle = new JButton();
        group = new JButton();
        ungroup = new JButton();
        zoomIn = new JButton();
        zoomOut = new JButton();

        setImageIcon("resources/fill 30x30.png", fill, "Fill");
        setImageIcon("resources/cursor.png", handCursor, "Hand Cursor");
        setImageIcon("resources/select.png", selectCursor, "Select Cursor");
        setImageIcon("resources/rect.png", rectangle, "Rectangle");
        setImageIcon("resources/line.png", line, "Line");
        setImageIcon("resources/circle 30x30.png", circle, "Circle");
        setImageIcon("resources/group.png", group, "Group");
        setImageIcon("resources/ungroup.png", ungroup, "Ungroup");
        setImageIcon("resources/zoomin.png", zoomIn, "Zoom In");
        setImageIcon("resources/zoomout.png", zoomOut, "Zoom Out");
    }

    /**
     * set an image icon and tool tip for a JButton
     *
     * @param imgPath url path to the image
     * @param button a JButton
     * @param toolTip String to set for toolTip
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

}
