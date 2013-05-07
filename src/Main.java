
import gui.PARootView;
import javax.swing.SwingUtilities;

/**
 *
 * @author KokHong
 *
 */
public class Main {

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         @Override
         public void run() {
            PARootView root = new PARootView();
            root.setVisible(true);
         }
      });
   }
}
