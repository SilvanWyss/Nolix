package ch.nolix.core.net.endpoint;

import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 * @version 2021-06-17
 */
public final class LocalServer extends BaseServer {

  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityMode() {
    return SecurityMode.NONE;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void noteClose() {
    //Does nothing.
  }
}
