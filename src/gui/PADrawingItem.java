package gui;

import controller.PAHandCursor;
import controller.PAMenuAction;
import controller.PADrawingShapeAction;
import controller.PAFillBucketAction;
import controller.PASelectCursorAction;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

import model.PASystem;

/**
 *
 * @author KokHong
 * @since 1.1
 * <p>
 * This is the component inside the Drawing Kit
 * </p>
 *
 */
public class PADrawingItem
{
    public static JToggleButton buttonSelected;
    public static JButton zoomIn;
    public static JButton zoomOut;
    public static JToggleButton fill;
    public static JToggleButton handCursor;
    public static JToggleButton selectCursor;
    public static JToggleButton line;
    public static JToggleButton rectangle;
    public static JToggleButton circle;
    public static JButton group;
    public static JButton ungroup;
    private static final int maxWidth = 40;
    private static final int maxHeight = 40;
    private PADrawingKit drawKitPanel;
    private PAMainFrame mainFrame;
    public static JLabel title;

    /**
     * <p>
     * construct to define this PADrawingKitButton for the PADrawingKit
     * </p>
     *
     * @param drawKitPanel the initialized PADrawingKit
     */
    public PADrawingItem(PADrawingKit drawKitPanel)
    {
        this.drawKitPanel = drawKitPanel;
        mainFrame = drawKitPanel.mainFrame;
        createButton();
        addAction(mainFrame);
        setUpButton();
        addButton();
        addTitle();
    }

    /**
     * Create title for drawing kit
     */
    private void addTitle()
    {
        title = new JLabel(PASystem.getWord("Tools"));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Helvetica", 10, 12));
        title.setBounds(24, 0, 40, 20);
        title.setBackground(new Color(40, 40, 40));
        title.setOpaque(true);
        drawKitPanel.add(title);
    }

    /**
     * add all buttons to drawing kit
     */
    private void addButton()
    {
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
        zoomIn = new JButton();
        zoomOut = new JButton();
        fill = new JToggleButton();
        handCursor = new JToggleButton();
        selectCursor = new JToggleButton();
        line = new JToggleButton();
        rectangle = new JToggleButton();
        circle = new JToggleButton();
        group = new JButton();
        ungroup = new JButton();
    }

    /**
     * <p>
     * Set up all the component in the Drawing Kit
     * </p>
     *
     */
    public static void setUpButton()
    {
        setToggleButtonAttribute("resources/select.png", selectCursor,
                PASystem.getWord("SelectCursor"), 40, 20);
        setToggleButtonAttribute("resources/line.png", line, PASystem.getWord("Line"), 40, 100);
        setToggleButtonAttribute("resources/rect.png", rectangle, PASystem.getWord("Rectangle"),
                0, 140);
        setToggleButtonAttribute("resources/circle 30x30.png", circle,
                PASystem.getWord("Circle"), 40, 140);
        setButtonAttribute("resources/zoomin.png", zoomIn, PASystem.getWord("ZoomIn"), 0, 60);
        setButtonAttribute("resources/zoomout.png", zoomOut, PASystem.getWord("ZoomOut"), 40, 60);
        setButtonAttribute("resources/group.png", group, PASystem.getWord("Group"), 0, 180);
        setButtonAttribute("resources/ungroup.png", ungroup, PASystem.getWord("Ungroup"), 40, 180);
        setToggleButtonAttribute("resources/cursor.png", handCursor,
                PASystem.getWord("HandCursor"), 0, 20);
        setToggleButtonAttribute("resources/fill 30x30.png", fill, PASystem.getWord("Fill"), 0,
                100);
    }

    /**
     * set an image icon, tool tip and position for a JButton
     *
     * @param imgPath url path to the image
     * @param button a JButton
     * @param toolTip String to set for toolTip
     * @param x X position for set bounds
     * @param y Y position for set bounds
     */
    private static void setToggleButtonAttribute(String imgPath,
            JToggleButton button, String toolTip, int x, int y)
    {
        ImageIcon imgIcon = new ImageIcon(imgPath);
        button.setIcon(imgIcon);
        button.setBackground(new Color(40, 40, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(null);
        button.setToolTipText(toolTip);
        button.setBounds(x, y, maxWidth, maxHeight);
    }

    private static void setButtonAttribute(String imgPath, JButton button,
            String toolTip, int x, int y)
    {
        ImageIcon imgIcon = new ImageIcon(imgPath);
        button.setIcon(imgIcon);
        button.setBackground(new Color(40, 40, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setBorder(null);
        button.setToolTipText(toolTip);
        button.setBounds(x, y, maxWidth, maxHeight);
    }

    /**
     * <p>
     * Add action to the component of Drawing Kit
     * </p>
     *
     * @param mainFrame the Active Main Frame
     */
    public static void addAction(PAMainFrame mainFrame)
    {
        // SelectCursor
        PASelectCursorAction selectCusrsorAction = new PASelectCursorAction(
                mainFrame.svgPanel, selectCursor, mainFrame.attributeBar);
        selectCursor.setAction(selectCusrsorAction);

        // Line
        PADrawingShapeAction.DrawLineAction drawLineAction = new PADrawingShapeAction.DrawLineAction(
                mainFrame.svgPanel, line, mainFrame.attributeBar);
        line.setAction(drawLineAction);

        // Rectangle
        PADrawingShapeAction.DrawRectangleAction drawRectAction = new PADrawingShapeAction.DrawRectangleAction(
                mainFrame.svgPanel, rectangle, mainFrame.attributeBar);
        rectangle.setAction(drawRectAction);

        // Circle
        PADrawingShapeAction.DrawCircleAction drawCircleAction = new PADrawingShapeAction.DrawCircleAction(
                mainFrame.svgPanel, circle, mainFrame.attributeBar);
        circle.setAction(drawCircleAction);

        // ZoomIn
        PAMenuAction.ZoomIn zoomInAction = new PAMenuAction.ZoomIn(
                mainFrame.svgPanel, zoomIn);
        zoomIn.setAction(zoomInAction);

        // ZoomOut
        PAMenuAction.ZoomOut zoomOutAction = new PAMenuAction.ZoomOut(
                mainFrame.svgPanel, zoomOut);
        zoomOut.setAction(zoomOutAction);

        // Pan
        PAHandCursor panAction = new PAHandCursor(mainFrame.svgPanel,
                handCursor, mainFrame.attributeBar);
        handCursor.setAction(panAction);

        // Group
        PAMenuAction.GroupAction groupAction = new PAMenuAction.GroupAction(
                mainFrame.svgPanel, group);
        group.setAction(groupAction);

        // UnGroup
        PAMenuAction.UnGroupAction unGroupAction = new PAMenuAction.UnGroupAction(
                mainFrame.svgPanel, ungroup);
        ungroup.setAction(unGroupAction);

        // Fill
        PAFillBucketAction fillAction = new PAFillBucketAction(
                mainFrame.svgPanel, fill, mainFrame.attributeBar);
        fill.setAction(fillAction);
    }

    public static void resetDrawingItemText()
    {
        try
        {
            fill.setToolTipText(PASystem.getWord("Fill"));
            handCursor.setToolTipText(PASystem.getWord("HandCursor"));
            selectCursor.setToolTipText(PASystem.getWord("SelectCursor"));
            line.setToolTipText(PASystem.getWord("Line"));
            rectangle.setToolTipText(PASystem.getWord("Rectangle"));
            circle.setToolTipText(PASystem.getWord("Circle"));
            group.setToolTipText(PASystem.getWord("Group"));
            ungroup.setToolTipText(PASystem.getWord("Ungroup"));
            zoomIn.setToolTipText(PASystem.getWord("ZoomIn"));
            zoomOut.setToolTipText(PASystem.getWord("ZoomOut"));
            title.setText(PASystem.getWord("Tools"));
        }
        catch (NullPointerException e)
        {
        }

    }

}
