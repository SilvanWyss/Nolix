/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * A {@link IMultiTextHolder} can contain several texts.
 * 
 * @author Silvan Wyss
 */
public interface IMultiTextHolder {
  /**
   * @return the texts of the current {@link IMultiTextHolder}.
   */
  IContainer<String> getTexts();
}
