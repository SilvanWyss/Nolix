//package declaration
package ch.nolix.elementTutorial._3D_GUITutorial;

//own imports
import ch.nolix.element.JMonkeyGUI.JMonkeyMainFrame;
import ch.nolix.element._3D_GUI.Cuboid;
import ch.nolix.element._3D_GUI.Cylinder;
import ch.nolix.element._3D_GUI.MultiShape;
import ch.nolix.element._3D_GUI.Sphere;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link _3D_GUITutorial2} is a tutorial for a {@link JMonkeyMainFrame}.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
public final class _3D_GUITutorial2 {

	//main method
	/**
	 * Creates a new {@link JMonkeyMainFrame} and adds shapes to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final var mainFrame = new JMonkeyMainFrame("3D GUI Tutorial");
		
		mainFrame.setRootShape(
			new MultiShape(
				new Cuboid()
				.setPosition(0.0, -2.0, 0.0)
				.setXLength(2.0)
				.setYLength(1.0)
				.setZLength(0.5)
				.setDefaultColor(Color.BLUE),
				new Sphere()
				.setPosition(4.0, -2.0, 0.0)
				.setDiameter(1.0)
				.setDefaultColor(Color.RED),
				new Cylinder()
				.setPosition(4.0, 2.0, 0.0)
				.setRadius(2.0)
				.setHeight(4.0)
				.setDefaultColor(Color.GREEN)
			)
		);
		
		mainFrame.refresh();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link _3D_GUITutorial2} can be created.
	 */
	private _3D_GUITutorial2() {}
}
