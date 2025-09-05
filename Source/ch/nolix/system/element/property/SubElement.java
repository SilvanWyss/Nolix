package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IMutableElement;

public final class SubElement<E extends IMutableElement> extends AbstractSubElement<E> {
  public SubElement(final String attributePrefix, final E internalExtensionElement) {
    super(attributePrefix, internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return false;
  }
}
