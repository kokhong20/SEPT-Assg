import gui.PARootView;

import javax.swing.SwingUtilities;

/**
 *
 * @author KokHong
 *
 */
public class Papoy
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
