//package declaration
package ch.nolix.core.commontypetool.inputstreamtool;

//Java imports
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.commontypetoolapi.inputstreamtoolapi.IInputStreamTool;

//class
public final class InputStreamTool implements IInputStreamTool {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String readLineFromInputStream(final InputStream inputStream) {

    final var bytes = new LinkedList<Byte>();

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
      } catch (final Exception exception) { //NOSONAR: At this place any Exception should be caught.
        return null;
      }
    }
  }
}
