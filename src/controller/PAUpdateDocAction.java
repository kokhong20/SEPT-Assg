/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gui.PANewFileSetting;
import gui.PASVGPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import static model.PAAttributeConstant.DEFAULT_SVG_SIZE;
import model.PASVGTag;
import model.PAUnit;

/**
 *
 * @author LiHao
 * @since 1.1
 *
 * <p>
 * This class creates a PAUpdateDocAction to set action performed
 * PAUpdateDocAction frame.
 * </p>
 *
 */
public class PAUpdateDocAction implements ActionListener
{
    private PASVGTag svgTag;
    private PASVGPanel drawPanel;
    private PANewFileSetting self;

    /**
     *
     * Create a new PAUpdateDocAction which accept PASVGTag to be updated,
     * PASVGPanel to update after change and PANewFileSetting to dispose after
     * complete action.
     *
     * @param svgTag set PASVGTag to be updated
     * @param drawPanel set PASVGPanel to update after change
     * @param self set PANewFileSetting which perform action
     */
    public PAUpdateDocAction(PASVGTag svgTag, PASVGPanel drawPanel, PANewFileSetting self)
    {
        this.svgTag = svgTag;
        this.drawPanel = drawPanel;
        this.self = self;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equals("OK"))
        {
            HashMap<String, String> textMap = self.getFieldText();
            String width = textMap.get("width");
            String height = textMap.get("height");
            String unit = textMap.get("unit");
            width = width.concat(unit);
            height = height.concat(unit);
            svgTag.setWidth(PAUnit.setUnit(width, DEFAULT_SVG_SIZE));
            svgTag.setHeight(PAUnit.setUnit(height, DEFAULT_SVG_SIZE));
            svgTag.setUnit(unit);
            drawPanel.zoomInOutSVG(drawPanel.getScale());
            drawPanel.reDrawImage(drawPanel.getScale());
        }

        self.setVisible(false);
        self.dispose();
    }

}
