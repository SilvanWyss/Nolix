/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.senderandreceiverserver;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.programcontrol.flowcontrol.FlowController;
import ch.nolix.base.programcontrol.worker.AbstractWorker;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.resourcecontrol.resourcerequest.OpennessRequestable;

/**
 * A {@link NetServerListener} listens to {@link SocketEndPoint}s for a
 * {@link NetServer}.
 * 
 * @author Silvan Wyss
 */
public final class NetServerListener extends AbstractWorker implements OpennessRequestable {
  /**
   * The {@link NetServer} the current {@link NetServerListener} is for.
   */
  private final NetServer parentServer;

  /**
   * Creates a new {@link NetServerListener} that will belong to the given
   * parentServer. The {@link NetServerListener} will start automatically.
   * 
   * @param parentServer
   * @throws RuntimeException if the given parentServer is null
   */
  private NetServerListener(final NetServer parentServer) {
    // Asserts that the given parentServer is not null.
    Validator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();

    // Sets the parentServer of the current ServerListener.
    this.parentServer = parentServer;

    // Starts the current ServerListener. 
    start();
  }

  /**
   * @param netServer
   * @return a new {@link NetServerListener} for the given server. The
   *         {@link NetServerListener} will start automatically
   * @throws RuntimeException if the given server is null
   */
  public static NetServerListener forServer(final NetServer netServer) {
    return new NetServerListener(netServer);
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
    } catch (final SocketException _) { // NOSONAR: serverSocket.accept will throw a SocketException if the serverSocket is stopped.
      parentServer.close();
    } catch (final IOException ioException) {
      parentServer.close();

      throw WrapperException.forError(ioException);
    }
  }

  /**
   * Lets the current {@link NetServerListener} handle the given socket.
   * 
   * @param socket
   */
  private void handleSocket(final Socket socket) {
    FlowController.runInBackground(() -> SocketHandler.handleSocketForServer(socket, parentServer));
  }
}
