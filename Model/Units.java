package Model;

import java.util.regex.Pattern;

public class Units
{
	public final static double setUnit(String att)
	{
		att = att.toLowerCase();
			if(!att.isEmpty())
			{
				if(Pattern.matches("(\\d+\\.?\\d)+(em|ex|px|in|cm|mm|pt|pc)", att) || Pattern.matches("(\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att))
				{
					switch(att.substring(att.length() - 2))
					{
						case "em": 
							return convertEM(att.replace("em", "")); 
						case "ex": 
							return convertEX(att.replace("ex", ""));
						case "px":
							return convertPX(att.replace("px", ""));
						case "in":
							return convertIN(att.replace("in", ""));
						case "cm":
							return convertCM(att.replace("cm", ""));
						case "mm":
							return convertMM(att.replace("mm", ""));
						case "pt":
							return convertPT(att.replace("pt", ""));
						case "pc":
							return convertPC(att.replace("pc", ""));
					}
				}
				else if(Pattern.matches("(\\.?\\d)+(em|ex|px|in|cm|mm|pt|pc)", att))
				{
					att = "0" + att;
					switch(att.substring(att.length() - 2))
					{
						case "em": 
							return convertEM(att.replace("em", "")); 
						case "ex": 
							return convertEX(att.replace("ex", ""));
						case "px":
							return convertPX(att.replace("px", ""));
						case "in":
							return convertIN(att.replace("in", ""));
						case "cm":
							return convertCM(att.replace("cm", ""));
						case "mm":
							return convertMM(att.replace("mm", ""));
						case "pt":
							return convertPT(att.replace("pt", ""));
						case "pc":
							return convertPC(att.replace("pc", ""));
					}
				}
				else if(Pattern.matches("(\\d+)", att) || Pattern.matches("(\\d+.\\d+)", att))
				{
					return Double.parseDouble(att);
				}	
			}
		return 0;
	}
	
	// Assumption made based on default of browsers(Firefox, IE, Chrome)
	public final static double convertEM(String bConvert)
	{
		return Double.parseDouble(bConvert) * 16;
	}
	
	// Assumption made based on default of browsers(Firefox, IE, Chrome)
	public final static double convertEX(String bConvert)
	{
		return Double.parseDouble(bConvert) * 7.1111;
	}

	public final static double convertPX(String bConvert)
	{
		return Double.parseDouble(bConvert);
	}

	public final static double convertPT(String bConvert)
	{
		return Double.parseDouble(bConvert) * 1.25;
	}

	public final static double convertPC(String bConvert)
	{
		return Double.parseDouble(bConvert) * 15;
	}

	public final static double convertMM(String bConvert)
	{
		return Double.parseDouble(bConvert) * 3.543307;
	}

	public final static double convertCM(String bConvert)
	{
		return Double.parseDouble(bConvert) * 35.43307;
	}

	public final static double convertIN(String bConvert)
	{
		return Double.parseDouble(bConvert) * 90;
	}
}
