package ch.nolix.core.programcontrol.closepool;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.GroupCloseable;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.ICloseController;
import ch.nolix.coreapi.resourcecontrolapi.resourceclosingapi.IClosePool;

/**
 * @author Silvan Wyss
 * @version 2020-07-05
 */
public final class CloseController implements ICloseController {

  private IClosePool parentClosePool;

  /**
   * Creates a new {@link CloseController} for the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  private CloseController(final GroupCloseable element) {
    parentClosePool = new ClosePool(element);
  }

  /**
   * @param element
   * @return a new {@link CloseController} for the given element.
   * @throws ArgumentIsNullException if the given element is null.
   */
  public static CloseController forElement(final GroupCloseable element) {
    return new CloseController(element);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    parentClosePool.closeElementsIfStateIsOpen();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createCloseDependencyTo(final GroupCloseable element) {

    final var elementsToAdd = element.getStoredCloseController().getParentClosePool().getStoredElements();

    for (final var e : elementsToAdd) {
      e.getStoredCloseController().setParentClosePool(parentClosePool);
    }

    parentClosePool.addElements(elementsToAdd);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IClosePool getParentClosePool() {
    return parentClosePool;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasClosed() {
    return (parentClosePool.getState() == CloseState.CLOSED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentClosePool(final IClosePool parentClosePool) {

    //Asserts that the given parentClosePool is not null.
    Validator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();

    //Sets the parentClosePool of the current CloseController.
    this.parentClosePool = parentClosePool;
  }
}
