package ch.nolix.system.element.style;

import ch.nolix.coreapi.programatomapi.variableapi.PascalCaseVariableCatalog;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;

/**
 * A {@link StyleElement} is a {@link StyleElement} that can have a
 * {@link Style}.
 * 
 * @author Silvan Wyss
 * @version 2016-05-01
 * @param <E> is the type of a {@link StyleElement}.
 */
public abstract class StyleElement<E extends StyleElement<E>> extends StylableElement<E> implements IStyleElement<E> {

  private static final String CONFIGURATION_HEADER = PascalCaseVariableCatalog.CONFIGURATION;

  private final MutableOptionalValue<IStyle> style = new MutableOptionalValue<>(
    CONFIGURATION_HEADER,
    this::setStyle,
    Style::fromSpecification,
    IStyle::getSpecification);

  /**
   * {@inheritDoc}
   */
  @Override
  public void applyStyleIfHasStyle() {

    //Handles the case that the current ConfigurationElement has a Configuration.
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
   * Resets the current {@link StyleElement}.
   */
  protected abstract void resetConfigurationElement();

  /**
   * @return the {@link Style} of the current {@link StyleElement}.
   */
  private IStyle getStoredConfiguration() {
    return style.getValue();
  }
}
