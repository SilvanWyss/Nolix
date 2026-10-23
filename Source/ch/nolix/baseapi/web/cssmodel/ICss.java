/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.baseapi.web.cssmodel;

import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;

/**
 * @author Silvan Wyss
 */
public interface ICss {
  /**
   * @return the {@link ICssRule}s of the current {@link ICss}
   */
  ExtendedIterable<ICssRule> getRules();

  /**
   * @return a {@link String} representation of the current {@link ICss} without
   *         enclosing brackets
   */
  String toStringWithoutEnclosingBrackets();
}
