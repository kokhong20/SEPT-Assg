package gui;

import controller.PAToolKitAction;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

/**
 *
 * @author KokHong
 *
 */
public class PADrawingItem
{
    public static JToggleButton buttonSelected;
    private final int maxWidth = 40;
    private final int maxHeight = 40;
    private PADrawingKit drawKitPanel;
    private PAMainFrame mainFrame;
    private JLabel title;
    public JToggleButton fill;
    public JToggleButton handCursor;
    public JToggleButton selectCursor;
    public JToggleButton line;
    public JToggleButton rectangle;
    public JToggleButton circle;
    public JToggleButton group;
    public JToggleButton ungroup;
    public JToggleButton zoomIn;
    public JToggleButton zoomOut;

    /**
     * construct to define this PADrawingKitButton for the PADrawingKit
     *
     * @param drawKitPanel PADrawingKit
     */
    public PADrawingItem(PADrawingKit drawKitPanel)
    {
        this.drawKitPanel = drawKitPanel;
        mainFrame = drawKitPanel.mainFrame;
        createButton();
        addAction();
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
        setButtonAttribute("resources/fill 30x30.png", fill, "Fill",0,100);
        setButtonAttribute("resources/cursor.png", handCursor, "Hand Cursor",0,20);
        setButtonAttribute("resources/select.png", selectCursor, "Select Cursor",40,20);
        setButtonAttribute("resources/rect.png", rectangle, "Rectangle",0,140);
        setButtonAttribute("resources/line.png", line, "Line",40,100);
        setButtonAttribute("resources/circle 30x30.png", circle, "Circle",40,140);
        setButtonAttribute("resources/group.png", group, "Group",0,180);
        setButtonAttribute("resources/ungroup.png", ungroup, "Ungroup",40,180);
        setButtonAttribute("resources/zoomin.png", zoomIn, "Zoom In",0,60);
        setButtonAttribute("resources/zoomout.png", zoomOut, "Zoom Out",40,60);
        
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
        fill = new JToggleButton();
        handCursor = new JToggleButton();
        selectCursor = new JToggleButton();
        line = new JToggleButton();
        rectangle = new JToggleButton();
        circle = new JToggleButton();
        group = new JToggleButton();
        ungroup = new JToggleButton();
        zoomIn = new JToggleButton();
        zoomOut = new JToggleButton();

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
    public void setButtonAttribute(String imgPath, JToggleButton button, String toolTip , int x , int y)
    {
        ImageIcon imgIcon = new ImageIcon(imgPath);
        button.setIcon(imgIcon);
        button.setBackground(new Color(40, 40, 40));
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setBorder(null);
//        button.setBorderPainted(false);
        button.setToolTipText(toolTip);
        button.setBounds(x,y,maxWidth,maxHeight);
    }

    private void addAction()
    {
        //Rectangle
    	PAToolKitAction.DrawRectangleAction drawRectAction = new PAToolKitAction.DrawRectangleAction(mainFrame.svgPanel,rectangle,mainFrame.attributeBar);
        rectangle.setAction(drawRectAction);
        
        //Line
        PAToolKitAction.DrawLineAction drawLineAction = new PAToolKitAction.DrawLineAction(mainFrame.svgPanel,line,mainFrame.attributeBar);
        line.setAction(drawLineAction);
    }

}
