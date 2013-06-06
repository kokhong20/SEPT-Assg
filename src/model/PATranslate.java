package model;

import gui.PAProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;
import java.util.Locale;

/**
 * 
 * @author SaiHoo
 * 
 *         <p>
 *         To translate the language with the word to another language
 *         </p>
 * 
 */
public class PATranslate
{
    static List<String> text = new ArrayList<String>();
    static PAProgressBar progressBar;

    public static Locale run(Language change, final JDesktopPane parent)
    {
        
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                

                
                PAProgressBar progressBar = new PAProgressBar();
                parent.add(progressBar);

//                progressBar.setVisible(true);
//                progressBar.setSize(200, 200);
                System.out.println(parent);
                System.out.println(progressBar);
                System.out.println("good");
                //JOptionPane.showMessageDialog(parent, "Click OK to start translate","Translation", JOptionPane.PLAIN_MESSAGE);
            }

        });
        t1.start();
        try
        {
            Thread.sleep(500);
        }
        catch (InterruptedException e2)
        {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        for (int i = 0; i < parent.getComponentCount(); i++)
        {
            if (parent.getComponent(i) instanceof PAProgressBar && parent.getComponent(i)!= null)
            {
                System.out.println("asdasdad + " + parent.getComponent(i));
                progressBar = (PAProgressBar) parent.getComponent(i);
                progressBar.toFront();
            }
        }

        int timeout = 1000;
        InetAddress[] addresses;
        try
        {
            addresses = InetAddress.getAllByName("www.google.com");
            for (InetAddress address : addresses)
            {
                if (address.isReachable(timeout))
                    System.out.printf("%s is reachable%n", address);

            }
        }
        catch (UnknownHostException e1)
        {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(parent,
                    "Not connected to internet .....");
            return new Locale("en", "US");
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String fName = "";
        List<String> test = new ArrayList<String>();

        test = translate(change);

        List<String> label = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        System.out.println(change);
        try
        {

            BufferedReader in;
            in = new BufferedReader(new FileReader(new File(
                    "src/resources/PA_label")));
            String line = in.readLine();
            while (line != null)
            {
                label.add(line);
                line = in.readLine();
            }
            in.close();
            for (int i = 0; i < test.size(); i++)
            {
                String write;

                write = label.get(i);
                write = write.concat("=");
                write = write.concat(test.get(i));

                output.add(write);

            }

            switch (change)
            {
            case ARABIC:
                fName = "PA_ar_AR";
                break;
            case BULGARIAN:
                fName = "PA_bg_BG";
                break;
            case CATALAN:
                fName = "PA_ca_CA";
                break;
            case CHINESE_SIMPLIFIED:
                fName = "PA_zh_CN";
                break;
            case CHINESE_TRADITIONAL:
                fName = "PA_zh_TW";
                break;
            case CZECH:
                fName = "PA_cs_CZ";
                break;
            case DANISH:
                fName = "PA_da_DK";
                break;
            case DUTCH:
                fName = "PA_nl_NL";
                break;
            case ENGLISH:
                fName = "PA_en_US";
                break;
            case ESTONIAN:
                fName = "PA_et_ET";
                break;
            case FINNISH:
                fName = "PA_fi_FI";
                break;
            case FRENCH:
                fName = "PA_fr_FR";
                break;
            case GERMAN:
                fName = "PA_de_DE";
                break;
            case GREEK:
                fName = "PA_el_EL";
                break;
            case HAITIAN_CREOLE:
                fName = "PA_ht_HT";
                break;
            case HEBREW:
                fName = "PA_he_HE";
                break;
            case HINDI:
                fName = "PA_hi_HI";
                break;
            case HMONG_DAW:
                fName = "PA_mww_MWW";
                break;
            case HUNGARIAN:
                fName = "PA_hu_HU";
                break;
            case INDONESIAN:
                fName = "PA_id_ID";
                break;
            case ITALIAN:
                fName = "PA_it_IT";
                break;
            case JAPANESE:
                fName = "PA_ja_JP";
                break;
            case KOREAN:
                fName = "PA_ko_KR";
                break;
            case LATVIAN:
                fName = "PA_lv_LV";
                break;
            case LITHUANIAN:
                fName = "PA_lt_LT";
                break;
            case MALAY:
                fName = "PA_ms_MY";
                break;
            case NORWEGIAN:
                fName = "PA_no_NO";
                break;
            case PERSIAN:
                fName = "PA_fa_FA";
                break;
            case POLISH:
                fName = "PA_pl_PL";
                break;
            case PORTUGUESE:
                fName = "PA_pt_PT";
                break;
            case ROMANIAN:
                fName = "PA_ro_RO";
                break;
            case RUSSIAN:
                fName = "PA_ru_RU";
                break;
            case SLOVAK:
                fName = "PA_sk_SK";
                break;
            case SLOVENIAN:
                fName = "PA_sl_SL";
                break;
            case SPANISH:
                fName = "PA_es_ES";
                break;
            case THAI:
                fName = "PA_th_TH";
                break;
            case TURKISH:
                fName = "PA_tr_TR";
                break;
            case UKRAINIAN:
                fName = "PA_uk_UK";
                break;
            case URDU:
                fName = "PA_ur_UR";
                break;
            case VIETNAMESE:
                fName = "PA_vi_VI";
                break;
            }
            fName = fName.concat(".properties");
            String fPath = "bin/resources/";
            fPath = fPath.concat(fName);
            File file = new File(fPath);
            file.createNewFile();
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));

            for (int i = 0; i < output.size(); i++)
            {
                out.write(output.get(i));
                out.write('\n');
            }
            out.close();
            fPath = "src/resources/";
            fPath = fPath.concat(fName);
            file = new File(fPath);
            file.createNewFile();
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), "UTF-8"));

            for (int i = 0; i < output.size(); i++)
            {
                out.write(output.get(i));
                out.write('\n');
            }

            out.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

//        for (int i = 0; i < output.size(); i++)
//        {
//            System.out.println(output.get(i));
//        }
        progressBar.dispose();

        String[] s = fName.replace(".properties", "").split("_");

        return new Locale(s[1], s[2]);
    }
    
    public static List<String> translate(Language constant)
    {

        text = readText();
        List<String> translate = new ArrayList<String>();
        int i = 0, size;
        size = text.size();
        try
        {

            while (i < size)
            {

                // This is my ID and API key for the library
                Translate.setClientId("Papoy");
                Translate
                        .setClientSecret("lKIgzJLTdG8Bh7n84EFQdYHFEJQ13zGqpWh15xeG9w4");
                // Translate.setKey("r3LT9E/EQ8d+XTUD70lxVgxXtJTxs+GYAS4EntBPJKA");
                // Text to be translated and language to be translate to and
                // from

                translate.add(Translate.execute(text.get(i), Language.ENGLISH,
                        constant));
                System.out.println(translate.get(i));
                i++;
                progressBar.changeText();

            }
            return translate;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static List<String> readText()
    {
        text.clear();
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(new File(
                    "src/resources/PA_text")));
            String line = in.readLine();
            while (line != null)
            {
                text.add(line);
                line = in.readLine();
            }
            in.close();
            return text;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }


}
