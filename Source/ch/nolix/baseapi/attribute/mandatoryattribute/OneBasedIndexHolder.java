/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.attribute.mandatoryattribute;

/**
 * A {@link OneBasedIndexHolder} has a one-based index.
 * 
 * @author Silvan Wyss
 */
public interface OneBasedIndexHolder {
  /**
   * @return the one-based index of the current {@link OneBasedIndexHolder}
   */
  int getOneBasedIndex();
}
