/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * A {@link MultiTagHolder} can contain several tags.
 * 
 * @author Silvan Wyss
 */
public interface MultiTagHolder {
  /**
   * @return the tags of the current {@link MultiTagHolder}
   */
  ExtendedIterable<String> getTags();
}
