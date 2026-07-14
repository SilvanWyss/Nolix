/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.cssmodel;

import ch.nolix.base.commontype.stringtool.StringTool;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.cssmodel.ICssRule;

/**
 * @author Silvan Wyss
 */
public final class Css implements ICss {
  private final ExtendedIterable<ICssRule> rules;

  private Css(final ExtendedIterable<ICssRule> rules) {
    this.rules = rules.to(CssRule::fromCssRule);
  }

  public static Css withRules(final ExtendedIterable<ICssRule> rules) {
    return new Css(rules);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ExtendedIterable<ICssRule> getRules() {
    return rules;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return StringTool.getInBraces(toStringWithoutEnclosingBrackets());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toStringWithoutEnclosingBrackets() {
    return getRules().toConcatenatedString();
  }
}
