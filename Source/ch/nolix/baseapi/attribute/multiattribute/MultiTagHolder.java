/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * A {@link MultiTagHolder} can contain several tags.
 * 
 * @author Silvan Wyss
 */
public interface MultiTagHolder {
  /**
   * @return the tags of the current {@link MultiTagHolder}
   */
  IWellOrderContainer<String> getTags();
}
