/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.multiattribute;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * A {@link MultiTextHolder} can contain several texts.
 * 
 * @author Silvan Wyss
 */
public interface MultiTextHolder {
  /**
   * @return the texts of the current {@link MultiTextHolder}
   */
  ExtendedIterable<String> getTexts();
}
