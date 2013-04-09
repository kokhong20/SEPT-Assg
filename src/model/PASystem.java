package model;

import java.awt.Dimension;
import java.awt.Toolkit;

/**
 * @author bryantylai
 *
 */
public class PASystem {
	private static Toolkit toolkit =  Toolkit.getDefaultToolkit ();
	private static Dimension dimension = toolkit.getScreenSize();
	private static int screenResolution = Toolkit.getDefaultToolkit().getScreenResolution();
	private static double screenHeight = dimension.getHeight();
	private static double screenWidth = dimension.getWidth();
	private static double screenSize = Math.sqrt((screenHeight * screenWidth));
	private static double screenRatio = (screenResolution / screenSize) + 1;
	private static final double dotsPerPixels = screenResolution * screenRatio;
	
	/**
	 * @return the dimension
	 */
	public static Dimension getDimension() {
		return dimension;
	}

	/**
	 * @return the screenResolution
	 */
	public static int getScreenResolution() {
		return screenResolution;
	}

	/**
	 * @return the screenHeight
	 */
	public static double getScreenHeight() {
		return screenHeight;
	}

	/**
	 * @return the screenWidth
	 */
	public static double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * @return the screenSize
	 */
	public static double getScreenSize() {
		return screenSize;
	}

	/**
	 * @return the screenRatio
	 */
	public static double getScreenRatio() {
		return screenRatio;
	}

	/**
	 * @return the dotsperpixels
	 */
	public static double getDotsperpixels() {
		return dotsPerPixels;
	}
	
}
