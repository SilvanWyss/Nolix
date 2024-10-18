package ch.nolix.core.container.sequencesearch;

import java.util.Iterator;
import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.sequencesearchapi.ISequencePattern;

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

  private final LinkedList<Predicate<E>> elementConditions = LinkedList.createEmpty();

  private final ILinkedList<Predicate<ILinkedList<E>>> sequenceConditions = LinkedList.createEmpty();

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
  public SequencePattern<E> addSequenceCondition(final Predicate<ILinkedList<E>> sequenceCondition) {

    sequenceConditions.addAtEnd(sequenceCondition);

    return this;
  }

  /**
   * @param count
   * @return a new {@link SequencePatternNextMediator} for the current
   *         {@link SequencePattern} with the given count.
   * @throws NegativeArgumentException if the given count is negative.
   */
  public SequencePatternNextMediator<E> forNext(final int count) {
    return new SequencePatternNextMediator<>(this, count);
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

        final ILinkedList<E> sequence = LinkedList.createEmpty();
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

  /**
   * @return the number of elements of the sequences of the current
   *         {@link SequencePattern}.
   */
  public int getSize() {
    return elementConditions.getCount();
  }

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
