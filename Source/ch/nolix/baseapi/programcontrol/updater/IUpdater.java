/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.programcontrol.updater;

import java.util.function.Consumer;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.generalstate.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s the updaters of a {@link IUpdater}
 *            can mutate.
 */
public interface IUpdater<O> extends Clearable {
  /**
   * Adds the given update to the current {@link IUpdater}.
   * 
   * @param update
   * @throws RuntimeException if the given update is null
   */
  void addUpdate(Consumer<O> update);

  /**
   * Adds the given updates to the current {@link IUpdater}.
   * 
   * @param updates
   * @throws RuntimeException if the given updates is null
   * @throws RuntimeException if one of the given updates is null
   */
  void addUpdates(ExtendedIterable<Consumer<O>> updates);

  /**
   * Updates the given object and clears the current {@link IUpdater}.
   * 
   * @param object
   * @throws RuntimeException if the given object is null
   */
  void updateObjectAndClear(O object);
}
