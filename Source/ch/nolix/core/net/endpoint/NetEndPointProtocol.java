//package declaration
package ch.nolix.core.net.endpoint;

//class
/**
 * Of the {@link NetEndPointProtocol} an instance cannot be created.
 * 
 * @author Silvan Wyss
 * @date 2019-05-01
 */
final class NetEndPointProtocol {

  // constant
  public static final char MESSAGE_PREFIX = 'M';

  // constant
  public static final char TARGET_PREFIX = 'T';

  // constant
  public static final char MAIN_TARGET_PREFIX = 'A';

  // constant
  public static final char CLOSE_PREFIX = 'C';

  // constructor
  /**
   * Prevents that an instance of the {@link NetEndPointProtocol} can be created.
   */
  private NetEndPointProtocol() {
  }
}
