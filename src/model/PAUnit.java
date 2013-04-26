package model;

import java.util.regex.Pattern;

/**
 * @author bryantylai/SaiHoo
 *
 */
public class PAUnit implements PAAttributeConstant
{
    /**
     * create a static variable as this is constantly used across the class
     *
     */
    public static double dpi = PASystem.getDotsPerInch();

    /**
     * removed convert the string to lower case as svg is case sensitive
     *
     * Validate and convert the SVG element attributes value received as String
     * to double
     *
     * @param att attribute value of an SVG element attribute
     * @param constant constant is returned if error in setting unit
     * @return unit of length in double
     */
    public static double setUnit(String att, int constant)
    {
        if (!att.isEmpty())
        {
            // 10.10px or 10px or .10px are valid
            if (Pattern.matches(
                    "(\\-?\\d+\\.?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc|%)",
                    att)
                    || Pattern.matches(
                    "(\\-?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc|%)",
                    att)
                    || Pattern
                    .matches(
                    "(\\-?\\.?\\d+[e]?\\d*)+(em|ex|px|in|cm|mm|pt|pc|%)",
                    att))
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
                    case "%":
                        return convertPercent(calculate(removeUnits(att)));
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

        switch (constant)
        {
            case DEFAULT_SVG_SIZE:
                return DEFAULT_SVG_SIZE;
            case DEFAULT_STROKE_WIDTH:
                return DEFAULT_STROKE_WIDTH;
            default:
                return DEFAULT_LENGTH;
        }
    }

    /**
     * To calculate exponential values
     *
     * @param att
     * @return a String with its exponential value calculated
     */
    private static String calculate(String att)
    {
        if (att.contains("e"))
        {
            int loop = Integer.parseInt(att.substring(att.indexOf("e") + 1));
            double value = Double
                    .parseDouble(att.substring(0, att.indexOf("e")));

            value = value * Math.pow(10, loop);

            return String.valueOf(value);
        }

        return att;
    }

    /**
     * remove symbol from a attribute value
     *
     * @param att attribute value
     * @return a String with its symbol removed
     */
    private static String removeUnits(String att)
    {
        if (!att.endsWith("%"))
        {
            return att.replace(att.substring(att.length() - 2), "");
        }
        else
        {
            return att.replace(att.substring(att.length() - 1), "");
        }

    }

    /**
     * Convert from EM to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from EM
     */
    public static double convertEM(String value)
    {
        return (Double.parseDouble(value) * 12 * dpi / 72);
    }

    /**
     * Convert from EX to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from EX
     */
    public static double convertEX(String value)
    {
        return Double.parseDouble(value) * 7;
    }

    /**
     * Convert from PX to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from PX
     */
    public static double convertPX(String value)
    {
        return Double.parseDouble(value);
    }

    /**
     * Convert from PT to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from PT
     */
    public static double convertPT(String value)
    {
        return (Double.parseDouble(value) * dpi / 72);
    }

    /**
     * Convert from PC to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from PC
     */
    public static double convertPC(String value)
    {
        return (Double.parseDouble(value) * dpi / 6);
    }

    /**
     * Convert from MM to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from MM
     */
    public static double convertMM(String value)
    {
        return (Double.parseDouble(value) * dpi / 25.4);
    }

    /**
     * Convert from CM to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from CM
     */
    public static double convertCM(String value)
    {
        return (Double.parseDouble(value) * dpi / 2.54);
    }

    /**
     * Convert from IN to PX
     *
     * @param value attribute value
     * @return length of attribute value double converted from IN
     */
    public static double convertIN(String value)
    {
        return Double.parseDouble(value) * dpi;
    }

    /**
     * To convert percentage to size
     *
     * @param att
     * @return calculated size with respect to size
     */
    public static double convertPercent(String att)
    {
        return Double.parseDouble(att.replace(att.substring(att.length() - 1),
                "")) / 100 * DEFAULT_SVG_SIZE;
    }

}
