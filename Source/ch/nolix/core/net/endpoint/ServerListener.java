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
final class ServerListener extends Worker implements CloseStateRequestable {

  //attribute
  private final Server parentServer;

  //constructor
  /**
   * Creates a new {@link ServerListener} that will belong to the given
   * parentServer.
   * 
   * @param parentServer
   * @throws ArgumentIsNullException if the given parentServer is null.
   */
  private ServerListener(final Server parentServer) {

    //Asserts that the given parentServer is not null.
    GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    //Sets the parentServer of the current ServerListener.
    this.parentServer = parentServer;

    start();
  }

  //static method
  /**
   * @param server
   * @return a new {@link ServerListener} for the given server.
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
   * Runs the current {@link ServerListener}. Will close the {@link Server}, the
   * current {@link ServerListener} belongs to, when an error occurs.
   */
  @Override
  protected void run() {
    try {
      while (isOpen()) {
        final var socket = parentServer.internalGetStoredServerSocket().accept();
        takeSocket(socket);
      }
    } catch (final IOException ioException) {
      parentServer.close();
      throw WrapperException.forError(ioException);
    }
  }

  //method
  /**
   * Lets the current {@link ServerListener} take the given socket.
   * 
   * @param socket
   */
  private void takeSocket(final Socket socket) {
    new ServerSocketProcessor(parentServer, socket);
  }
}
