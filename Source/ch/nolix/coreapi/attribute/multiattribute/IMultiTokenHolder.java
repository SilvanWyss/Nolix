package ch.nolix.coreapi.attribute.multiattribute;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * A {@link IMultiTokenHolder} can have several tokens.
 * 
 * @author Silvan Wyss
 */
public interface IMultiTokenHolder {
  /**
   * @return the tokens of the current {@link IMultiTokenHolder}.
   */
  IContainer<String> getTokens();
}
