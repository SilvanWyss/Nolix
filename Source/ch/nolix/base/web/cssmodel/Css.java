/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.cssmodel;

import ch.nolix.base.commontypetool.stringtool.StringTool;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.baseapi.web.cssmodel.ICss;
import ch.nolix.baseapi.web.cssmodel.ICssRule;

/**
 * @author Silvan Wyss
 */
public final class Css implements ICss {
  private final IWellOrderContainer<ICssRule> rules;

  private Css(final IWellOrderContainer<ICssRule> rules) {
    this.rules = rules.to(CssRule::fromCssRule);
  }

  public static Css withRules(final IWellOrderContainer<ICssRule> rules) {
    return new Css(rules);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IWellOrderContainer<ICssRule> getRules() {
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
