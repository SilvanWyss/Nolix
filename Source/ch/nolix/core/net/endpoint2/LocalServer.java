//package declaration
package ch.nolix.core.net.endpoint2;

import ch.nolix.coreapi.netapi.endpoint2api.ISlot;

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
  protected void noteAddedDefaultSlot(final ISlot defaultEndPointTaker) {
    //Does nothing.
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected void noteAddedSlot(final ISlot endPointTaker) {
    //Does nothing.
  }
}
