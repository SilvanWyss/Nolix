package ch.nolix.elementtutorial.gui3dtutorial.jmonkeyguitutorial;

import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui3d.jmonkeygui.JMonkeyMainFrame;
import ch.nolix.element.gui3d.shape.BaseCube;
import ch.nolix.element.gui3d.shape.Cylinder;
import ch.nolix.element.gui3d.shape.MultiShape;
import ch.nolix.element.gui3d.shape.Sphere;

/**
 * The {@link GUI3DTutorial2} is a tutorial for a {@link JMonkeyMainFrame}.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 * @lines 60
 */
public final class GUI3DTutorial2 {
	
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
	 * Prevents that an instance of the {@link GUI3DTutorial2} can be created.
	 */
	private GUI3DTutorial2() {}
}
