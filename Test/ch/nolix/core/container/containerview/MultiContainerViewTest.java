package ch.nolix.core.container.containerview;

import org.junit.jupiter.api.Test;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.MultiContainerView;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class MultiContainerViewTest extends ContainerTest {

  @Test
  void testCase_forArray() {

    //setup
    final var array1 = new String[] { "x", "xx" };
    final var array2 = new String[] { "y", "yy" };
    final var array3 = new String[] { "z", "zz" };

    //execution
    final var result = MultiContainerView.forArray(array1, array2, array3);

    //verification
    expect(result).containsExactlyInSameOrder("x", "xx", "y", "yy", "z", "zz");
  }

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {

    final var container = ImmutableList.withElement(element, elements);

    return MultiContainerView.forIterable(container);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return MultiContainerView.forEmpty();
  }
}
