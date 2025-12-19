package ch.nolix.core.net.endpoint;

import ch.nolix.coreapi.net.securityproperty.SecurityMode;

/**
 * @author Silvan Wyss
 */
public final class LocalServer extends AbstractServer {
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
