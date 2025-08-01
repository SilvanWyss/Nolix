package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IRespondingMutableElement;

public final class ExchangableExtensionElement<E extends IRespondingMutableElement<E>>
extends AbstractExtensionElement<E> {

  public ExchangableExtensionElement(final E internalExtensionElement) {
    super(internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return true;
  }

  public void setExtensionElement(final E internalExtensionElement) {
    internalSetExtensionElement(internalExtensionElement);
  }
}
