package ch.nolix.core.container.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.BiggerArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.iteratorvalidatorapi.IIterableTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StoringRequestable;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.StringCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalogue;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements a {@link Container} can store.
 */
public abstract class Container<E> //NOSONAR: A Container has many methods and thus many dependencies.
implements IContainer<E> {

  private static final IIterableTool ITERABLE_TOOL = new IterableTool();

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(final Object object) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given object.
      if (e == object) {
        return true;
      }
    }

    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Iterable<?> objects) {

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container does not contain the current object.
      if (!contains(o)) {
        return false;
      }
    }

    return true;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Object object, final Object... objects) {
    return //
    contains(object)
    && containsAll(objects);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Object[] objects) {

    //Asserts that the given objects is not null.
    GlobalValidator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalogue.OBJECTS).isNotNull();

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container does not contain the given object.
      if (!contains(o)) {
        return false;
      }
    }

    return true;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Predicate<E> selector) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the given selector selects the current element.
      if (selector.test(e)) {
        return true;
      }
    }

    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Object object, final Object... objects) {
    return //
    contains(object)
    || containsAnyOf(objects);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAnyOf(final Iterable<?> objects) {

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container contains the current object.
      if (contains(o)) {
        return true;
      }
    }

    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAnyOf(final Object[] objects) {

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container contains the current object.
      if (contains(o)) {
        return true;
      }
    }

    return false;
  }

  /**
   * The time complexity of this implementation is -O(1) if the given container is
   * a {@link IContainer}. -O(n) otherwise.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAsManyAs(Iterable<?> iterable) {

    //Handles the case that the given iterable is a IContainer.
    if (iterable instanceof IContainer<?> container) {
      return (getCount() == container.getCount());
    }

    //Handles the case that the given iterable is not a IContainer.
    return (getCount() == ITERABLE_TOOL.getCount(iterable));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsEqualing(final Object element) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element equals the given element.
      if (e.equals(element)) {
        return true;
      }
    }

    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * The current {@link Container} contains m elements.
   * 
   * The given iterable contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyEqualingInSameOrder(final Iterable<?> iterable) {

    //Handles the case that the given iterable is null.
    if (iterable != null) {
      return containsExactlyEqualingInSameOrderIfGivenIterableIsNotNull(iterable);
    }

    //Handles the case that the given iterable is null.
    return isEmpty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyInSameOrder(final Iterable<?> container) {

    //Iterates the current Container.
    final var iterator = container.iterator();
    for (final var e : this) {

      //Handles the case that the current element is not the next element in the
      //given container.
      if (!iterator.hasNext() || e != iterator.next()) {
        return false;
      }
    }

    return !iterator.hasNext();
  }

  /**
   * The time complexity of this implementation is -O(1) if the given container is
   * a {@link IContainer}.
   * 
   * The time complexity of this implementation is O(n) if the given iterable is
   * not a {@link IContainer} and if the current {@link Container} contains n
   * elements..
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsLessThan(final Iterable<?> iterable) {

    //Handles the case that the given iterable is a IContainer.
    if (iterable instanceof IContainer<?> container) {
      return (getCount() < container.getCount());
    }

    //Handles the case that the given iterable is not a IContainer.
    return (getCount() < ITERABLE_TOOL.getCount(iterable));
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsMoreThan(final Iterable<?> container) {

    //Handles the case that the given container is a IContainer.
    if (container instanceof IContainer<?> lContainer) {
      return (getCount() > lContainer.getCount());
    }

    //Handles the case that the given container is not a IContainer.
    return (getCount() > ITERABLE_TOOL.getCount(container));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Predicate<E> selector) {
    return !containsAny(selector::test);
  }

  /**
   * The time complexity of this implementation is O(m*n) if: -The current
   * {@link Container} contains m elements. -n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Object element, final Object... elements) {
    return !containsAny(element, elements);
  }

  /**
   * The time complexity of this implementation is O(m*n) if: -The current
   * {@link Container} contains m elements. -n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNoneOf(final Iterable<?> elements) {
    return !containsAnyOf(elements);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnce(final Object object) {

    var found = false;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given object.
      if (e == object) {

        //Handles the case that the given element is already found.
        if (found) {
          return false;
        }

        //Handles the case that the given element is not already found.
        found = true;
      }
    }

    return found;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOne() {

    final var iterator = iterator();

    //Handles the case that the current Container is empty.
    if (!iterator.hasNext()) {
      return false;
    }

    //Handles the case that the current Container is not empty.
    iterator.next();
    return !iterator.hasNext();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOne(final Predicate<E> selector) {

    var found = false;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the given selector selects the current element.
      if (selector.test(e)) {

        //Handles the case that an element the given selector selects was already
        //found.
        if (found) {
          return false;
        }

        found = true;
      }
    }

    return found;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneEqualing(final E element) {
    return containsOne(e -> e.equals(element));
  }

  /**
   * The time complexity of this implementation is O(m*n) if: -The current
   * {@link Container} contains m elements. -n elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnly(final Predicate<E> selector) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the given selector does not select the current element.
      if (!selector.test(e)) {
        return false;
      }
    }

    return true;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewFrom1BasedStartIndex(final int startIndex) {
    return getSubContainerFromStartIndexToEndIndex(startIndex, getCount());
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewFrom1BasedStartIndexTo1BasedEndIndex(final int startIndex, final int endIndex) {
    return getSubContainerFromStartIndexToEndIndex(startIndex, endIndex);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverage(final Function<E, Number> norm) {

    assertIsNotEmpty();

    final var sumAsBigDecimal = getSum(norm);
    final var elementCountAsBigDecimal = BigDecimal.valueOf(getCount());
    final var averageAsBigDecimal = sumAsBigDecimal.divide(elementCountAsBigDecimal, MathContext.DECIMAL32);

    return averageAsBigDecimal.doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverageOrZero(final Function<E, Number> norm) {

    //Handles the case that the current Container is empty.
    if (isEmpty()) {
      return 0.0;
    }

    //Handles the case that the current Container contains elements.
    return getAverage(norm);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCount(final Predicate<E> selector) {

    var elementCount = 0;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the given selector selects the current element.
      if (selector.test(e)) {
        elementCount++;
      }
    }

    return elementCount;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCount(final Object element) {

    var elementCount = 0;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given element.
      if (e == element) {
        elementCount++;
      }
    }

    return elementCount;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int get1BasedIndexOfFirst(final Predicate<E> selector) {

    //Iterates the current Container.
    var l1BasedIndex = 1;
    for (final var e : this) {

      //Handles the case that the given selector selects the current element.
      if (selector.test(e)) {
        return l1BasedIndex;
      }

      //Increments the index.
      l1BasedIndex++;
    }

    throw ArgumentDoesNotContainElementException.forArgument(this);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int get1BasedIndexOfFirstEqualElement(final E object) {

    //Initializes index.
    var index = 1;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element equals the given object.
      if (Objects.equals(e, object)) {
        return index;
      }

      index++;
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain an element an equal element");
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int get1BasedIndexOfFirstOccurrenceOf(final E element) {

    //Iterates the current Container.
    var index = 1;
    for (final var e : this) {

      //Handles the case that the current element is the given element.
      if (e == element) {
        return index;
      }

      //Increments index.
      index++;
    }

    throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain the given element");
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMax(final Function<E, C> norm) {

    var max = norm.apply(getStoredFirst());

    for (final var e : this) {

      final var comparableValueOfElement = norm.apply(e);

      if (comparableValueOfElement.compareTo(max) > 0) {
        max = comparableValueOfElement;
      }
    }

    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMaxOrZero(Function<E, Number> norm) {

    //Handles the case that the current Container is empty.
    if (isEmpty()) {
      return 0.0;
    }

    //Handles the case that the current Container contains elements.
    return getMaxWhenContainsAny(norm);
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMedian(final Function<E, Number> norm) {

    //Asserts that the current Container is not empty.
    assertIsNotEmpty();

    //Calculates the values the given norm returns from the elements of the current
    //Container.
    final var values = to(norm::apply);

    //Orders the values by an ascending order.
    final var orderedValues = values.toOrderedList(Number::doubleValue);

    //Gets the number of values.
    final var valueCount = values.getCount();

    //Handles the case that the number of values is even.
    if (valueCount % 2 == 0) {

      final var firstIndex = valueCount / 2;
      final var preMedian = orderedValues.getStoredAt1BasedIndex(firstIndex).doubleValue();
      final var postMedian = orderedValues.getStoredAt1BasedIndex(firstIndex + 1).doubleValue();

      return 0.5 * (preMedian + postMedian);
    }

    //Handles the case that the number of values is odd.
    return orderedValues.getStoredAt1BasedIndex((valueCount / 2) + 1).doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMedianOrZero(Function<E, Number> norm) {

    //Handles the case that the current Container is empty.
    if (isEmpty()) {
      return 0.0;
    }

    //Handles the case that the current Container contains elements.
    return getMedian(norm);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMin(final Function<E, C> norm) {

    var min = norm.apply(getStoredFirst());

    for (final var e : this) {

      final var comparableValueOfElement = norm.apply(e);

      if (comparableValueOfElement.compareTo(min) < 0) {
        min = comparableValueOfElement;
      }
    }

    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double getMinOrZero(Function<E, Number> norm) {

    //Handles the case that the current Container is empty.
    if (isEmpty()) {
      return 0.0;
    }

    //Handles the case that the current Container contains elements.
    return getMinWhenContainsAny(norm);
  }

  //For a better performance, this implementation does not use all comfortable methods.
  /**
   * The time complexity of this implementation is O(1) if the current Container
   * does not contain null elements. The time complexity of this implementation is
   * O(n) if the current Container contains null elements and if the current
   * Container contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst() {

    for (final var e : this) {
      if (e != null) {
        return Optional.of(e);
      }
    }

    return Optional.empty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst(final Predicate<? super E> selector) {

    GlobalValidator.assertThat(selector).thatIsNamed("selector").isNotNull();

    for (final var e : this) {
      if (e != null && selector.test(e)) {
        return Optional.of(e);
      }
    }

    return Optional.empty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMax(final Function<E, C> norm) {

    var max = getStoredFirst();
    var comparebleOfMax = norm.apply(max);

    for (final var e : this) {
      if (e != null) {

        final var comparableValueOfElement = norm.apply(e);

        if (comparableValueOfElement.compareTo(comparebleOfMax) > 0) {
          max = e;
          comparebleOfMax = norm.apply(max);
        }
      }
    }

    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMin(final Function<E, C> norm) {

    var min = getStoredFirst();
    var comparebleOfMin = norm.apply(min);

    for (var e : this) {
      if (e != null) {

        final var comparableValueOfElement = norm.apply(e);

        if (comparableValueOfElement.compareTo(comparebleOfMin) < 0) {
          min = e;
          comparebleOfMin = norm.apply(min);
        }
      }
    }

    return min;
  }

  /**
   * The time complexity of this implementation is O(1) if the current Container
   * does not contain null elements. The time complexity of this implementation is
   * O(n) if the current Container contains null elements and if the current
   * Container contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirst() {

    for (final var e : this) {
      if (e != null) {
        return e;
      }
    }

    throw EmptyArgumentException.forArgument(this);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirst(final Predicate<? super E> selector) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {
        return e;
      }
    }

    throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "element the given selector selects");
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link Container} contains m elements.
   * 
   * -The given norm assignes the elements of the current {@link Container} in n
   * groups.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IContainer<E>> getStoredInGroups(final Function<E, ?> norm) {

    final var groups = createEmptyMutableList(new Marker<ILinkedList<E>>());

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        final var groupKey = norm.apply(e);
        final var group = groups.getOptionalStoredFirst(g -> g.containsAny(e2 -> norm.apply(e2).equals(groupKey)));

        if (group.isEmpty()) {

          final var list = createEmptyMutableList(new Marker<E>());

          list.addAtEnd(e);
          groups.addAtEnd(list);
        } else {
          group.get().addAtEnd(e);
        }
      }
    }

    return groups;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <E2 extends E> IContainer<E2> getStoredOfType(final Class<E2> type) {
    return (IContainer<E2>) getStoredSelected(e -> type.isAssignableFrom(e.getClass()));
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredOne() {

    //Enumerates the element count of the current Container.
    return switch (getCount()) {
      case 0 ->
        throw EmptyArgumentException.forArgument(this);
      case 1 ->
        iterator().next();
      default ->
        throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "contains several elements");
    };
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredOne(final Predicate<? super E> selector) {

    //Declares the selected element.
    E selectedElement = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Handles the case that the given selector selected already an element.
        if (selectedElement != null) {
          throw //
          InvalidArgumentException.forArgumentAndErrorPredicate(
            this,
            "contains several elements the given selector selects");
        }

        //Handles the case that the given selector did not select already an element.
        selectedElement = e;
      }
    }

    //Handles the case that the given selector did not select an element.
    if (selectedElement == null) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "does not contain an element the given selector selects");
    }

    //Handles the case that the given selector selected an element.
    return selectedElement;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getStoredOthers(final Predicate<E> selector) {
    return getStoredSelected(e -> !selector.test(e));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getStoredSelected(final Predicate<? super E> selector) {

    //Creates list.
    final var list = createEmptyMutableList(new Marker<E>());

    //Fills up the list with the elements the given selector selects from the
    //current IContainer.
    for (final var e : this) {

      //Handles the case that the given selector selects the current element.
      if (selector.test(e)) {
        list.addAtEnd(e);
      }
    }

    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getStandardDeviation(final Function<E, Number> norm) {
    return Math.sqrt(getVariance(norm));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigDecimal getSum(final Function<E, Number> norm) {

    var sum = BigDecimal.ZERO;

    for (final var e : this) {
      sum = sum.add(BigDecimal.valueOf(norm.apply(e).doubleValue()));
    }

    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigInteger getSumOfIntegers(final ToIntFunction<E> norm) {

    var sum = BigInteger.ZERO;

    for (final var e : this) {
      sum = sum.add(BigInteger.valueOf(norm.applyAsInt(e)));
    }

    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getVariance(final Function<E, Number> norm) {

    final var average = getAverage(norm);

    var sumOfSquaredDeviationsAsBigDecimal = BigDecimal.ZERO;
    for (final var e : this) {

      final var deviation = norm.apply(e).doubleValue() - average;
      final var squaredDevication = Math.pow(deviation, 2);

      sumOfSquaredDeviationsAsBigDecimal = sumOfSquaredDeviationsAsBigDecimal
        .add(BigDecimal.valueOf(squaredDevication));
    }

    final var elementCountAsBigDecimal = BigDecimal.valueOf(getCount());

    final var varianceAsBigDecimal = sumOfSquaredDeviationsAsBigDecimal.divide(elementCountAsBigDecimal,
      MathContext.DECIMAL32);

    return varianceAsBigDecimal.doubleValue();
  }

  /**
   * The time complexity of this implementation is O(1) if the current Container
   * does not contain null elements. The time complexity of this implementation is
   * O(n) if the current Container contains null elements and if the current
   * Container contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {

    for (final var e : this) {
      if (e != null) {
        return false;
      }
    }

    return true;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <E2> IContainer<E2> to(final Function<E, E2> extractor) {

    //Creates a list.
    final var list = createEmptyMutableList(new Marker<E2>());

    //Iterates the current Container.
    for (final var e : this) {
      list.addAtEnd(extractor.apply(e));
    }

    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final Object[] toArray() {

    //Creates array.
    final var array = new Object[getCount()];

    //Fills up the array.
    var i = 0;
    for (final var e : this) {
      array[i] = e;
      i++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final byte[] toByteArray(final Function<E, Byte> byteNorm) {

    //Creates array.
    final var array = new byte[getCount()];

    //Fills up the array.
    var i = 0;
    for (final var e : this) {
      array[i] = byteNorm.apply(e);
      i++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final char[] toCharArray(final Function<E, Character> charNorm) {

    //Creates array.
    final var array = new char[getCount()];

    //Fills up the array.
    var i = 0;
    for (final var e : this) {
      array[i] = charNorm.apply(e);
      i++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toConcatenatedString() {

    final var stringBuilder = new StringBuilder();

    for (final var e : this) {
      stringBuilder.append(e);
    }

    return stringBuilder.toString();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double[] toDoubleArray(final ToDoubleFunction<E> doubleNorm) {

    //Creates array.
    final var array = new double[getCount()];

    //Fills up the array.
    var i = 0;
    for (final var e : this) {
      array[i] = doubleNorm.applyAsDouble(e);
      i++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <E2> IContainer<E2> toFromGroups(final Function<E, IContainer<E2>> extractor) {

    final var list = createEmptyMutableList(new Marker<E2>());

    for (final var e : this) {
      list.addAtEnd(extractor.apply(e));
    }

    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int[] toIntArray(final ToIntFunction<E> norm) {

    //Creates array.
    final var array = new int[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0;
      }

      //Handles the case that the current element is not null.
      else {
        array[index] = norm.applyAsInt(e);
        index++;
      }
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final long[] toLongArray(final ToLongFunction<E> norm) {

    //Creates the array.
    final var array = new long[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0;
      }

      //Handles the case that the current element is not null.
      else {
        array[index] = norm.applyAsLong(e);
        index++;
      }
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> toReversedList() {

    //Creates a ILinkedList.
    final var reversedList = createEmptyMutableList(new Marker<E>());

    //Iterates the current Container.
    for (final var e : this) {
      reversedList.addAtBegin(e);
    }

    return reversedList;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String[] toStringArray() {

    final var stringArray = new String[getCount()];

    //Iterates the elements of the current Container.
    var i = 0;
    for (final var e : this) {
      stringArray[i] = e.toString();
      i++;
    }

    return stringArray;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> toStrings() {
    return to(E::toString);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toStringWithSeparator(final char separator) {

    //Calls other method.
    return toStringWithSeparator(String.valueOf(separator));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toStringWithSeparator(final String separator) {

    //Enumerates the element count of the current Container.
    return switch (getCount()) {
      case 0 ->
        StringCatalogue.EMPTY_STRING;
      case 1 ->
        getStoredFirst().toString();
      default ->
        toStringWhenContainsSeveralElements(separator);
    };
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewTo1BasedEndIndex(final int endIndex) {
    return getSubContainerFromStartIndexToEndIndex(1, endIndex);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutFirst() {

    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }

    return getViewWithoutFirst(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutFirst(final int n) {

    //Asserts that the given n is positive.
    GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();

    final var elementCount = getCount();

    //Handles the case that the current Container contains more than n elements.
    if (elementCount > n) {
      return getSubContainerFromStartIndexToEndIndex(n + 1, elementCount);
    }

    //Handles the case that the current Container contains n or less elements.
    return createEmptyMutableList(new Marker<E>());
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutLast() {

    //Asserts that the current Container is not empty.
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }

    return getViewWithoutLast(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutLast(final int n) {

    //Asserts that the given n is positive.
    GlobalValidator.assertThat(n).thatIsNamed("n").isPositive();

    final var elementCount = getCount();

    //Handles the case that the current Container contains more than n elements.
    if (elementCount > 0) {
      return getSubContainerFromStartIndexToEndIndex(1, elementCount - n);
    }

    //Handles the case that the current Container contains n or less elements.
    return createEmptyMutableList(new Marker<E>());
  }

  /**
   * @param marker
   * @param <E2>   is the type of the elements the created {@link ILinkedList} can
   *               contain.
   * @return a new empty {@link ILinkedList}.
   */
  protected abstract <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker);

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @throws EmptyArgumentException if the current {@link Container} is empty.
   */
  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }
  }

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order
   *         like the given iterable, false otherwise, for the case that the given
   *         iterable is not null
   */
  private boolean containsExactlyEqualingInSameOrderIfGivenIterableIsNotNull(final Iterable<?> iterable) {

    //Gets a new iterator from the given iterable.
    var iterator = iterable.iterator();

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the iterator has a next.
      if (iterator.hasNext()) {

        //Handles the case that the current element does not equal the next of the iterator.
        if (!Objects.equals(e, iterator.next())) {
          return false;
        }

        //Handles the case that the iterator has does not have a next.
      } else {
        return false;
      }
    }

    return !iterator.hasNext();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * @param norm
   * @return the biggest value the given norm returns from the elements of the
   *         current {@link IContainer} for the case that the current
   *         {@link IContainer} contains elements.
   */
  private double getMaxWhenContainsAny(final Function<E, Number> norm) {

    //Declares max.
    var max = norm.apply(getStoredFirst()).doubleValue();

    //Iterates the current Container.
    for (final var e : this) {

      //Extracts the current number.
      final var number = norm.apply(e).doubleValue();

      //Handles the case that the current number is bigger than max.
      if (number > max) {
        max = number;
      }
    }

    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * @param norm
   * @return the smallest value the given norm returns from the elements of the
   *         current {@link IContainer} for the case that the current
   *         {@link IContainer} contains elements.
   */
  private double getMinWhenContainsAny(final Function<E, Number> norm) {

    //Declares min.
    var min = norm.apply(getStoredFirst()).doubleValue();

    //Iterates the current Container.
    for (final var e : this) {

      //Extracts the current number.
      final var number = norm.apply(e).doubleValue();

      //Handles the case that the current number is smaller than min.
      if (number < min) {
        min = number;
      }
    }

    return min;
  }

  /**
   * @param param1BasedStartIndex
   * @param param1BasedEndIndex
   * @return a {@link IContainer} that views the current {@link Container} from
   *         the given param1BasedStartIndex to the given param1BasedEndIndex.
   * @throws NonPositiveArgumentException if the given param1BasedStartIndex is
   *                                      not positive.
   * @throws NonPositiveArgumentException if the given param1BasedEndIndex is not
   *                                      positive.
   * @throws SmallerArgumentException     if the given param1BasedEndIndex is
   *                                      smaller than the given
   *                                      param1BasedStartIndex.
   * @throws BiggerArgumentException      if the given endIndex is bigger than the
   *                                      number of elements of the current
   *                                      {@link Container}.
   */

  private IContainer<E> getSubContainerFromStartIndexToEndIndex(
    final int param1BasedStartIndex,
    final int param1BasedEndIndex) {
    return ContainerView.forContainerAndStartIndexAndEndIndex(this, param1BasedStartIndex, param1BasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link Container} contains n elements.
   * 
   * @param separator
   * @return a {@link String} representation of the current {@link Container}
   *         using the given separator for the case that the current
   *         {@link Container} contains several elements.
   * @throws ArgumentIsNullException if the given separator is null.
   */
  private String toStringWhenContainsSeveralElements(final String separator) {

    //Asserts that the given separator is not null.
    GlobalValidator.assertThat(separator).thatIsNamed(LowerCaseVariableCatalogue.SEPARATOR).isNotNull();

    //Creates a StringBuilder.
    final var stringBuilder = new StringBuilder();

    //Appends the String representation of the first element to the StringBuilder.
    stringBuilder.append(getStoredFirst());

    //Iterates the elements of the current Container without the first element.
    for (final var e : getViewWithoutFirst()) {

      //Appends the separator and the String representation of the current element to
      //the StringBuilder.
      stringBuilder.append(separator + e);
    }

    return stringBuilder.toString();
  }
}
