//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.readcontainer.MultiReadContainer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class MultiReadContainerTest extends ContainerTest {

  //method
  @TestCase
  public void testCase_forArray() {

    //setup
    final var array1 = new String[] { "x", "xx" };
    final var array2 = new String[] { "y", "yy" };
    final var array3 = new String[] { "z", "zz" };

    //execution
    final var result = MultiReadContainer.forArray(array1, array2, array3);

    //verification
    expect(result).containsExactlyInSameOrder("x", "xx", "y", "yy", "z", "zz");
  }

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {

    final var container = ImmutableList.withElement(element, elements);

    return MultiReadContainer.forIterable(container);
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new MultiReadContainer<>();
  }
}
