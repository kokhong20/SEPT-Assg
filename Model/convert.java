package Model;

public class convert
{

	public static double convertPX(String bConvert)
	{
		String[] parts = bConvert.split("px");
		double back = Double.parseDouble(parts[0]);
		return back;
	}

	public static double convertPT(String bConvert)
	{
		String[] parts = bConvert.split("pt");
		double back = Double.parseDouble(parts[0]);
		back = back * 1.25;
		return back;
	}

	public static double convertPC(String bConvert)
	{
		String[] parts = bConvert.split("pc");
		double back = Double.parseDouble(parts[0]);
		back = back * 15;
		return back;
	}

	public static double convertMM(String bConvert)
	{
		String[] parts = bConvert.split("mm");
		double back = Double.parseDouble(parts[0]);
		back = back * 3.543307;
		return back;
	}

	public static double convertCM(String bConvert)
	{
		String[] parts = bConvert.split("cm");
		double back = Double.parseDouble(parts[0]);
		back = back * 35.43307;
		return back;
	}

	public static double convertIN(String bConvert)
	{
		String[] parts = bConvert.split("in");
		double back = Double.parseDouble(parts[0]);
		back = back * 90;
		return back;
	}

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

}
