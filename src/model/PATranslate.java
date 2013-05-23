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
                progressBar.setProgress((i * 100 / size));

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

    public static void main(String[] args)
    {
        List<String> test = new ArrayList<String>();
        test = translate(Language.CHINESE_TRADITIONAL);

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

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for (int i = 0; i < test.size(); i++)
        {
            String write;
            write = label.get(i);
            write = write.concat("=");
            write = write.concat(test.get(i));

            output.add(write);

        }

        try
        {
            File file = new File("src/resources/hi.abc");
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
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int i = 0; i < output.size(); i++)
        {
            System.out.println(output.get(i));
        }
    }
}
