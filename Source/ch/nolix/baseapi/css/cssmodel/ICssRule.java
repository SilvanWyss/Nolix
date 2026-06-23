/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.css.cssmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ICssRule {
  ExtendedIterable<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
