package ch.nolix.core.net.endpoint3;

import ch.nolix.coreapi.net.endpoint3.ISlot;
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
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    //Does nothing.
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    //Does nothing.
  }
}
