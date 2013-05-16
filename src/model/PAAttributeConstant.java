package model;

import java.awt.Color;

/**
 *
 * @author bryantylai
 * @since 18 April 2013
 * <p>This interface determines the constants required for svg</p>
 */
public interface PAAttributeConstant
{
    public int DEFAULT_SVG_SIZE = 500; // SVG tag default width/height
    public int DEFAULT_STROKE_WIDTH = 1; // SVG default stroke width
    public int DEFAULT_LENGTH = 0; // SVG default length for others
    public int STROKE = 0; // SVG elements stroke
    public int FILL = 1; // SVG elements fill
    public Color DEFAULT_FILL = Color.black; // SVG default fill color
    public Color DEFAULT_STROKE = PAColor.none.getColor(); // SVG default stroke color
}
