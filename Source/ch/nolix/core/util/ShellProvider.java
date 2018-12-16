//package declaration
package ch.nolix.core.util;

//Java import
import java.io.IOException;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator2.Validator;

//class
public final class ShellProvider {
	
	//static method
	public static void run(final String command) {
		
		Validator
		.suppose(command)
		.thatIsNamed(VariableNameCatalogue.COMMAND)
		.isInstance();
		
		try {
			Runtime.getRuntime().exec("cmd.exe /c " + command);
		}
		catch (final IOException IOException) {
			new RuntimeException(IOException);
		}
	}
	
	//private constructor
	private ShellProvider() {}
}
