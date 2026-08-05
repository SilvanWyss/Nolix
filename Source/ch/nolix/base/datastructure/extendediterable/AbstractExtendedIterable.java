/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.extendediterable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import ch.nolix.base.commontype.arraymapper.ArrayMapper;
import ch.nolix.base.commontype.arraytool.ArraySorter;
import ch.nolix.base.commontype.iterableexaminer.IterableExaminer;
import ch.nolix.base.commontype.iterablesearcher.IterableSearcher;
import ch.nolix.base.datastructure.extendediterablefilterview.ExtendedIterableFilterView;
import ch.nolix.base.datastructure.extendediterableintervalview.ExtendedIterableIntervalView;
import ch.nolix.base.datastructure.extendediterablemapperview.ExtendedIterableMapperView;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.set.SingleProvider;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.textcatalog.StringCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link AbstractExtendedIterable}
 */
public abstract class AbstractExtendedIterable<E> // NOSONAR: An AbstractExtendedIterable is a principal object thus it has many methods.
implements ExtendedIterable<E> {
  private static final IterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

  private static final IterableSearcher ITERABLE_SEARCHER = new IterableSearcher();

  private static final ArrayMapper ITERABLE_MAPPER = new ArrayMapper();

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(final Object object) {
    return ITERABLE_EXAMINER.contains(this, object);
  }

  /**
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Object... objects) {
    return ITERABLE_EXAMINER.containsAll(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Iterable<?> objects) {
    return ITERABLE_EXAMINER.containsAll(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsMatching(final Predicate<E> selector) {
    return ITERABLE_EXAMINER.containsMatching(this, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny() {
    return iterator().hasNext();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Iterable<?> objects) {
    return ITERABLE_EXAMINER.containsAny(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Object... objects) {
    return ITERABLE_EXAMINER.containsAny(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsEqual(final Object object) {
    return ITERABLE_EXAMINER.containsEqual(this, object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyAllEqualInSameOrder(final Iterable<?> objects) {
    return ITERABLE_EXAMINER.containsExactlyAllEqualInSameOrder(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyInSameOrder(final Iterable<?> iterable) {
    return ITERABLE_EXAMINER.containsExactlyAllInSameOrder(this, iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsMatchingOnly(final Predicate<E> selector) {
    return ITERABLE_EXAMINER.containsMatchingOnly(this, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNoEqual(final E object) {
    return ITERABLE_EXAMINER.containsNoEqual(this, object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNoMatching(final Predicate<E> selector) {
    return ITERABLE_EXAMINER.containsNoMatching(this, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Iterable<?> elements) {
    return ITERABLE_EXAMINER.containsNone(this, elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Object... objects) {
    return ITERABLE_EXAMINER.containsNone(this, objects);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNonNull() {
    return ITERABLE_EXAMINER.containsNonNull(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnce(final Object object) {
    return ITERABLE_EXAMINER.containsOnce(this, object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOne() {
    return ITERABLE_EXAMINER.containsOne(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneEqual(final E object) {
    return ITERABLE_EXAMINER.containsOneEqual(this, object);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneMatching(final Predicate<E> selector) {
    return ITERABLE_EXAMINER.containsOneMatching(this, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneNoneNull() {
    return ITERABLE_EXAMINER.containsOneNoneNull(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnly(final Object object) {
    return ITERABLE_EXAMINER.containsOnly(this, object);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverage(final Function<E, Number> valueMapper) {
    // Asserts that the current Container is not empty.
    assertIsNotEmpty();

    // Calculates the average as BigDecimal.
    final var sumAsBigDecimal = getSum(valueMapper);
    final var elementCountAsBigDecimal = BigDecimal.valueOf(getCount());
    final var averageAsBigDecimal = sumAsBigDecimal.divide(elementCountAsBigDecimal, MathContext.DECIMAL32);

    // Returns the average as double.
    return averageAsBigDecimal.doubleValue();
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverageOrZero(final Function<E, Number> mapper) {
    // Handles the case that the current Container is empty.
    if (isEmpty()) {
      // Asserts that the given mapper is not null.
      if (mapper == null) {
        // Creates and throws a new ArgumentIsNullException. 
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.MAPPER);
      }

      // Returns 0.0.
      return 0.0;
    }

    // Handles the case that the current Container is not empty.
    return getAverage(mapper);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCount(final Predicate<E> selector) {
    return ITERABLE_SEARCHER.getCount(this, selector);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCountOf(final Object element) {
    return ITERABLE_SEARCHER.getCountOf(this, element);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirst(final Predicate<E> selector) {
    // Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableNameCatalog.SELECTOR).isNotNull();

    // Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {
        // Returns the localOneBasedIndex.
        return localOneBasedIndex;
      }

      // Increments the localOneBasedIndex.
      localOneBasedIndex++;
    }

    // Creates and throws a new ArgumentDoesNotContainElementException. 
    throw ArgumentDoesNotContainElementException.forArgument(this);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirstEqualElement(final Object object) {
    // Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element equals the given object.
      if (Objects.equals(e, object)) {
        // Returns the localOneBasedIndex.
        return localOneBasedIndex;
      }

      // Handles the case that the current element does not equals the given object.
      localOneBasedIndex++;
    }

    // Creates and throws a new InvalidArgumentException. 
    throw //
    InvalidArgumentException.forArgumentAndErrorPredicate(
      this,
      "does not contain an element that equals '" + object + "'.");
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirstOccurrenceOf(final Object object) {
    // Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is the given object.
      if (e == object) {
        return localOneBasedIndex;
      }

      // Handles the case that the current element is not the given object.
      localOneBasedIndex++;
    }

    // Creates and throws a new ArgumentDoesNotContainElementException. 
    throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, object);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMax(final Function<E, C> comparableMapper) {
    // Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    // Initializes max.
    C max = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        // Handles the case that max is null or the Comparable of the current element is bigger than max.
        if (max == null || comparable.compareTo(max) > 0) {
          // Sets max as the Comparable of the current element.
          max = comparable;
        }
      }
    }

    // Handles the case that max is null.
    if (max == null) {
      // Creates and throws a new InvalidArgumentException.
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    // Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMaxOrZero(Function<E, Number> numberMapper) {
    // Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("Number mapper").isNotNull();

    // Initializes max.
    Double max = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the number of the current element.
        final var number = numberMapper.apply(e).doubleValue();

        // Handles the case that max is null or the number of the current element is bigger than max.
        if (max == null || number > max) {
          // Sets max as the number of the current element..
          max = number;
        }
      }
    }

    // Handles the case that max is null.
    if (max == null) {
      // Returns 0.0.
      return 0.0;
    }

    // Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMedian(final Function<E, Number> numberMapper) {
    // Asserts that the current Container is not empty.
    assertIsNotEmpty();

    // Gets the numbers the numberMapper maps from the elements of the current Container.
    final var numbers = toNumbers(numberMapper);

    // Orders the numbers by an ascending order.
    final var orderedValues = numbers.toOrdered(Number::doubleValue);

    // Gets the number of numbers.
    final var valueCount = numbers.getCount();

    // Handles the case that the number of values is even.
    if (valueCount % 2 == 0) {
      // Calculates the preMedianIndex.
      final var preMedianIndex = valueCount / 2;

      // Calculates the postMedianIndex.
      final var postMedianIndex = preMedianIndex + 1;

      // Calculates the preMedian.
      final var preMedian = orderedValues.getStoredAtOneBasedIndex(preMedianIndex).doubleValue();

      // Calculates the postMedian
      final var postMedian = orderedValues.getStoredAtOneBasedIndex(postMedianIndex).doubleValue();

      // Calculates and returns the median.
      return 0.5 * (preMedian + postMedian);
    }

    // Calculates the medianIndex.
    final var medianIndex = (valueCount / 2) + 1;

    // Calculates and returns the median.
    return orderedValues.getStoredAtOneBasedIndex(medianIndex).doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMedianOrZero(Function<E, Number> norm) {
    // Handles the case that the current Container is empty.
    if (isEmpty()) {
      return 0.0;
    }

    // Handles the case that the current Container contains elements.
    return getMedian(norm);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMin(final Function<E, C> comparableMapper) {
    // Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    // Initializes min.
    C min = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        // Handles the case that min is null or the Comparable of the current element is smaller than min.
        if (min == null || comparable.compareTo(min) < 0) {
          // Sets min as the Comparable of the current element.
          min = comparable;
        }
      }
    }

    // Handles the case that min is null.
    if (min == null) {
      // Creates and throws a new InvalidArgumentException.
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    // Handles the case that min is not null.
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double getMinOrZero(final Function<E, Number> numberMapper) {
    // Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("Number mapper").isNotNull();

    // Initializes min.
    Double min = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the number of the current element.
        final var number = numberMapper.apply(e).doubleValue();

        // Handles the case that min is null or the number of the current element is smaller than min.
        if (min == null || number < min) {
          // Sets min as the number of the current element..
          min = number;
        }
      }
    }

    // Handles the case that min is null.
    if (min == null) {
      // Returns 0.0.
      return 0.0;
    }

    // Handles the case that min is not null.
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst() {
    final var iterator = iterator();

    if (iterator.hasNext()) {
      return Optional.ofNullable(iterator.next());
    }

    return Optional.empty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst(final Predicate<? super E> selector) {
    if (selector != null) {
      for (final var e : this) {
        if (e != null && selector.test(e)) {
          return Optional.of(e);
        }
      }
    }

    // Creates an empty Optional.
    return Optional.empty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getStandardDeviation(final Function<E, Number> norm) {
    // Calculates the variance.
    final var variance = getVariance(norm);

    // Calculates and returns the standard deviation.
    return Math.sqrt(variance);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMax(final Function<E, C> comparableMapper) {
    // Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    // Declares max.
    E max = null;

    // Declares comparebleOfMax.
    C comparebleOfMax = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        // Handles the case that max is null or the Comparable of the current element is bigger than comparebleOfMax.
        if (max == null || comparable.compareTo(comparebleOfMax) > 0) {
          // Sets max as the the current element.
          max = e;

          // Sets comparebleOfMax as the Comparable of the current element.
          comparebleOfMax = comparable;
        }
      }
    }

    // Handles the case that max is null.
    if (max == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    // Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMin(final Function<E, C> comparableMapper) {
    // Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    // Declares min.
    E min = null;

    // Declares comparebleOfMin.
    C comparebleOfMin = null;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        // Handles the case that min is null or the Comparable of the current element is smaller than comparebleOfMin.
        if (min == null || comparable.compareTo(comparebleOfMin) < 0) {
          // Sets min as the the current element.
          min = e;

          // Sets comparebleOfMin as the Comparable of the current element.
          comparebleOfMin = comparable;
        }
      }
    }

    // Handles the case that min is null.
    if (min == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    // Handles the case that min is not null.
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirst() {
    return iterator().next();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirstNonNull() {
    return ITERABLE_SEARCHER.getStoredFirstNonNull(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirst(final Predicate<? super E> selector) {
    return ITERABLE_SEARCHER.getStoredFirst(this, selector);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <T extends E> T getStoredFirstOfType(final Class<T> type) {
    return ITERABLE_SEARCHER.getStoredFirstOfType(this, type);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractExtendedIterable} contains m elements.
   * 
   * -The given norm assignes the elements of the current
   * {@link AbstractExtendedIterable} in n groups.
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<? extends ExtendedIterable<E>> getStoredInGroups(final Function<E, ?> norm) {
    // Asserts that the given norm is not null.
    Validator.assertThat(norm).thatIsNamed("norm").isNotNull();

    // Initializes groups.
    final var groups = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<IArrayList<E>>(), 0);

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Gets the groupKey of the current element.
        final var groupKey = norm.apply(e);

        // Gets the optionalGroup of the current element.
        final var optionalGroup = //
        groups.getOptionalStoredFirst(g -> g.containsAny() && norm.apply(g.getStoredFirstNonNull()).equals(groupKey));

        // Handles the case that the optionalGroup of the current element does not exist.
        if (optionalGroup.isEmpty()) {
          // Creates group for the current element.
          final var group = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), 1);

          // Adds the current element to the group for the current element.
          group.addAtEnd(e);

          // Adds the group for the current element to the groups.
          groups.addAtEnd(group);

          // Handles the case that the optionalGroup of the current element exists. 
        } else {
          // Adds the current element to the group for the current element.
          optionalGroup.get().addAtEnd(e);
        }
      }
    }

    // Returns groups.
    return groups;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredLast() {
    return getStoredAtOneBasedIndex(getCount());
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <T extends E> ExtendedIterable<T> getStoredOfType(final Class<T> type) {
    // Asserts that the given type is not null.
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableNameCatalog.TYPE).isNotNull();

    // Calls other method.
    return (ExtendedIterable<T>) getStoredSelected(e -> type.isAssignableFrom(e.getClass()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredSingle() {
    final var iterator = iterator();

    if (!iterator.hasNext()) {
      throw EmptyArgumentException.forArgument(this);
    }

    final var element = iterator.next(); // NOSONAR: The next method has to be called before the hasNext method.

    if (iterator.hasNext()) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "contains several elements");
    }

    return element;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final E getStoredSingle(final Predicate<? super E> selector) {
    if (selector != null) {
      return getStoredSingleWhenSelectorIsNotNull(selector);
    }

    throw //
    InvalidArgumentException.forArgumentAndErrorPredicate(
      this,
      "does not contain an element the given selector selects");
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getStoredOthers(final Predicate<E> selector) {
    // Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableNameCatalog.SELECTOR).isNotNull();

    // Initializes otherElements.
    final var initialCapacity = getCount() / 2;
    final var otherElements = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), initialCapacity);

    // Iterates the current Container.
    for (final var e : this) {
      /*
       * Handles the case that the current element is not null and the given selector
       * does not select the current element.
       */
      if (e != null && !selector.test(e)) {
        // Adds the current element to the otherElements.
        otherElements.addAtEnd(e);
      }
    }

    // Returns the otherElements.
    return otherElements;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getStoredSelected(final Predicate<? super E> selector) {
    // Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableNameCatalog.SELECTOR).isNotNull();

    // Initializes selectedElements.
    final var selectedElements = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), 10);

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {
        // Adds the current element to the selectedElements.
        selectedElements.addAtEnd(e);
      }
    }

    // Returns the selectedElements.
    return selectedElements;
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigDecimal getSum(final Function<E, Number> valueMapper) {
    // Asserts that the given valueMapper is not null.
    Validator.assertThat(valueMapper).thatIsNamed("value mapper").isNotNull();

    // Initializes sum.
    var sum = BigDecimal.ZERO;

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Adds the value the given valueMapper maps from the current element to the sum.
        sum = sum.add(BigDecimal.valueOf(valueMapper.apply(e).doubleValue()));
      }
    }

    // Returns sum.
    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigInteger getSumOfInts(final ToIntFunction<E> intMapper) {
    // Asserts that the given intMapper is not null.
    Validator.assertThat(intMapper).thatIsNamed("int mapper").isNotNull();

    // Initializes sum.
    var sum = BigInteger.ZERO;

    // Iterates the current container.
    for (final var e : this) {
      // Handles the case that the current element is not null.
      if (e != null) {
        // Adds the int the given intMapper maps from the current element to the sum.
        sum = sum.add(BigInteger.valueOf(intMapper.applyAsInt(e)));
      }
    }

    // Returns the sum.
    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getVariance(final Function<E, Number> numberMapper) {
    // Calculates the average.
    final var average = getAverage(numberMapper);

    // Initializes sumOfSquaredDifferencesToAverage.
    var sumOfSquaredDifferencesToAverage = BigDecimal.ZERO;

    // Iterates the current Container.
    for (final var e : this) {
      // Initializes number.
      var number = 0.0;

      // Handles the case that the current element is not null.
      if (e != null) {
        number = numberMapper.apply(e).doubleValue();
      }

      // Calculates differenceToAverage.
      final var differenceToAverage = number - average;

      // Calculates squaredDifferenceToAverage.
      final var squaredDifferenceToAverage = Math.pow(differenceToAverage, 2);

      // Adds the squaredDifferenceToAverage to the sumOfSquaredDifferencesToAverage.
      sumOfSquaredDifferencesToAverage = //
      sumOfSquaredDifferencesToAverage.add(BigDecimal.valueOf(squaredDifferenceToAverage));
    }

    // Gets the elementCount.
    final var elementCount = BigDecimal.valueOf(getCount());

    // Calculates and returns the variance.
    return sumOfSquaredDifferencesToAverage.divide(elementCount, MathContext.DECIMAL32).doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> ExtendedIterable<T> getViewOf(final Function<E, T> mapper) {
    return //
    ExtendedIterableMapperView.forContainerAndMapperAndArrayListCreator(
      this,
      mapper,
      this::createEmptyArrayListFromMarkerWithInitialCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewFromOneBasedStartIndex(final int oneBasedStartIndex) {
    // Calls other method.
    return getViewFromOneBasedStartIndexToOneBasedEndIndex(oneBasedStartIndex, getCount());
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewFromOneBasedStartIndexToOneBasedEndIndex(
    final int oneBasedStartIndex,
    final int oneBasedEndIndex) {
    return //
    ExtendedIterableIntervalView.forExtendedIterableAndStartIndexAndEndIndexAndArrayListCreator(
      this,
      oneBasedStartIndex,
      oneBasedEndIndex,
      this::createEmptyArrayListFromMarkerWithInitialCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewOfStoredSelected(final Predicate<E> selector) {
    return //
    ExtendedIterableFilterView.forContainerAndSelectorAndArrayListCreator(
      this,
      selector,
      this::createEmptyArrayListFromMarkerWithInitialCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewToOneBasedEndIndex(final int oneBasedEndIndex) {
    // Calls other method.
    return getViewFromOneBasedStartIndexToOneBasedEndIndex(1, oneBasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewWithoutFirst() {
    // Calls other method.
    return getViewWithoutFirst(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewWithoutFirst(final int n) {
    // Asserts that the given n is not negative.
    Validator.assertThat(n).thatIsNamed("n").isNotNegative();

    // Gets the count.
    final var count = getCount();

    // Handles the case that the current Container contains more than n elements.
    if (count > n) {
      // Creates and returns a new view IContainer.
      return getViewFromOneBasedStartIndexToOneBasedEndIndex(n + 1, count);
    }

    // Handles the case that the current Container contains n or less elements.
    return createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), 0);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewWithoutLast() {
    // Calls other method.
    return getViewWithoutLast(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> getViewWithoutLast(final int n) {
    // Asserts that the given n is not negative.
    Validator.assertThat(n).thatIsNamed("n").isNotNegative();

    // Gets the count.
    final var count = getCount();

    // Handles the case that the current Container contains more than n elements.
    if (count > 0) {
      // Creates and returns a new view IContainer.
      return getViewFromOneBasedStartIndexToOneBasedEndIndex(1, count - n);
    }

    // Handles the case that the current Container contains n or less elements.
    return createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), 0);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEmpty() {
    return !iterator().hasNext();
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> ExtendedIterable<T> to(final Function<E, T> mapper) {
    // Asserts that the given mapper is not null.
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableNameCatalog.MAPPER).isNotNull();

    // Creates list.
    final var list = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<T>(), getCount());

    // Iterates the current Container.
    for (final var e : this) {
      // Asserts that the current element is not null.
      if (e == null) {
        // Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ELEMENT);
      }

      // Lets the given given mapper create mappingElement from the current element.
      final var mappingElement = mapper.apply(e);

      // Adds the mappingElement at the end of the list.
      list.addAtEnd(mappingElement);
    }

    // Returns list.
    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public Object[] toArray() {
    // Creates array.
    final var array = new Object[getCount()];

    // Initializes index.
    var index = 0;

    // Iterates the current container.
    for (final var e : this) {
      // Sets the field of the array at the current index to the current element.
      array[index] = e;

      // Increments the index.
      index++;
    }

    // Returns the array.
    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final byte[] toByteArray(final Function<E, Byte> byteMapper) {
    // Asserts that the given byteMapper is not null.
    Validator.assertThat(byteMapper).thatIsNamed("byte mapper").isNotNull();

    // Creates array.
    final var array = new byte[getCount()];

    // Fills up the array.
    var index = 0;
    for (final var e : this) {
      // Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0;

        // Handles the case that the current element is not  null.
      } else {
        array[index] = byteMapper.apply(e);
      }

      // Increments the index.
      index++;
    }

    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final char[] toCharArray(final Function<E, Character> charMapper) {
    // Asserts that the given charMapper is not null.
    Validator.assertThat(charMapper).thatIsNamed("char mapper").isNotNull();

    // Creates array.
    final var array = new char[getCount()];

    // Fills up the array.
    var index = 0;
    for (final var e : this) {
      // Handles the case that the current element is null.
      if (e == null) {
        array[index] = CharacterCatalog.SPACE;

        // Handles the case that the current element is not  null.
      } else {
        array[index] = charMapper.apply(e);
      }

      // Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
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
   * {@inheritDoc}
   */
  @Override
  public final double[] toDoubleArray(final ToDoubleFunction<E> doubleMapper) {
    return ITERABLE_MAPPER.toDoubleArray(this, getCount(), doubleMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final int[] toIntArray(final ToIntFunction<E> intMapper) {
    return ITERABLE_MAPPER.toIntArray(this, getCount(), intMapper);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final long[] toLongArray(final ToLongFunction<E> longMapper) {
    // Asserts that the given longMapper is not null.
    Validator.assertThat(longMapper).thatIsNamed("long mapper").isNotNull();

    // Creates the array.
    final var array = new long[getCount()];

    // Fills up the array.
    var index = 0;
    for (final var e : this) {
      // Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0L;

        // Handles the case that the current element is not null.
      } else {
        array[index] = longMapper.applyAsLong(e);
      }

      // Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current * {@link AbstractExtendedIterable} contains m elements.
   * 
   * -On average, the given multipleMapper maps n elements from an element of the
   * current {@link AbstractExtendedIterable}
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> ExtendedIterable<T> toMultiples(final Function<E, ExtendedIterable<T>> multipleMapper) {
    // Asserts that the given multipleMapper is not null.
    Validator.assertThat(multipleMapper).thatIsNamed("multiple mapper").isNotNull();

    // Creates list.
    final var list = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<T>(), getCount());

    // Iterates the current Container.
    for (final var e : this) {
      // Asserts that the current element is not null.
      if (e == null) {
        // Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ELEMENT);
      }

      // Adds the elements the given multipleMapper maps from the current element at the end of the list.
      list.addAtEnd(multipleMapper.apply(e));
    }

    // Returns the list.
    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <N extends Number> ExtendedIterable<N> toNumbers(final Function<E, N> numberMapper) {
    // Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("number mapper").isNotNull();

    // Initializes numbers.
    final var numbers = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<N>(), getCount());

    // Creates zero.
    @SuppressWarnings("unchecked")
    final var zero = (N) Double.valueOf(0.0);

    // Iterates the current Container.
    for (final var e : this) {
      // Handles the case that the current element is null.
      if (e == null) {
        // Adds zero to numbers.
        numbers.addAtEnd(zero);

        // Handles the case that the current element is not null.
      } else {
        // Adds the Numebr the given numberMapper maps from the current element.
        numbers.addAtEnd(numberMapper.apply(e));
      }
    }

    // Returns numbers.
    return numbers;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> ExtendedIterable<E> toOrdered(final Function<E, C> comparableMapper) {
    final var marker = new Marker<E>();
    final var ordered = createEmptyArrayListFromMarkerWithInitialCapacity(marker, getCount());

    @SuppressWarnings("unchecked")
    final var array = (E[]) toArray();

    ArraySorter.sortArray(array, comparableMapper);
    ordered.addAtEnd(array);

    return ordered;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<E> toReversed() {
    final var reversedList = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<E>(), getCount());

    @SuppressWarnings("unchecked")
    final var array = (E[]) new Object[(getCount())];

    var index = getCount() - 1;

    for (final var e : this) {
      array[index] = e;
      index--;
    }

    reversedList.addAtEnd(array);

    return reversedList;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final String[] toStringArray() {
    final var stringArray = new String[getCount()];

    // Iterates the elements of the current Container.
    var i = 0;
    for (final var e : this) {
      stringArray[i] = e.toString();
      i++;
    }

    return stringArray;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final ExtendedIterable<String> toStrings() {
    // Creates list.
    final var list = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<String>(), getCount());

    // Iterates the current Container.
    for (final var e : this) {
      list.addAtEnd(Objects.toString(e));
    }

    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toStringWithDelimiter(final char delimiter) {
    // Calls other method.
    return toStringWithDelimiter(String.valueOf(delimiter));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toStringWithDelimiter(final String delimiter) {
    // Enumerates the element count of the current Container.
    return switch (getCount()) {
      case 0 ->
        StringCatalog.EMPTY_STRING;
      case 1 ->
        getStoredFirstNonNull().toString();
      default ->
        toStringWhenContainsSeveralElements(delimiter);
    };
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <T> ExtendedIterable<T> toWithOneBasedIndex(final BiFunction<Integer, E, T> mapper) {
    // Asserts that the given mapper is not null.
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableNameCatalog.MAPPER).isNotNull();

    // Creates list.
    final var list = createEmptyArrayListFromMarkerWithInitialCapacity(new Marker<T>(), 0);

    // Declares index.
    var index = 1;

    // Iterates the current Container.
    for (final var e : this) {
      // Asserts that the current element is not null.
      if (e == null) {
        // Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableNameCatalog.ELEMENT);
      }

      // Lets the given mapper create mappingElement from the current element.
      final var mappingElement = mapper.apply(index, e);

      // Adds the mappingElement at the end of the list.
      list.addAtEnd(mappingElement);

      // Increments the index.
      index++;
    }

    // Returns list.
    return list;
  }

  /**
   * @param marker
   * @param initialCapacity
   * @param <T>             the type of the elements the created
   *                        {@link IArrayList} can contain
   * @return a new empty {@link IArrayList}
   */
  protected abstract <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    Marker<T> marker,
    int initialCapacity);

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @throws RuntimeException if the current {@link AbstractExtendedIterable} is
   *                          empty.
   */
  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }
  }

  /**
   * The time complexity of this method is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * The time complexity of this method is O(n) if the current
   * {@link SingleProvider} contains n elements.
   * 
   * @param selector can select elements, is considered not to be null
   * @return the one element the given selector selects from the current
   *         {@link SingleProvider}, ignoring null elements
   * @throws RuntimeException if the given the current {@link SingleProvider}
   *                          contains none or several elements the given selector
   *                          selects
   */
  private E getStoredSingleWhenSelectorIsNotNull(final Predicate<? super E> selector) {
    E selectedElement = null;

    for (final var e : this) {
      if (e != null && selector.test(e)) {
        if (selectedElement != null) {
          throw //
          InvalidArgumentException.forArgumentAndErrorPredicate(
            this,
            "contains several elements the given selector selects");
        }

        selectedElement = e;
      }
    }

    if (selectedElement == null) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        this,
        "does not contain an element the given selector selects");
    }

    return selectedElement;
  }

  /**
   * The time complexity of this method is O(n) if the current
   * {@link AbstractExtendedIterable} contains n elements.
   * 
   * @param separator
   * @return a {@link String} representation of the current
   *         {@link AbstractExtendedIterable} using the given separator for the
   *         case that the current {@link AbstractExtendedIterable} contains
   *         several elements
   * @throws RuntimeException if the given separator is null
   */
  private String toStringWhenContainsSeveralElements(final String separator) {
    // Asserts that the given separator is not null.
    Validator.assertThat(separator).thatIsNamed(LowerCaseVariableNameCatalog.SEPARATOR).isNotNull();

    // Creates a StringBuilder.
    final var stringBuilder = new StringBuilder();

    // Appends the String representation of the first element to the StringBuilder.
    stringBuilder.append(getStoredFirstNonNull());

    // Iterates the elements of the current Container without the first element.
    for (final var e : getViewWithoutFirst()) {
      // Appends the separator and the String representation of the current element to
      // the StringBuilder.
      stringBuilder.append(separator).append(e);
    }

    return stringBuilder.toString();
  }
}
