//package declaration
package ch.nolix.systemtest.guitest.containerwidgettest;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-09-01
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
	
	//constructor
	/**
	 * Prevents that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
