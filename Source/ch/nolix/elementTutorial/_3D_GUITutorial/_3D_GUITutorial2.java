package ch.nolix.elementTutorial._3D_GUITutorial;

import ch.nolix.element.JMonkeyGUI.JMonkeyMainFrame;
import ch.nolix.element.color.Color;
import ch.nolix.element.shape.BaseCube;
import ch.nolix.element.shape.Cylinder;
import ch.nolix.element.shape.MultiShape;
import ch.nolix.element.shape.Sphere;

/**
 * The {@link _3D_GUITutorial2} is a tutorial for a {@link JMonkeyMainFrame}.
 * 
 * @author Silvan Wyss
 * @month 2017-10
 * @lines 60
 */
public final class _3D_GUITutorial2 {
	
	/**
	 * Creates a new {@link JMonkeyMainFrame} and adds shapes to it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		final var mainFrame = new JMonkeyMainFrame("3D GUI Tutorial");
		
		mainFrame.setRootShape(
			new MultiShape(
				new BaseCube()
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
	
	/**
	 * Avoids that an instance of the {@link _3D_GUITutorial2} can be created.
	 */
	private _3D_GUITutorial2() {}
}
