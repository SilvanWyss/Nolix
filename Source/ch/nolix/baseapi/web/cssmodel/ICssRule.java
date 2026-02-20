/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cssmodel;

import ch.nolix.baseapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface ICssRule {
  IContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
