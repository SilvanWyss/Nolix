//package declaration
package ch.nolix.coreapi.attributeapi.multiattributeapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * A {@link IMultiTextHolder} can contain several texts.
 * 
 * @author Silvan Wyss
 * @version 2023-10-25
 */
public interface IMultiTextHolder {

  //method declaration
  /**
   * @return the texts of the current {@link IMultiTextHolder}.
   */
  IContainer<String> getTexts();
}
