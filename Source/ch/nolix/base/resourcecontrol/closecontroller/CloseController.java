/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.resourcecontrol.closecontroller;

import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.resourcecontrol.closecontroller.GroupCloseable;
import ch.nolix.baseapi.resourcecontrol.closecontroller.ICloseController;
import ch.nolix.baseapi.resourcecontrol.closecontroller.IClosePool;
import ch.nolix.baseapi.resourcecontrol.resourceproperty.Openness;

/**
 * @author Silvan Wyss
 */
public final class CloseController implements ICloseController {
  private IClosePool parentClosePool;

  /**
   * Creates a new {@link CloseController} for the given element.
   * 
   * @param element
   * @throws RuntimeException if the given element is null
   */
  private CloseController(final GroupCloseable element) {
    parentClosePool = ClosePool.forElement(element);
  }

  /**
   * @param element
   * @return a new {@link CloseController} for the given element
   * @throws RuntimeException if the given element is null
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
    return (parentClosePool.getState() == Openness.CLOSED);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParentClosePool(final IClosePool parentClosePool) {
    // Asserts that the given parentClosePool is not null.
    Validator.assertThat(parentClosePool).thatIsNamed("parent ClosePool").isNotNull();

    // Sets the parentClosePool of the current CloseController.
    this.parentClosePool = parentClosePool;
  }
}
