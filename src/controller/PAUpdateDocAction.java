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
import model.PASVGTag;

/**
 *
 * @author LiHao
 */
public class PAUpdateDocAction implements ActionListener
{
    private PASVGTag svgTag;
    private PASVGPanel drawPanel;
    private PANewFileSetting self;

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
            double width = Double.parseDouble(textMap.get("width"));
            double height = Double.parseDouble(textMap.get("height"));
            svgTag.setWidth(width);
            svgTag.setHeight(height);
            drawPanel.zoomInOutSVG(drawPanel.getScale());
        }
        
        self.setVisible(false);
        self.dispose();
    }

}
