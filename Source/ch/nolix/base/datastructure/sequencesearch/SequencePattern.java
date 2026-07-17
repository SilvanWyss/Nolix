/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.sequencesearch;

import java.util.Iterator;
import java.util.function.Predicate;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.datastructure.sequencesearch.ISequencePattern;

/**
 * A {@link SequencePattern} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of the sequences of a
 *            {@link SequencePattern}.
 */
public final class SequencePattern<E> implements ISequencePattern<E> {
  private final ExtendedIterable<Predicate<E>> elementConditions;

  private final ExtendedIterable<Predicate<ExtendedIterable<E>>> sequenceConditions;

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
   * @throws RuntimeException if the given elementConditions is null
   * @throws RuntimeException if one of the given sequenceConditions is null
   * @throws RuntimeException if the given elementConditions is null
   * @throws RuntimeException if one of the given sequenceConditions is null.
   */
  private SequencePattern(
    final ExtendedIterable<Predicate<E>> elementConditions,
    final ExtendedIterable<Predicate<ExtendedIterable<E>>> sequenceConditions) {
    this.elementConditions = ImmutableList.fromIterable(elementConditions);
    this.sequenceConditions = ImmutableList.fromIterable(sequenceConditions);
  }

  /**
   * @param <T>
   * @param elementType
   * @return a new {@link SequencePattern} for the given elementType.
   */
  public static <T> SequencePattern<T> forElementType(
    final Class<T> elementType //NOSONAR: The unused parameter is necessary to know the type.
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
   * @throws RuntimeException if the given elementConditions is null
   * @throws RuntimeException if one of the given sequenceConditions is null
   * @throws RuntimeException if the given elementConditions is null
   * @throws RuntimeException if one of the given sequenceConditions is null.
   */
  private static <T> SequencePattern<T> withElementConditionsAndSequenceConditions(
    final ExtendedIterable<Predicate<T>> elementConditions,
    final ExtendedIterable<Predicate<ExtendedIterable<T>>> sequenceConditions) {
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
    final var allElementConditions = ExtendedIterableView.forIterableAndElement(elementConditions, condition);

    return withElementConditionsAndSequenceConditions(allElementConditions, sequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withConditionsForNexts(ExtendedIterable<Predicate<E>> conditions) {
    Validator.assertThatTheElements(conditions).areNotNull();

    final var allElementConditions = ExtendedIterableView.forIterables(elementConditions, conditions);

    return withElementConditionsAndSequenceConditions(allElementConditions, sequenceConditions);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withSequenceCondition(final Predicate<ExtendedIterable<E>> sequenceCondition) {
    final var newSequenceConditions = ExtendedIterableView.forIterableAndElement(sequenceConditions, sequenceCondition);

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
  public ExtendedIterable<? extends ExtendedIterable<E>> getMatchingSequencesFrom(final ExtendedIterable<E> list) {
    final ILinkedList<ILinkedList<E>> sequences = LinkedList.createEmpty();

    final int maxSequenceCount = list.getCount() - getSize() + 1;

    // Iterates the given list.
    final var iterator = list.iterator();
    for (var i = 1; i <= maxSequenceCount; i++) {
      // Asserts that the current sequence fulfills the element conditions of the current SequencePattern.
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

        // Asserts that the current sequence fulfills the sequence conditions of the current SequencePattern.
        if (sequenceConditions.containsMatchingOnly(sc -> sc.test(sequence))) {
          sequences.addAtEnd(sequence);
        }
      }

      // Increments the iterator.
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
   *         false otherwise
   */
  public boolean matches(final LinkedList<E> list) {
    // Asserts that the given list has as many elements as the current SequencePattern requires.
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

    // Asserts that the given list fulfils the sequence conditions of the current SequencePattern.
    return sequenceConditions.containsMatchingOnly(sc -> sc.test(list));
  }
}
