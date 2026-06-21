/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cssmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ICss {
  ExtendedIterable<ICssRule> getRules();

  String toStringWithoutEnclosingBrackets();
}
