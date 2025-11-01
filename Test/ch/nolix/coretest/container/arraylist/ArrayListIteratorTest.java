package ch.nolix.coretest.container.arraylist;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coretest.container.base.CopyableIteratorTest;

final class ArrayListIteratorTest extends CopyableIteratorTest {
  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {
    final ArrayList<E> arrayList = ArrayList.createEmpty();

    return arrayList.iterator();
  }

  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    final @SuppressWarnings("unchecked") E... elements) {
    final var arrayList = ArrayList.withElements(elements);

    return arrayList.iterator();
  }
}
