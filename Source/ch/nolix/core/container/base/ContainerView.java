//package declaration
package ch.nolix.core.container.base;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * A {@link ContainerView} can iterate over a part of another container.
 * 
 * A {@link ContainerView} must not use the methods of the accessed container
 * except the iterator method. The reason is that the accessed container can be
 * a specialized container that does not use its iterator in any of its declared
 * or overwritten method.
 * 
 * @author Silvan Wyss
 * @version 2017-08-27
 * @param <E> is the type of the elements of a {@link ContainerView}.
 */
final class ContainerView<E> extends Container<E> {

  //attribute
  private final Container<E> container;

  //attribute
  private final int startIndex;

  //attribute
  private final int endIndex;

  //constructor
  /**
   * Creates a new {@link ContainerView} with the given container, startIndex and
   * endIndex.
   * 
   * @param container
   * @param startIndex
   * @param endIndex
   * @throws ArgumentIsNullException      if the given container is null.
   * @throws NonPositiveArgumentException if the given startIndex is not positive.
   * @throws NonPositiveArgumentException if the given endIndex is not positive.
   * @throws SmallerArgumentException     if the given endIndex is smaller than
   *                                      the given startIndex.
   * @throws BiggerArgumentException      if the given endIndex is bigger than the
   *                                      number of elements of the given
   *                                      container.
   */
  public ContainerView(final Container<E> container, final int startIndex, final int endIndex) {

    GlobalValidator.assertThat(container).thatIsNamed(LowerCaseVariableCatalogue.CONTAINER).isNotNull();
    GlobalValidator.assertThat(startIndex).thatIsNamed(LowerCaseVariableCatalogue.START_INDEX).isPositive();
    GlobalValidator.assertThat(endIndex).thatIsNamed(LowerCaseVariableCatalogue.END_INDEX).isPositive();

    GlobalValidator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.END_INDEX)
      .isBiggerThanOrEquals(startIndex);

    GlobalValidator
      .assertThat(endIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.END_INDEX)
      .isNotBiggerThan(container.getCount());

    this.container = container;
    this.startIndex = startIndex;
    this.endIndex = endIndex;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return (endIndex - startIndex + 1);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {

    GlobalValidator.assertThat(p1BasedIndex).thatIsNamed(LowerCaseVariableCatalogue.INDEX).isPositive();

    GlobalValidator
      .assertThat(p1BasedIndex)
      .thatIsNamed(LowerCaseVariableCatalogue.INDEX)
      .isNotBiggerThan(getCount());

    return container.getStoredAt1BasedIndex(startIndex + p1BasedIndex - 1);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return getStoredAt1BasedIndex(getCount());
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return new ContainerViewIterator<>(
      container,
      startIndex,
      endIndex);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return container.createEmptyMutableList(new Marker<E>()).toOrderedList(norm);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return container.createEmptyMutableList(marker);
  }
}
