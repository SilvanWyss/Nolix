//package declaration
package ch.nolix.elementtest.containerwidgettest;

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
	public static void main(String[] args) {
		new ContainerWidgetTestPool().run();
	}
	
	//visibility-reduced constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
