package ch.nolix.core.container.sequencesearch;

import java.util.Iterator;
import java.util.function.Predicate;

import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.programcontrol.sequencer.GlobalSequencer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.containerapi.sequencesearchapi.ISequencePattern;

/**
 * @author Silvan Wyss
 * @version 2016-10-01
 * @param <E> is the type of the elements of the sequences of a
 *            {@link SequencePattern}.
 */
public final class SequencePattern<E> implements ISequencePattern<E> {

  private final LinkedList<Predicate<E>> elementConditions = LinkedList.createEmpty();

  private final ILinkedList<Predicate<ILinkedList<E>>> sequenceConditions = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> addBlankForNext() {

    addConditionForNext(e -> true);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> addConditionForNext(final Predicate<E> condition) {

    elementConditions.addAtEnd(condition);

    return this;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ISequencePattern<E> addSequenceCondition(final Predicate<ILinkedList<E>> sequenceCondition) {

    sequenceConditions.addAtEnd(sequenceCondition);

    return this;
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
