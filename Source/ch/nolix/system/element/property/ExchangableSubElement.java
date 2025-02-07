package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

public final class ExchangableSubElement<E extends IMutableElement> extends AbstractSubElement<E> {

  public ExchangableSubElement(final String attributePrefix, final E internalSubElement) {
    super(attributePrefix, internalSubElement);
  }

  @Override
  public boolean isExchangable() {
    return true;
  }

  public void setSubElement(final E extensionElement) {
    internalSetSubElement(extensionElement);
  }
}
