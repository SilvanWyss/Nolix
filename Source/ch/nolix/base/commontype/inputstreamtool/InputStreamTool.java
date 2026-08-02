/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontype.inputstreamtool;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.base.commontype.arraymapper.ArrayMapper;
import ch.nolix.base.foundation.linkedlist.SimpleLinkedList;
import ch.nolix.baseapi.commontype.inputstreamtool.IInputStreamTool;

/**
 * @author Silvan Wyss
 */
public final class InputStreamTool implements IInputStreamTool {
  private static final ArrayMapper ARRAY_MAPPER = new ArrayMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public String readLineFromInputStreamOrNull(final InputStream inputStream) {
    final SimpleLinkedList<Byte> bytes = SimpleLinkedList.createEmpty();

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
          final var byteArray = ARRAY_MAPPER.toByteArray(bytes, bytes.getElementCount(), Byte::byteValue);

          return new String(byteArray, StandardCharsets.UTF_8);
        }

        bytes.addAtEnd((byte) lByte);
      } catch (final Exception _) { // NOSONAR: All Exception must be caught.
        return null;
      }
    }
  }
}
