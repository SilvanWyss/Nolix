//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.commontype.commontypehelper.GlobalArrayHelper;
import ch.nolix.core.container.readcontainer.ArrayReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class ArrayReadContainerTest extends ContainerTest {

  // method
  @TestCase
  public void testCase_forArray_whenTheGivenArrayIsNull() {

    // execution & verification
    expectRunning(() -> ArrayReadContainer
        .forArray(null)).throwsException()
        .ofType(ArgumentIsNullException.class)
        .withMessage("The given array is null.");
  }

  // method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
      final E element,
      final @SuppressWarnings("unchecked") E... elements) {
    return ArrayReadContainer.forArray(GlobalArrayHelper.createArrayWithElement(element, elements));
  }

  // method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new ArrayReadContainer<>();
  }
}
