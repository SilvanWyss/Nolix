//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.net.Socket;

//own imports
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.worker.Worker;
import ch.nolix.coreapi.functionapi.requestapi.CloseStateRequestable;

//class
/**
 * A {@link ServerListener} listens to {@link SocketEndPoint}s for a
 * {@link Server}.
 * 
 * @author Silvan Wyss
 * @date 2017-05-06
 */
public final class ServerListener extends Worker implements CloseStateRequestable {

  //attribute
  /**
   * The {@link Server} the current {@link ServerListener} is for.
   */
  private final Server parentServer;

  //constructor
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

  //static method
  /**
   * @param server
   * @return a new {@link ServerListener} for the given server. The
   *         {@link ServerListener} will start automatically.
   * @throws ArgumentIsNullException if the given server is null.
   */
  public static ServerListener forServer(final Server server) {
    return new ServerListener(server);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isClosed() {
    return parentServer.isClosed();
  }

  //method
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
    } catch (final IOException ioException) {

      parentServer.close();

      throw WrapperException.forError(ioException);
    }
  }

  //method
  /**
   * Lets the current {@link ServerListener} handle the given socket.
   * 
   * @param socket
   */
  private void handleSocket(final Socket socket) {
    new ServerSocketProcessor(parentServer, socket);
  }
}
