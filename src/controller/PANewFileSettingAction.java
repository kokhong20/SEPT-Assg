package controller;

import gui.PADrawingKit;
import gui.PAMainFrame;
import gui.PANewFileSetting;
import gui.PAStartMenu;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.JDesktopPane;
import model.PASVGContainer;
import model.PASVGTag;

/**
 *
 * @author LiHao
 * @since 1.1
 *
 * <p>
 * This class creates a PANewFileSettingAction to set action performed
 * PANewFileSetting frame.
 * </p>
 *
 */
public class PANewFileSettingAction implements ActionListener
{
    private int svgWidth;
    private int svgHeight;
    private boolean checkDrawingKit;
    private BufferedImage svgImage;
    private JDesktopPane parent;
    private PAStartMenu startMenu;
    private PANewFileSetting self;

    /**
     *
     * Create a PANewFileSettingAction which accept JDeskropPane as parent to
     * show JInternalFrame and PANewFileSetting for dispose after action
     * completed
     *
     * @param parent set PANewFileSetting parent
     * @param self set PANewFileSetting which perform action
     */
    public PANewFileSettingAction(JDesktopPane parent, PANewFileSetting self)
    {
        this.parent = parent;
        this.self = self;
        this.checkDrawingKit = false;
    }

    /**
     *
     * Create a PANewFileSettingAction which accept JDeskropPane as parent to
     * show JInternalFrame and PANewFileSetting for dispose after action
     * completed
     *
     * @param parent set PANewFileSetting parent
     * @param self set PANewFileSetting which perform action
     * @param startMenu set PAStartMenu for startup
     */
    public PANewFileSettingAction(JDesktopPane parent, PANewFileSetting self, PAStartMenu startMenu)
    {
        this.parent = parent;
        this.self = self;
        this.startMenu = startMenu;
        this.checkDrawingKit = false;
    }

    private void drawToImage()
    {
        svgImage = new BufferedImage(svgWidth, svgHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = svgImage.createGraphics();

        //for anti-aliasing for better output.
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillRect(0, 0, svgWidth, svgHeight);
        g2d.setPaint(new Color(255, 255, 255, 255));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("OK"))
        {
            HashMap<String, String> textMap = self.getFieldText();
            String fileName = textMap.get("fileName");

            if (!fileName.contains(".svg"))
            {
                fileName = fileName.concat(".svg");
            }

            String width = textMap.get("width");
            String height = textMap.get("height");
            String unit = textMap.get("unit");
            width = width.concat(unit);
            height = height.concat(unit);
            PASVGTag svgTag = new PASVGTag(width, height, unit);
            svgWidth = (int) svgTag.getWidth();
            svgHeight = (int) svgTag.getHeight();
            PASVGContainer svgContainer = new PASVGContainer(svgTag, fileName);
            drawToImage();
            PAMainFrame svgDisplay = new PAMainFrame(parent, svgContainer, fileName);
            parent.add(svgDisplay);
            svgDisplay.toFront();

            if (startMenu != null)
            {
                startMenu.setVisible(false);
                startMenu.dispose();
            }

            for (int i = 0; i < parent.getComponentCount(); i++)
            {
                if (parent.getComponent(i) instanceof PADrawingKit)
                {
                    checkDrawingKit = true;
                }
            }
            if (checkDrawingKit == false)
            {
                PADrawingKit drawingKit = new PADrawingKit(svgDisplay);
                parent.add(drawingKit);
            }
        }

        self.setVisible(false);
        self.dispose();
        PAMenuAction.NewFile.newFileSetting = null;
    }

}
