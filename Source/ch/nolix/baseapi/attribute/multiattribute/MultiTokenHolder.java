/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * A {@link MultiTokenHolder} can have several tokens.
 * 
 * @author Silvan Wyss
 */
public interface MultiTokenHolder {
  /**
   * @return the tokens of the current {@link MultiTokenHolder}
   */
  IWellOrderContainer<String> getTokens();
}
