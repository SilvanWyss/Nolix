/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.cssmodel;

import ch.nolix.base.commontypetool.stringtool.StringTool;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.cssmodel.ICssRule;

/**
 * @author Silvan Wyss
 */
public final class Css implements ICss {
  private final IContainer<CssRule> rules;

  private Css(final IContainer<ICssRule> rules) {
    this.rules = rules.to(CssRule::fromCssRule);
  }

  public static Css withRules(final IContainer<ICssRule> rules) {
    return new Css(rules);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<CssRule> getRules() {
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
