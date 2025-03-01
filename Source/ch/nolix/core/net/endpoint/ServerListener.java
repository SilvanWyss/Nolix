package ch.nolix.core.net.endpoint;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.flowcontrol.FlowController;
import ch.nolix.core.programcontrol.worker.Worker;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.CloseStateRequestable;

/**
 * A {@link ServerListener} listens to {@link SocketEndPoint}s for a
 * {@link Server}.
 * 
 * @author Silvan Wyss
 * @version 2017-05-06
 */
public final class ServerListener extends Worker implements CloseStateRequestable {

  private static final SocketHandler SOCKET_HANDLER = new SocketHandler();

  /**
   * The {@link Server} the current {@link ServerListener} is for.
   */
  private final Server parentServer;

  /**
   * Creates a new {@link ServerListener} that will belong to the given
   * parentServer. The {@link ServerListener} will start automatically.
   * 
   * @param parentServer
   * @throws ArgumentIsNullException if the given parentServer is null.
   */
  private ServerListener(final Server parentServer) {

    //Asserts that the given parentServer is not null.
    GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    //Sets the parentServer of the current ServerListener.
    this.parentServer = parentServer;

    //Starts the current ServerListener. 
    start();
  }

  /**
   * @param server
   * @return a new {@link ServerListener} for the given server. The
   *         {@link ServerListener} will start automatically.
   * @throws ArgumentIsNullException if the given server is null.
   */
  public static ServerListener forServer(final Server server) {
    return new ServerListener(server);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isClosed() {
    return parentServer.isClosed();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void run() {

    final var serverSocket = parentServer.internalGetStoredServerSocket();

    try {
      while (isOpen()) {

        final var socket = serverSocket.accept();

        handleSocket(socket);
      }
    } catch (final SocketException socketException) { //NOSONAR: serverSocket.accept will throw a SocketException when the serverSocket is stopped.
      parentServer.close();
    } catch (final IOException ioException) {

      parentServer.close();

      throw WrapperException.forError(ioException);
    }
  }

  /**
   * Lets the current {@link ServerListener} handle the given socket.
   * 
   * @param socket
   */
  private void handleSocket(final Socket socket) {
    FlowController.runInBackground(() -> SOCKET_HANDLER.handleSocketForServer(socket, parentServer));
  }
}
