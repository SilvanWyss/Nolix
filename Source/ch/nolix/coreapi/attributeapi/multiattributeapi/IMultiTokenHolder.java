package ch.nolix.coreapi.attributeapi.multiattributeapi;

import ch.nolix.coreapi.containerapi.baseapi.IContainer;

/**
 * A {@link IMultiTokenHolder} can have several tokens.
 * 
 * @author Silvan Wyss
 * @version 2023-06-16
 */
public interface IMultiTokenHolder {

  /**
   * @return the tokens of the current {@link IMultiTokenHolder}.
   */
  IContainer<String> getTokens();
}
