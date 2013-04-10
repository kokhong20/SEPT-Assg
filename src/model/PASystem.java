package model;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author bryantylai
 *
 */
public class PASystem
{
    private static String currentOS = System.getProperty("os.name");
    private static Toolkit toolkit = Toolkit.getDefaultToolkit();
    private static final Dimension screenDimension = toolkit.getScreenSize();
    private static final int screenResolution = toolkit.getScreenResolution();
    private static final double screenSize = Math.sqrt((screenDimension.height * screenDimension.width));
    private static final double screenRatio = (screenResolution / screenSize) + 1;
    private static final double dotsPerInch = screenResolution * screenRatio;

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
            path += selectedFile.getName();
        }
        else
        {
            if (currentOS.toLowerCase().contains("mac"))
            {
                path += "//" + selectedFile.getName();
            }
            else if (currentOS.toLowerCase().contains("windows"))
            {
                path += "\\" + selectedFile.getName();
            }
        }

        return path;
    }

    /**
     * set the look and feel of system based on currentOS
     */
    public static void setLookandFeel()
    {
        if (currentOS.toLowerCase().contains("mac"))
        {
            // macos use its own menubar
            System.setProperty("apple.laf.useScreenMenuBar", "true");
            System.setProperty("com.apple.mrj.application.apple.menu.about.name", "My Application");
        }

        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

}
