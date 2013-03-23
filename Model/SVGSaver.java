package Model;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;
//import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class SVGSaver {

	public SVGSaver(LinkedHashSet<Drawings> drawings, String path) {
		// TODO Auto-generated constructor stub
		try
		{
			 DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			 DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			 //root elements
			 Document doc = docBuilder.newDocument();
			 
			 Element svg = doc.createElement("svg");
			 doc.appendChild(svg);

			Iterator<Drawings> it = drawings.iterator();
				
			while(it.hasNext())
			{
				Drawings draw = it.next();
				
				if(draw instanceof Rectangles)
				{
					Rectangles saveRect = (Rectangles) draw;
					
					Element rect = doc.createElement("rect");
					rect.setAttribute("width", String.valueOf(saveRect.getWidth()));
					rect.setAttribute("height", String.valueOf(saveRect.getHeight()));
					rect.setAttribute("x", String.valueOf(saveRect.getX()));
					rect.setAttribute("y", String.valueOf(saveRect.getY()));
					rect.setAttribute("fill", String.valueOf("rgb(" + saveRect.getFill().getRed() + "," + saveRect.getFill().getGreen() + "," + saveRect.getFill().getBlue()) + ")");
					rect.setAttribute("stroke", String.valueOf("rgb(" + saveRect.getStrokeColor().getRed() + "," + saveRect.getStrokeColor().getGreen() + "," + saveRect.getStrokeColor().getBlue()) + ")");
					rect.setAttribute("stroke-width", String.valueOf(saveRect.getStrokeWidth()));
					
					svg.appendChild(rect);
					
				}
				else if(draw instanceof Circles)
				{
					Circles saveCircle = (Circles) draw;	

					Element circle = doc.createElement("circle");
					circle.setAttribute("cx", String.valueOf(saveCircle.getCX()));
					circle.setAttribute("cy", String.valueOf(saveCircle.getCY()));
					circle.setAttribute("r", String.valueOf(saveCircle.getR()));
					circle.setAttribute("fill", String.valueOf("rgb(" + saveCircle.getFill().getRed() + "," + saveCircle.getFill().getGreen() + "," + saveCircle.getFill().getBlue()) + ")");
					circle.setAttribute("stroke", String.valueOf("rgb(" + saveCircle.getStrokeColor().getRed() + "," + saveCircle.getStrokeColor().getGreen() + "," + saveCircle.getStrokeColor().getBlue()) + ")");
					circle.setAttribute("stroke-width", String.valueOf(saveCircle.getStrokeWidth()));
					
					svg.appendChild(circle);
				}
					else if(draw instanceof Lines)
				{
					Lines saveLine = (Lines) draw;

					Element line = doc.createElement("line");
					line.setAttribute("x1", String.valueOf(saveLine.getX1()));
					line.setAttribute("x2", String.valueOf(saveLine.getX2()));
					line.setAttribute("y1", String.valueOf(saveLine.getY1()));
					line.setAttribute("y2", String.valueOf(saveLine.getY2()));
					line.setAttribute("stroke", String.valueOf("rgb(" + saveLine.getStrokeColor().getRed() + "," + saveLine.getStrokeColor().getGreen() + "," + saveLine.getStrokeColor().getBlue()) + ")");
					line.setAttribute("stroke-width", String.valueOf(saveLine.getStrokeWidth()));
					
					svg.appendChild(line);
				} 			
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result =  new StreamResult(new File(path));
			transformer.transform(source, result);

			System.out.println("Done");
		}
		catch(ParserConfigurationException pce)
		{
			  pce.printStackTrace();
		}
		catch(TransformerException tfe)
		{
			  tfe.printStackTrace();
		}
	}
}
