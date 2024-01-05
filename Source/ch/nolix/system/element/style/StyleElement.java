//package declaration
package ch.nolix.system.element.style;

//own imports
import ch.nolix.coreapi.programatomapi.variablenameapi.PascalCaseCatalogue;
import ch.nolix.system.element.mutableelement.StylableElement;
import ch.nolix.system.element.property.MutableOptionalValue;
import ch.nolix.systemapi.elementapi.styleapi.IStyle;
import ch.nolix.systemapi.elementapi.styleapi.IStyleElement;

//class
/**
 * A {@link StyleElement} is a {@link StyleElement} that can have a
 * {@link Style}.
 * 
 * @author Silvan Wyss
 * @date 2016-05-01
 * @param <SE> is the type of a {@link StyleElement}.
 */
public abstract class StyleElement<SE extends StyleElement<SE>>
extends StylableElement<SE>
implements IStyleElement<SE> {

  //constant
  private static final String CONFIGURATION_HEADER = PascalCaseCatalogue.CONFIGURATION;

  //attribute
  private final MutableOptionalValue<IStyle> style = new MutableOptionalValue<>(
    CONFIGURATION_HEADER,
    this::setStyle,
    Style::fromSpecification,
    IStyle::getSpecification);

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public void applyStyleIfHasStyle() {

    //Handles the case that the current ConfigurationElement has a Configuration.
    if (hasStyle()) {
      resetStyleRecursively();
      getStoredConfiguration().styleElement(this);
    }
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean hasStyle() {
    return style.containsAny();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public final void removeStyle() {
    style.clear();
    resetStyleRecursively();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public SE setStyle(IStyle style) {

    this.style.setValue(style);
    applyStyleIfHasStyle();

    return asConcrete();
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected final void resetStylableElement() {

    removeStyle();

    resetConfigurationElement();
  }

  //method
  /**
   * Resets the current {@link StyleElement}.
   */
  protected abstract void resetConfigurationElement();

  //method
  /**
   * @return the {@link Style} of the current {@link StyleElement}.
   */
  private IStyle getStoredConfiguration() {
    return style.getValue();
  }
}
