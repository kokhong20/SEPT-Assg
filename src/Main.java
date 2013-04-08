
import gui.PADrawingKit;
import gui.PAStartMenu;
/**
 * 
 * @author KokHong
 *
 */

public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// Testing gui
		PADrawingKit abc = new PADrawingKit();
		abc.initDrawingKit();
		abc.setVisible(true);
		abc.setSize(80,220);
		
//        PAStartMenu start = new PAStartMenu();
//        start.setVisible(true);
	}

}
