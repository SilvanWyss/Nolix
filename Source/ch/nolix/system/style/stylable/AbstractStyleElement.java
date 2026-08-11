/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.system.style.stylable;

import ch.nolix.baseapi.generalcatalog.variablenamecatalog.PascalCaseVariableNameCatalog;
import ch.nolix.system.element.valueproperty.OptionalValueProperty;
import ch.nolix.system.style.model.Style;
import ch.nolix.systemapi.style.model.IStyle;
import ch.nolix.systemapi.style.styleholder.StyleHolder;

/**
 * A {@link AbstractStyleElement} is a {@link AbstractStyleElement} that can
 * have a {@link Style}.
 * 
 * @author Silvan Wyss
 * @param <E> the type of a {@link AbstractStyleElement}.
 */
public abstract class AbstractStyleElement<E extends AbstractStyleElement<E>> extends AbstractStylableElement<E>
implements StyleHolder<E> {
  private static final String CONFIGURATION_HEADER = PascalCaseVariableNameCatalog.CONFIGURATION;

  private final OptionalValueProperty<IStyle> style = //
  OptionalValueProperty.withNameAndSetterAndValueMapperAndSpecificationMapper(
    CONFIGURATION_HEADER,
    this::setStyle,
    Style::fromSpecification,
    IStyle::getSpecification);

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyStyleIfHasStyle() {
    // Handles the case that the current ConfigurationElement has a Configuration.
    if (hasStyle()) {
      resetStyleRecursively();
      getStoredConfiguration().applyToElement(this);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasStyle() {
    return style.containsAny();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeStyle() {
    style.clear();
    resetStyleRecursively();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E setStyle(IStyle style) {
    this.style.setValue(style);
    applyStyleIfHasStyle();

    return asConcrete();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetStylableElement() {
    removeStyle();

    resetConfigurationElement();
  }

  /**
   * Resets the current {@link AbstractStyleElement}.
   */
  protected abstract void resetConfigurationElement();

  /**
   * @return the {@link Style} of the current {@link AbstractStyleElement}.
   */
  private IStyle getStoredConfiguration() {
    return style.getStoredValue();
  }
}
