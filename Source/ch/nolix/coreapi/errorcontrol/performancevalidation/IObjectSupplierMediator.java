package ch.nolix.coreapi.errorcontrol.performancevalidation;

import java.util.function.Consumer;

/**
 * @author Silvan Wyss
 */
public interface IObjectSupplierMediator<O> {
  IActionMediator running(Consumer<O> action);
}
