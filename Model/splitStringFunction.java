package Model;

public interface splitStringFunction 
{
	public String[] splitString(String inputString);
}

/*
 * This function take one string as parameter and split the string into array of string
 * which contain two new string with the splited contain.
 * -string 1 contain only numeric character
 * -string 2 contain only alphabetic character
 * -If either string is empty means the inputString contain either no numeric character or alphabetic character
 * -If both strings are empty means there only contain character " "
 * 
 * empty string can be checked by if(string="")
 */



/*below is the implementation for this function */
/*
public String[] splitString(String inputString)
{
	char ch;
	int stringIndex=0;
	int count =0;
	String temp1,temp2;
	
	// Check whether inputString contain only one character 
	if(inputString.length()==1)
	{
		ch=inputString.charAt(0);
		
		//check whether ch is digit
		if(!Character.isDigit(ch))
		{
			if(ch==' ')
			{
				//return two empty string
				return new String[]{"",""};	
			}
			else
			{
				//return string 1 as empty string and string 2 which contain non-numeric character
				return new String[]{"",Character.toString(ch)};	
			}
		}
		else
		{
			//return string 1 as string which contain numeric character and string 2 as empty string
			return new String[]{Character.toString(ch),""};	
		}
	}
	//----------------------------------------------------//
	
	// If inputString contain more than 1 character
	for(int i =0;i<inputString.length();i++)
	{
		ch = inputString.charAt(i);
		
		if(!Character.isDigit(ch))
		{
			//check for character '.' 
			if(ch=='.')
			{
				if(count==0)
				{
					count++;
				}
				else
				{
					stringIndex=i;
					break;
				}
				
			}
			else
			{
				stringIndex=i;
				break;
			}
		}
	}
	
	//creating 1st string which contain only integer or double
	temp1=inputString.substring(0,stringIndex);
	//creating 2nd string which contain only non-numeric character
	temp2=inputString.substring(stringIndex);
	
	return new String[]{temp1,temp2};
}
*/