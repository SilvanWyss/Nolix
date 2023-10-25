//package declaration
package ch.nolix.coreapi.attributeapi.multiattributeapi;

//own imports
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

//interface
/**
 * A {@link IMultiTokenHolder} can have several tokens.
 * 
 * @author Silvan Wyss
 * @date 2023-06-16
 */
public interface IMultiTokenHolder {

  //method declaration
  /**
   * @return the tokens of the current {@link IMultiTokenHolder}.
   */
  IContainer<String> getTokens();
}
