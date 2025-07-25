package ch.nolix.core.commontypetool.inputstreamtool;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.commontypetool.inputstreamtool.IInputStreamTool;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

public final class InputStreamTool implements IInputStreamTool {

  /**
   * {@inheritDoc}
   */
  @Override
  public String readLineFromInputStream(final InputStream inputStream) {

    final ILinkedList<Byte> bytes = LinkedList.createEmpty();

    while (true) {
      try {

        final var lByte = inputStream.read();

        if (lByte == -1) {
          return null;
        }

        if (lByte == '\r') {
          continue;
        }

        if (lByte == '\n') {
          return new String(bytes.toByteArray(Byte::byteValue), StandardCharsets.UTF_8);
        }

        bytes.addAtEnd((byte) lByte);
      } catch (final Exception exception) { //NOSONAR: All Exceptions must be caught.
        return null;
      }
    }
  }
}
