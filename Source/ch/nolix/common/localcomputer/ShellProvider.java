//package declaration
package ch.nolix.common.localcomputer;

//Java imports
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//own imports
import ch.nolix.common.constant.IPv4Catalogue;
import ch.nolix.common.constant.PortCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.exception.WrapperException;
import ch.nolix.common.validator.Validator;

//TODO: Unify method names.

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
		catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//static method
	public static void startDefaultWebBrowserOpeningLoopBackAddress() {
		try {
			Desktop.getDesktop().browse(new URI("http://" + IPv4Catalogue.LOOP_BACK_ADDRESS));
		} 
		catch (final IOException | URISyntaxException exception) {
			throw new WrapperException(exception);
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
		startFirefox(url, PortCatalogue.HTTP_PORT);
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
	
	//static method
	public static void startFirefoxOpeningLoopBackAddress() {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, PortCatalogue.HTTP_PORT);
	}
	
	//visibility-reduced constructor
	private ShellProvider() {}
}
