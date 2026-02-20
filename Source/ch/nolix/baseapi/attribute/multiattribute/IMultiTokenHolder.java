/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.base.IContainer;

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
