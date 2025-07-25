package ch.nolix.coreapi.attribute.multiattribute;

import ch.nolix.coreapi.container.base.IContainer;

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
