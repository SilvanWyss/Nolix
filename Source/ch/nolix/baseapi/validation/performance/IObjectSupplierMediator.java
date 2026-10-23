/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.validation.performance;

import java.util.function.Consumer;

/**
 * @author Silvan Wyss
 * @param <O> the type of the {@link Object}s a {@link IObjectSupplierMediator}
 *            is for.
 */
public interface IObjectSupplierMediator<O> {
  /**
   * @param action
   * @return a new {@link IActionMediator} for the object supplier of the current
   *         {@link IObjectSupplierMediator} and the given action
   */
  IActionMediator running(Consumer<O> action);
}
