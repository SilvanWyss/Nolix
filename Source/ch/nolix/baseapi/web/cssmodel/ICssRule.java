/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cssmodel;

import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;

/**
 * @author Silvan Wyss
 */
public interface ICssRule {
  IWellOrderContainer<? extends ICssProperty> getProperties();

  String getSelector();

  ICssRule withPrefixedSelector(String selectorPrefix);
}
