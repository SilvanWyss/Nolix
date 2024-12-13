package ch.nolix.core.container.sequencesearch;

import java.util.function.Predicate;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.sequencesearchapi.ISequencePattern;
import ch.nolix.coreapi.containerapi.sequencesearchapi.ISequencePatternNextMediator;

/**
 * @author Silvan Wyss
 * @version 2016-10-01
 * @param <E> is the type of the elements of the sequences of the
 *            {@link SequencePattern} of a {@link SequencePatternNextMediator}.
 */
public final class SequencePatternNextMediator<E> implements ISequencePatternNextMediator<E> {

  private final ISequencePattern<E> sequencePattern;

  private final int count;

  private final Predicate<E> blankCondition = e -> true;

  /**
   * Creates a new {@link SequencePatternNextMediator} for the given
   * sequencePattern and count.
   * 
   * @param sequencePattern
   * @param count
   * @throws ArgumentIsNullException   if the sequencePattern is null.
   * @throws NegativeArgumentException if the given count is negative.
   */
  private SequencePatternNextMediator(final ISequencePattern<E> sequencePattern, final int count) {

    GlobalValidator.assertThat(sequencePattern).thatIsNamed(ISequencePattern.class).isNotNull();
    GlobalValidator.assertThat(count).thatIsNamed("count").isNotNegative();

    this.sequencePattern = sequencePattern;
    this.count = count;
  }

  /**
   * @return a new {@link SequencePatternNextMediator} for the given
   *         sequencePattern and count.
   * 
   * @param sequencePattern
   * @param count
   * @param <E2>            is the type of the elements of the sequences of the
   *                        {@link ISequencePattern} of the
   *                        {@link ISequencePatternNextMediator}.
   * @throws ArgumentIsNullException   if the sequencePattern is null.
   * @throws NegativeArgumentException if the given count is negative.
   */
  public static <E2> SequencePatternNextMediator<E2> forSequencePatternAndCount(
    final ISequencePattern<E2> sequencePattern,
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
  @SuppressWarnings("unchecked")
  public ISequencePattern<E> withCondition(final Predicate<E> condition) {

    final IArrayList<Predicate<E>> conditions = ArrayList.withInitialCapacity(count);

    for (var i = 1; i < count; i++) {
      conditions.addAtEnd(condition);
    }

    return sequencePattern.withConditionsForNexts(conditions);
  }

  /**
   * @param paramCount
   * @return a new {@link IContainer} with as many blank conditions as the given
   *         paramCount says.
   * @throws NegativeArgumentException if the given paramCount is negative.
   */
  @SuppressWarnings("unchecked")
  private IContainer<Predicate<E>> createBlanks(final int paramCount) {

    final IArrayList<Predicate<E>> blanks = ArrayList.withInitialCapacity(paramCount);

    for (var i = 1; i < paramCount; i++) {
      blanks.addAtEnd(blankCondition);
    }

    return blanks;
  }
}
