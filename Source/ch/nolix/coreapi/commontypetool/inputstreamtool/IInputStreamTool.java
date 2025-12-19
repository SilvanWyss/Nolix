package ch.nolix.coreapi.commontypetool.inputstreamtool;

import java.io.InputStream;

/**
 * @author Silvan Wyss
 */
public interface IInputStreamTool {
  /**
   * @param inputStream
   * @return the next line from the given inputStream or null.
   */
  String readLineFromInputStreamOrNull(InputStream inputStream);
}
