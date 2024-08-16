//package declaration
package ch.nolix.coretest.containertest.arraylisttest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
final class ArrayListTest extends ContainerTest {

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {
    return ArrayList.withElement(element, elements);
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ArrayList.createEmpty();
  }

  //method
  @Test
  void testCase_addAtEnd_whenHasAvailableCapacity() {

    //setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };
    final var testUnit = ArrayList.withInitialCapacity(10);

    //execution
    testUnit.addAtEnd(elements);

    //verification
    expect(testUnit).containsAll(elements);
  }

  //method
  @Test
  void testCase_addAtEnd_whenDoesNotHaveAvailableCapacity() {

    //setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };
    final var testUnit = ArrayList.withInitialCapacity(5);

    //execution
    testUnit.addAtEnd(elements);

    //verification
    expect(testUnit).containsAll(elements);
  }

  //method
  @Test
  void testCase_clear_whenIsEmpty() {

    //setup
    final ArrayList<String> testUnit = ArrayList.createEmpty();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit).isEmpty();
  }

  //method
  @Test
  void testCase_clear_whenContainsAny() {

    //setup
    final var testUnit = ArrayList.withElement("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit).isEmpty();
  }

  //method
  @Test
  void testCase_getCopy() {

    //setup
    final var testUnit = ArrayList.withElement("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(result).containsExactlyInSameOrder(testUnit);
  }

  //method
  @Test
  void testCase_isMaterialized() {

    //setup
    final var testUnit = ArrayList.createEmpty();

    //execution
    final var result = testUnit.isMaterialized();

    //verification
    expect(result);
  }

  //method
  @Test
  void testCase_withElemens() {

    //setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };

    //execution
    final var result = ArrayList.withElements(elements);

    //verification
    expect(result).containsExactlyInSameOrder(elements);
  }

  //method
  @Test
  void testCase_withInitialCapacity() {

    //execution
    final var result = ArrayList.withInitialCapacity(10);

    //verification
    expect(result).isEmpty();
  }

  //method
  @Test
  void testCase_withInitialCapacity_whenTheGivenInitialCapacityIsNegative() {

    //execution & verification
    expectRunning(() -> ArrayList.withInitialCapacity(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given initial capacity '-1' is negative.");
  }
}
