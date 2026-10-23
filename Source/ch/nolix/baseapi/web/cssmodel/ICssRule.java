/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cssmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ICssRule {
  /**
   * @return the {@link ICssProperty}s of the current {@link ICssRule}
   */
  ExtendedIterable<? extends ICssProperty> getProperties();

  /**
   * @return the selector of the current {@link ICssRule}
   */
  String getSelector();

  /**
   * @param selectorPrefix
   * @return a new {@link ICssRule} with the properties of the current
   *         {@link ICssRule} and the selector of the current {@link ICssRule}
   *         prefixed with the given selectorPrefix
   */
  ICssRule withPrefixedSelector(String selectorPrefix);
}
