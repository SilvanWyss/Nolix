/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.coreapi.web.cssmodel;

import ch.nolix.coreapi.container.base.IContainer;

/**
 * @author Silvan Wyss
 */
public interface ICss {
  IContainer<? extends ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
