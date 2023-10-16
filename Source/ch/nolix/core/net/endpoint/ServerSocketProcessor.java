//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalInputStreamHelper;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.singlecontainer.SingleContainer;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.logger.GlobalLogger;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.net.http.HttpRequest;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programcontrol.worker.Worker;

//class
final class ServerSocketProcessor extends Worker {

  //attribute
  private final Server parentServer;

  //attribute
  private final Socket socket;

  //attribute
  private final InputStream socketInputStream;

  //attribute
  private final OutputStream socketOutputStream;

  //constructor
  public ServerSocketProcessor(final Server parentServer, final Socket socket) {

    GlobalValidator.assertThat(parentServer).thatIsNamed("parent server").isNotNull();
    GlobalValidator.assertThat(socket).thatIsNamed(Socket.class).isNotNull();

    this.parentServer = parentServer;
    this.socket = socket;
    try {
      socketInputStream = socket.getInputStream();
      socketOutputStream = socket.getOutputStream();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }

    start();

    GlobalLogger.logInfo("Created a ServerSocketProcessor.");
  }

  //method
  @Override
  protected void run() {
    try {

      final var netEndPoint = createOptionalNetEndPoint();

      if (netEndPoint.isEmpty()) {
        closeSocket();
      } else {
        parentServer.internalTakeBackendEndPoint(netEndPoint.getStoredElement());
      }
    } catch (final Throwable error) { //NOSONAR: All Throwables must be caught here.

      closeSocket();

      throw WrapperException.forError(error);
    }
  }

  //method
  private void closeSocket() {
    try {
      socket.close();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }

  //method
  private SingleContainer<NetEndPoint> createOptionalNetEndPoint() {

    final var firstReveivedLine = GlobalInputStreamHelper.readLineFrom(socketInputStream);

    GlobalLogger.logInfo("The current ServerSocketProcessor received the first line: " + firstReveivedLine);

    switch (getNetEndPointCreationTypeFromFirstReceivedLine(firstReveivedLine)) {
      case REGULAR_SOCKET_WITH_DEFAULT_TARGET:
        return new SingleContainer<>(new SocketEndPoint(socket, socketInputStream, socketOutputStream));
      case REGULAR_SOCKET_WITH_CUSTOM_TARGET:
        return new SingleContainer<>(
            new SocketEndPoint(
                socket,
                socketInputStream,
                socketOutputStream,
                Node.fromString(firstReveivedLine.substring(1)).getHeader()));
      case WEB_SOCKET_OR_HTTP:

        final var lines = LinkedList.withElement(firstReveivedLine);
        fillUpUntilEmptyLineFollows(lines, socketInputStream);

        if (WebSocketHandShakeRequest.canBe(lines)) {

          GlobalLogger.logInfo("Received a web socket opening handshake request: " + lines.toString());

          final var openingHandshakeResponse = new WebSocketHandShakeRequest(lines).getWebSocketHandShakeResponse()
              .toString();

          GlobalLogger.logInfo("Send opening handshake response: " + openingHandshakeResponse);
          sendRawMessage(openingHandshakeResponse);

          return new SingleContainer<>(new WebSocketEndPoint(socket, socketInputStream, socketOutputStream));
        }

        if (HttpRequest.canBe(lines)) {
          sendRawMessage(parentServer.getHttpMessage());
          return new SingleContainer<>();
        }

        throw InvalidArgumentException.forArgumentNameAndArgument("first received line", firstReveivedLine);
      default:
        throw InvalidArgumentException.forArgumentNameAndArgument("first received line", firstReveivedLine);
    }
  }

  //method
  private void fillUpUntilEmptyLineFollows(final LinkedList<String> lines, final InputStream inputStream) {
    while (true) {

      final var line = GlobalInputStreamHelper.readLineFrom(inputStream);

      if (line == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.LINE);
      }

      if (line.isEmpty()) {
        break;
      }

      lines.addAtEnd(line);
    }
  }

  //method
  private NetEndPointCreationType getNetEndPointCreationTypeFromFirstReceivedLine(final String firstReceivedLine) {

    if (firstReceivedLine.equals(String.valueOf(NetEndPointProtocol.MAIN_TARGET_PREFIX))) {
      return NetEndPointCreationType.REGULAR_SOCKET_WITH_DEFAULT_TARGET;
    }

    if (firstReceivedLine.startsWith(String.valueOf(NetEndPointProtocol.TARGET_PREFIX))) {
      return NetEndPointCreationType.REGULAR_SOCKET_WITH_CUSTOM_TARGET;
    }

    if (firstReceivedLine.startsWith("G")) {
      return NetEndPointCreationType.WEB_SOCKET_OR_HTTP;
    }

    throw InvalidArgumentException.forArgumentNameAndArgument("first received line", firstReceivedLine);
  }

  //method
  private void sendRawMessage(final String rawMessage) {
    try {
      socketOutputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
      socketOutputStream.flush();
    } catch (final IOException pIOException) {
      throw WrapperException.forError(pIOException);
    }
  }
}
