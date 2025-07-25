package ch.nolix.coreapi.programcontrol.process;

import java.util.function.Consumer;

import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.state.statemutation.Clearable;

/**
 * @author Silvan Wyss
 * @version 2025-01-12
 * @param <O> is the type of the {@link Object}s the updaters of a
 *            {@link IUpdaterCollector} can mutate.
 */
public interface IUpdaterCollector<O> extends Clearable {

  /**
   * Adds the given updater to the current {@link IUpdaterCollector}.
   * 
   * @param updater
   * @throws RuntimeException if the given updater is null.
   */
  void addUpdater(Consumer<O> updater);

  /**
   * Adds the given updaters to the current {@link IUpdaterCollector}.
   * 
   * @param updaters
   * @throws RuntimeException if the given updaters is null.
   * @throws RuntimeException if one of the given updaters is null.
   */
  void addUpdaters(IContainer<Consumer<O>> updaters);

  /**
   * Updates the given object and clears the current {@link IUpdaterCollector}.
   * 
   * @param object
   * @throws RuntimeException if the given object is null.
   */
  void updateObjectAndClear(O object);
}
