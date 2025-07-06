package ch.nolix.core.container.arraylist;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class ArrayListTest extends ContainerTest {

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {
    return ArrayList.withElement(element, elements);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ArrayList.createEmpty();
  }

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

  @Test
  void testCase_clear_whenIsEmpty() {

    //setup
    final ArrayList<String> testUnit = ArrayList.createEmpty();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit).isEmpty();
  }

  @Test
  void testCase_clear_whenContainsAny() {

    //setup
    final var testUnit = ArrayList.withElement("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit).isEmpty();
  }

  @Test
  void testCase_getCopy() {

    //setup
    final var testUnit = ArrayList.withElement("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

    //execution
    final var result = testUnit.getCopy();

    //verification
    expect(result).containsExactlyInSameOrder(testUnit);
  }

  @Test
  void testCase_isMaterialized() {

    //setup
    final var testUnit = ArrayList.createEmpty();

    //execution
    final var result = testUnit.isMaterialized();

    //verification
    expect(result).isTrue();
  }

  @Test
  void testCase_withElemens() {

    //setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };

    //execution
    final var result = ArrayList.withElements(elements);

    //verification
    expect(result).containsExactlyInSameOrder(elements);
  }

  @Test
  void testCase_withInitialCapacity() {

    //execution
    final var result = ArrayList.withInitialCapacity(10);

    //verification
    expect(result).isEmpty();
  }

  @Test
  void testCase_withInitialCapacity_whenTheGivenInitialCapacityIsNegative() {

    //execution & verification
    expectRunning(() -> ArrayList.withInitialCapacity(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given initial capacity '-1' is negative.");
  }
}
