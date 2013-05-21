package model;

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
    public void translate() throws Exception
    {
        // This is my ID and API key for the library
        Translate.setClientId("1e420484-e51b-43ef-8e76-97200e58860f");
        Translate
                .setClientSecret("r3LT9E/EQ8d+XTUD70lxVgxXtJTxs+GYAS4EntBPJKA");
        // Text to be translated and language to be translate to and from
        Translate
                .execute("Bonjour le monde", Language.FRENCH, Language.ENGLISH);
        Translate.execute("Bonjour le monde", Language.FRENCH, Language.KOREAN);

    }
}
