/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.commontypetool.inputstreamtool;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import ch.nolix.base.independent.list.List;
import ch.nolix.baseapi.commontypetool.inputstreamtool.IInputStreamTool;

/**
 * @author Silvan Wyss
 */
public final class InputStreamTool implements IInputStreamTool {
  /**
   * {@inheritDoc}
   */
  @Override
  public String readLineFromInputStreamOrNull(final InputStream inputStream) {
    final List<Byte> bytes = List.createEmpty();

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
