package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

public final class SubElement<ME extends IMutableElement> extends BaseSubElement<ME> {

  public SubElement(final String attributePrefix, final ME internalExtensionElement) {
    super(attributePrefix, internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return false;
  }
}
