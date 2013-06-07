
import gui.PARootView;

import javax.swing.SwingUtilities;

/**
 *
 * @author KokHong
 * @since 1.0
 * <p>This is the main class to execute the program.</p>
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
