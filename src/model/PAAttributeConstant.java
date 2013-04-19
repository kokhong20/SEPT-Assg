package model;

import java.awt.Color;

public interface PAAttributeConstant {
	public static final int DEFAULT_SVG_SIZE = 500; // SVG tag default width/height
	public static final int DEFAULT_STROKE_WIDTH  = 1; // SVG default stroke width
	public static final int DEFAULT_LENGTH = 0; // SVG default length for others
	public static final int STROKE = 0; // SVG elements stroke
	public static final int FILL = 1; // SVG elements fill
	public static final Color DEFAULT_FILL = Color.black; // SVG default fill color
	public static final Color DEFAULT_STROKE = PAColor.none.getColor(); // SVG default stroke color
}
