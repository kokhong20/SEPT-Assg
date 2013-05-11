package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;

/**
 * @author LaiSaiHoo
 * @since 1.0
 * @version 1.0
 */
public class PACommandArgument 
{
//    public boolean validate(String fileName)
//    {
//        boolean results = true;
//        try
//        {
//            if (!Pattern.matches("([a-zA-Z0-9\\!\\@\\#\\$\\%\\^\\&\\(\\)\\-\\_\\=\\+\\{\\}\\[\\]\\;\\'\\,\\.\\`\\~])+(.svg|.xml)", fileName))
//            {
//                JOptionPane.showMessageDialog(null, "The file is not a SVG file.", "Not SVG File", JOptionPane.WARNING_MESSAGE);
//                results =  false;
//            }
//            
//            else
//            {
//                File selectedFile = new File(fileName);
//                Document svgDoc = PASVGImport.processFiletoDoc(selectedFile);
//            }
//        }
//
//        catch(Exception ex)
//        {
//            ex.printStackTrace();
//        }
//        return results;
//    }
}
