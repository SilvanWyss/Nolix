package ch.nolix.core.container.base;

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

import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.linkedlist.MappingContainerView;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.StoringRequestable;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.StringCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements of a {@link AbstractContainer}.
 */
public abstract class AbstractContainer<E> //NOSONAR: An AbstractContainer is a principal object thus it has many methods.
implements IContainer<E> {

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean contains(final Object object) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given object.
      if (e == object) {

        //Returns true.
        return true;
      }
    }

    //Returns false.
    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Iterable<?> objects) {

    //Asserts that the given objects is not null.
    Validator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalog.OBJECTS).isNotNull();

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container does not contain the current object.
      if (!contains(o)) {

        //Returns false.
        return false;
      }
    }

    //Returns true.
    return true;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Object object, final Object... objects) {

    //Calls other methods.
    return //
    contains(object)
    && containsAll(objects);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAll(final Object[] objects) {

    //Asserts that the given objects is not null.
    Validator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalog.OBJECTS).isNotNull();

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container does not contain the given object.
      if (!contains(o)) {

        //Returns false.
        return false;
      }
    }

    //Returns true.
    return true;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Returns true.
        return true;
      }
    }

    //Returns false.
    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAny(final Object object, final Object... objects) {

    //Calls other methods.
    return //
    contains(object)
    || containsAnyOf(objects);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAnyOf(final Iterable<?> objects) {

    //Asserts that the given objects is not null.
    Validator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalog.OBJECTS).isNotNull();

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container contains the current object.
      if (contains(o)) {

        //Returns true.
        return true;
      }
    }

    //Returns false.
    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAnyOf(final Object[] objects) {

    //Asserts that the given objects is not null.
    Validator.assertThat(objects).thatIsNamed(PluralLowerCaseVariableCatalog.OBJECTS).isNotNull();

    //Iterates the given objects.
    for (final var o : objects) {

      //Handles the case that the current Container contains the current object.
      if (contains(o)) {

        //Returns true.
        return true;
      }
    }

    //Returns false.
    return false;
  }

  /**
   * The time complexity of this implementation is O(n) if the current container
   * contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsAsManyAs(Iterable<?> iterable) {

    //Handles the case that the given iterable is a IContainer.
    if (iterable instanceof final IContainer<?> container) {
      return (getCount() == container.getCount());
    }

    //Handles the case that the given iterable is not a IContainer.
    return (getCount() == IterableTool.getCount(iterable));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsEqualing(final Object element) {

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element equals the given element.
      if (e.equals(element)) {

        //Returns true.
        return true;
      }
    }

    //Returns false.
    return false;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * The current {@link AbstractContainer} contains m elements.
   * 
   * The given iterable contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyEqualingInSameOrder(final Iterable<?> iterable) {

    //Handles the case that the given iterable is null.
    if (iterable == null) {
      return isEmpty();
    }

    //Handles the case that the given iterable is not null.
    return containsExactlyEqualingInSameOrderWhenGivenIterableIsNotNull(iterable);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsExactlyInSameOrder(final Iterable<?> iterable) {

    //Handles the case that the given iterable is null.
    if (iterable == null) {
      return isEmpty();
    }

    //Handles the case that the given iterable is not null.
    return containsExactlyInSameOrderWhenGivenIterableIsNotNull(iterable);
  }

  /**
   * The time complexity of this implementation is O(n) if the given iterable
   * contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsLessThan(final Iterable<?> iterable) {

    //Handles the case that the given iterable is a IContainer.
    if (iterable instanceof final IContainer<?> container) {
      return (getCount() < container.getCount());
    }

    //Handles the case that the given iterable is not a IContainer.
    return (getCount() < IterableTool.getCount(iterable));
  }

  /**
   * The time complexity of this implementation is O(n) if the given iterable
   * contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsMoreThan(final Iterable<?> iterable) {

    //Handles the case that the given container is a IContainer.
    if (iterable instanceof final IContainer<?> container) {
      return (getCount() > container.getCount());
    }

    //Handles the case that the given container is not a IContainer.
    return (getCount() > IterableTool.getCount(iterable));
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Predicate<E> selector) {

    //Calls other method.
    return !containsAny(selector);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNone(final Object object, final Object... objects) {

    //Calls other method.
    return !containsAny(object, objects);
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -n objects are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsNoneOf(final Iterable<?> elements) {

    //Calls other method.
    return !containsAnyOf(elements);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnce(final Object object) {

    //Initializes found.
    var found = false;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given object.
      if (e == object) {

        //Handles the case that the given object is already found.
        if (found) {
          return false;
        }

        //Handles the case that the given object is not already found.
        found = true;
      }
    }

    //Returns found.
    return found;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOne() {

    //Creates iterator.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOne(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes found.
    var found = false;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Handles the case the given selector selected already another element.
        if (found) {

          //Returns false.
          return false;
        }

        //Handles the case that the given selector did not select already another element.
        found = true;
      }
    }

    //Returns found.
    return found;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOneEqualing(final E object) {

    //Initializes found.
    var found = false;

    //Iterates the current container.
    for (final var e : this) {

      //Handles the case that the current element equals he given object..
      if (Objects.equals(e, object)) {

        //Handles the case that an element that equals the given object is already found.
        if (found) {

          //Returns false.
          return false;
        }

        //Handles the case that an element that equals the given object is not already found.
        found = true;
      }
    }

    //Returns found.
    return found;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final boolean containsOnly(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is null or the given selector does not select the current element.
      if (e == null || !selector.test(e)) {

        //Returns false.
        return false;
      }
    }

    //Returns true.
    return true;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverage(final Function<E, Number> valueMapper) {

    //Asserts that the current Container is not empty.
    assertIsNotEmpty();

    //Calculates the average as BigDecimal.
    final var sumAsBigDecimal = getSum(valueMapper);
    final var elementCountAsBigDecimal = BigDecimal.valueOf(getCount());
    final var averageAsBigDecimal = sumAsBigDecimal.divide(elementCountAsBigDecimal, MathContext.DECIMAL32);

    //Returns the average as double.
    return averageAsBigDecimal.doubleValue();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getAverageOrZero(final Function<E, Number> mapper) {

    //Handles the case that the current Container is empty.
    if (isEmpty()) {

      //Asserts that the given mapper is not null.
      if (mapper == null) {

        //Creates and throws a new ArgumentIsNullException. 
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.MAPPER);
      }

      //Returns 0.0.
      return 0.0;
    }

    //Handles the case that the current Container is not empty.
    return getAverage(mapper);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCount(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes count.
    var count = 0;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Increments the count.
        count++;
      }
    }

    //Returns the count.
    return count;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getCountOf(final Object element) {

    //Initializes count.
    var count = 0;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given element.
      if (e == element) {

        //Increments the count.
        count++;
      }
    }

    //Returns the count.
    return count;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirst(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Returns the localOneBasedIndex.
        return localOneBasedIndex;
      }

      //Increments the localOneBasedIndex.
      localOneBasedIndex++;
    }

    //Creates and throws a new ArgumentDoesNotContainElementException. 
    throw ArgumentDoesNotContainElementException.forArgument(this);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirstEqualElement(final Object object) {

    //Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element equals the given object.
      if (Objects.equals(e, object)) {

        //Returns the localOneBasedIndex.
        return localOneBasedIndex;
      }

      //Handles the case that the current element does not equals the given object.
      localOneBasedIndex++;
    }

    //Creates and throws a new InvalidArgumentException. 
    throw //
    InvalidArgumentException.forArgumentAndErrorPredicate(
      this,
      "does not contain an element that equals '" + object + "'.");
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int getOneBasedIndexOfFirstOccurrenceOf(final Object object) {

    //Initializes localOneBasedIndex.
    var localOneBasedIndex = 1;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is the given object.
      if (e == object) {
        return localOneBasedIndex;
      }

      //Handles the case that the current element is not the given object.
      localOneBasedIndex++;
    }

    //Creates and throws a new ArgumentDoesNotContainElementException. 
    throw ArgumentDoesNotContainElementException.forArgumentAndElement(this, object);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMax(final Function<E, C> comparableMapper) {

    //Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    //Initializes max.
    C max = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        //Handles the case that max is null or the Comparable of the current element is bigger than max.
        if (max == null || comparable.compareTo(max) > 0) {

          //Sets max as the Comparable of the current element.
          max = comparable;
        }
      }
    }

    //Handles the case that max is null.
    if (max == null) {

      //Creates and throws a new InvalidArgumentException.
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    //Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMaxOrZero(Function<E, Number> numberMapper) {

    //Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("Number mapper").isNotNull();

    //Initializes max.
    Double max = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the number of the current element.
        final var number = numberMapper.apply(e).doubleValue();

        //Handles the case that max is null or the number of the current element is bigger than max.
        if (max == null || number > max) {

          //Sets max as the number of the current element..
          max = number;
        }
      }
    }

    //Handles the case that max is null.
    if (max == null) {

      //Returns 0.0.
      return 0.0;
    }

    //Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getMedian(final Function<E, Number> numberMapper) {

    //Asserts that the current Container is not empty.
    assertIsNotEmpty();

    //Gets the numbers the numberMapper maps from the elements of the current Container.
    final var numbers = toNumbers(numberMapper);

    //Orders the numbers by an ascending order.
    final var orderedValues = numbers.toOrderedList(Number::doubleValue);

    //Gets the number of numbers.
    final var valueCount = numbers.getCount();

    //Handles the case that the number of values is even.
    if (valueCount % 2 == 0) {

      //Calculates the preMedianIndex.
      final var preMedianIndex = valueCount / 2;

      //Calculates the postMedianIndex.
      final var postMedianIndex = preMedianIndex + 1;

      //Calculates the preMedian.
      final var preMedian = orderedValues.getStoredAtOneBasedIndex(preMedianIndex).doubleValue();

      //Calculates the postMedian
      final var postMedian = orderedValues.getStoredAtOneBasedIndex(postMedianIndex).doubleValue();

      //Calculates and returns the median.
      return 0.5 * (preMedian + postMedian);
    }

    //Calculates the medianIndex.
    final var medianIndex = (valueCount / 2) + 1;

    //Calculates and returns the median.
    return orderedValues.getStoredAtOneBasedIndex(medianIndex).doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n*log(n)) if the current
   * {@link AbstractContainer} contains n elements.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> C getMin(final Function<E, C> comparableMapper) {

    //Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    //Initializes min.
    C min = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        //Handles the case that min is null or the Comparable of the current element is smaller than min.
        if (min == null || comparable.compareTo(min) < 0) {

          //Sets min as the Comparable of the current element.
          min = comparable;
        }
      }
    }

    //Handles the case that min is null.
    if (min == null) {

      //Creates and throws a new InvalidArgumentException.
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    //Handles the case that min is not null.
    return min;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final double getMinOrZero(final Function<E, Number> numberMapper) {

    //Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("Number mapper").isNotNull();

    //Initializes min.
    Double min = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the number of the current element.
        final var number = numberMapper.apply(e).doubleValue();

        //Handles the case that min is null or the number of the current element is smaller than min.
        if (min == null || number < min) {

          //Sets min as the number of the current element..
          min = number;
        }
      }
    }

    //Handles the case that min is null.
    if (min == null) {

      //Returns 0.0.
      return 0.0;
    }

    //Handles the case that min is not null.
    return min;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst() {

    //Creates iterator.
    final var iterator = iterator();

    //Handles the case that the iterator has a next element.
    if (iterator.hasNext()) {

      //Creates and returns a new Optional with the next element of the iterator.
      return Optional.ofNullable(iterator.next());
    }

    //Handles the case that the iterator does not have a next element.
    return Optional.empty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final Optional<E> getOptionalStoredFirst(final Predicate<? super E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Creates an returns a new Optional with the current element.
        return Optional.of(e);
      }
    }

    //Creates an empty Optional.
    return Optional.empty();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getStandardDeviation(final Function<E, Number> norm) {

    //Calculates the variance.
    final var variance = getVariance(norm);

    //Calculates and returns the standard deviation.
    return Math.sqrt(variance);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMax(final Function<E, C> comparableMapper) {

    //Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    //Declares max.
    E max = null;

    //Declares comparebleOfMax.
    C comparebleOfMax = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        //Handles the case that max is null or the Comparable of the current element is bigger than comparebleOfMax.
        if (max == null || comparable.compareTo(comparebleOfMax) > 0) {

          //Sets max as the the current element.
          max = e;

          //Sets comparebleOfMax as the Comparable of the current element.
          comparebleOfMax = comparable;
        }
      }
    }

    //Handles the case that max is null.
    if (max == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    //Handles the case that max is not null.
    return max;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <C extends Comparable<C>> E getStoredByMin(final Function<E, C> comparableMapper) {

    //Asserts that the given comparableMapper is not null.
    Validator.assertThat(comparableMapper).thatIsNamed("Comparable mapper").isNotNull();

    //Declares min.
    E min = null;

    //Declares comparebleOfMin.
    C comparebleOfMin = null;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the Comparable of the current element.
        final var comparable = comparableMapper.apply(e);

        //Handles the case that min is null or the Comparable of the current element is smaller than comparebleOfMin.
        if (min == null || comparable.compareTo(comparebleOfMin) < 0) {

          //Sets min as the the current element.
          min = e;

          //Sets comparebleOfMin as the Comparable of the current element.
          comparebleOfMin = comparable;
        }
      }
    }

    //Handles the case that min is null.
    if (min == null) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(this, "does not contain a non-null element");
    }

    //Handles the case that min is not null.
    return min;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredFirst() {

    //Creates iterator.
    final var iterator = iterator();

    //Handles the case that the iterator has a next element.
    if (iterator.hasNext()) {

      //Returns the next element of the iterator.
      return iterator.next();
    }

    //Handles the case that the iterator does not have a next element.
    throw EmptyArgumentException.forArgument(this);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
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
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <E2 extends E> E2 getStoredFirstOfType(final Class<E2> type) {

    //Asserts that the given type is not null.
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotNull();

    //Calls other method.
    return (E2) getStoredFirst(e -> type.isAssignableFrom(e.getClass()));
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current {@link AbstractContainer} contains m elements.
   * 
   * -The given norm assignes the elements of the current
   * {@link AbstractContainer} in n groups.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<? extends IContainer<E>> getStoredInGroups(final Function<E, ?> norm) {

    //Asserts that the given norm is not null.
    Validator.assertThat(norm).thatIsNamed("norm").isNotNull();

    //Initializes groups.
    final var groups = createEmptyMutableList(new Marker<ILinkedList<E>>());

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Gets the groupKey of the current element.
        final var groupKey = norm.apply(e);

        //Gets the optionalGroup of the current element.
        final var optionalGroup = //
        groups.getOptionalStoredFirst(g -> g.containsAny() && norm.apply(g.getStoredFirst()).equals(groupKey));

        //Handles the case that the optionalGroup of the current element does not exist.
        if (optionalGroup.isEmpty()) {

          //Creates group for the current element.
          final var group = createEmptyMutableList(new Marker<E>());

          //Adds the current element to the group for the current element.
          group.addAtEnd(e);

          //Adds the group for the current element to the groups.
          groups.addAtEnd(group);

          //Handles the case that the optionalGroup of the current element exists. 
        } else {

          //Adds the current element to the group for the current element.
          optionalGroup.get().addAtEnd(e);
        }
      }
    }

    //Returns groups.
    return groups;
  }

  /**
   * The time complexity of this implementation is O(1) or O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredLast() {

    //Calls other method.
    return getStoredAtOneBasedIndex(getCount());
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public final <E2 extends E> IContainer<E2> getStoredOfType(final Class<E2> type) {

    //Asserts that the given type is not null.
    Validator.assertThat(type).thatIsNamed(LowerCaseVariableCatalog.TYPE).isNotNull();

    //Calls other method.
    return (IContainer<E2>) getStoredSelected(e -> type.isAssignableFrom(e.getClass()));
  }

  //For a better performance, this implementation does not use all available comfort methods.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final E getStoredOne(final Predicate<? super E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes selectedElement.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getStoredOthers(final Predicate<E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes otherElements.
    final var otherElements = createEmptyMutableList(new Marker<E>());

    //Iterates the current Container.
    for (final var e : this) {

      /*
       * Handles the case that the current element is not null and the given selector
       * does not select the current element.
       */
      if (e != null && !selector.test(e)) {

        //Adds the current element to the otherElements.
        otherElements.addAtEnd(e);
      }
    }

    //Returns the otherElements.
    return otherElements;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getStoredSelected(final Predicate<? super E> selector) {

    //Asserts that the given selector is not null.
    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    //Initializes selectedElements.
    final var selectedElements = createEmptyMutableList(new Marker<E>());

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null and the given selector selects the current element.
      if (e != null && selector.test(e)) {

        //Adds the current element to the selectedElements.
        selectedElements.addAtEnd(e);
      }
    }

    //Returns the selectedElements.
    return selectedElements;
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigDecimal getSum(final Function<E, Number> valueMapper) {

    //Asserts that the given valueMapper is not null.
    Validator.assertThat(valueMapper).thatIsNamed("value mapper").isNotNull();

    //Initializes sum.
    var sum = BigDecimal.ZERO;

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Adds the value the given valueMapper maps from the current element to the sum.
        sum = sum.add(BigDecimal.valueOf(valueMapper.apply(e).doubleValue()));
      }
    }

    //Returns sum.
    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final BigInteger getSumOfInts(final ToIntFunction<E> intMapper) {

    //Asserts that the given intMapper is not null.
    Validator.assertThat(intMapper).thatIsNamed("int mapper").isNotNull();

    //Initializes sum.
    var sum = BigInteger.ZERO;

    //Iterates the current container.
    for (final var e : this) {

      //Handles the case that the current element is not null.
      if (e != null) {

        //Adds the int the given intMapper maps from the current element to the sum.
        sum = sum.add(BigInteger.valueOf(intMapper.applyAsInt(e)));
      }
    }

    //Returns the sum.
    return sum;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double getVariance(final Function<E, Number> numberMapper) {

    //Calculates the average.
    final var average = getAverage(numberMapper);

    //Initializes sumOfSquaredDifferencesToAverage.
    var sumOfSquaredDifferencesToAverage = BigDecimal.ZERO;

    //Iterates the current Container.
    for (final var e : this) {

      //Initializes number.
      var number = 0.0;

      //Handles the case that the current element is not null.
      if (e != null) {
        number = numberMapper.apply(e).doubleValue();
      }

      //Calculates differenceToAverage.
      final var differenceToAverage = number - average;

      //Calculates squaredDifferenceToAverage.
      final var squaredDifferenceToAverage = Math.pow(differenceToAverage, 2);

      //Adds the squaredDifferenceToAverage to the sumOfSquaredDifferencesToAverage.
      sumOfSquaredDifferencesToAverage = //
      sumOfSquaredDifferencesToAverage.add(BigDecimal.valueOf(squaredDifferenceToAverage));
    }

    //Gets the elementCount.
    final var elementCount = BigDecimal.valueOf(getCount());

    //Calculates and returns the variance.
    return sumOfSquaredDifferencesToAverage.divide(elementCount, MathContext.DECIMAL32).doubleValue();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public <T> IContainer<T> getViewOf(final Function<E, T> mapper) {
    return MappingContainerView.forContainerAndMapper(this, mapper);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewFromOneBasedStartIndex(final int oneBasedStartIndex) {

    //Calls other method.
    return getViewFromOneBasedStartIndexToOneBasedEndIndex(oneBasedStartIndex, getCount());
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewToOneBasedEndIndex(final int oneBasedEndIndex) {

    //Calls other method.
    return getViewFromOneBasedStartIndexToOneBasedEndIndex(1, oneBasedEndIndex);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutFirst() {

    //Calls other method.
    return getViewWithoutFirst(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutFirst(final int n) {

    //Asserts that the given n is not negative.
    Validator.assertThat(n).thatIsNamed("n").isNotNegative();

    //Gets the count.
    final var count = getCount();

    //Handles the case that the current Container contains more than n elements.
    if (count > n) {

      //Creates and returns a new view IContainer.
      return getViewFromOneBasedStartIndexToOneBasedEndIndex(n + 1, count);
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

    //Calls other method.
    return getViewWithoutLast(1);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<E> getViewWithoutLast(final int n) {

    //Asserts that the given n is not negative.
    Validator.assertThat(n).thatIsNamed("n").isNotNegative();

    //Gets the count.
    final var count = getCount();

    //Handles the case that the current Container contains more than n elements.
    if (count > 0) {

      //Creates and returns a new view IContainer.
      return getViewFromOneBasedStartIndexToOneBasedEndIndex(1, count - n);
    }

    //Handles the case that the current Container contains n or less elements.
    return createEmptyMutableList(new Marker<E>());
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

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <E2> IContainer<E2> to(final Function<E, E2> mapper) {

    //Asserts that the given mapper is not null.
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableCatalog.MAPPER).isNotNull();

    //Creates list.
    final var list = createEmptyMutableList(new Marker<E2>());

    //Iterates the current Container.
    for (final var e : this) {

      //Asserts that the current element is not null.
      if (e == null) {

        //Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ELEMENT);
      }

      //Lets the given given mapper create mappingElement from the current element.
      final var mappingElement = mapper.apply(e);

      //Adds the mappingElement at the end of the list.
      list.addAtEnd(mappingElement);
    }

    //Returns list.
    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final Object[] toArray() {

    //Creates array.
    final var array = new Object[getCount()];

    //Initializes index.
    var index = 0;

    //Iterates the current container.
    for (final var e : this) {

      //Sets the field of the array at the current index to the current element.
      array[index] = e;

      //Increments the index.
      index++;
    }

    //Returns the array.
    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final byte[] toByteArray(final Function<E, Byte> byteMapper) {

    //Asserts that the given byteMapper is not null.
    Validator.assertThat(byteMapper).thatIsNamed("byte mapper").isNotNull();

    //Creates array.
    final var array = new byte[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0;

        //Handles the case that the current element is not  null.
      } else {
        array[index] = byteMapper.apply(e);
      }

      //Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final char[] toCharArray(final Function<E, Character> charMapper) {

    //Asserts that the given charMapper is not null.
    Validator.assertThat(charMapper).thatIsNamed("char mapper").isNotNull();

    //Creates array.
    final var array = new char[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = CharacterCatalog.SPACE;

        //Handles the case that the current element is not  null.
      } else {
        array[index] = charMapper.apply(e);
      }

      //Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final double[] toDoubleArray(final ToDoubleFunction<E> doubleMapper) {

    //Asserts that the given doubleMapper is not null.
    Validator.assertThat(doubleMapper).thatIsNamed("double mapper").isNotNull();

    //Creates array.
    final var array = new double[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0.0;

        //Handles the case that the current element is not null.
      } else {
        array[index] = doubleMapper.applyAsDouble(e);
      }

      //Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final int[] toIntArray(final ToIntFunction<E> intMapper) {

    //Asserts that the given intMapper is not null.
    Validator.assertThat(intMapper).thatIsNamed("int mapper").isNotNull();

    //Creates array.
    final var array = new int[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0;

        //Handles the case that the current element is not null.
      } else {
        array[index] = intMapper.applyAsInt(e);

      }

      //Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final long[] toLongArray(final ToLongFunction<E> longMapper) {

    //Asserts that the given longMapper is not null.
    Validator.assertThat(longMapper).thatIsNamed("long mapper").isNotNull();

    //Creates the array.
    final var array = new long[getCount()];

    //Fills up the array.
    var index = 0;
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {
        array[index] = 0L;

        //Handles the case that the current element is not null.
      } else {
        array[index] = longMapper.applyAsLong(e);
      }

      //Increments the index.
      index++;
    }

    return array;
  }

  /**
   * The time complexity of this implementation is O(m*n) if:
   * 
   * -The current * {@link AbstractContainer} contains m elements.
   * 
   * -On average, the given multipleMapper maps n elements from an element of the
   * current {@link AbstractContainer}.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <E2> IContainer<E2> toMultiples(final Function<E, IContainer<E2>> multipleMapper) {

    //Asserts that the given multipleMapper is not null.
    Validator.assertThat(multipleMapper).thatIsNamed("multiple mapper").isNotNull();

    //Creates list.
    final var list = createEmptyMutableList(new Marker<E2>());

    //Iterates the current Container.
    for (final var e : this) {

      //Asserts that the current element is not null.
      if (e == null) {

        //Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ELEMENT);
      }

      //Adds the elements the given multipleMapper maps from the current element at the end of the list.
      list.addAtEnd(multipleMapper.apply(e));
    }

    //Returns the list.
    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public <N extends Number> IContainer<N> toNumbers(final Function<E, N> numberMapper) {

    //Asserts that the given numberMapper is not null.
    Validator.assertThat(numberMapper).thatIsNamed("number mapper").isNotNull();

    //Initializes numbers.
    final var numbers = createEmptyMutableList(new Marker<N>());

    //Creates zero.
    @SuppressWarnings("unchecked")
    final var zero = (N) Double.valueOf(0.0);

    //Iterates the current Container.
    for (final var e : this) {

      //Handles the case that the current element is null.
      if (e == null) {

        //Adds zero to numbers.
        numbers.addAtEnd(zero);

        //Handles the case that the current element is not null.
      } else {

        //Adds the Numebr the given numberMapper maps from the current element.
        numbers.addAtBegin(numberMapper.apply(e));
      }
    }

    //Returns numbers.
    return numbers;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
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
   * {@link AbstractContainer} contains n elements.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final IContainer<String> toStrings() {

    //Creates list.
    final var list = createEmptyMutableList(new Marker<String>());

    //Iterates the current Container.
    for (final var e : this) {
      list.addAtEnd(Objects.toString(e));
    }

    return list;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
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
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final String toStringWithSeparator(final String separator) {

    //Enumerates the element count of the current Container.
    return switch (getCount()) {
      case 0 ->
        StringCatalog.EMPTY_STRING;
      case 1 ->
        getStoredFirst().toString();
      default ->
        toStringWhenContainsSeveralElements(separator);
    };
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public final <E2> IContainer<E2> toWithOneBasedIndex(final BiFunction<Integer, E, E2> mapper) {

    //Asserts that the given mapper is not null.
    Validator.assertThat(mapper).thatIsNamed(LowerCaseVariableCatalog.MAPPER).isNotNull();

    //Creates list.
    final var list = createEmptyMutableList(new Marker<E2>());

    //Declares index.
    var index = 1;

    //Iterates the current Container.
    for (final var e : this) {

      //Asserts that the current element is not null.
      if (e == null) {

        //Creates and throws a ArgumentIsNullException.
        throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ELEMENT);
      }

      //Lets the given mapper create mappingElement from the current element.
      final var mappingElement = mapper.apply(index, e);

      //Adds the mappingElement at the end of the list.
      list.addAtEnd(mappingElement);

      //Increments the index.
      index++;
    }

    //Returns list.
    return list;
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
   * @throws EmptyArgumentException if the current {@link AbstractContainer} is
   *                                empty.
   */
  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw EmptyArgumentException.forArgument(this);
    }
  }

  /**
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly
   *         elements that equal the elements of given iterable in the same order,
   *         false otherwise, for the case that the given iterable is not null.
   */
  private boolean containsExactlyEqualingInSameOrderWhenGivenIterableIsNotNull(final Iterable<?> iterable) {

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
   * @param iterable
   * @return true if the current {@link StoringRequestable} contains exactly the
   *         elements of the given iterable in the same order, false otherwise,
   *         for the case that the given iterable is not null.
   */
  private boolean containsExactlyInSameOrderWhenGivenIterableIsNotNull(final Iterable<?> iterable) {

    //Creates iterator.
    final var iterator = iterable.iterator();

    //Iterates the current Container.
    for (final var e : this) {

      /*
       * Handles the case that the iterator does not have a next element of the
       * current element is not the next element in the given iterable.
       */
      if (!iterator.hasNext() || e != iterator.next()) {

        //Returns false.
        return false;
      }
    }

    //Returns if the given iterable has more elements than the current Container.
    return !iterator.hasNext();
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link AbstractContainer} contains n elements.
   * 
   * @param separator
   * @return a {@link String} representation of the current
   *         {@link AbstractContainer} using the given separator for the case that
   *         the current {@link AbstractContainer} contains several elements.
   * @throws ArgumentIsNullException if the given separator is null.
   */
  private String toStringWhenContainsSeveralElements(final String separator) {

    //Asserts that the given separator is not null.
    Validator.assertThat(separator).thatIsNamed(LowerCaseVariableCatalog.SEPARATOR).isNotNull();

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
