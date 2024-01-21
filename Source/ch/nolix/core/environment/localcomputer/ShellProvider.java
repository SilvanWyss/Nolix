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
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class ShellProvider {

  //constructor
  private ShellProvider() {
  }

  //static method
  public static void run(final String[] command) {

    GlobalValidator.assertThat(command).thatIsNamed(LowerCaseVariableCatalogue.COMMAND).isNotNull();

    final var runtimeCommand = createRuntimeCommandFromCommand(command);

    runRuntimeCommand(runtimeCommand);
  }

  //static method
  public static void startDefaultWebBrowserOpeningLoopBackAddress() {
    startDefaultWebBrowserOpeningUrl(IPv4Catalogue.LOOP_BACK_ADDRESS);
  }

  //static method
  public static void startDefaultWebBrowserOpeningUrl(final String url) {
    try {
      Desktop.getDesktop().browse(new URI(getUrlWithHttpPrefix(url)));
    } catch (final IOException | URISyntaxException exception) {
      throw WrapperException.forError(exception);
    }
  }

  //static method
  public static void startFirefox() {
    run(new String[] { "start", "firefox" });
  }

  //static method
  public static void startFirefox(final String url) {
    startFirefox(url, PortCatalogue.HTTP);
  }

  //static method
  public static void startFirefox(final String url, final int port) {

    GlobalValidator
      .assertThat(url)
      .thatIsNamed(LowerCaseVariableCatalogue.URL)
      .isNotBlank();

    GlobalValidator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalogue.PORT)
      .isBetween(PortCatalogue.MIN_PORT, PortCatalogue.MAX_PORT);

    run(new String[] { "start", "firefox", "--url", url + ":" + port });
  }

  //static method
  public static void startFirefoxOpeningLoopBackAddress() {
    startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, PortCatalogue.HTTP);
  }

  //static method
  public static void startFirefoxOpeningLoopBackAddress(final int port) {
    startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, port);
  }

  //static method
  private static String[] createRuntimeCommandFromCommand(final String[] command) {

    final var preCommand = new String[] { "cmd.exe", "/c" };

    return ReadContainer.forArray(preCommand, command).toStringArray();
  }

  //static method
  private static String getUrlWithHttpPrefix(final String url) {

    if (!url.startsWith("http://")) {
      return ("http://" + url);
    }

    return url;
  }

  //static method
  private static void runRuntimeCommand(final String[] runtimeCommand) {
    try {
      Runtime.getRuntime().exec(runtimeCommand);
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
