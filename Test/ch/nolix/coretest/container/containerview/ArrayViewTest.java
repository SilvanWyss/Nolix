package ch.nolix.coretest.container.containerview;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.containerview.ArrayContainerView;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coretest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class ArrayViewTest extends ContainerTest {
  @Test
  void testCase_forArray_whenTheGivenArrayIsNull() {
    //execution & verification
    expectRunning(() -> ArrayContainerView
      .forArray(null)).throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IContainer<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return ArrayContainerView.forArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return ArrayContainerView.createEmpty();
  }
}
