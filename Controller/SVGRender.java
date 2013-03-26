package Controller;

import java.util.LinkedHashSet;
import java.awt.Dimension;
import Model.SVGReader;
import Model.Drawings;
/*
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.Iterator;
import java.util.LinkedHashSet;

import GUI.SVGDisplay;
import Model.Circles;
import Model.Drawings;
import Model.Lines;
import Model.Rectangles;
import Model.SVGReader;
import Model.Shapes;
*/
public class SVGRender {

	private SVGReader reader;
	//private double zoomScale;
	//private double xPosition;
	//private double yPosition;
	private String path;
	private Dimension svgDimension;
	private LinkedHashSet<Drawings> drawCollection;

	public SVGRender() 
	{
		// TODO Auto-generated constructor stub
		this.drawCollection = new LinkedHashSet<Drawings>();
		this.setPreferredSize(new Dimension(500, 500));
	}

	public SVGRender(String path)
	{
		// TODO Auto-generated constructor stub
		this.reader = new SVGReader();
		this.reader.setDoc(path);
		this.setPreferredSize(new Dimension((int)this.reader.getSVGElement().getWidth(),(int)this.reader.getSVGElement().getHeight()));
		this.drawCollection = this.reader.getDrawings();
		this.path = path;
	}
	
	public LinkedHashSet<Drawings> getDrawings()
	{
		return this.drawCollection;
	}


	private void setPreferredSize(Dimension dimension) {
		// TODO Auto-generated method stub
		this.svgDimension = dimension;
	}

	public Dimension getPreferredSize()
	{
		return this.svgDimension;
	}
	
	public String getPath()
	{
		return this.path;
	}
}
