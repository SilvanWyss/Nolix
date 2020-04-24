//package declaration
package ch.nolix.common.localComputer;

import java.io.IOError;
//Java import
import java.io.IOException;

import ch.nolix.common.constant.IPv4Catalogue;
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
public final class ShellProvider {
	
	//static method
	public static void run(final String command) {
		
		Validator
		.assertThat(command)
		.thatIsNamed(VariableNameCatalogue.COMMAND)
		.isNotNull();
		
		try {
			Runtime.getRuntime().exec("cmd.exe /c " + command);
		}
		catch (final IOException IOException) {
			throw new IOError(IOException);
		}
	}
	
	//static method
	public static void startFirefox() {
		run("start firefox");
	}
	
	//static method
	public static void startFirefox(final int port) {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//static method
	public static void startFirefox(final String url) {
		startFirefox(url, PortCatalogue.DE_FACTO_HTTP_PORT);
	}
	
	//static method
	public static void startFirefox(final String url, final int port) {
		
		Validator
		.assertThat(url)
		.thatIsNamed(VariableNameCatalogue.URL)
		.isNotBlank();
		
		Validator
		.assertThat(port)
		.thatIsNamed(VariableNameCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		run("start firefox --url " + url + ":" + port);
	}
	
	//visibility-reducing constructor
	private ShellProvider() {}
}
