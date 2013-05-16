
//import java.util.Locale;

import gui.PARootView;

import javax.swing.SwingUtilities;

/**
 *
 * @author KokHong
 *
 */
public class Main
{
    public static void main(final String[] args)
    {    	
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                PARootView root = new PARootView(args);
                root.setVisible(true);
            }

        });
    }

}
