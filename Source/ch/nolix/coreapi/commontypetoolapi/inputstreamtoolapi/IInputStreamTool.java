//package declaration
package ch.nolix.coreapi.commontypetoolapi.inputstreamtoolapi;

//Java imports
import java.io.InputStream;

//interface
public interface IInputStreamTool {

  //method declaration
  /**
   * @param inputStream
   * @return the next line from the given inputStream or null.
   */
  String readLineFromInputStream(InputStream inputStream);
}
