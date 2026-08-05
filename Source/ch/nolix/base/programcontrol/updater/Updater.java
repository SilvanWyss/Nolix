/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.programcontrol.updater;

import java.util.function.Consumer;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.programcontrol.updater.IUpdater;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s the updaters of a {@link Updater}
 *            can mutate.
 */
public final class Updater<O> implements IUpdater<O> {
  private final ILinkedList<Consumer<O>> memberUpdaters = LinkedList.createEmpty();

  /**
   * {@inheritDoc}
   */
  @Override
  public void addUpdate(final Consumer<O> update) {
    memberUpdaters.addAtEnd(update);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addUpdates(final ExtendedIterable<Consumer<O>> updates) {
    memberUpdaters.addAtEnd(updates);
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
   * Updates the given object and clears the current {@link IUpdater} for the case
   * when the given object is not null.
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
