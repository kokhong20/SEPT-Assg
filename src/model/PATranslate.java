package model;

import gui.PAProgressBar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

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
    static PAProgressBar progressBar = new PAProgressBar();

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

    public void run(Language change)
    {
        List<String> test = new ArrayList<String>();

        test = translate(change);

        List<String> label = new ArrayList<String>();
        List<String> output = new ArrayList<String>();

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
            String fName = "";
            switch (change)
            {
            case ARABIC:
                fName = "PA_ar_AR";
            case BULGARIAN:
                fName = "PA_bg_BG";
            case CATALAN:
                fName = "PA_ca_CA";
            case CHINESE_SIMPLIFIED:
                fName = "PA_zh_CN";
            case CHINESE_TRADITIONAL:
                fName = "PA_zh_TW";
            case CZECH:
                fName = "PA_cs_CZ";
            case DANISH:
                fName = "PA_da_DK";
            case DUTCH:
                fName = "PA_nl_NL";
            case ENGLISH:
                fName = "PA_en_US";
            case ESTONIAN:
                fName = "PA_et_ET";
            case FINNISH:
                fName = "PA_fi_FI";
            case FRENCH:
                fName = "PA_fr_FR";
            case GERMAN:
                fName = "PA_de_DE";
            case GREEK:
                fName = "PA_el_EL";
            case HAITIAN_CREOLE:
                fName = "PA_ht_HT";
            case HEBREW:
                fName = "PA_he_HE";
            case HINDI:
                fName = "PA_hi_HI";
            case HMONG_DAW:
                fName = "PA_mww_MWW";
            case HUNGARIAN:
                fName = "PA_hu_HU";
            case INDONESIAN:
                fName = "PA_id_ID";
            case ITALIAN:
                fName = "PA_it_IT";
            case JAPANESE:
                fName = "PA_ja_JP";
            case KOREAN:
                fName = "PA_ko_KR";
            case LATVIAN:
                fName = "PA_lv_LV";
            case LITHUANIAN:
                fName = "PA_lt_LT";
            case MALAY:
                fName = "PA_ms_MY";
            case NORWEGIAN:
                fName = "PA_no_NO";
            case PERSIAN:
                fName = "PA_fa_FA";
            case POLISH:
                fName = "PA_pl_PL";
            case PORTUGUESE:
                fName = "PA_pt_PT";
            case ROMANIAN:
                fName = "PA_ro_RO";
            case RUSSIAN:
                fName = "PA_ru_RU";
            case SLOVAK:
                fName = "PA_sk_SK";
            case SLOVENIAN:
                fName = "PA_sl_SL";
            case SPANISH:
                fName = "PA_es_ES";
            case THAI:
                fName = "PA_th_TH";
            case TURKISH:
                fName = "PA_tr_TR";
            case UKRAINIAN:
                fName = "PA_uk_UK";
            case URDU:
                fName = "PA_ur_UR";
            case VIETNAMESE:
                fName = "PA_vi_VI";
            }
            fName = fName.concat(".properties");
            String fPath = "src/resource";
            fPath = fPath.concat(fName);
            File file = new File(fPath);
            file.createNewFile();
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < output.size(); i++)
            {
                bw.write(output.get(i));
                bw.write('\n');
            }

            bw.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < output.size(); i++)
        {
            System.out.println(output.get(i));
        }
        progressBar.dispose();
    }
}
