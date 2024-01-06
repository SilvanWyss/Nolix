//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;
import ch.nolix.coreapi.netapi.securityproperty.SecurityMode;

//class
/**
 * @author Silvan Wyss
 * @date 2021-06-28
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
