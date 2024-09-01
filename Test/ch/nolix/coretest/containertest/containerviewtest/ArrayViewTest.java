//package declaration
package ch.nolix.coretest.containertest.containerviewtest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.commontypetool.arraytool.ArrayTool;
import ch.nolix.core.container.containerview.ArrayView;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
final class ArrayViewTest extends ContainerTest {

  //constant
  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  //method
  @Test
  void testCase_forArray_whenTheGivenArrayIsNull() {

    //execution & verification
    expectRunning(() -> ArrayView
      .forArray(null)).throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return ArrayView.forArray(ARRAY_TOOL.createArrayWithElement(element, elements));
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new ArrayView<>();
  }
}
