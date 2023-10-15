//package declaration
package ch.nolix.core.container.sequencesearch;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

//class
/**
 * @author Silvan Wyss
 * @date 2016-10-01
 * @param <E> is the type of the elements of the sequences of the
 *            {@link SequencePattern} of a {@link SequencePatternNextMediator}.
 */
public final class SequencePatternNextMediator<E> {

  // attribute
  private final SequencePattern<E> sequencePattern;

  // attribute
  private final int count;

  // constructor
  /**
   * Creates a new {@link SequencePatternNextMediator} for the given
   * sequencePattern and with the given count.
   * 
   * @param sequencePattern
   * @param count
   * @throws ArgumentIsNullException   if the givenSequence pattern is null.
   * @throws NegativeArgumentException if the given count is negative.
   */
  SequencePatternNextMediator(final SequencePattern<E> sequencePattern, final int count) {

    // Asserts that the given sequencePattern is not null.
    GlobalValidator.assertThat(sequencePattern).thatIsNamed(SequencePattern.class).isNotNull();

    // Asserts that the given count is not negative.
    GlobalValidator.assertThat(count).thatIsNamed("count").isNotNegative();

    // Sets the sequencePattern and the count of the current
    // SequencePatternNextMediator.
    this.sequencePattern = sequencePattern;
    this.count = count;
  }

  // method
  /**
   * Adds a blank condition for the next elements of the sequences of the
   * {@link SequencePattern} of the current {@link SequencePatternNextMediator}.
   * 
   * @return the sequence pattern of the current
   *         {@link SequencePatternNextMediator}.
   */
  public SequencePattern<E> addBlank() {

    for (var i = 1; i < count; i++) {
      sequencePattern.addBlankForNext();
    }

    return sequencePattern;
  }

  // method
  /**
   * Adds the given condition for the next elements of the sequences of the
   * {@link SequencePattern} of the current {@link SequencePatternNextMediator}.
   * 
   * @param condition
   * @return the {@link SequencePattern} of the current
   *         {@link SequencePatternNextMediator}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public SequencePattern<E> addCondition(final IElementTakerBooleanGetter<E> condition) {

    GlobalSequencer.forCount(count).run(() -> sequencePattern.addConditionForNext(condition));

    return sequencePattern;
  }
}
