/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.inputstreamtool;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.base.independent.linkedlist.LinkedList;
import ch.nolix.baseapi.commontype.inputstreamtool.IInputStreamTool;

/**
 * @author Silvan Wyss
 */
public final class InputStreamTool implements IInputStreamTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public String readLineFromInputStreamOrNull(final InputStream inputStream) {
    final LinkedList<Byte> bytes = LinkedList.createEmpty();

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
      } catch (final Exception _) { //NOSONAR: All Exception must be caught.
        return null;
      }
    }
  }
}
