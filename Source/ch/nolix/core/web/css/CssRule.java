//package declaration
package ch.nolix.core.web.css;

//own imports
import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringtoolapi.IStringTool;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

//class
public final class CssRule implements ICssRule {

  //constant
  private static final IStringTool STRING_TOOL = new StringTool();

  //multi-attribute
  private final String selector;

  //multi-attribute
  private final IContainer<CssProperty> properties;

  //constructor
  private CssRule(
    final String selector,
    final IContainer<? extends ICssProperty> properties) {

    GlobalValidator.assertThat(selector).thatIsNamed("selector").isNotNull();

    this.properties = properties.to(CssProperty::fromCssProperty);
    this.selector = selector;
  }

  //static method
  public static CssRule fromCssRule(final ICssRule cssRule) {
    return withSelectorAndProperties(cssRule.getSelector(), cssRule.getProperties());
  }

  //static method
  public static CssRule withSelectorAndProperties(
    final String selector,
    final IContainer<? extends ICssProperty> properties) {
    return new CssRule(selector, properties);
  }

  //static method
  public static CssRule withSelectorAndProperty(
    final String selector,
    final ICssProperty property,
    final ICssProperty... properties) {

    final var allProperties = ReadContainer.forElementAndArray(property, properties);

    return new CssRule(selector, allProperties);
  }

  //method
  @Override
  public IContainer<CssProperty> getProperties() {
    return properties;
  }

  //method
  @Override
  public String getSelector() {
    return selector;
  }

  //method
  @Override
  public String toString() {
    return (getSelector() + STRING_TOOL.getInBraces(getProperties().toConcatenatedString()));
  }

  //method
  @Override
  public ICssRule withPrefixedSelector(final String selectorPrefix) {

    GlobalValidator.assertThat(selectorPrefix).thatIsNamed("selector prefix").isNotNull();

    final var prefixedSelector = selectorPrefix + getSelector();

    return withSelectorAndProperties(prefixedSelector, getProperties());
  }
}
