package model;

import java.awt.Dimension;
import java.awt.Event;
import java.awt.Toolkit;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.memetix.mst.language.Language;

/**
 * @author bryantylai
 * @since 1.1
 * <p>This class is for the settings based on screen and operating system of the
 * user's machine</p>
 */
public class PASystem
{
	private final static LinkedHashMap<String, Locale> languages;
	
	static
	{
		languages = new LinkedHashMap<>();
		languages.put("Bulgarian", new Locale("bg","BG"));
		languages.put("Czech", new Locale("cz","CZ"));
		languages.put("Danish", new Locale("da","DK"));
		languages.put("Dutch", new Locale("nl","NL"));
		
		languages.put("English (US)", Locale.US);
		languages.put("Estonian", new Locale("et","ET"));
		languages.put("Finnish", new Locale("fi","FI"));
		languages.put("German", new Locale("de","DE"));
		languages.put("Greek", new Locale("el","EL"));
		languages.put("Hungarian", new Locale("hu","HU"));
		languages.put("Italian", new Locale("it","IT"));
		
		languages.put("Malay (Malaysia)", new Locale("ms", "MY"));
		
//		languages.put("Japanese", Locale.JAPAN);
		languages.put("Mandarin (Simplified)", Locale.CHINA);
		languages.put("Mandarin (Traditional)", Locale.TAIWAN);
		languages.put("Norwegian", new Locale("no","NO"));
		languages.put("Polish", new Locale("pl","PL"));
		languages.put("Portugese", new Locale("pt","PT"));
		languages.put("Russian", new Locale("ru","RU"));
		languages.put("Spanish", new Locale("es","ES"));
		languages.put("Thai", new Locale("th","TH"));
		languages.put("Turkish", new Locale("tr","TR"));
		//		languages.put("Tamil", new Locale("", ""));
//		languages.put("Korean", new Locale("", ""));
//		languages.put("Thai", new Locale("", ""));
		languages.put("French", Locale.FRANCE);
//		languages.put("Spanish", new Locale("", ""));
		setCurrentResource("English (US)");
	}

    private static ResourceBundle currentResource;
    public static String currentOS = System.getProperty("os.name").toLowerCase();
    public static int keyMask = setKeyMask();
    private static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final Dimension screenDimension = toolkit.getScreenSize();
    private static final int screenResolution = toolkit.getScreenResolution();
    private static final double screenSize = Math.sqrt((screenDimension.height * screenDimension.width));
    private static final double screenRatio = (screenResolution / screenSize) + 1;
    private static final double dotsPerInch = screenResolution * screenRatio;

    /**
     * @return the key of languages
     */    
    public static Set<String> getLanguages()
    {
    	return languages.keySet();
    }

    /**
     * Change current ResourceBundle based on language set
     *
     * @param language the language set by user
     */
    public static void setCurrentResource(String language)
    {
        Language temp;
    	temp = Language.ENGLISH;
    		try
    		{
    		    switch(language)
    		    {
    		    case "Bulgarian":
    		        temp = Language.BULGARIAN;
    		        break;
    		    case "Czech":
    		        temp = Language.CZECH;
    		        break;
    		    case "Danish":
    		        temp = Language.DANISH;
    		        break;
    		    case "Dutch":
    		        temp = Language.DUTCH;
    		        break;
    		    case "Estonian":
    		        temp = Language.ESTONIAN;
    		        break;
    		    case "Finnish":
    		        temp = Language.FINNISH;
    		        break;
    		    case "French":
    		        temp = Language.FRENCH;
    		        break;
    		    case "German":
    		        temp = Language.GERMAN;
    		        break;
    		    case "Greek":
    		        temp = Language.GREEK;
    		        break;
    		    case "Hungarian":
    		        temp = Language.HUNGARIAN;
    		        break;
    		    case "Italian":
    		        temp = Language.ITALIAN;
    		        break;
    		    case "Norwegian":
    		        temp = Language.NORWEGIAN;
    		        break;
    		    case "Polish":
    		        temp = Language.POLISH;
    		        break;
    		    case "Portugese":
    		        temp = Language.PORTUGUESE;
    		        break;
    		    case "Russian":
    		        temp = Language.RUSSIAN;
    		        break;
    		    case "Spanish":
    		        temp = Language.SPANISH;
    		        break;
    		    case "Thai":
    		        temp = Language.THAI;
    		        break;
    		    case "Turkish":
    		        temp = Language.TURKISH;
    		        break;
    		    default:
    		        temp = Language.ENGLISH;
    		        break;
    		        
    		    }

    		    if(currentResource == null)
    	        {
    			currentResource = ResourceBundle.getBundle("resources.PA", Locale.getDefault());
    			
    			return;
    	        }
    		    else
    		    {
    		        PATranslate.run(temp);
    		        currentResource = ResourceBundle.getBundle("resources.PA", Locale.getDefault());
    		    }
    		}
    		catch(MissingResourceException e)
    		{
    		    
    		}
    	
    	currentResource = ResourceBundle.getBundle("resources.PA", languages.get(language));
    }

    /**
     * Get the word based on current ResourceBundle
     *
     * @param key
     * @return the translated word
     */
    public static String getWord(String key)
    {
        return currentResource.getString(key);
        
    }

    /**
     * @return the currentOS
     */
    public static String getCurrentOS()
    {
        return currentOS;
    }

    /**
     * @return the toolkit
     */
    public static Toolkit getToolkit()
    {
        return toolkit;
    }

    /**
     * @return the screenDimension
     */
    public static Dimension getScreenDimension()
    {
        return screenDimension;
    }

    /**
     * @return the screenResolution
     */
    public static int getScreenResolution()
    {
        return screenResolution;
    }

    /**
     * @return the screenSize
     */
    public static double getScreenSize()
    {
        return screenSize;
    }

    /**
     * @return the screenRatio
     */
    public static double getScreenRatio()
    {
        return screenRatio;
    }

    /**
     * @return the dotsPerInch
     */
    public static double getDotsPerInch()
    {
        return dotsPerInch;
    }

    /**
     * Concat name of selected file with path based on current OS
     *
     * @param path the path leading to the selected file
     * @param selectedFile the file selected from JFileChooser
     * @return a concatenated path
     */
    public static String setPath(String path, File selectedFile)
    {
        if (path.endsWith(":\\"))
        {
            return path += selectedFile.getName();
        }
        else
        {
            if (currentOS.indexOf("mac") >= 0)
            {
                return path += "//" + selectedFile.getName();
            }

            return path += "\\" + selectedFile.getName();
        }
    }

    /**
     * Set Key mask based on currentOS
     *
     * @return mask constant based on currentOS
     */
    public static int setKeyMask()
    {
        if (currentOS.indexOf("mac") >= 0)
        {
            return Event.META_MASK;
        }

        return Event.CTRL_MASK;
    }

    /**
     * Set the look and feel of system based on currentOS
     */
    public static void setLookandFeel()
    {
        if (currentOS.indexOf("mac") >= 0)
        {
            // macos use its own menubar
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "My Application");
        }

        try
        {
            if (currentOS.indexOf("mac") >= 0)
            {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
            else
            {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                {
                    if ("Nimbus".equals(info.getName()))
                    {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

}
