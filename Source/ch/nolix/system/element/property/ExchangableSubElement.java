package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

public final class ExchangableSubElement<ME extends IMutableElement> extends BaseSubElement<ME> {

  public ExchangableSubElement(final String attributePrefix, final ME internalSubElement) {
    super(attributePrefix, internalSubElement);
  }

  @Override
  public boolean isExchangable() {
    return true;
  }

  public void setSubElement(final ME extensionElement) {
    internalSetSubElement(extensionElement);
  }
}
