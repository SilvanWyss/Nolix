//package declaration
package ch.nolix.core.container.sequencesearch;

//Java imports
import java.util.Iterator;
import java.util.function.Predicate;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.sequencesearchapi.ISequencePattern;

//class
/**
 * A {@link SequencePattern} is a pattern for sequences. -The sequences of a
 * {@link SequencePattern} must have a defined length. -The elements of the
 * sequences of a {@link SequencePattern} must fulfill the according element
 * conditions of the {@link SequencePattern}. -The sequences of a
 * {@link SequencePattern} must fulfill the sequence conditions of the
 * {@link SequencePattern}.
 * 
 * @author Silvan Wyss
 * @version 2016-10-01
 * @param <E> is the type of the elements of the sequences of a
 *            {@link SequencePattern}.
 */
public final class SequencePattern<E> implements ISequencePattern<E> {

  //multi-attribute
  private final LinkedList<Predicate<E>> elementConditions = new LinkedList<>();

  //multi-attribute
  private final LinkedList<Predicate<LinkedList<E>>> sequenceConditions = new LinkedList<>();

  //method
  /**
   * Adds a blank condition for the next element of the sequences of the current
   * {@link SequencePattern}.
   * 
   * @return this {@link SequencePattern}.
   */
  public SequencePattern<E> addBlankForNext() {

    addConditionForNext(e -> true);

    return this;
  }

  //method
  /**
   * Adds the given condition for the next element of the sequences of the current
   * {@link SequencePattern}.
   * 
   * @param condition
   * @return the current {@link SequencePattern}.
   * @throws ArgumentIsNullException if the given condition is null.
   */
  public SequencePattern<E> addConditionForNext(final Predicate<E> condition) {

    elementConditions.addAtEnd(condition);

    return this;
  }

  //method
  /**
   * Adds the given sequence condition to the current {@link SequencePattern}. The
   * sequence conditions must be fulfilled from the sequences of a
   * {@link SequencePattern}.
   * 
   * @param sequenceCondition
   * @return this {@link SequencePattern}.
   * @throws ArgumentIsNullException if the given sequence condition is null.
   * 
   */
  public SequencePattern<E> addSequenceCondition(
    final Predicate<LinkedList<E>> sequenceCondition) {

    sequenceConditions.addAtEnd(sequenceCondition);

    return this;
  }

  //method
  /**
   * @param count
   * @return a new {@link SequencePatternNextMediator} for the current
   *         {@link SequencePattern} with the given count.
   * @throws NegativeArgumentException if the given count is negative.
   */
  public SequencePatternNextMediator<E> forNext(final int count) {
    return new SequencePatternNextMediator<>(this, count);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<? extends IContainer<E>> getMatchingSequencesFrom(final IContainer<E> list) {

    final var sequences = new LinkedList<LinkedList<E>>();

    final int maxSequenceCount = list.getCount() - getSize() + 1;

    //Iterates the given list.
    final var iterator = list.iterator();
    for (var i = 1; i <= maxSequenceCount; i++) {

      //Asserts that the current sequence fulfills the element conditions of the
      //current SequencePattern.
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

        final var sequence = new LinkedList<E>();
        final var iterator3 = iterator.getCopy();
        GlobalSequencer.forCount(getSize()).run(() -> sequence.addAtEnd(iterator3.next()));

        //Asserts that the current sequence fulfills the sequence conditions of the
        //current SequencePattern.
        if (sequenceConditions.containsOnly(sc -> sc.test(sequence))) {
          sequences.addAtEnd(sequence);
        }
      }

      //Increments the iterator.
      iterator.next();
    }

    return sequences;
  }

  //method
  /**
   * @return the number of elements of the sequences of the current
   *         {@link SequencePattern}.
   */
  public int getSize() {
    return elementConditions.getCount();
  }

  //method
  /**
   * @param list
   * @return true if this {@link SequencePattern} matches the given list.
   */
  boolean matches(final LinkedList<E> list) {

    //Asserts that the given list has as many elements as the current
    //SequencePattern requires.
    if (list.getCount() != getSize()) {
      return false;
    }

    //Asserts that the elements of the given list
    //fulfill the according element conditions the current SequencePattern
    //requires.
    final Iterator<Predicate<E>> iterator = elementConditions.iterator();
    for (final E e : list) {
      if (!iterator.next().test(e)) {
        return false;
      }
    }

    //Asserts that the given list fulfils the sequence conditions of the current
    //SequencePattern.
    return sequenceConditions.containsOnly(sc -> sc.test(list));
  }
}
