package model;

import java.awt.Color;

/**
 * 
 * @author bryantylai
 * @since 1.1
 * @version 1.1
 * <p>This interface determines the constants required for svg</p>
 */
public interface PAAttributeConstant {
	public int DEFAULT_SVG_SIZE = 500; // SVG tag default width/height
	public int DEFAULT_STROKE_WIDTH  = 1; // SVG default stroke width
	public int DEFAULT_LENGTH = 0; // SVG default length for others
	public int STROKE = 0; // SVG elements stroke
	public int FILL = 1; // SVG elements fill
	public Color DEFAULT_FILL = Color.black; // SVG default fill color
	public Color DEFAULT_STROKE = PAColor.none.getColor(); // SVG default stroke color
	public int UNIT_EM = 0 ;
	public int UNIT_EX = 1;
	public int UNIT_PX = 2;
	public int UNIT_IN = 3;
	public int UNIT_CM = 4;
	public int UNIT_MM = 5;
	public int UNIT_PT = 6;
	public int UNIT_PC = 7;
	public int UNIT_PERCENT = 8;
}
