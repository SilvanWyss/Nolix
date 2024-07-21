//package declaration
package ch.nolix.coretest.containertest.arraylisttest;

//own imports
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coretest.containertest.basetest.CopyableIteratorTest;

//class
final class ArrayListIteratorTest extends CopyableIteratorTest {

  //method
  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {

    final ArrayList<E> arrayList = ArrayList.createEmpty();

    return arrayList.iterator();
  }

  //method
  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {

    final var arrayList = ArrayList.withElement(element, elements);

    return arrayList.iterator();
  }
}
