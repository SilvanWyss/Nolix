package ch.nolix.core.net.endpoint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import ch.nolix.core.commontypetool.inputstreamtool.InputStreamTool;
import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.document.node.Node;
import ch.nolix.core.errorcontrol.exception.WrapperException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.logging.Logger;
import ch.nolix.core.net.http.HttpRequest;
import ch.nolix.core.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.coreapi.commontypetoolapi.inputstreamtoolapi.IInputStreamTool;
import ch.nolix.coreapi.netapi.endpointapi.IEndPoint;
import ch.nolix.coreapi.netapi.endpointapi.SocketType;
import ch.nolix.coreapi.netapi.endpointprotocol.MessageType;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

public final class SocketHandler {

  private static final IInputStreamTool INPUT_STREAM_TOOL = new InputStreamTool();

  public void handleSocketForServer(final Socket socket, final Server server) {

    final var backendNetEndPoint = createOptionalBackendNetEndPointForSocketAndServer(socket, server);

    if (backendNetEndPoint.isEmpty()) {
      closeSocket(socket);
    } else {
      server.internalTakeBackendEndPoint(backendNetEndPoint.get());
    }
  }

  private void closeSocket(final Socket socket) {
    try {
      socket.close();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }

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

  private Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServer(
    final Socket socket,
    final Server server) {

    final var socketInputStream = getOptionalInputStreamOfSocket(socket);
    final var socketOutputStream = getOptionalOutputStreamOfSocket(socket);

    if (socketInputStream.isEmpty() || socketOutputStream.isEmpty()) {
      return Optional.empty();
    }

    final var firstReveivedLine = INPUT_STREAM_TOOL.readLineFromInputStream(socketInputStream.get());

    Logger.logInfo(
      "The current SocketHandler received the first line from the given socket: "
      + StringTool.getInSingleQuotes(firstReveivedLine));

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

  private Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServerWhenIsHttpSocketOrWebSocket(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String firstReveivedLine,
    final Server server) {
    final var lines = LinkedList.withElement(firstReveivedLine);
    fillUpLinesIntoListUntilReceivesEmptyLine(lines, socketInputStream);

    if (WebSocketHandShakeRequest.canBe(lines)) {

      Logger.logInfo(
        "The current SocketHandler has received the web socket opening handshake request: "
        + StringTool.getInSingleQuotes(lines.toString()));

      final var openingHandshakeResponse = new WebSocketHandShakeRequest(lines)
        .getWebSocketHandShakeResponse()
        .toString();

      Logger.logInfo(
        "The current SocketHandler sends the opening handshake response: "
        + StringTool.getInSingleQuotes(openingHandshakeResponse));

      sendRawMessageToOutputStream(socketOutputStream, openingHandshakeResponse);

      return Optional.of(new WebSocketEndPoint(socket, socketInputStream, socketOutputStream));
    }

    if (HttpRequest.canBe(lines)) {
      sendRawMessageToOutputStream(socketOutputStream, server.getInitialHttpMessage());
    }

    return Optional.empty();
  }

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

  private SocketEndPoint createSocketEndPointWithDefaultTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    return new SocketEndPoint(socket, socketInputStream, socketOutputStream);
  }

  private void fillUpLinesIntoListUntilReceivesEmptyLine(
    final LinkedList<String> lines,
    final InputStream inputStream) {
    while (true) {

      final var line = INPUT_STREAM_TOOL.readLineFromInputStream(inputStream);

      if (line == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.LINE);
      }

      if (line.isEmpty()) {
        break;
      }

      lines.addAtEnd(line);
    }
  }

  private Optional<InputStream> getOptionalInputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getInputStream());
    } catch (final IOException ioException) {

      Logger.logError(ioException);

      return Optional.empty();
    }
  }

  private Optional<OutputStream> getOptionalOutputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getOutputStream());
    } catch (final IOException ioException) {

      Logger.logError(ioException);

      return Optional.empty();
    }
  }

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

  private void sendRawMessageToOutputStream(final OutputStream outputStream, final String rawMessage) {
    try {
      outputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
      outputStream.flush();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }
}
