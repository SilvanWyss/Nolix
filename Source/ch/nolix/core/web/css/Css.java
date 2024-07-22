//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICss;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

//class
public final class Css implements ICss {

  //constant
  private static final IStringTool STRING_TOOL = new StringTool();

  //multi-attribute
  private final IContainer<CssRule> rules;

  //constructor
  private Css(final IContainer<ICssRule> rules) {
    this.rules = rules.to(CssRule::fromCssRule);
  }

  //static method
  public static Css withRules(final IContainer<ICssRule> rules) {
    return new Css(rules);
  }

  //method
  @Override
  public IContainer<CssRule> getRules() {
    return rules;
  }

  //method
  @Override
  public String toString() {
    return STRING_TOOL.getInBraces(toStringWithoutEnclosingBrackets());
  }

  //method
  @Override
  public String toStringWithoutEnclosingBrackets() {
    return getRules().toConcatenatedString();
  }
}
