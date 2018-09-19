//package declaration
package ch.nolix.elementTutorial._3DGUITutorial;

//own imports
import ch.nolix.element.JMonkeyGUI.JMonkeyMainFrame;
import ch.nolix.element._3DGUI.Cuboid;
import ch.nolix.element._3DGUI.Cylinder;
import ch.nolix.element._3DGUI.MultiShape;
import ch.nolix.element._3DGUI.Sphere;
import ch.nolix.element.color.Color;

//class
/**
 * The {@link _3DGUITutorial2} is a tutorial for a {@link JMonkeyMainFrame}.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
public final class _3DGUITutorial2 {

	//main method
	/**
	 * Creates a new {@link JMonkeyMainFrame} and adds shapes to it.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		final var mainFrame = new JMonkeyMainFrame("3D GUI Tutorial");
		
		mainFrame.setRootShape(
			new MultiShape(
				new Cuboid()
				.setPosition(0.0, -2.0, 0.0)
				.setXLength(2.0)
				.setYLength(1.0)
				.setZLength(0.5)
				.setColor(Color.BLUE),
				new Sphere()
				.setPosition(4.0, -2.0, 0.0)
				.setDiameter(1.0)
				.setColor(Color.RED),
				new Cylinder()
				.setPosition(4.0, 2.0, 0.0)
				.setRadius(2.0)
				.setHeight(4.0)
				.setColor(Color.GREEN)
			)
		);
		
		mainFrame.refresh();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link _3DGUITutorial2} can be created.
	 */
	private _3DGUITutorial2() {}
}
