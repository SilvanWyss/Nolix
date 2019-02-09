//package declaration
package ch.nolix.core.util;

//Java import
import java.io.IOException;

//own imports
import ch.nolix.core.constants.PortCatalogue;
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.validator.Validator;

//class
public final class ShellProvider {
	
	//static method
	public static void run(final String command) {
		
		Validator
		.suppose(command)
		.thatIsNamed(VariableNameCatalogue.COMMAND)
		.isNotNull();
		
		try {
			Runtime.getRuntime().exec("cmd.exe /c " + command);
		}
		catch (final IOException IOException) {
			new RuntimeException(IOException);
		}
	}
	
	//static method
	public static void startFirefox() {
		run("start firefox");
	}
	
	//static method
	public static void startFirefox(final String url) {
		startFirefox(url, PortCatalogue.DE_FACTO_HTTP_PORT);
	}
	
	//static method
	public static void startFirefox(final String url, final int port) {
		
		Validator
		.suppose(url)
		.thatIsNamed(VariableNameCatalogue.URL)
		.isNotBlank();
		
		Validator
		.suppose(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		run("start firefox --url " + url + ":" + port);
	}
	
	//private constructor
	private ShellProvider() {}
}
