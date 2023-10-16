//package declaration
package ch.nolix.core.net.endpoint3;

//own imports
import ch.nolix.coreapi.netapi.endpoint3api.ISlot;

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
