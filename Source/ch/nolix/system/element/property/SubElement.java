package ch.nolix.system.element.property;

import ch.nolix.systemapi.element.mutableelement.IMutableElement;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the actual element of a {@link SubElement}.
 */
public final class SubElement<E extends IMutableElement> extends AbstractSubElement<E> {
  public SubElement(final String attributePrefix, final E internalExtensionElement) {
    super(attributePrefix, internalExtensionElement);
  }

  @Override
  public boolean isExchangable() {
    return false;
  }
}
