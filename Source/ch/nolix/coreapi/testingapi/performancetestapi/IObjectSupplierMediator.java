//package declaration
package ch.nolix.coreapi.testingapi.performancetestapi;

//Java imports
import java.util.function.Consumer;

//interface
public interface IObjectSupplierMediator<O> {

  //method declaration
  IActionMediator running(Consumer<O> action);
}
