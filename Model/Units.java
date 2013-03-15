package Model;

import java.util.regex.Pattern;

public class Units
{
	public final static double setUnit(String att)
	{
			if(!att.isEmpty())
			{
				if(Pattern.matches("(\\d+.\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att) || Pattern.matches("(\\d+)+(em|ex|px|in|cm|mm|pt|pc)", att))
				{
					switch(att.substring(att.length() - 2))
					{
						case "em":
						case "ex":
							return 0;
						case "px":
							return Double.parseDouble(att.replace("px", ""));
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
/*
	public static double checkUnit(String check)
	{
		int count = 0;
		double answer = 0;
		double error = -999;
		check = check.toLowerCase();

		for (int i = 0; i < check.length(); i++)
		{
			if (check.charAt(i) == '.')

			{
				count++;
			}
		}
		if (count == 1 || count == 0)
		{
			if (check.contains("pt"))
			{
				answer = convertPT(check);

			}

			else if (check.contains("pc"))
			{
				answer = convertPC(check);

			}
			else if (check.contains("mm"))
			{
				answer = convertMM(check);

			}
			else if (check.contains("cm"))
			{
				answer = convertCM(check);

			}
			else if (check.contains("in"))
			{
				answer = convertIN(check);

			}
			if (check.contains("px"))
			{
				answer = convertPX(check);

			}
		}
		else
		{
			System.out.println("Error!!! contains more than one decimal point");
			return error;
		}

		return answer;
	}
*/
}
