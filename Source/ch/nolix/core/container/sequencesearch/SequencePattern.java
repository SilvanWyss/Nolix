package ch.nolix.core.container.sequencesearch;

import java.util.Iterator;
import java.util.function.Predicate;

import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.list.ILinkedList;
import ch.nolix.coreapi.container.sequencesearch.ISequencePattern;

/**
 * A {@link SequencePattern} is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2016-10-01
 * @param <E> is the type of the elements of the sequences of a
 *            {@link SequencePattern}.
 */
public final class SequencePattern<E> implements ISequencePattern<E> {
  private final IContainer<Predicate<E>> elementConditions;

  private final IContainer<Predicate<IContainer<E>>> sequenceConditions;

  private final Predicate<E> blankCondition = _ -> true; //NOSONAR: This field must be a member.

  /**
   * Creates a new {@link SequencePattern}.
   */
  private SequencePattern() {
    this(ImmutableList.createEmpty(), ImmutableList.createEmpty());
  }

  /**
   * Creates a new {@link SequencePattern} with the given elementConditions and
   * sequenceConditions.
   * 
   * @param elementConditions
   * @param sequenceConditions
   * @throws ArgumentIsNullException if the given elementConditions is null.
   * @throws ArgumentIsNullException if one of the given sequenceConditions is
   *                                 null.
   * @throws ArgumentIsNullException if the given elementConditions is null.
   * @throws ArgumentIsNullException if one of the given sequenceConditions is
   *                                 null.
   */
  private SequencePattern(
    final IContainer<Predicate<E>> elementConditions,
    final IContainer<Predicate<IContainer<E>>> sequenceConditions) {
    this.elementConditions = ImmutableList.fromIterable(elementConditions);
    this.sequenceConditions = ImmutableList.fromIterable(sequenceConditions);
  }

  /**
   * @param <E2>
   * @param elementType
   * @return a new {@link SequencePattern} for the given elementType.
   */
  public static <E2> SequencePattern<E2> forElementType(
    final Class<E2> elementType //NOSONAR: The unused parameter is necessary to know the type.
  ) {
    return new SequencePattern<>();
  }

  /**
   * @param elementConditions
   * @param sequenceConditions
   * @param <T>                is the type of the elements of the new
   *                           {@link SequencePattern}.
   * @return a new {@link SequencePattern} with the given elementConditions and
   *         sequenceConditions.
   * @throws ArgumentIsNullException if the given elementConditions is null.
   * @throws ArgumentIsNullException if one of the given sequenceConditions is
   *                                 null.
   * @throws ArgumentIsNullException if the given elementConditions is null.
   * @throws ArgumentIsNullException if one of the given sequenceConditions is
   *                                 null.
   */
  private static <T> SequencePattern<T> withElementConditionsAndSequenceConditions(
    final IContainer<Predicate<T>> elementConditions,
    final IContainer<Predicate<IContainer<T>>> sequenceConditions) {
    return new SequencePattern<>(elementConditions, sequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withBlankForNext() {
    return withConditionForNext(blankCondition);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withConditionForNext(final Predicate<E> condition) {
    final var allElementConditions = ContainerView.forIterableAndElement(elementConditions, condition);

    return withElementConditionsAndSequenceConditions(allElementConditions, sequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withConditionsForNexts(IContainer<Predicate<E>> conditions) {
    Validator.assertThatTheElements(conditions).areNotNull();

    final var allElementConditions = ContainerView.forIterable(elementConditions, conditions);

    return withElementConditionsAndSequenceConditions(allElementConditions, sequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withSequenceCondition(final Predicate<IContainer<E>> sequenceCondition) {
    final var newSequenceConditions = ContainerView.forIterableAndElement(sequenceConditions, sequenceCondition);

    return withElementConditionsAndSequenceConditions(elementConditions, newSequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SequencePatternNextMediator<E> forNext(final int count) {
    return SequencePatternNextMediator.forSequencePatternAndCount(this, count);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IContainer<E>> getMatchingSequencesFrom(final IContainer<E> list) {
    final ILinkedList<ILinkedList<E>> sequences = LinkedList.createEmpty();

    final int maxSequenceCount = list.getCount() - getSize() + 1;

    //Iterates the given list.
    final var iterator = list.iterator();
    for (var i = 1; i <= maxSequenceCount; i++) {
      //Asserts that the current sequence fulfills the element conditions of the current SequencePattern.
      var sequenceFulfillsElementConditions = true;
      final var iterator2 = iterator.getCopy();
      for (final Predicate<E> c : elementConditions) {
        final var element = iterator2.next();

        if (!c.test(element)) {
          sequenceFulfillsElementConditions = false;
          break;
        }
      }

      if (sequenceFulfillsElementConditions) {
        final ILinkedList<E> sequence = LinkedList.createEmpty();
        final var iterator3 = iterator.getCopy();

        for (var j = 0; j < getSize(); j++) {
          sequence.addAtEnd(iterator3.next());
        }

        //Asserts that the current sequence fulfills the sequence conditions of the current SequencePattern.
        if (sequenceConditions.containsOnly(sc -> sc.test(sequence))) {
          sequences.addAtEnd(sequence);
        }
      }

      //Increments the iterator.
      iterator.next();
    }

    return sequences;
  }

  /**
   * @return the number of elements of the sequences of the current
   *         {@link SequencePattern}.
   */
  public int getSize() {
    return elementConditions.getCount();
  }

  /**
   * @param list
   * @return true if the current {@link SequencePattern} matches the given list,
   *         false otherwise.
   */
  public boolean matches(final LinkedList<E> list) {
    //Asserts that the given list has as many elements as the current SequencePattern requires.
    if (list.getCount() != getSize()) {
      return false;
    }

    /*
     * Asserts that the elements of the given list fulfill the according element
     * conditions the current SequencePattern requires.
     */
    final Iterator<Predicate<E>> iterator = elementConditions.iterator();
    for (final E e : list) {
      if (!iterator.next().test(e)) {
        return false;
      }
    }

    //Asserts that the given list fulfils the sequence conditions of the current SequencePattern.
    return sequenceConditions.containsOnly(sc -> sc.test(list));
  }
}
