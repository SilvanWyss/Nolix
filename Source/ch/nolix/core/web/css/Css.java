package ch.nolix.core.web.css;

import ch.nolix.core.commontypetool.stringtool.StringToolUnit;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

public final class Css implements ICss {

  private static final IStringTool STRING_TOOL = new StringToolUnit();

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
    return STRING_TOOL.getInBraces(toStringWithoutEnclosingBrackets());
  }

  @Override
  public String toStringWithoutEnclosingBrackets() {
    return getRules().toConcatenatedString();
  }
}
