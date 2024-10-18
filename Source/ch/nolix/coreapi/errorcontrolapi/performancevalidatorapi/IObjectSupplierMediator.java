package ch.nolix.coreapi.errorcontrolapi.performancevalidatorapi;

import java.util.function.Consumer;

public interface IObjectSupplierMediator<O> {

  IActionMediator running(Consumer<O> action);
}
