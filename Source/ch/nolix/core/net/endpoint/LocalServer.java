//package declaration
package ch.nolix.core.net.endpoint;

//own imports
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;

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
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
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
