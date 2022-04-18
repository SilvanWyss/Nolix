//package declaration
package ch.nolix.core.environment.localcomputer;

//Java imports
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//own imports
import ch.nolix.core.constant.IPv4Catalogue;
import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.constant.PortCatalogue;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;

//class
public final class ShellProvider {
	
	//static method
	public static void run(final String command) {
		
		Validator
		.assertThat(command)
		.thatIsNamed(LowerCaseCatalogue.COMMAND)
		.isNotNull();
		
		try {
			Runtime.getRuntime().exec("cmd.exe /c " + command);
		} catch (final IOException pIOException) {
			throw new WrapperException(pIOException);
		}
	}
	
	//static method
	public static void startDefaultWebBrowserOpeningLoopBackAddress() {
		startDefaultWebBrowserOpeningURL(IPv4Catalogue.LOOP_BACK_ADDRESS);
	}
	
	//static method
	public static void startDefaultWebBrowserOpeningURL(String pURL) {
		
		if (!pURL.startsWith("http://")) {
			pURL = "http://" + pURL;
		}
		
		try {
			Desktop.getDesktop().browse(new URI(pURL));
		} catch (final IOException | URISyntaxException exception) {
			throw new WrapperException(exception);
		}
	}
	
	//static method
	public static void startFirefox() {
		run("start firefox");
	}
	
	//static method
	public static void startFirefox(final String url) {
		startFirefox(url, PortCatalogue.HTTP_PORT);
	}
	
	//static method
	public static void startFirefox(final String url, final int port) {
		
		Validator
		.assertThat(url)
		.thatIsNamed(LowerCaseCatalogue.URL)
		.isNotBlank();
		
		Validator
		.assertThat(port)
		.thatIsNamed(LowerCaseCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		run("start firefox --url " + url + ":" + port);
	}
	
	//static method
	public static void startFirefoxOpeningLoopBackAddress() {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, PortCatalogue.HTTP_PORT);
	}
	
	//static method
	public static void startFirefoxOpeningLoopBackAddress(final int port) {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//constructor
	private ShellProvider() {}
}
