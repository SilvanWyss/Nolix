//package declaration
package ch.nolix.techTest.genericMathTest;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new GenericMathTestPool().run();
	}
	
	//private constructor
	private Launcher() {
		throw new UninstantiableClassException(Launcher.class);
	}
}
