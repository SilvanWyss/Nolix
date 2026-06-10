/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * A {@link IMultiTagHolder} can contain several tags.
 * 
 * @author Silvan Wyss
 */
public interface IMultiTagHolder {
  /**
   * @return the tags of the current {@link IMultiTagHolder}
   */
  IContainer<String> getTags();
}
