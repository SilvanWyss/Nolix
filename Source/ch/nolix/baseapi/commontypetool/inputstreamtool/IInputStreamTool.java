/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.commontypetool.inputstreamtool;

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
