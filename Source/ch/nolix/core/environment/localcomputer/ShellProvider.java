package ch.nolix.core.environment.localcomputer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.net.netconstant.IPv4Catalog;
import ch.nolix.coreapi.net.netconstant.PortCatalog;

/**
 * @author Silvan Wyss
 */
public final class ShellProvider {
  private ShellProvider() {
  }

  public static void run(final String[] command) {
    Validator.assertThat(command).thatIsNamed(LowerCaseVariableCatalog.COMMAND).isNotNull();

    final var runtimeCommand = createRuntimeCommandFromCommand(command);

    runRuntimeCommand(runtimeCommand);
  }

  public static void startDefaultWebBrowserOpeningLoopBackAddress() {
    startDefaultWebBrowserOpeningUrl(IPv4Catalog.LOOP_BACK_ADDRESS);
  }

  public static void startDefaultWebBrowserOpeningUrl(final String url) {
    try {
      Desktop.getDesktop().browse(new URI(getUrlWithHttpPrefix(url)));
    } catch (final IOException | URISyntaxException exception) {
      throw WrapperException.forError(exception);
    }
  }

  public static void startFirefox() {
    run(new String[] { "start", "firefox" });
  }

  public static void startFirefox(final String url) {
    startFirefox(url, PortCatalog.HTTP);
  }

  public static void startFirefox(final String url, final int port) {
    Validator
      .assertThat(url)
      .thatIsNamed(LowerCaseVariableCatalog.URL)
      .isNotBlank();

    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    run(new String[] { "start", "firefox", "--url", url + ":" + port });
  }

  public static void startFirefoxOpeningLoopBackAddress() {
    startFirefox(IPv4Catalog.LOOP_BACK_ADDRESS, PortCatalog.HTTP);
  }

  public static void startFirefoxOpeningLoopBackAddress(final int port) {
    startFirefox(IPv4Catalog.LOOP_BACK_ADDRESS, port);
  }

  private static String[] createRuntimeCommandFromCommand(final String[] command) {
    final var preCommand = new String[] { "cmd.exe", "/c" };

    return ContainerView.forArrays(preCommand, command).toStringArray();
  }

  private static String getUrlWithHttpPrefix(final String url) {
    if (!url.startsWith("http://")) {
      return ("http://" + url);
    }

    return url;
  }

  private static void runRuntimeCommand(final String[] runtimeCommand) {
    try {
      Runtime.getRuntime().exec(runtimeCommand);
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
