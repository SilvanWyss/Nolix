//package declaration
package ch.nolix.core.environment.localcomputer;

//Java imports
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.constant.IPv4Catalogue;
import ch.nolix.core.net.constant.PortCatalogue;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class ShellProvider {
	
	//static method
	public static void run(final String[] command) {
		
		GlobalValidator.assertThat(command).thatIsNamed(LowerCaseCatalogue.COMMAND).isNotNull();
		
		final var runtimeCommand = createRuntimeCommandFromCommand(command);
		
		runRuntimeCommand(runtimeCommand);
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
			throw WrapperException.forError(exception);
		}
	}
	
	//static method
	public static void startFirefox() {
		run(new String[]{"start", "firefox"});
	}
	
	//static method
	public static void startFirefox(final String url) {
		startFirefox(url, PortCatalogue.HTTP_PORT);
	}
	
	//static method
	public static void startFirefox(final String url, final int port) {
		
		GlobalValidator
		.assertThat(url)
		.thatIsNamed(LowerCaseCatalogue.URL)
		.isNotBlank();
		
		GlobalValidator
		.assertThat(port)
		.thatIsNamed(LowerCaseCatalogue.PORT)
		.isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);
		
		run(new String[] {"start" ,"firefox", "--url", url + ":" + port});
	}
	
	//static method
	public static void startFirefoxOpeningLoopBackAddress() {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, PortCatalogue.HTTP_PORT);
	}
	
	//static method
	public static void startFirefoxOpeningLoopBackAddress(final int port) {
		startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, port);
	}
	
	//static method
	private static String[] createRuntimeCommandFromCommand(final String[] command) {
		
		final var preCommand = new String[] {"cmd.exe", "/c"};
		
		return ReadContainer.forArrays(preCommand, command).toStringArray();
	}
	
	//static method
	private static void runRuntimeCommand(final String[] runtimeCommand) {
		try {
			Runtime.getRuntime().exec(runtimeCommand);
		} catch (final IOException pIOException) {
			throw WrapperException.forError(pIOException);
		}
	}
	
	//constructor
	private ShellProvider() {}
}
