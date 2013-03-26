package Controller;

import java.util.LinkedHashSet;
import java.awt.Dimension;
import Model.SVGReader;
import Model.Drawings;

public class SVGRender {

	private SVGReader reader;
	private String path;
	private Dimension svgDimension;
	private LinkedHashSet<Drawings> drawCollection;

	// Constructor is used when new SVG is created
	public SVGRender() 
	{
		// TODO Auto-generated constructor stub
		this.setPreferredSize(new Dimension(500, 500));
		this.drawCollection = new LinkedHashSet<Drawings>();
	}

	// Constructor is used when existing SVG is opened
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
