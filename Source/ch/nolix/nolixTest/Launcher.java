//package declaration
package ch.nolix.nolixTest;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
/**
 * Of the {@link Launcher} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 30
 */
public final class Launcher {
	
	//main method
	/**
	 * Creates a new {@link NolixTestPool} and runs it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new NolixTestPool().run();
	}
	
	//private constructor
	/**
	 * Avoids that an instance of the {@link NolixTestPool} can be created.
	 * 
	 * @throws UninstantiableClassException
	 */
	private Launcher() {
		throw new UninstantiableClassException(getClass());
	}
}
