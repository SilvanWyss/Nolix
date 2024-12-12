package ch.nolix.system.element.property;

import ch.nolix.systemapi.elementapi.mutableelementapi.IMutableElement;

public final class SubElement<E extends IMutableElement> extends BaseSubElement<E> {

  public SubElement(final String attributePrefix, final E internalExtensionElement) {
    super(attributePrefix, internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return false;
  }
}
