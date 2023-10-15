//package declaration
package ch.nolix.core.net.endpoint3;

//class
/**
 * Of the {@link NetEndPointProtocol} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 */
final class NetEndPointProtocol {

  // constant
  public static final String COMMANDS_HEADER = "Commands";

  // constant
  public static final String DONE_HEADER = "Done";

  // constant
  public static final String ERROR_HEADER = "Error";

  // constant
  public static final String MULTI_DATA_HEADER = "MultiData";

  // constant
  public static final String MULTI_DATA_REQUEST_HEADER = "MultiDataRequest";

  // constructor
  /**
   * Prevents that an instance of the {@link NetEndPointProtocol} can be created.
   */
  private NetEndPointProtocol() {
  }
}
