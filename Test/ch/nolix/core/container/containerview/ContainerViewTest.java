package ch.nolix.core.container.containerview;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.core.container.linkedlist.ContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class ContainerViewTest extends ContainerTest {

  @Test
  void testCase_containsEqualing() {

    //setup
    final String[] array1 = { "apple", "banana", "cerish" };
    final String[] array2 = { "antelope", "baboon", "lion" };
    final String[] array3 = { "flower", "tree", "palm" };

    //execution
    final var testUnit = ContainerView.forArray(array1, array2, array3);

    //execution
    expect(testUnit.containsEqualing("apple")).isTrue();
    expect(testUnit.containsEqualing("banana")).isTrue();
    expect(testUnit.containsEqualing("cerish")).isTrue();
    expect(testUnit.containsEqualing("antelope")).isTrue();
    expect(testUnit.containsEqualing("baboon")).isTrue();
    expect(testUnit.containsEqualing("lion")).isTrue();
    expect(testUnit.containsEqualing("flower")).isTrue();
    expect(testUnit.containsEqualing("tree")).isTrue();
    expect(testUnit.containsEqualing("palm")).isTrue();
    expect(testUnit.containsEqualing("jupiter")).isFalse();
    expect(testUnit.containsEqualing("saturn")).isFalse();
    expect(testUnit.containsEqualing("uranus")).isFalse();
  }

  @Test
  void testCase_getStoredAtOneBasedIndex() {

    //setup
    final String[] array1 = { "apple", "banana", "cerish" };
    final String[] array2 = { "antelope", "baboon", "elephant" };
    final String[] array3 = { "flower", "tree", "palm" };

    //execution
    final var testUnit = ContainerView.forArray(array1, array2, array3);

    //verification
    expect(testUnit.getStoredAtOneBasedIndex(1)).isEqualTo("apple");
    expect(testUnit.getStoredAtOneBasedIndex(2)).isEqualTo("banana");
    expect(testUnit.getStoredAtOneBasedIndex(3)).isEqualTo("cerish");
    expect(testUnit.getStoredAtOneBasedIndex(4)).isEqualTo("antelope");
    expect(testUnit.getStoredAtOneBasedIndex(5)).isEqualTo("baboon");
    expect(testUnit.getStoredAtOneBasedIndex(6)).isEqualTo("elephant");
    expect(testUnit.getStoredAtOneBasedIndex(7)).isEqualTo("flower");
    expect(testUnit.getStoredAtOneBasedIndex(8)).isEqualTo("tree");
    expect(testUnit.getStoredAtOneBasedIndex(9)).isEqualTo("palm");
  }

  @Test
  void testCase_getStoredSelected() {

    //setup
    final String[] array1 = { "A", "AA", "AAA" };
    final String[] array2 = { "B", "BB", "BBB" };
    final String[] array3 = { "C", "CC", "CCC" };

    //execution
    final var testUnit = ContainerView.forArray(array1, array2, array3);

    //verification
    expect(testUnit.getStoredSelected(s -> s.length() == 1).toString()).isEqualTo("A,B,C");
    expect(testUnit.getStoredSelected(s -> s.length() == 2).toString()).isEqualTo("AA,BB,CC");
    expect(testUnit.getStoredSelected(s -> s.length() == 3).toString()).isEqualTo("AAA,BBB,CCC");
  }

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return ContainerView.forElementAndArray(element, elements);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ContainerView.forEmpty();
  }
}
