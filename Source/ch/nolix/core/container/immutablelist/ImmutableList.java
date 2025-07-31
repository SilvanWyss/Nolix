package ch.nolix.core.container.immutablelist;

import java.util.stream.Stream;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.commontypetool.arraytool.ArrayTool;
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.base.AbstractContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.iterator.CopyableIterator;

/**
 * A {@link ImmutableList} is a {@link AbstractContainer} that is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2022-07-08
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> extends AbstractExtendedContainer<E> {

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private final E[] elements;

  /**
   * Creates a new empty {@link ImmutableList}.
   */
  @SuppressWarnings("unchecked")
  private ImmutableList() {
    elements = (E[]) new Object[0];
  }

  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   */
  private ImmutableList(final E[] elements) {
    this.elements = elements; //NOSONAR: A ImmutableList operates on the original instance.
  }

  /**
   * Creates a new {@link ImmutableList} with the given element and elements.
   * 
   * @param element
   * @param elements
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  private ImmutableList(final E element, final E[] elements) {

    this.elements = ARRAY_TOOL.createArrayWithElement(element, elements);

    Validator.assertThatTheElements(elements).areNotNull();
  }

  /**
   * @return a new empty {@link ImmutableList}.
   * @param <E2> is the type of the elements the {@link ImmutableList} could have.
   */
  public static <E2> ImmutableList<E2> createEmpty() {
    return new ImmutableList<>();
  }

  public static <E2> ImmutableList<E2> forArray(final E2[] array) {
    return new ImmutableList<>(array.clone());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param <E2>
   * @param container
   * @return a new {@link ImmutableList} with the elements from the given
   *         container.
   * @throws ArgumentIsNullException if one of the elements of the given container
   *                                 is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ImmutableList<E2> forIterable(final Iterable<E2> container) {

    if (container instanceof final ImmutableList<E2> immutableList) {
      return immutableList;
    }

    final var elementCount = IterableTool.getCount(container);
    final var elements = new Object[elementCount];
    var index = 0;
    for (final var e : container) {

      if (e == null) {
        throw ArgumentIsNullException.forArgumentName((index + 1) + "th element");
      }

      elements[index] = e;

      index++;
    }

    return new ImmutableList<>((E2[]) elements);
  }

  /**
   * @param stream
   * @param <E2>   is the type of the elements of the given stream.
   * @return a new {@link ImmutableList} with the elements from the given stream.
   * @throws ArgumentIsNullException if the given stream is null.
   * @throws ArgumentIsNullException if one of the elements of the given stream is
   *                                 null.
   */
  public static <E2> ImmutableList<E2> fromStream(final Stream<E2> stream) {

    Validator.assertThat(stream).thatIsNamed(Stream.class).isNotNull();

    return forIterable(stream.toList());
  }

  /**
   * @param element
   * @param elements
   * @param <E2>     is the type of the given element and of the given elements.
   * @return a new {@link ImmutableList} with the given element and elements.
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  public static <E2> ImmutableList<E2> withElement(
    final E2 element,
    final @SuppressWarnings("unchecked") E2... elements) {
    return new ImmutableList<>(element, elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return elements.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {

    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(1, getCount());

    return elements[oneBasedIndex - 1];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
