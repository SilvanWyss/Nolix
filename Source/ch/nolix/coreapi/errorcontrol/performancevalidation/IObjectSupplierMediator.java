package ch.nolix.coreapi.errorcontrol.performancevalidation;

import java.util.function.Consumer;

public interface IObjectSupplierMediator<O> {
  IActionMediator running(Consumer<O> action);
}
