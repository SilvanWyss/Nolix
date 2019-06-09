//package declaration
package ch.nolix.coreTest.statementTest;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new StatementTestPool().run();
	}
	
	//private constructor
	private Launcher() {
		throw new UninstantiableClassException(getClass());
	}
}
