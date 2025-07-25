package ch.nolix.core.web.css;

import ch.nolix.core.commontypetool.stringtool.StringTool;
import ch.nolix.core.container.containerview.ContainerView;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.programatom.variable.LowerCaseVariableCatalog;
import ch.nolix.coreapi.webapi.cssapi.ICssProperty;
import ch.nolix.coreapi.webapi.cssapi.ICssRule;

public final class CssRule implements ICssRule {

  private final String selector;

  private final IContainer<CssProperty> properties;

  private CssRule(final String selector, final IContainer<? extends ICssProperty> properties) {

    Validator.assertThat(selector).thatIsNamed(LowerCaseVariableCatalog.SELECTOR).isNotNull();

    this.properties = properties.to(CssProperty::fromCssProperty);
    this.selector = selector;
  }

  public static CssRule fromCssRule(final ICssRule cssRule) {
    return withSelectorAndProperties(cssRule.getSelector(), cssRule.getProperties());
  }

  public static CssRule withSelectorAndProperties(
    final String selector,
    final IContainer<? extends ICssProperty> properties) {
    return new CssRule(selector, properties);
  }

  public static CssRule withSelectorAndProperty(
    final String selector,
    final ICssProperty property,
    final ICssProperty... properties) {

    final var allProperties = ContainerView.forElementAndArray(property, properties);

    return new CssRule(selector, allProperties);
  }

  @Override
  public IContainer<CssProperty> getProperties() {
    return properties;
  }

  @Override
  public String getSelector() {
    return selector;
  }

  @Override
  public String toString() {
    return (getSelector() + StringTool.getInBraces(getProperties().toConcatenatedString()));
  }

  @Override
  public ICssRule withPrefixedSelector(final String selectorPrefix) {

    Validator.assertThat(selectorPrefix).thatIsNamed("selector prefix").isNotNull();

    final var prefixedSelector = selectorPrefix + getSelector();

    return withSelectorAndProperties(prefixedSelector, getProperties());
  }
}
