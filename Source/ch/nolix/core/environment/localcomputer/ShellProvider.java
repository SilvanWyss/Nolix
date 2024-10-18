package ch.nolix.core.environment.localcomputer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.netapi.netconstantapi.IPv4Catalogue;
import ch.nolix.coreapi.netapi.netconstantapi.PortCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

public final class ShellProvider {

  private ShellProvider() {
  }

  public static void run(final String[] command) {

    GlobalValidator.assertThat(command).thatIsNamed(LowerCaseVariableCatalogue.COMMAND).isNotNull();

    final var runtimeCommand = createRuntimeCommandFromCommand(command);

    runRuntimeCommand(runtimeCommand);
  }

  public static void startDefaultWebBrowserOpeningLoopBackAddress() {
    startDefaultWebBrowserOpeningUrl(IPv4Catalogue.LOOP_BACK_ADDRESS);
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
    startFirefox(url, PortCatalogue.HTTP);
  }

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

  public static void startFirefoxOpeningLoopBackAddress() {
    startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, PortCatalogue.HTTP);
  }

  public static void startFirefoxOpeningLoopBackAddress(final int port) {
    startFirefox(IPv4Catalogue.LOOP_BACK_ADDRESS, port);
  }

  private static String[] createRuntimeCommandFromCommand(final String[] command) {

    final var preCommand = new String[] { "cmd.exe", "/c" };

    return ContainerView.forArray(preCommand, command).toStringArray();
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
