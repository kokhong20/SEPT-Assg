package Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.regex.Pattern;

public class Units
{
	public static Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();
	public static int res = Toolkit.getDefaultToolkit().getScreenResolution();

	// These static variables only used once, no need to create variable/memory location for them
	// public static double height = screen.getHeight();
	// public static double width = screen.getWidth();
	// public static double size = Math.sqrt((height * width));
	// public static double ratio = (res / size) + 1;
	
	public static final double dpi = res * (res / Math.sqrt((screen.getHeight() * screen.getWidth())) + 1);

	public final static double setUnit(String att)
	{
		att = att.toLowerCase();
		if (!att.isEmpty())
		{
			// 10.10px or 10px or .10px are valid
			if (Pattern.matches("(\\-?\\d+\\.?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc)", att)
					|| Pattern.matches("(\\-?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc)", att)
					|| Pattern.matches("(\\-?\\.?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc)", att))
			{
				switch (att.substring(att.length() - 2))
				{
				case "em":
					return convertEM(calculate(removeUnits(att)));
				case "ex":
					return convertEX(calculate(removeUnits(att)));
				case "px":
					return convertPX(calculate(removeUnits(att)));
				case "in":
					return convertIN(calculate(removeUnits(att)));
				case "cm":
					return convertCM(calculate(removeUnits(att)));
				case "mm":
					return convertMM(calculate(removeUnits(att)));
				case "pt":
					return convertPT(calculate(removeUnits(att)));
				case "pc":
					return convertPC(calculate(removeUnits(att)));
				}
			}
			// 10 or 10.10 or .10 are valid
			else if (Pattern.matches("(\\-?\\d+[e]?\\d*)", att)
					|| Pattern.matches("(\\-?\\d+.\\d+[e]?\\d*)", att)
					|| Pattern.matches("(\\-?\\.?\\d+[e]?\\d*)", att))
			{
				return Double.parseDouble(calculate(att));
			}
		}
		return 0;
	}
	
	private static String calculate(String att)
	{
		if(att.contains("e"))
		{
			int loop = Integer.parseInt(att.substring(att.indexOf("e") + 1));
			double value = Double.parseDouble(att.substring(0, att.indexOf("e")));

			value = value * Math.pow(10, loop);
			
			return String.valueOf(value);
		}
		
		return att;
	}
	
	// Remove the last 2 characters which are units
	private static String removeUnits(String att)
	{
		return att.replace(att.substring(att.length() - 2), "");
	}
	
	// Calculate based on 1 em = 12 pt
	public final static double convertEM(String bConvert)
	{
		return (Double.parseDouble(bConvert) * 12 * dpi / 72);
	}

	// Assumption made based on default of browsers(Firefox, IE, Chrome)
	public final static double convertEX(String bConvert)
	{
		return Double.parseDouble(bConvert) * 7;
	}

	public final static double convertPX(String bConvert)
	{
		return Double.parseDouble(bConvert);
	}

	public final static double convertPT(String bConvert)
	{
		return (Double.parseDouble(bConvert) * dpi / 72);
	}

	public final static double convertPC(String bConvert)
	{
		return (Double.parseDouble(bConvert) * dpi / 6);
	}

	public final static double convertMM(String bConvert)
	{
		return (Double.parseDouble(bConvert) * dpi / 25.4);
	}

	public final static double convertCM(String bConvert)
	{
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println("cm =" + bConvert + "  converted = "
				+ Double.parseDouble(bConvert) * dpi / 2.54);
		System.out.println(screen);
		return (Double.parseDouble(bConvert) * dpi / 2.54);

	}

	public final static double convertIN(String bConvert)
	{
		System.out.println("in = " + bConvert + " converted = "
				+ Double.parseDouble(bConvert) * dpi);
		//System.out.println("res: " + res + " ratio: " + ratio + " height: " + height + " width: " + width);
		return Double.parseDouble(bConvert) * dpi;
	}

}
