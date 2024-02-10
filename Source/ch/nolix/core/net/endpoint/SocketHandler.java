//package declaration
package ch.nolix.core.net.endpoint;

//Java imports
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

//own imports
import ch.nolix.core.commontypetool.GlobalInputStreamTool;
import ch.nolix.core.commontypetool.GlobalStringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.logging.GlobalLogger;
import ch.nolix.core.net.http.HttpRequest;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.SocketType;
import ch.nolix.coreapi.netapi.endpointprotocol.MessageType;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
public final class SocketHandler {

  //method
  public void handleSocketForServer(final Socket socket, final Server server) {

    final var backendNetEndPoint = createOptionalBackendNetEndPointForSocketAndServer(socket, server);

    if (backendNetEndPoint.isEmpty()) {
      closeSocket(socket);
    } else {
      server.internalTakeBackendEndPoint(backendNetEndPoint.get());
    }
  }

  //method
  private void closeSocket(final Socket socket) {
    try {
      socket.close();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }

  //method
  private Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServer(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String firstReveivedLine,
    final SocketType socketType,
    final Server server) {
    return switch (socketType) {
      case NET_SOCKET_WITH_DEFAULT_TARGET ->
        Optional.of(createSocketEndPointWithDefaultTarget(socket, socketInputStream, socketOutputStream));
      case NET_SOCKET_WITH_CUSTOM_TARGET ->
        Optional.of(
          createSocketEndPointWithCustomTarget(socket, socketInputStream, socketOutputStream, firstReveivedLine));
      case HTTP_SOCKET_OR_WEB_SOCKET ->
        createOptionalBackendNetEndPointForSocketAndServerWhenIsHttpSocketOrWebSocket(socket, socketInputStream,
          socketOutputStream, firstReveivedLine, server);
    };
  }

  //method
  private Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServer(
    final Socket socket,
    final Server server) {

    final var socketInputStream = getOptionalInputStreamOfSocket(socket);
    final var socketOutputStream = getOptionalOutputStreamOfSocket(socket);

    if (socketInputStream.isEmpty() || socketOutputStream.isEmpty()) {
      return Optional.empty();
    }

    final var firstReveivedLine = GlobalInputStreamTool.readLineFrom(socketInputStream.get());

    GlobalLogger.logInfo(
      "The current SocketHandler received the first line from the given socket: "
      + GlobalStringTool.getInSingleQuotes(firstReveivedLine));

    final var socketType = getSocketTypeFromFirstReceivedLine(firstReveivedLine);

    if (socketType.isEmpty()) {
      return Optional.empty();
    }

    return createOptionalBackendNetEndPointForSocketAndServer(
      socket,
      socketInputStream.get(),
      socketOutputStream.get(),
      firstReveivedLine,
      socketType.get(),
      server);
  }

  //method
  private Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServerWhenIsHttpSocketOrWebSocket(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String firstReveivedLine,
    final Server server) {
    final var lines = LinkedList.withElement(firstReveivedLine);
    fillUpLinesIntoListUntilReceivesEmptyLine(lines, socketInputStream);

    if (WebSocketHandShakeRequest.canBe(lines)) {

      GlobalLogger.logInfo(
        "The current SocketHandler has received the web socket opening handshake request: "
        + GlobalStringTool.getInSingleQuotes(lines.toString()));

      final var openingHandshakeResponse = new WebSocketHandShakeRequest(lines)
        .getWebSocketHandShakeResponse()
        .toString();

      GlobalLogger.logInfo(
        "The current SocketHandler sends the opening handshake response: "
        + GlobalStringTool.getInSingleQuotes(openingHandshakeResponse));

      sendRawMessageToOutputStream(socketOutputStream, openingHandshakeResponse);

      return Optional.of(new WebSocketEndPoint(socket, socketInputStream, socketOutputStream));
    }

    if (HttpRequest.canBe(lines)) {
      sendRawMessageToOutputStream(socketOutputStream, server.getInitialHttpMessage());
    }

    return Optional.empty();
  }

  //method
  private SocketEndPoint createSocketEndPointWithCustomTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String firstReveivedLine) {
    return new SocketEndPoint(
      socket,
      socketInputStream,
      socketOutputStream,
      Node.fromString(firstReveivedLine.substring(1)).getHeader());
  }

  //method
  private SocketEndPoint createSocketEndPointWithDefaultTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    return new SocketEndPoint(socket, socketInputStream, socketOutputStream);
  }

  //method
  private void fillUpLinesIntoListUntilReceivesEmptyLine(
    final LinkedList<String> lines,
    final InputStream inputStream) {
    while (true) {

      final var line = GlobalInputStreamTool.readLineFrom(inputStream);

      if (line == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.LINE);
      }

      if (line.isEmpty()) {
        break;
      }

      lines.addAtEnd(line);
    }
  }

  //method
  private Optional<InputStream> getOptionalInputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getInputStream());
    } catch (final IOException ioException) {

      GlobalLogger.logError(ioException);

      return Optional.empty();
    }
  }

  //method
  private Optional<OutputStream> getOptionalOutputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getOutputStream());
    } catch (final IOException ioException) {

      GlobalLogger.logError(ioException);

      return Optional.empty();
    }
  }

  //method
  private Optional<SocketType> getSocketTypeFromFirstReceivedLine(
    final String firstReceivedLine) {
  
    if (firstReceivedLine.equals(MessageType.DEFAULT_TARGET_MESSAGE.getPrefix())) {
      return Optional.of(SocketType.NET_SOCKET_WITH_DEFAULT_TARGET);
    }
  
    if (firstReceivedLine.startsWith(MessageType.TARGET_MESSAGE.getPrefix())) {
      return Optional.of(SocketType.NET_SOCKET_WITH_CUSTOM_TARGET);
    }
  
    if (firstReceivedLine.startsWith("G")) {
      return Optional.of(SocketType.HTTP_SOCKET_OR_WEB_SOCKET);
    }
  
    return Optional.empty();
  }

  //method
  private void sendRawMessageToOutputStream(final OutputStream outputStream, final String rawMessage) {
    try {
      outputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
      outputStream.flush();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }
}