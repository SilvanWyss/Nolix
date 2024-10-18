package ch.nolix.coreapi.commontypetoolapi.inputstreamtoolapi;

import java.io.InputStream;

public interface IInputStreamTool {

  /**
   * @param inputStream
   * @return the next line from the given inputStream or null.
   */
  String readLineFromInputStream(InputStream inputStream);
}
