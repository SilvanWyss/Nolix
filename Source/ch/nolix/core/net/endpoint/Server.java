package ch.nolix.core.net.endpoint;

import java.io.IOException;
import java.net.ServerSocket;

import ch.nolix.core.errorcontrol.generalexception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.net.netconstant.PortCatalog;
import ch.nolix.coreapi.net.securityproperty.SecurityMode;

/**
 * A {@link Server} is a {@link AbstractServer} that listens to
 * {@link AbstractNetEndPoint} on a specific port.
 * 
 * A {@link Server} supports the WebSocket protocol and can communicate with a
 * WebSocket.
 * 
 * @author Silvan Wyss
 */
public final class Server extends AbstractServer {
  public static final String DEFAULT_INITIAL_HTTP_MESSAGE = //
  """
  HTTP/1.1 200 OK
  Content-Type: text/html; charset=UTF-8

  <!DOCTYPE html>
  <html>
  <head>
  <title>Nolix</title>
  <style>*{font-family: Calibri;}</style>
  </head>
  <body>
  <h1>Nolix</h1>
  <p>The requested server does not support web clients.</p>
  </body>
  </html>
  """;

  private final int port;

  private final String initialHttpMessage;

  private final ServerSocket serverSocket;

  /**
   * Creates a new {@link Server} that will listen to {@link AbstractNetEndPoint}s
   * on the given port.
   * 
   * When a web browser connects to the {@link Server}, the {@link Server} will
   * send the given initialHttpMessageForWebBrowsers to the web browser and close
   * the connection.
   * 
   * @param port
   * @param initialHttpMessage
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given initialHttpMessage is
   *                                       null.
   * @throws EmptyArgumentException        if the given initialHttpMessage is
   *                                       blank.
   */
  private Server(final int port, final String initialHttpMessage) {
    //Asserts that the given port is in [0,65535].
    Validator.assertThat(port).isBetween(PortCatalog.MIN_PORT, PortCatalog.MAX_PORT);

    //Asserts that the given initialHttpMessage is not blank.
    Validator.assertThat(initialHttpMessage).thatIsNamed("initial HTTP message").isNotBlank();

    //Sets the port of the current Server.
    this.port = port;

    //Sets the initialHttpMessageForWebBrowsers of the current Server.
    this.initialHttpMessage = initialHttpMessage;

    try {
      //Creates the serverSocket of the current Server.
      serverSocket = new ServerSocket(getPort());

      //Makes that the address of the current Server can be reused immediately after the current Sever is closed.
      serverSocket.setReuseAddress(true);
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }

    //Creates and starts a ServerListener for the current Server.
    createServerListener();
  }

  /**
   * @return a new {@link Server} that will listen to {@link AbstractNetEndPoint}s
   *         on the HTTP port (80).
   */
  public static Server forHttpPort() {
    return forPort(PortCatalog.HTTP);
  }

  /**
   * @param port
   * @return a new {@link Server} that will listen to {@link AbstractNetEndPoint}s
   *         on the given port.
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   */
  public static Server forPort(final int port) {
    return new Server(port, DEFAULT_INITIAL_HTTP_MESSAGE);
  }

  /**
   * @param port
   * @param initialHttpMessage
   * @return a new {@link Server} that will listen to {@link AbstractNetEndPoint}s
   *         on the given port. When a web browser connects to the {@link Server},
   *         the {@link Server} will send the given initialHttpMessage to the web
   *         browser and close the connection.
   * @throws ArgumentIsOutOfRangeException if the given port is not in [0, 65535].
   * @throws ArgumentIsNullException       if the given initialHttpMessage is
   *                                       null.
   * @throws InvalidArgumentException      if the given initialHttpMessage is
   *                                       blank.
   */
  public static Server forPortAndInitialHttpMessage(final int port, final String initialHttpMessage) {
    return new Server(port, initialHttpMessage);
  }

  /**
   * The HTTP message of a {@link Server} is the message a {@link Server} sends to
   * web browsers.
   * 
   * @return the initial HTTP message the current {@link Server} will send to web
   *         browsers.
   */
  public String getInitialHttpMessage() {
    return initialHttpMessage;
  }

  /**
   * @return the port of the current {@link Server}.
   */
  public int getPort() {
    return port;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    try {
      serverSocket.close();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  /**
   * @return the {@link ServerSocket} of the current {@link Server}.
   */
  ServerSocket internalGetStoredServerSocket() {
    return serverSocket;
  }

  /**
   * Creates a {@link ServerListener} for the current {@link Server}.
   */
  private void createServerListener() {
    ServerListener.forServer(this);
  }
}
