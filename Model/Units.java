package Model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.regex.Pattern;

public class Units
{
	public static Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();
	public static int res = Toolkit.getDefaultToolkit().getScreenResolution();

	public static double height = screen.getHeight();
	public static double width = screen.getWidth();
	public static double size = Math.sqrt((height * width));
	public static double ratio = (res / size) + 1;
	public static final double dpi = res * ratio;

	public final static double setUnit(String att)
	{
		att = att.toLowerCase();
		if (!att.isEmpty())
		{
			if (Pattern.matches("(\\d+\\.?\\d)+(em|ex|px|in|cm|mm|pt|pc)", att)
					|| Pattern.matches("(\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att))
			{
				switch (att.substring(att.length() - 2))
				{
				case "em":
					return convertEM(att.replace(
							att.substring(att.length() - 2), ""));
				case "ex":
					return convertEX(att.replace(
							att.substring(att.length() - 2), ""));
				case "px":
					return convertPX(att.replace(
							att.substring(att.length() - 2), ""));
				case "in":
					return convertIN(att.replace(
							att.substring(att.length() - 2), ""));
				case "cm":
					return convertCM(att.replace(
							att.substring(att.length() - 2), ""));
				case "mm":
					return convertMM(att.replace(
							att.substring(att.length() - 2), ""));
				case "pt":
					return convertPT(att.replace(
							att.substring(att.length() - 2), ""));
				case "pc":
					return convertPC(att.replace(
							att.substring(att.length() - 2), ""));
				}
			}
			else if (Pattern
					.matches("(\\.?\\d)+(em|ex|px|in|cm|mm|pt|pc)", att))
			{
				att = "0" + att;
				switch (att.substring(att.length() - 2))
				{
				case "em":
					return convertEM(att.replace(
							att.substring(att.length() - 2), ""));
				case "ex":
					return convertEX(att.replace(
							att.substring(att.length() - 2), ""));
				case "px":
					return convertPX(att.replace(
							att.substring(att.length() - 2), ""));
				case "in":
					return convertIN(att.replace(
							att.substring(att.length() - 2), ""));
				case "cm":
					return convertCM(att.replace(
							att.substring(att.length() - 2), ""));
				case "mm":
					return convertMM(att.replace(
							att.substring(att.length() - 2), ""));
				case "pt":
					return convertPT(att.replace(
							att.substring(att.length() - 2), ""));
				case "pc":
					return convertPC(att.replace(
							att.substring(att.length() - 2), ""));
				}
			}
			else if (Pattern.matches("(\\d+)", att)
					|| Pattern.matches("(\\d+.\\d+)", att))
			{
				return Double.parseDouble(att);
			}
		}
		return 0;
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
		System.out.println("res: " + res + " ratio: " + ratio + " height: " + height + " width: " + width);
		return Double.parseDouble(bConvert) * dpi;
	}

}
