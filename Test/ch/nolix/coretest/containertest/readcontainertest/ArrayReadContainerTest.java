//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//JUnit imports
import org.junit.jupiter.api.Test;

import ch.nolix.core.commontypetool.arraytool.GlobalArrayTool;
import ch.nolix.core.container.readcontainer.ArrayReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
final class ArrayReadContainerTest extends ContainerTest {

  //method
  @Test
  void testCase_forArray_whenTheGivenArrayIsNull() {

    //execution & verification
    expectRunning(() -> ArrayReadContainer
      .forArray(null)).throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return ArrayReadContainer.forArray(GlobalArrayTool.createArrayWithElement(element, elements));
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new ArrayReadContainer<>();
  }
}
