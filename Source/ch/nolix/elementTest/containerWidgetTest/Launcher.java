//package declaration
package ch.nolix.elementTest.containerWidgetTest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2016-08
 * @lines 20
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link ContainerWidgetTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static final void main(String[] args) {
		new ContainerWidgetTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
