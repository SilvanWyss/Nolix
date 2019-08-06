//package declaration
package ch.nolix.elementTutorial._3D_GUITutorial;

//own imports
import ch.nolix.element.JMonkeyGUI.JMonkeyMainFrame;
import ch.nolix.element._3D_GUI.Sphere;
import ch.nolix.element.color.Color;

//class
/**
 * The 3D GUI tutorial is a tutorial for the 3D GUI class.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class _3D_GUITutorial {

	//main method
	/**
	 * Creates a new main frame and adds a sphere to it as root shape.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final JMonkeyMainFrame mainFrame = new JMonkeyMainFrame();
		
		mainFrame.setRootShape(
			new Sphere().setRadius(2.0).setDefaultColor(Color.BLUE)
		);
		
		mainFrame.refresh();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private _3D_GUITutorial() {}
}
