//package declaration
package ch.nolix.core.net.endpoint2;

//own imports
import ch.nolix.coreapi.netapi.endpoint2api.ISlot;
import ch.nolix.coreapi.netapi.securityapi.SecurityMode;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-27
 */
public final class LocalServer extends BaseServer {

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SecurityMode getSecurityLevel() {
    return SecurityMode.NONE;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedDefaultSlot(final ISlot defaultSlot) {
    //Does nothing.
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot slot) {
    //Does nothing.
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteRemovedSlot(final ISlot slot) {
    //Does nothing.
  }
}
