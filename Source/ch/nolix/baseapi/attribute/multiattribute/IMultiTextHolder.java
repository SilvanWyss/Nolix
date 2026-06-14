/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * A {@link IMultiTextHolder} can contain several texts.
 * 
 * @author Silvan Wyss
 */
public interface IMultiTextHolder {
  /**
   * @return the texts of the current {@link IMultiTextHolder}
   */
  IWellOrderContainer<String> getTexts();
}
