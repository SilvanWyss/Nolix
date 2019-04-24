//package declaration
package ch.nolix.coreTest.licenseTest;

//own import
import ch.nolix.core.invalidArgumentException.UninstantiableClassException;

//class
public final class Launcher {
	
	//main method
	public static void main(String[] args) {
		new LicenseTestPool().run();
	}
	
	//private constructor
	private Launcher() {
		throw new UninstantiableClassException(Launcher.class);
	}
}
