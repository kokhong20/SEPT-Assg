package Model;

import java.util.regex.Pattern;

/**
 * @author bryantylai
 *
 */

public class PAUnit
{
	/**
	 * Default dots per pixels
	 */
	private static double dpi = 96;
	
	/**
	 * 
	 * @param att
	 * @param isStrokeWidth
	 * @return
	 */
	public final static double setUnit(String att, boolean isStrokeWidth)
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
				
		if(isStrokeWidth)
			return 1;
		else 
			return 0;
	}
	
	/**
	 * 
	 * @param att
	 * @return
	 */
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
	
	/**
	 * 
	 * @param att
	 * @return
	 */
	private static String removeUnits(String att)
	{
		return att.replace(att.substring(att.length() - 2), "");
	}
	
	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertEM(String value)
	{
		return (Double.parseDouble(value) * 12 * dpi / 72);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertEX(String value)
	{
		return Double.parseDouble(value) * 7;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertPX(String value)
	{
		return Double.parseDouble(value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertPT(String value)
	{
		return (Double.parseDouble(value) * dpi / 72);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertPC(String value)
	{
		return (Double.parseDouble(value) * dpi / 6);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertMM(String value)
	{
		return (Double.parseDouble(value) * dpi / 25.4);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertCM(String value)
	{
		return (Double.parseDouble(value) * dpi / 2.54);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public final static double convertIN(String value)
	{
		return Double.parseDouble(value) * dpi;
	}

}
