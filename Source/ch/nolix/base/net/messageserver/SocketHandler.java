/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.net.messageserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import ch.nolix.base.commontype.inputstreamtool.InputStreamTool;
import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.document.node.ImmutableNode;
import ch.nolix.base.errorcontrol.generalexception.WrapperException;
import ch.nolix.base.errorcontrol.logging.Logger;
import ch.nolix.base.net.http.HttpRequest;
import ch.nolix.base.net.websocket.WebSocketHandShakeRequest;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;
import ch.nolix.baseapi.net.messageserver.IEndPoint;
import ch.nolix.baseapi.net.messageserver.SocketType;
import ch.nolix.baseapi.net.messageserverprotocol.MessageType;

/**
 * @author Silvan Wyss
 */
public final class SocketHandler {
  private static final InputStreamTool INPUT_STREAM_TOOL = new InputStreamTool();

  private SocketHandler() {
  }

  public static void handleSocketForServer(final Socket socket, final Server server) {
    final var backendNetEndPoint = createOptionalBackendNetEndPointForSocketAndServer(socket, server);

    if (backendNetEndPoint.isEmpty()) {
      closeSocket(socket);
    } else {
      server.internalTakeBackendEndPoint(backendNetEndPoint.get());
    }
  }

  private static void closeSocket(final Socket socket) {
    try {
      socket.close();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }

  private static Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServer(
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

  private static Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServer(
    final Socket socket,
    final Server server) {
    final var socketInputStream = getOptionalInputStreamOfSocket(socket);
    final var socketOutputStream = getOptionalOutputStreamOfSocket(socket);

    if (socketInputStream.isEmpty() || socketOutputStream.isEmpty()) {
      return Optional.empty();
    }

    final var firstReveivedLine = INPUT_STREAM_TOOL.readLineFromInputStreamOrNull(socketInputStream.get());

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

  private static Optional<IEndPoint> createOptionalBackendNetEndPointForSocketAndServerWhenIsHttpSocketOrWebSocket(
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

      final var openingHandshakeResponse = //
      WebSocketHandShakeRequest
        .fromLines(lines)
        .getWebSocketHandShakeResponse()
        .toString();

      Logger.logInfo(
        "The current SocketHandler sends the opening handshake response: "
        + StringTool.getInSingleQuotes(openingHandshakeResponse));

      sendRawMessageToOutputStream(socketOutputStream, openingHandshakeResponse);

      final var webSocketEndPoint = //
      WebSocketEndPoint.withSocketAndSocketInputStreamAndSocketOutputStream(
        socket,
        socketInputStream,
        socketOutputStream);

      return Optional.of(webSocketEndPoint);
    }

    if (HttpRequest.canBe(lines)) {
      sendRawMessageToOutputStream(socketOutputStream, server.getInitialHttpMessage());
    }

    return Optional.empty();
  }

  private static SocketEndPoint createSocketEndPointWithCustomTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream,
    final String firstReveivedLine) {
    return //
    SocketEndPoint.withSocketAndSocketInputStreamAndSocketOutputStreamAndTarget(
      socket,
      socketInputStream,
      socketOutputStream,
      ImmutableNode.fromString(firstReveivedLine.substring(1)).getHeader());
  }

  private static SocketEndPoint createSocketEndPointWithDefaultTarget(
    final Socket socket,
    final InputStream socketInputStream,
    final OutputStream socketOutputStream) {
    return //
    SocketEndPoint.withSocketAndSocketInputStreamAndSocketOutputStream(socket, socketInputStream, socketOutputStream);
  }

  private static void fillUpLinesIntoListUntilReceivesEmptyLine(
    final LinkedList<String> lines,
    final InputStream inputStream) {
    while (true) {
      final var line = INPUT_STREAM_TOOL.readLineFromInputStreamOrNull(inputStream);

      if (line == null) {
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.LINE);
      }

      if (line.isEmpty()) {
        break;
      }

      lines.addAtEnd(line);
    }
  }

  private static Optional<InputStream> getOptionalInputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getInputStream());
    } catch (final IOException ioException) {
      Logger.logError(ioException);

      return Optional.empty();
    }
  }

  private static Optional<OutputStream> getOptionalOutputStreamOfSocket(final Socket socket) {
    try {
      return Optional.of(socket.getOutputStream());
    } catch (final IOException ioException) {
      Logger.logError(ioException);

      return Optional.empty();
    }
  }

  private static Optional<SocketType> getSocketTypeFromFirstReceivedLine(
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

  private static void sendRawMessageToOutputStream(final OutputStream outputStream, final String rawMessage) {
    try {
      outputStream.write(rawMessage.getBytes(StandardCharsets.UTF_8));
      outputStream.flush();
    } catch (final IOException ioException) {
      throw WrapperException.forError(ioException);
    }
  }
}
