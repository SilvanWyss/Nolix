/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.web.cssmodel;

import ch.nolix.base.commontypetool.stringtool.StringTool;
import ch.nolix.base.container.containerview.ContainerView;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.container.base.IContainer;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.web.cssmodel.ICssProperty;
import ch.nolix.baseapi.web.cssmodel.ICssRule;

/**
 * @author Silvan Wyss
 */
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

  public static CssRule withSelectorAndProperties(
    final String selector,
    final ICssProperty... properties) {
    final var propertiesContainer = ContainerView.forArray(properties);

    return new CssRule(selector, propertiesContainer);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IContainer<CssProperty> getProperties() {
    return properties;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getSelector() {
    return selector;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return (getSelector() + StringTool.getInBraces(getProperties().toConcatenatedString()));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ICssRule withPrefixedSelector(final String selectorPrefix) {
    Validator.assertThat(selectorPrefix).thatIsNamed("selector prefix").isNotNull();

    final var prefixedSelector = selectorPrefix + getSelector();

    return withSelectorAndProperties(prefixedSelector, getProperties());
  }
}
