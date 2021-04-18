//package declaration
package ch.nolix.elementtest.widgettest;

//class
/**
* Of the {@link Launcher} an instance cannot be created.
* 
* @author Silvan Wyss
* @month 2019-05
* @lines 30
*/
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link WidgetTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new WidgetTestPool().run();
	}
	
	//constructor
	/**
	 * Prevents that an instance of the {@link Launcher} can be created.
	 */
	private Launcher() {}
}
