/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.process;

import java.util.function.Consumer;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.programcontrol.process.IUpdaterCollector;

/**
 * @author Silvan Wyss
 * @param <O> is the type of the {@link Object}s the updaters of a
 *            {@link UpdaterCollector} can mutate.
 */
public final class UpdaterCollector<O> implements IUpdaterCollector<O> {
  private final ILinkedList<Consumer<O>> memberUpdaters = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addUpdater(final Consumer<O> updater) {
    memberUpdaters.addAtEnd(updater);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addUpdaters(final ExtendedIterable<Consumer<O>> updaters) {
    memberUpdaters.addAtEnd(updaters);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void clear() {
    memberUpdaters.clear();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return memberUpdaters.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updateObjectAndClear(final O object) {
    Validator.assertThat(object).thatIsNamed(Object.class).isNotNull();

    updateObjectAndClearWhenObjectIsNotNull(object);
  }

  /**
   * Updates the given object and clears the current {@link IUpdaterCollector} for
   * the case when the given object is not null.
   * 
   * @param object
   */
  private void updateObjectAndClearWhenObjectIsNotNull(final O object) {
    try {
      memberUpdaters.forEach(u -> u.accept(object));
    } finally {
      clear();
    }
  }
}
