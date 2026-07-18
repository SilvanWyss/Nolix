/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.sequencesearch;

import java.util.function.Predicate;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.sequencesearch.ISequencePattern;
import ch.nolix.baseapi.datastructure.sequencesearch.ISequencePatternNextMediator;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of the sequences of the
 *            {@link SequencePattern} of a {@link SequencePatternNextMediator}.
 */
public final class SequencePatternNextMediator<E> implements ISequencePatternNextMediator<E> {
  private final ISequencePattern<E> sequencePattern;

  private final int count;

  private final Predicate<E> blankCondition = _ -> true; //NOSONAR: This field must be a member.

  /**
   * Creates a new {@link SequencePatternNextMediator} for the given
   * sequencePattern and count.
   * 
   * @param sequencePattern
   * @param count
   * @throws RuntimeException if the sequencePattern is null
   * @throws RuntimeException if the given count is negative
   */
  private SequencePatternNextMediator(final ISequencePattern<E> sequencePattern, final int count) {
    Validator.assertThat(sequencePattern).thatIsNamed(ISequencePattern.class).isNotNull();
    Validator.assertThat(count).thatIsNamed("count").isNotNegative();

    this.sequencePattern = sequencePattern;
    this.count = count;
  }

  /**
   * @return a new {@link SequencePatternNextMediator} for the given
   *         sequencePattern and count.
   * 
   * @param sequencePattern
   * @param count
   * @param <T>             the type of the elements of the sequences of the
   *                        {@link ISequencePattern} of the
   *                        {@link ISequencePatternNextMediator}
   * @throws RuntimeException if the sequencePattern is null
   * @throws RuntimeException if the given count is negative
   */
  public static <T> SequencePatternNextMediator<T> forSequencePatternAndCount(
    final ISequencePattern<T> sequencePattern,
    final int count) {
    return new SequencePatternNextMediator<>(sequencePattern, count);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withBlank() {
    final var blanks = createBlanks(count);

    return sequencePattern.withConditionsForNexts(blanks);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> withCondition(final Predicate<E> condition) {
    final IArrayList<Predicate<E>> conditions = ArrayList.withInitialCapacity(count);

    for (var i = 1; i < count; i++) {
      conditions.addAtEnd(condition);
    }

    return sequencePattern.withConditionsForNexts(conditions);
  }

  /**
   * @param paramCount
   * @return a new {@link ExtendedIterable} with as many blank conditions as the
   *         given paramCount says
   * @throws RuntimeException if the given paramCount is negative
   */
  private ExtendedIterable<Predicate<E>> createBlanks(final int paramCount) {
    final IArrayList<Predicate<E>> blanks = ArrayList.withInitialCapacity(paramCount);

    for (var i = 1; i < paramCount; i++) {
      blanks.addAtEnd(blankCondition);
    }

    return blanks;
  }
}
