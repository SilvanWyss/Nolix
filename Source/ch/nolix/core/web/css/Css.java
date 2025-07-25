package ch.nolix.core.web.css;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.web.css.ICss;
import ch.nolix.coreapi.web.css.ICssRule;

public final class Css implements ICss {

  private final IContainer<CssRule> rules;

  private Css(final IContainer<ICssRule> rules) {
    this.rules = rules.to(CssRule::fromCssRule);
  }

  public static Css withRules(final IContainer<ICssRule> rules) {
    return new Css(rules);
  }

  @Override
  public IContainer<CssRule> getRules() {
    return rules;
  }

  @Override
  public String toString() {
    return StringTool.getInBraces(toStringWithoutEnclosingBrackets());
  }

  @Override
  public String toStringWithoutEnclosingBrackets() {
    return getRules().toConcatenatedString();
  }
}
