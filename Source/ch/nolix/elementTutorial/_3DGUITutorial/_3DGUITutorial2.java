//package declaration
package ch.nolix.elementTutorial._3DGUITutorial;

//own imports
import ch.nolix.element.JMonkeyGUI.JMonkeyMainFrame;
import ch.nolix.element._3DGUI.Cuboid;
import ch.nolix.element._3DGUI.MultiShape;
import ch.nolix.element._3DGUI.Sphere;
import ch.nolix.element.color.Color;

//class
/**
 * The 3D GUI tutorial 2 is a tutorial for the 3D GUI class.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 30
 */
public final class _3DGUITutorial2 {

	//main method
	/**
	 * Creates a new main frame and adds a sphere to it as root shape.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final JMonkeyMainFrame mainFrame = new JMonkeyMainFrame();
		
		mainFrame.setRootShape(
			new MultiShape(
				new Cuboid()
					.setPosition(0.0, -2.0, 0.0)
					.setXLenght(2.0)
					.setYLength(1.0)
					.setZLength(0.5)
					.setColor(Color.BLUE),
				new Sphere()
					.setPosition(4.0, -2.0, 0.0)
					.setDiameter(1.0)
					.setColor(Color.RED)
			)
		);
		
		mainFrame.refresh();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private _3DGUITutorial2() {}
}
