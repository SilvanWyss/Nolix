//package declaration
package ch.nolix.coreapi.webapi.cssapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
public interface ICssRule {

  //method declaration
  IContainer<? extends ICssProperty> getProperties();

  //method declaration
  String getSelector();

  //method declaration
  ICssRule withPrefixedSelector(String selectorPrefix);
}
