//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.coreapi.netapi.securityapi.SecurityLevel;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-17
 */
public final class LocalServer extends BaseServer {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityLevel getSecurityLevel() {
    return SecurityLevel.UNSECURE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    //Does nothing.
  }
}
