package ch.nolix.elementtutorial.gui3dtutorial.jmonkeyguitutorial;

//own imports
import ch.nolix.element.gui.color.Color;
import ch.nolix.element.gui3d.jmonkeygui.JMonkeyMainFrame;
import ch.nolix.element.gui3d.shape.Sphere;

/**
 * The 3D GUI tutorial is a tutorial for the 3D GUI class.
 * 
 * @author Silvan Wyss
 * @date 2017-11-11
 */
public final class GUI3DTutorial {
	
	/**
	 * Creates a new main frame and adds a sphere to it as root shape.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		final JMonkeyMainFrame mainFrame = new JMonkeyMainFrame();
		
		mainFrame.setRootShape(
			new Sphere().setRadius(2.0).setDefaultColor(Color.BLUE)
		);
		
		mainFrame.refresh();
	}
	
	/**
	 * Avoids that an instance of this class can be created.
	 */
	private GUI3DTutorial() {}
}
