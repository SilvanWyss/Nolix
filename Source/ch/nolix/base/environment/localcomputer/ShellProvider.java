/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.environment.localcomputer;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.netcatalog.IPv4Catalog;
import ch.nolix.baseapi.net.netcatalog.PortCatalog;

/**
 * @author Silvan Wyss
 */
public final class ShellProvider {
  private ShellProvider() {
  }

  /**
   * Runs the given command in a shell.
   * 
   * @param command
   */
  public static void run(final String[] command) {
    Validator.assertThat(command).thatIsNamed(LowerCaseVariableNameCatalog.COMMAND).isNotNull();

    final var runtimeCommand = createRuntimeCommandFromCommand(command);

    runRuntimeCommand(runtimeCommand);
  }

  /**
   * Starts the default web browser opening the loop back address.
   */
  public static void startDefaultWebBrowserOpeningLoopBackAddress() {
    startDefaultWebBrowserOpeningUrl(IPv4Catalog.LOOP_BACK_ADDRESS);
  }

  /**
   * Starts the default web browser opening the given url.
   * 
   * @param url
   */
  public static void startDefaultWebBrowserOpeningUrl(final String url) {
    try {
      Desktop.getDesktop().browse(new URI(getUrlWithHttpPrefix(url)));
    } catch (final IOException | URISyntaxException exception) {
      throw WrapperException.forError(exception);
    }
  }

  /**
   * Starts Firefox.
   */
  public static void startFirefox() {
    run(new String[] { "start", "firefox" });
  }

  /**
   * Starts Firefox opening the given url.
   * 
   * @param url
   */
  public static void startFirefox(final String url) {
    startFirefox(url, PortCatalog.HTTP);
  }

  /**
   * Starts Firefox opening the given url with the given port.
   * 
   * @param url
   * @param port
   */
  public static void startFirefox(final String url, final int port) {
    Validator
      .assertThat(url)
      .thatIsNamed(LowerCaseVariableNameCatalog.URL)
      .isNotBlank();

    Validator
      .assertThat(port)
      .thatIsNamed(LowerCaseVariableNameCatalog.PORT)
      .isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    run(new String[] { "start", "firefox", "--url", url + ":" + port });
  }

  /**
   * Starts Firefox opening the loop back address.
   */
  public static void startFirefoxOpeningLoopBackAddress() {
    startFirefox(IPv4Catalog.LOOP_BACK_ADDRESS, PortCatalog.HTTP);
  }

  /**
   * Starts Firefox opening the loop back address with the given port.
   * 
   * @param port
   */
  public static void startFirefoxOpeningLoopBackAddress(final int port) {
    startFirefox(IPv4Catalog.LOOP_BACK_ADDRESS, port);
  }

  private static String[] createRuntimeCommandFromCommand(final String[] command) {
    final var preCommand = new String[] { "cmd.exe", "/c" };

    return ExtendedIterableView.forArrays(preCommand, command).toStringArray();
  }

  private static String getUrlWithHttpPrefix(final String url) {
    if (!url.startsWith("http://")) {
      return "http://" + url;
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
