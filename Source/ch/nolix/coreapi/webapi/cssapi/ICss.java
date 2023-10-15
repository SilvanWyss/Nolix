//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICss {

  // method declaration
  IContainer<? extends ICssRule> getRules();

  // method declaration
  String toStringWithoutEnclosingBrackets();
}
