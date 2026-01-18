package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the extension element of a
 *            {@link ExchangableExtensionElement}.
 */
public final class ExchangableExtensionElement<E extends IRespondingMutableElement<E>>
extends AbstractExtensionElement<E> {
  public ExchangableExtensionElement(final E internalExtensionElement) {
    super(internalExtensionElement);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isExchangable() {
    return true;
  }

  public void setExtensionElement(final E internalExtensionElement) {
    internalSetExtensionElement(internalExtensionElement);
  }
}
