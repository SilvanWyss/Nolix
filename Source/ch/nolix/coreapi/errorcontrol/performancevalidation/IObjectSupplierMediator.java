/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.errorcontrol.performancevalidation;

import java.util.function.Consumer;

/**
 * @author Silvan Wyss
 * @param <O> is the type of the {@link Object}s a
 *            {@link IObjectSupplierMediator} is for.
 */
public interface IObjectSupplierMediator<O> {
  IActionMediator running(Consumer<O> action);
}
