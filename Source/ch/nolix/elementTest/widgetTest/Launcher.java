//package declaration
package ch.nolix.elementTest.widgetTest;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

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
	public static final void main(String[] args) {
		new WidgetTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link Launcher} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private Launcher() {
		throw new UninstantiableClassException(getClass());
	}
}
