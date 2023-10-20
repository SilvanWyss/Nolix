//package declaration
package ch.nolix.core.programcontrol.groupcloseable;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.programcontrolapi.processproperty.CloseState;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.GroupCloseable;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.ICloseController;
import ch.nolix.coreapi.programcontrolapi.resourcecontrolapi.IClosePool;

//class
/**
 * @author Silvan Wyss
 * @date 2020-07-05
 */
public final class CloseController implements ICloseController {

  //attribute
  private IClosePool parentClosePool;

  //constructor
  /**
   * Creates a new {@link CloseController} for the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  private CloseController(final GroupCloseable element) {
    parentClosePool = new ClosePool(element);
  }

  //static method
  /**
   * @param element
   * @return a new {@link CloseController} for the given element.
   * @throws ArgumentIsNullException if the given element is null.
   */
  public static CloseController forElement(final GroupCloseable element) {
    return new CloseController(element);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void close() {
    parentClosePool.closeElementsIfStateIsOpen();
  }

  //method
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

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IClosePool getParentClosePool() {
    return parentClosePool;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasClosed() {
    return (parentClosePool.getState() == CloseState.CLOSED);
  }

  //method.
  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentClosePool(final IClosePool parentClosePool) {

    //Asserts that the given parentClosePool is not null.
    GlobalValidator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();

    //Sets the parentClosePool of the current CloseController.
    this.parentClosePool = parentClosePool;
  }
}
