package Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import javax.swing.JPanel;

public class RenderSVG extends JPanel
{
	public RenderSVG()
	{
		this.setBackground(Color.white);
		this.setSize(500, 500);
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		//for anti-aliasing for better output.
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(!SVGReader.rectShapeList.isEmpty())
		{
			for(int i=0;i<SVGReader.rectShapeList.size() ; i++)
			{
				System.out.println(SVGReader.rectShapeList.get(i));
				g2d.draw(SVGReader.rectShapeList.get(i));
			}
		}
		
		if(!SVGReader.circleShapeList.isEmpty())
		{
			for(int i=0;i<SVGReader.circleShapeList.size() ; i++)
			{
				System.out.println(SVGReader.circleShapeList.get(i));
				g2d.fill(SVGReader.circleShapeList.get(i));
			}
		}
	}
}
